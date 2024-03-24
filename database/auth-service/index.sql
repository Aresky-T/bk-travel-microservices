DROP DATABASE IF EXISTS `bk_travel_auth`;

CREATE DATABASE `bk_travel_auth`;

USE `bk_travel_auth`;


--
-- Table structure for table "auth"
--
DROP TABLE IF EXISTS `auth`;
CREATE TABLE `auth_access_token` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `account_id` INT NOT NULL UNIQUE,
    `access_token` VARCHAR(50) NOT NULL UNIQUE,
    `expiry_time` DATETIME NOT NULL
);

