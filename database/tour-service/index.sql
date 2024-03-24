DROP DATABASE IF EXISTS `bk_travel_tour`;

CREATE DATABASE `bk_travel_tour`;

USE `bk_travel_tour`;

CREATE TABLE tour (
    id INT PRIMARY KEY AUTO_INCREMENT,
    destinations VARCHAR(255) NOT NULL,
	duration VARCHAR(100) NOT NULL,
    departure_location VARCHAR(255) NOT NULL,
    total_seats INT NOT NULL,
    vehicle VARCHAR(50) NOT NULL,
    schedules TEXT NOT NULL,
    price1 INT NOT NULL CHECK (price1 >= 0),
    price2 INT NOT NULL CHECK (price2 >= 0),
    price3 INT NOT NULL CHECK (price3 >= 0),
    image1 VARCHAR(255) NOT NULL,
    image2 VARCHAR(255) NOT NULL,
    image3 VARCHAR(255) NOT NULL,
    image4 VARCHAR(255) NOT NULL,
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE sub_tour (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL UNIQUE,
    tour_code VARCHAR(100) NOT NULL UNIQUE,
    departure_time DATETIME NOT NULL,
    available_seats INT NOT NULL,
    `status` ENUM('HIDDEN', 'NOT_STARTED','ON_GOING','FINISHED', 'BE_DELAYED', 'CANCELED') NOT NULL DEFAULT 'HIDDEN',
    tour_id INT NOT NULL,
    employee_id INT,
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT sub_tour_fk1 FOREIGN KEY (tour_id) REFERENCES tour(id) ON DELETE CASCADE
);