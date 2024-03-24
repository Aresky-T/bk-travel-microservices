DROP DATABASE IF EXISTS `bk_travel_chat`;

CREATE DATABASE `bk_travel_chat`;

USE `bk_travel_chat`;

--
-- Table structure for table `customer`
--
DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `status` ENUM('ONLINE', 'OFFLINE') NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    account_id INT
);

CREATE INDEX idx_full_name ON customer (full_name);

--
-- Table structure for table `chat_box`
--
DROP TABLE IF EXISTS chat_box;
CREATE TABLE chat_box (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    employee_id INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_customer_employee (customer_id, employee_id),
    CONSTRAINT chat_box_fk_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);

--
-- Table structure for table `chat`
--
DROP TABLE IF EXISTS chat;
CREATE TABLE chat (
    id INT PRIMARY KEY AUTO_INCREMENT,
    message TEXT NOT NULL,
    chat_box INT NOT NULL,
    sender ENUM('CUSTOMER', 'EMPLOYEE') NOT NULL,
    `status` ENUM('NEW', 'SEEN') NOT NULL DEFAULT 'NEW',
    sent_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chat_fk_chat_box FOREIGN KEY (chat_box) REFERENCES chat_box (id) ON DELETE CASCADE
);

CREATE INDEX idx_sent_at ON chat (sent_at);