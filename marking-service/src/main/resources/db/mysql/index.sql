DROP DATABASE IF EXISTS `bk_travel_marking`;

CREATE DATABASE `bk_travel_marking`;

USE `bk_travel_marking`;

--
-- Table structure for table `marked_tour`
-- 
DROP TABLE IF EXISTS `marked_tours`;
CREATE TABLE `saved_tour` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `account_id` INT NOT NULL,
    `sub_tour_id` INT NOT NULL,
    `tour_id` INT NOT NULL,
    `marked_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY `uk_account_and_tour` (`account_id`, `sub_tour_id`)
);

CREATE INDEX idx_tour_id ON marked_tours (`tour_id`);

--
-- Table structure for table `marked_tours_statistics`
--
DROP TABLE IF EXISTS `marked_tours_statistics`
CREATE TABLE `marked_tours_statistics` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `tour_id` INT NOT NULL,
    `sub_tour_id` INT NOT NULL,
    `marks` INT NOT NULL,
    `month` INT NOT NULL,
    `year` INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY `uk_tour_month_year` (`tour_id`, `sub_tour_id`, `month`, `year`),
    CONSTRAINT `ck_marks` CHECK (`marks` >= 0),
    CONSTRAINT `ck_month` CHECK (`month` >= 1 AND `month` <= 12),
    CONSTRAINT `ck_year` CHECK (`year` >= 1900 AND `year` <= 2999)
);

-- Index for tour_id
CREATE INDEX idx_tour_id ON marked_tours_statistics (`tour_id`);

-- Index for sub_tour_id
CREATE INDEX idx_sub_tour_id ON marked_tours_statistics(sub_tour_id);

-- Combine Indexes for month and year
CREATE INDEX idx_month_year ON marked_tours_statistics(month, year);

-- Combine Indexes for tour_id, month and year
CREATE INDEX idx_tour_id_month_year ON marked_tours_statistics(tour_id, month, year);

-- Combine indexes for sub_tour_id, month and year
CREATE INDEX idx_sub_tour_month_year ON marked_tours_statistics(sub_tour_id, month, year);