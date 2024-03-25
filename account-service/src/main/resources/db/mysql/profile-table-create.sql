CREATE TABLE IF NOT EXISTS `profile`(
    id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT NOT NULL UNIQUE,
    avatar_url VARCHAR(255) DEFAULT NULL,
    full_name VARCHAR(100) DEFAULT NULL,
    address VARCHAR(255) DEFAULT NULL,
    phone VARCHAR(20) DEFAULT NULL,
    date_of_birth DATE DEFAULT NULL,
    gender ENUM('MALE', 'FEMALE', 'OTHER') DEFAULT NULL,
    CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES `account`(id) ON DELETE CASCADE
);