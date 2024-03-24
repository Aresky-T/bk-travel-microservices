DROP DATABASE IF EXISTS `bk_travel_review`;

CREATE DATABASE `bk_travel_review`;

USE `bk_travel_review`;

--
-- Table structure for table `review`
--
DROP TABLE IF EXISTS review;
CREATE TABLE review (
    id INT NOT NULL AUTO_INCREMENT,
    `stars` INT NOT NULL,
    `comment` VARCHAR(255) NOT NULL,
    `review_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `tour_id` INT NOT NULL,
    `sub_tour_id` INT NOT NULL,
    `account_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_account_sub_tour (`sub_tour_id`, `account_id`),
    CONSTRAINT ck_stars CHECK ((`stars` between 1 and 5))
);

CREATE INDEX idx_tour_id ON review(tour_id);


--
-- Table structure for table `review_statistic`
--
DROP TABLE IF EXISTS review_statistic;
CREATE TABLE review_statistic (
    tour_id INT PRIMARY KEY,
    number_of_reviews INT NOT NULL DEFAULT 0,
    stars_average DECIMAL(3,2) NOT NULL DEFAULT 0.0,
    CONSTRAINT ck_stars_average check(stars_average >= 0.0),
    CONSTRAINT ck_number_of_reviews check(number_of_reviews >= 0)
);


--
-- Trigger for table `review_statistic` after insert or ofter delete in `review`
--
DROP TRIGGER IF EXISTS update_review_statistic_after_insert_into_review;
DELIMITER $$
CREATE TRIGGER update_review_statistic_after_insert_into_review
AFTER INSERT ON `review`
FOR EACH ROW
BEGIN
	INSERT INTO review_statistic (tour_id, number_of_reviews, stars_average)
    VALUES (NEW.tour_id, 1, NEW.stars)
    ON DUPLICATE KEY UPDATE
		number_of_reviews = (
			SELECT COUNT(*)
            FROM review
            WHERE tour_id = NEW.tour_id
            GROUP BY tour_id
        ),
        stars_average = (
			SELECT AVG(stars)
            FROM review
            WHERE tour_id = NEW.tour_id
            GROUP BY tour_id
        );
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS update_review_statistic_after_delete_in_review;
DELIMITER $$
CREATE TRIGGER update_review_statistic_after_delete_in_review
AFTER DELETE ON `review`
FOR EACH ROW
BEGIN
	IF NOT EXISTS (
		SELECT * FROM review
        WHERE tour_id = OLD.tour_id
    )
    THEN
		DELETE FROM review_statistic WHERE tour_id = OLD.tour_id;
    ELSE
		UPDATE review_statistic AS R
		INNER JOIN (
			SELECT tour_id, avg(stars) AS stars_average, count(id) AS number_of_reviews
			FROM review
			GROUP BY tour_id
		) AS avg_table
		ON R.tour_id = avg_table.tour_id
		SET R.number_of_reviews = avg_table.number_of_reviews,
			R.stars_average = avg_table.stars_average;
	END IF;
END$$
DELIMITER ;