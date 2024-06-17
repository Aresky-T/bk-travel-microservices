DROP DATABASE IF EXISTS `bk_travel_mail`;

CREATE DATABASE `bk_travel_mail`;

USE `bk_travel_mail`;

--
-- Table structure for table `customer`
--
DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
    `id` int NOT NULL AUTO_INCREMENT,
    `full_name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email` (`email`)

);

--
-- Table structure for table `staff`
--
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
    `id` int NOT NULL,
    `email` varchar(100) NOT NULL,
    `full_name` varchar(100) NOT NULL,
    `avatar_url` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email` (`email`)
);

--
-- Table structure for table `mail_box`
--
DROP TABLE IF EXISTS mail_box;
CREATE TABLE mail_box (
    `id` int NOT NULL AUTO_INCREMENT,
    `customer_id` int NOT NULL,
    `staff_id` int NOT NULL,
    `total_mail` int NOT NULL DEFAULT '0',
    `unreplied_mail_count` int NOT NULL DEFAULT '0',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_customer_staff` (`customer_id`,`staff_id`),
    CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_staff_id` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE CASCADE,
    CONSTRAINT `ck_total_mail` CHECK ((`total_mail` >= 0)),
    CONSTRAINT `ck_unreplied_mail_count` CHECK ((`unreplied_mail_count` >= 0))

);

--
-- Table structure for table `mail`
--
DROP TABLE IF EXISTS mail;
CREATE TABLE mail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `subject` VARCHAR(255) NOT NULL,
    `body` TEXT NOT NULL,
    `mail_box_id` INT NOT NULL,
    `sent_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status` ENUM('NEW', 'READ', 'REPLIED') NOT NULL DEFAULT 'NEW',
    CONSTRAINT fk_mail_box_id FOREIGN KEY (`mail_box_id`) REFERENCES `mail_box` (id) ON DELETE CASCADE
);


--
-- Table structure for table `mail_response`
--
DROP TABLE IF EXISTS mail_response;
CREATE TABLE mail_response (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `subject` VARCHAR(255) NOT NULL,
    `body` TEXT NOT NULL,
    `mail_id` INT NOT NULL UNIQUE,
    `replied_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_mail_id FOREIGN KEY (`mail_id`) REFERENCES `mail` (id)
);