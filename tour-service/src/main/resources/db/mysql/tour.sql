CREATE TABLE tour (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL UNIQUE,
    destinations VARCHAR(255) NOT NULL,
	duration VARCHAR(100) NOT NULL,
    departure_location VARCHAR(255) NOT NULL,
    total_seats INT NOT NULL,
    vehicle VARCHAR(50) NOT NULL,
    schedules TEXT NOT NULL,
    adult_price INT NOT NULL CHECK (adult_price >= 0),
    children_price INT NOT NULL CHECK (children_price >= 0),
    baby_price INT NOT NULL CHECK (baby_price >= 0),
    image1 VARCHAR(255) NOT NULL,
    image2 VARCHAR(255) NOT NULL,
    image3 VARCHAR(255) NOT NULL,
    image4 VARCHAR(255) NOT NULL,
    created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);