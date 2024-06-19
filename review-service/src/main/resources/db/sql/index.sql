--
-- Table structure for table `reviewer`
--
DROP TABLE IF EXISTS reviewer;
CREATE TABLE reviewer (
	`id` INT NOT NULL AUTO_INCREMENT,
    `full_name` VARCHAR(100) NOT NULL,
    `avatar_url` VARCHAR(255) NOT NULL,
    `account_id` INT NOT NULL UNIQUE,
    PRIMARY KEY(id)
);

--
-- Table structure for table `review`
--
DROP TABLE IF EXISTS review;
CREATE TABLE review (
    id INT NOT NULL AUTO_INCREMENT,
    `reviewer_id` INT NOT NULL,
    `sub_tour_id` INT NOT NULL,
    `tour_id` INT NOT NULL,
    `stars` INT NOT NULL,
    `comment` TEXT NOT NULL,
    `review_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `edited_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_account_sub_tour (`reviewer_id`, `sub_tour_id`),
  	CONSTRAINT fk_reviewer_id FOREIGN KEY (`reviewer_id`) REFERENCES reviewer(id) ON DELETE CASCADE,
    CONSTRAINT ck_stars CHECK ((`stars` between 1 and 5))
);

CREATE INDEX idx_tour_id ON review (tour_id);

--
-- Table structure for table `review_statistic`
--
DROP TABLE IF EXISTS review_statistic;

CREATE TABLE review_statistic (
    `tour_id` INT NOT NULL,
    `number_of_reviews` INT NOT NULL DEFAULT 0,
    `stars_average` DECIMAL(3, 2) NOT NULL DEFAULT 0.0,
    PRIMARY KEY (tour_id),
    CONSTRAINT ck_stars_average check (stars_average >= 0.0),
    CONSTRAINT ck_number_of_reviews check (number_of_reviews >= 0)
);

--
-- Trigger for table `review_statistic` after insert on `review`
--
DROP TRIGGER IF EXISTS update_review_statistic_after_insert_on_review;

DELIMITER $ $
CREATE TRIGGER update_review_statistic_after_insert_on_review

AFTER
INSERT
    ON `review` FOR EACH ROW BEGIN
INSERT INTO
    `review_statistic` (
        tour_id,
        number_of_reviews,
        stars_average
    )
VALUES (NEW.tour_id, 1, NEW.stars)
ON DUPLICATE KEY UPDATE
    number_of_reviews = (
        SELECT COUNT(*)
        FROM review
        WHERE
            tour_id = NEW.tour_id
        GROUP BY
            tour_id
    ),
    stars_average = (
        SELECT AVG(stars)
        FROM review
        WHERE
            tour_id = NEW.tour_id
        GROUP BY
            tour_id
    );

END;

/ / DELIMITER;

--
-- Trigger for table `review_statistic` after update on `review`
--
DROP TRIGGER IF EXISTS update_review_statistic_after_update_on_review;
DELIMITER / /
CREATE TRIGGER update_review_statistic_after_update_on_review

AFTER
UPDATE ON review FOR EACH ROW BEGIN
UPDATE review_statistic AS rs
SET
    rs.stars_average = (
        SELECT AVG(stars)
        FROM review
        WHERE
            tour_id = NEW.tour_id
    )
WHERE
    rs.tour_id = NEW.tour_id;

END;

/ / DELIMITER;


--
-- Trigger for table `review_statistic` after delete on `review`
--
DROP TRIGGER IF EXISTS update_review_statistic_after_delete_in_review;

DELIMITER $ $
CREATE TRIGGER update_review_statistic_after_delete_in_review

AFTER DELETE ON `review` FOR EACH ROW BEGIN IF NOT EXISTS (
    SELECT *
    FROM review
    WHERE
        tour_id = OLD.tour_id
) THEN
DELETE FROM review_statistic
WHERE
    tour_id = OLD.tour_id;

ELSE
UPDATE review_statistic AS R
INNER JOIN (
    SELECT
        tour_id,
        avg(stars) AS stars_average,
        count(id) AS number_of_reviews
    FROM review
    GROUP BY
        tour_id
) AS avg_table ON R.tour_id = avg_table.tour_id
SET
    R.number_of_reviews = avg_table.number_of_reviews,
    R.stars_average = avg_table.stars_average;

END IF;

END $ $ DELIMITER;