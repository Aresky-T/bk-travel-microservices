DROP DATABASE IF EXISTS `bk_travel_tourist_attraction`;

CREATE DATABASE `bk_travel_tourist_attraction`;

USE `bk_travel_tourist_attraction`;

CREATE TABLE tourist_attraction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL UNIQUE,
    image_url text NOT NULL,
    title VARCHAR(255) NOT NULL
);

CREATE TABLE blog (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL UNIQUE,
    intro TEXT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    tourist_attraction_id INT NOT NULL,
    author VARCHAR(50) NOT NULL  DEFAULT 'BK TRAVEL',
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT blog_fk1 FOREIGN KEY (tourist_attraction_id) REFERENCES tourist_attraction (id) ON DELETE CASCADE
);

CREATE TABLE blog_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sub_title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    image_url VARCHAR(255),
    blog_id INT NOT NULL,
    CONSTRAINT blog_item_fk1 FOREIGN KEY (blog_id) REFERENCES blog (id) ON DELETE CASCADE
);