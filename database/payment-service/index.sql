DROP DATABASE IF EXISTS `bk_travel_payment`;

CREATE DATABASE `bk_travel_payment`;

USE `bk_travel_payment`;

CREATE TABLE vnpay_payment_info (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_info VARCHAR(255) DEFAULT NULL,
    transaction_no VARCHAR(255) DEFAULT NULL,
    txn_ref VARCHAR(255) DEFAULT NULL,
    amount VARCHAR(255) DEFAULT NULL,
    booking_id INT NOT NULL
);