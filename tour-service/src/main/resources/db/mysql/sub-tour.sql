CREATE TABLE sub_tour (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL UNIQUE,
    tour_code VARCHAR(100) NOT NULL UNIQUE,
    departure_time DATETIME NOT NULL,
    available_seats INT NOT NULL CHECK (available_seats >= 0),
    `status` ENUM('HIDDEN', 'NOT_STARTED','ON_GOING','FINISHED', 'BE_DELAYED', 'CANCELED') NOT NULL DEFAULT 'HIDDEN',
    tour_id INT NOT NULL,
    tour_guide_id INT,
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_tour_id FOREIGN KEY (tour_id) REFERENCES tour(id) ON DELETE CASCADE
);