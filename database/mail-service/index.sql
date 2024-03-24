DROP DATABASE IF EXISTS `bk_travel_mail`;

CREATE DATABASE `bk_travel_mail`;

USE `bk_travel_mail`;

--
-- Table structure for table `customer`
--
DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    auth_id INT
);

CREATE INDEX idx_full_name ON customer (full_name);

--
-- Table structure for table `mail_box`
--
DROP TABLE IF EXISTS mail_box;
CREATE TABLE mail_box (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    employee_id INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_customer_employee (customer_id, employee_id),
    CONSTRAINT mail_box_fk_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);

--
-- Table structure for table `mail`
--
DROP TABLE IF EXISTS mail;
CREATE TABLE mail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `subject` VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    mail_box INT NOT NULL,
    sent_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status` ENUM('REPLIED', 'UNREPLIED') NOT NULL DEFAULT 'UNREPLIED', 
    CONSTRAINT mail_fk_mail_box FOREIGN KEY (mail_box) REFERENCES mail_box (id) ON DELETE CASCADE
);

CREATE INDEX idx_sent_at ON mail(sent_at);

--
-- Table structure for table `mail_reply`
--
DROP TABLE IF EXISTS mail_reply;
CREATE TABLE mail_reply (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `subject` VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    original_mail INT NOT NULL,
    replied_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT mail_reply_fk1 FOREIGN KEY (original_mail) REFERENCES mail (id) ON DELETE CASCADE
);