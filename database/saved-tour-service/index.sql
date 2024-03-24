DROP DATABASE IF EXISTS `bk_travel_saved_tour`;

CREATE DATABASE `bk_travel_saved_tour`;

USE `bk_travel_saved_tour`;

--
-- Table structure for table `saved_tour`
-- 
DROP TABLE IF EXISTS saved_tour;
CREATE TABLE saved_tour (
    account_id INT NOT NULL,
    tour_id INT NOT NULL,
    sub_tour_id INT NOT NULL,
    saved_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (account_id, sub_tour_id)
);

CREATE INDEX idx_tour_id ON saved_tour (tour_id);

--
-- Table structure for table `saved_tour_statistic`
-- 
DROP TABLE IF EXISTS saved_tour_statistic;
CREATE TABLE saved_tour_statistic (
	sub_tour_id  INT NOT NULL,
    total_saves INT NOT NULL DEFAULT 0,
    PRIMARY KEY (sub_tour_id),
    CONSTRAINT ck_total_saves CHECK(total_saves >= 0)
);

--
-- Trigger structure for table `saved_tour_statistic`
-- 
DROP TRGGER IF EXISTS update_saved_tour_statistic_after_insert_into_saved_tour
DELIMITER $$
CREATE TRIGGER update_saved_tour_statistic_after_insert_into_saved_tour
AFTER INSERT ON saved_tour
FOR EACH ROW
BEGIN
	INSERT INTO saved_tour_statistic (sub_tour_id, total_saves)
    VALUES (NEW.sub_tour_id, 1)
    ON DUPLICATE KEY UPDATE
		total_saves = total_saves + 1;
END $$
DELIMITER ;

DROP TRIGGER IF EXISTS update_saved_tour_statistic_after_delete_on_saved_tour
DELIMITER $$
CREATE TRIGGER update_saved_tour_statistic_after_delete_on_saved_tour
AFTER DELETE ON saved_tour
FOR EACH ROW
BEGIN
	IF NOT EXISTS (
		SELECT * FROM saved_tour
        WHERE sub_tour_id = OLD.sub_tour_id
    ) 
    THEN 
		UPDATE saved_tour_statistic
        SET total_saves = 0
        WHERE sub_tour_id = OLD.sub_tour_id;
	ELSE 
		UPDATE saved_tour_statistic
        SET total_saves = total_saves - 1
        WHERE sub_tour_id = OLD.sub_tour_id;
	END IF;
END $$
DELIMITER ;