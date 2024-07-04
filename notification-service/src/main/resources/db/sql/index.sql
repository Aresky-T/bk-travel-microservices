DROP DATABASE IF EXISTS `bk_travel_notification`;
CREATE DATABASE `bk_travel_notification`;
USE `bk_travel_notification`;

--
-- Table structure for table `notification_types`
--
DROP TABLE IF EXISTS `notification_types`;
CREATE TABLE `notification_types` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL UNIQUE,
    `description` VARCHAR(255) NOT NULL,
    `template` TEXT NOT NULL,
    `entity_type` ENUM('AUTH', 'BOOKING', 'PAYMENT', 'MAIL', 'OTHER') NOT NULL,
    PRIMARY KEY (`id`)
);

--
-- Table structure for table `notification_keywords`
--
DROP TABLE IF EXISTS `notification_keywords`;
CREATE TABLE `notification_keywords` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `keyword` VARCHAR(100) NOT NULL,
    `notification_type_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT fk_notification_type_id FOREIGN KEY (`notification_type_id`) REFERENCES `notification_types`(`id`) ON DELETE CASCADE
);


--
-- Table structure for table `notifications`
--
DROP TABLE IF EXISTS `notifications`;
CREATE TABLE `notifications` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `type_id` INT NOT NULL,
    `entity_id` INT,
    `message` TEXT NOT NULL,
    `is_read` BOOLEAN DEFAULT FALSE,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    CONSTRAINT fk_type_id FOREIGN KEY (`type_id`) REFERENCES `notification_types`(`id`) ON DELETE CASCADE
);
