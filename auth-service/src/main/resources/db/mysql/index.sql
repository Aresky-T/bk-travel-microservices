CREATE TABLE IF NOT EXISTS auth (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    account_id INT NOT NULL UNIQUE,
    access_token VARCHAR(255) NOT NULL UNIQUE
);