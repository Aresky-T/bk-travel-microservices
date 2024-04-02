DROP DATABASE IF EXISTS `bk_travel_booking`;

CREATE DATABASE `bk_travel_booking`;

USE `bk_travel_booking`;

--
-- Table structure for table `form_of_payment`
--
CREATE TABLE `form_of_payment` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL UNIQUE,
    `description` VARCHAR(255) NOT NULL
);

INSERT INTO form_of_payment(`name`, `description`) VALUES
('BANK_TRANSFER', 'Thanh toán bằng hình thức chuyển khoản qua ngân hàng'), 
('CASH_PAYMENT', 'Thanh toán bằng tiền mặt'), 
('VNPAY_ON_WEBSITE', 'Thanh toán qua VNPAY tại trang web của BK TRAVEL');

--
-- Table structure for table `booking_info`
--
CREATE TABLE booking_info (
    id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    adult_number INT NOT NULL,
    children_number INT NOT NULL,
    baby_number INT NOT NULL,
    note VARCHAR(255) DEFAULT NULL,
    total_price INT NOT NULL,
    book_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status` enum(
        'NOT_PAY',
        'PAY_UP',
        'REJECTED'
	) NOT NULL DEFAULT 'NOT_PAY',
    form_of_payment INT NOT NULL,
    account_id INT NOT NULL,
    tour_id INT NOT NULL,
    sub_tour_id VARCHAR(100) NOT NULL,
    UNIQUE KEY uk_account_tour (account_id, sub_tour_id),
    CONSTRAINT fk_form_of_payment FOREIGN KEY (form_of_payment) REFERENCES form_of_payment(id)
);

--
-- Table structure for table `tourist`
--
CREATE TABLE tourist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    gender ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
    booking_id INT NOT NULL,
    CONSTRAINT tourist_fk1 FOREIGN KEY (booking_id) REFERENCES booking_info (id) ON DELETE CASCADE
);

--
-- Table structure for table `request_cancel_booked_tour`
--
CREATE TABLE request_cancel_booking (
    id INT PRIMARY KEY AUTO_INCREMENT,
    reason VARCHAR(255) NOT NULL,
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    booking_id INT NOT NULL,
    `status` ENUM('WAITING', 'APPROVE', 'REJECT') NOT NULL DEFAULT 'WAITING',
    CONSTRAINT request_cancel_booking_fk1 FOREIGN KEY (booking_id) REFERENCES booking_info (id) ON DELETE CASCADE
);

--
-- Table structure for table `booking_statistic`
--
CREATE TABLE booking_statistic (
	tour_id INT NOT NULL,
    sub_tour_id INT NOT NULL,
    number_of_booking INT NOT NULL DEFAULT 0,
    PRIMARY KEY (sub_tour_id),
    CONSTRAINT booking_statistic_ck1 CHECK (number_of_booking >= 0)
);