DROP DATABASE IF EXISTS `bk_travel_notification`;

CREATE DATABASE `bk_travel_notification`;

USE `bk_travel_notification`;

--
-- Table structure for table `notification`
--
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    `type` ENUM('SYSTEM', 'OTHER') NOT NULL,
    `read_at` TIMESTAMP DEFAULT NULL,
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

/*
    Cac loai thong bao

        (*) Customer User:
        1. Login -> thong bao chao mung quay tro lai (n - after login success)
        2. Sign up -> thong bao chao mung toi bk travel (1 - after sign up success)
        3. Booking:
        - Booking success -> Dat tour thanh cong
        - Booked tour Non-started -> Thong bao thoi gian dem nguoc toi thoi diem xuat phat
        (*) Employee

    Table
        1. notification_statistic
            - id -> INT
            - user_id (auth_id) -> INT
            - status -> ENUM('READ','UNREAD')
            - type -> ENUM('AUTH','BOOKING')


        2. auth_notification
            - id -> INT
            - auth_id -> INT
            - message -> TEXT
            - created_at -> DATETIME
        3. booking_notification
            - id -> INT
            - auth_id -> INT
            - sub_tour_id -> INT
            - message -> TEXT
            - created_at -> DATETIME

        3. notification_type (AUTH, BOOKING, CHAT, MAIL)
            - id
            - name
            - description

*/