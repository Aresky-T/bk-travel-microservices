DROP DATABASE IF EXISTS `bk_travel_employee`;

CREATE DATABASE `bk_travel_employee`;

USE `bk_travel_employee`;

--
-- Table structure for table `department`
--
CREATE TABLE department (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL UNIQUE,
    total_members INT NOT NULL DEFAULT 0,
    CONSTRAINT ck_total_members CHECK (total_members >= 0)
);

INSERT INTO department (`name`) VALUES
('Director'),
('Customer And Tourism Service'),
('Human Resources Or Department Management'),
('Information Technology Or Product Development'),
('Security and Protection');

--
-- Table structure for table `position`
--
CREATE TABLE position (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL UNIQUE,
    `description` TEXT NOT NULL
);

INSERT INTO position (`name`, `description`) VALUES
('Chief Executive Officer', 'Chịu trách nhiệm lập kế hoạch chiến lược tổng thể và ra quyết định cho công ty.'),
('Tour Guide', 'Chịu trách nhiệm dẫn dắt, hướng dẫn du khách trong các chuyến đi.'),
('Hotel Management', 'Chịu trách nhiệm quản lý các công việc như liên hệ, quản lý thông tin của các khách sạn liên kết với các tour du lịch'),
('Travel Consulting', 'Chịu trách nhiệm cung cấp tư vấn du lịch và dịch vụ đặt chỗ cho khách hàng.'),
('Event Coordinator', 'Chịu trách nhiệm lập kế hoạch và điều phối các sự kiện và hoạt động dành cho khách du lịch.'),
('Customer Service Representative', 'Chịu trách nhiệm hỗ trợ khách hàng giải đáp thắc mắc và giải quyết các vấn đề liên quan đến dịch vụ du lịch.'),
('Department Head', 'Chịu trách nhiệm giám sát và quản lý một bộ phận trong công ty.'),
('Booking Manager', 'Chịu trách nhiệm quản lý đội ngũ bán hàng và phát triển chiến lược bán hàng.'),
('Human Resources Director', 'Chịu trách nhiệm quản lý các chính sách, thủ tục và chương trình nhân sự trong công ty.'),
('Frontend Developer', 'Chịu trách nhiệm phát triển giao diện người dùng và trải nghiệm người dùng của trang web hoặc ứng dụng web của công ty.'),
('Backend Developer', 'Chịu trách nhiệm phát triển logic phía máy chủ và tương tác cơ sở dữ liệu cho trang web hoặc ứng dụng web của công ty.'),
('Full Stack Developer', 'Chịu trách nhiệm phát triển cả phần frontend và backend của trang web hoặc ứng dụng web của công ty.'),
('UI/UX Designer', 'Chịu trách nhiệm thiết kế giao diện người dùng và trải nghiệm người dùng của trang web hoặc ứng dụng web của công ty.'),
('Web Master', 'Chịu trách nhiệm bảo trì và cập nhật trang web của công ty, bao gồm sửa chữa sự cố và tối ưu hóa hiệu suất.'),
('Intern','Thực tập sinh');

--
-- Table structure for table `status` for employee
--
CREATE TABLE `status` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `description` VARCHAR(255) NOT NULL
);

INSERT INTO `status` (`name`, `description`) VALUES
    ('ACTIVE', 'Nhân viên hiện đang làm việc và hoàn thành nhiệm vụ theo hợp đồng lao động'),
    ('ON_LEAVE', 'Nhân viên đã được nghỉ phép theo chính sách của công ty hoặc theo yêu cầu cá nhân bao gồm nghỉ có lương, nghỉ không lương, nghỉ ốm, v.v.'),
    ('EXPIRED_CONTRACT','Nhân viên đã hết thời hạn làm việc như trong thoả thuận hợp đồng, đã rời khỏi công ty'),
    ('LAYOFFED','Nhân viên đã bị sa thải');

--
-- Table structure for table `employee`
--
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    avatar_url VARCHAR(255), 
    gender ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    `description` VARCHAR(255),
    basic_salary DECIMAL(18, 2) NOT NULL CHECK(basic_salary > 0),
    started_date DATE NOT NULL DEFAULT (current_date()),
    contract_url VARCHAR(255) NOT NULL,
    account_id INT ,
    department_id INT,
    position_id INT,
    status_id INT,
    CONSTRAINT employee_fk1 FOREIGN KEY (`department_id`) REFERENCES `department`(id) ON DELETE SET NULL,
    CONSTRAINT employee_fk2 FOREIGN KEY (`position_id`) REFERENCES `position`(id) ON DELETE SET NULL,
    CONSTRAINT employee_fk3 FOREIGN KEY (`status_id`) REFERENCES `status`(id) ON DELETE SET NULL
);

--
-- Trigger structure for table `employee`
--
DROP TRIGGER IF EXISTS update_department_after_insert_on_employee;
DELIMITER $$
CREATE TRIGGER update_department_after_insert_on_employee
AFTER INSERT ON employee
FOR EACH ROW
BEGIN
	DECLARE dept_id INT;
    SET dept_id = NEW.department_id;
    
    IF dept_id IS NOT NULL THEN
		UPDATE department 
		SET total_members = total_members + 1
		WHERE id = dept_id;
    END IF;
END $$
DELIMITER ;


DROP TRIGGER IF EXISTS update_department_after_update_on_employee
DELIMITER $$
CREATE TRIGGER update_department_after_update_on_employee
AFTER UPDATE ON employee
FOR EACH ROW
BEGIN
	DECLARE old_dept_id INT;
    DECLARE new_dept_id INT;
    
    SET old_dept_id = OLD.department_id;
    SET new_dept_id = NEW.department_id;
    
    -- Kiểm tra xem id của phòng ban mới và cũ có khác nhau không
    IF old_dept_id != new_dept_id THEN
		-- Giảm số lượng thành viên của phòng ban cũ
		IF old_dept_id IS NOT NULL THEN
			UPDATE department 
			SET total_members = total_members - 1
			WHERE id = old_dept_id;
		END IF;
		 -- Tăng số lượng thành viên của phòng ban mới
		IF new_dept_id IS NOT NULL THEN
			UPDATE department
			SET total_members = total_members + 1
			WHERE id = new_dept_id;
		END IF;
    END IF;
END $$
DELIMITER ;

INSERT INTO employee (`first_name`, `last_name`, `gender`, `date_of_birth`, `email`, `phone`, `address`, `basic_salary`, `contract_url`, `department_id`, `position_id`, `status_id`) VALUES
('John', 'Doe', 'MALE', '1990-05-15', 'john.doe@example.com', '123456789', '123 Main Street, City, Country', 3000.00, 'http://example.com/contract/johndoe', 1, 1, 1),
('Jane', 'Smith', 'FEMALE', '1992-09-20', 'jane.smith@example.com', '987654321', '456 Elm Street, City, Country', 3200.00, 'http://example.com/contract/janesmith', 2, 2, 1),
('Michael', 'Johnson', 'MALE', '1985-07-10', 'michael.johnson@example.com', '555123456', '789 Oak Street, City, Country', 3500.00, 'http://example.com/contract/michaeljohnson', 1, 3, 1),
('Emily', 'Williams', 'FEMALE', '1988-12-05', 'emily.williams@example.com', '999888777', '101 Pine Street, City, Country', 3300.00, 'http://example.com/contract/emilywilliams', 3, 4, 1),
('Daniel', 'Brown', 'MALE', '1993-03-25', 'daniel.brown@example.com', '222333444', '202 Maple Street, City, Country', 3400.00, 'http://example.com/contract/danielbrown', 2, 5, 1),
('Jennifer', 'Jones', 'FEMALE', '1987-11-18', 'jennifer.jones@example.com', '777666555', '303 Walnut Street, City, Country', 3200.00, 'http://example.com/contract/jenniferjones', 1, 6, 1),
('David', 'Davis', 'MALE', '1991-08-30', 'david.davis@example.com', '444555666', '404 Cedar Street, City, Country', 3300.00, 'http://example.com/contract/daviddavis', 3, 7, 1),
('Jessica', 'Martinez', 'FEMALE', '1989-06-12', 'jessica.martinez@example.com', '666777888', '505 Oakwood Street, City, Country', 3600.00, 'http://example.com/contract/jessicamartinez', 2, 8, 1),
('Christopher', 'Miller', 'MALE', '1990-04-08', 'christopher.miller@example.com', '111222333', '606 Pinehurst Street, City, Country', 3700.00, 'http://example.com/contract/christophermiller', 1, 9, 1),
('Amanda', 'Garcia', 'FEMALE', '1986-10-22', 'amanda.garcia@example.com', '888999000', '707 Birchwood Street, City, Country', 3100.00, 'http://example.com/contract/amandagarcia', 3, 10, 1);
