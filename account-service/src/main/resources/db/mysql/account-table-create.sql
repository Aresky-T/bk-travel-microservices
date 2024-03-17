CREATE TABLE IF NOT EXISTS `account` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `avatar_url` varchar(255) DEFAULT NULL,
    `full_name` varchar(100) DEFAULT NULL,
    `address` varchar(255) DEFAULT NULL,
    `phone` varchar(20) DEFAULT NULL,
    `date_of_birth` date DEFAULT NULL,
    `gender` enum('MALE', 'FEMALE', 'OTHER') DEFAULT NULL,
    `auth_id` int NOT NULL,
    `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY`auth_id`(`auth_id`),
    PRIMARY KEY (`id`)
);