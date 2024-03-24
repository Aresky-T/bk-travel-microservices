DROP DATABASE IF EXISTS `bk_travel_account`;

CREATE DATABASE `bk_travel_account`;

USE `bk_travel_account`;

CREATE TABLE `account` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(30) NOT NULL UNIQUE,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `role` ENUM('USER', 'ADMIN', 'EMPLOYEE') NOT NULL DEFAULT 'USER',
    `activation_status` ENUM('ACTIVE', 'BLOCKED') NOT NULL DEFAULT 'ACTIVE',
    `online_status` ENUM('ONLINE', 'OFFLINE') NOT NULL DEFAULT 'OFFLINE',
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Table structure for table `profile`
--
CREATE TABLE `profile`(
    id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT NOT NULL UNIQUE,
    avatar_url VARCHAR(255) DEFAULT NULL,
    full_name VARCHAR(100) DEFAULT NULL,
    address VARCHAR(255) DEFAULT NULL,
    phone VARCHAR(20) DEFAULT NULL,
    date_of_birth DATE DEFAULT NULL,
    gender ENUM('MALE', 'FEMALE', 'OTHER') DEFAULT NULL,
    CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES `account`(id)
);