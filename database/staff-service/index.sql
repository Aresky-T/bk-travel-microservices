DROP DATABASE IF EXISTS `bk_travel_staff`;

CREATE DATABASE `bk_travel_staff`;

USE `bk_travel_staff`;

--
-- Table structure for table `department`
--
CREATE TABLE department (
  id INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  `description` varchar(1000),
  total_members INT NOT NULL DEFAULT 0,
  CONSTRAINT ck_total_members CHECK (total_members >= 0)
);

INSERT INTO
  `department`
VALUES
  (
    1,
    'Phòng Giám Đốc',
    'Phòng chịu trách nhiệm quản lý và điều hành toàn bộ hoạt động của doanh nghiệp',
    0
  ),
(
    2,
    'Phòng Hỗ Trợ Khách Hàng',
    'Phòng chịu trách nhiệm hỗ trợ và giải đáp thắc mắc của khách hàng',
    0
  ),
(
    3,
    'Phòng Phát Triển Sản Phẩm',
    'Phòng chịu trách nhiệm phát triển và cải thiện sản phẩm Tour du lịch',
    0
  ),
(
    4,
    'Phòng Sales',
    'Phòng chịu trách nhiệm về việc tăng doanh số booking',
    0
  ),
(
    5,
    'Phòng Kế Toán',
    'Phòng chịu trách nhiệm về việc quản lý tài chính và kế toán của công ty',
    0
  ),
(
    6,
    'Phòng Marketing',
    'Phòng chịu trách nhiệm về việc quảng bá và tiếp thị các tour du lịch',
    0
  ),
(
    7,
    'Phòng Kinh Doanh',
    'Phòng chịu trách nhiệm về việc tìm kiếm và phát triển các dịch vụ du lịch mới',
    0
  ),
(
    8,
    'Phòng Quản lý Hệ thống',
    'Chịu trách nhiệm giám sát, phát triển và bảo trì các hệ thống công nghệ thông tin. Vai trò bao gồm giám sát hoạt động của mạng, máy chủ và ứng dụng, đảm bảo tính ổn định và hiệu suất của hệ thống. Tham gia vào việc phát triển và triển khai các hệ thống mới, bảo trì định kỳ và sửa chữa các sự cố kỹ thuật. Bảo mật thông tin và hỗ trợ kỹ thuật cho nhân viên cũng là các nhiệm vụ quan trọng của phòng quản lý hệ thống. Bằng cách thực hiện các hoạt động này, luôn đảm bảo rằng hệ thống thông tin của công ty luôn hoạt động một cách an toàn và hiệu quả.',
    0
  );

--
-- Table structure for table `position`
--
CREATE TABLE `position` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `department_id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  `description` VARCHAR(1000) NOT NULL,
  `headcount` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `position_fk_department_id` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE
);

INSERT INTO
  `position`
VALUES
  (
    1,
    1,
    'Giams đốc điều hành',
    'CEO của công ty du lịch.Là trụ cột quan trọng, định hình chiến lược và hướng đi cho doanh nghiệp. Với sự lãnh đạo mạnh mẽ, tạo ra một môi trường làm việc tích cực, khuyến khích sáng tạo và động viên nhân viên phát triển bản thân. Giám đốc điều hành chịu trách nhiệm về việc đảm bảo hiệu suất kinh doanh và tạo ra giá trị cho cả doanh nghiệp và cộng đồng.',
    1
  ),
(
    2,
    2,
    'Nhân viên Hỗ trợ Khách hàng',
    'Trả lời các câu hỏi của khách hàng, cung cấp thông tin về chương trình du lịch, giải quyết các vấn đề phát sinh trong quá trình du lịch, cung cấp hướng dẫn và hỗ trợ kỹ thuật nếu cần.',
    2
  ),
(
    3,
    2,
    'Trưởng phòng Dịch vụ Khách hàng',
    'Điều hành và quản lý hoạt động của phòng Hỗ Trợ Khách Hàng, đảm bảo sự hài lòng của khách hàng. Lập kế hoạch và tổ chức công việc của nhân viên hỗ trợ khách hàng, theo dõi hiệu suất và chất lượng dịch vụ, xử lý các vấn đề phát sinh và đề xuất các biện pháp cải tiến',
    1
  ),
(
    4,
    2,
    'Chuyên viên Tư vấn Du lịch',
    'Cung cấp thông tin và tư vấn chi tiết về các điểm đến, chương trình du lịch, dịch vụ đi kèm và các hoạt động du lịch. Tư vấn cho khách hàng về các lựa chọn du lịch phù hợp với nhu cầu và mong muốn của họ, đề xuất các gói tour và dịch vụ phù hợp, hỗ trợ đặt chỗ và xử lý các yêu cầu đặc biệt từ khách hàng.',
    2
  ),
(
    5,
    3,
    'Nhà sản xuất Sản phẩm Du lịch',
    'Tham gia vào quá trình phát triển sản phẩm tour du lịch từ ý tưởng ban đầu đến sản phẩm hoàn chỉnh. Nghiên cứu và đề xuất các ý tưởng mới cho tour du lịch, thực hiện các cuộc thử nghiệm và nghiên cứu thị trường để đảm bảo sản phẩm đáp ứng nhu cầu của khách hàng, xây dựng các gói tour chi tiết và hấp dẫn.',
    1
  ),
(
    6,
    3,
    'Chuyên viên Phát triển Sản phẩm',
    'Điều hành các hoạt động phát triển sản phẩm, đảm bảo sự đổi mới và cải thiện liên tục. Phân tích các xu hướng thị trường du lịch, thu thập và đánh giá ý kiến phản hồi từ khách hàng, đề xuất các cải tiến và nâng cấp cho các sản phẩm tour du lịch hiện có, hợp tác với các bộ phận khác để triển khai các cải tiến.',
    1
  ),
(
    7,
    3,
    'Quản lý Sản phẩm Du lịch',
    'Chịu trách nhiệm quản lý toàn bộ quá trình phát triển sản phẩm tour du lịch. Lập kế hoạch và điều phối các hoạt động phát triển sản phẩm, theo dõi tiến độ và chất lượng sản phẩm, đảm bảo tuân thủ ngân sách và thời gian hoàn thành, xử lý các vấn đề phát sinh và đề xuất các giải pháp.',
    1
  ),
(
    8,
    3,
    'Chuyên viên Tiếp thị Sản phẩm Du lịch:',
    'Phụ trách việc quảng bá và tiếp thị các sản phẩm tour du lịch đến khách hàng. Phát triển chiến lược tiếp thị sản phẩm, thiết kế và triển khai các chiến dịch quảng cáo và truyền thông, tạo ra nội dung tiếp thị hấp dẫn như video, hình ảnh và bài viết blog, theo dõi hiệu quả tiếp thị và điều chỉnh chiến lược nếu cần.',
    1
  );

--
-- Table structure for table `status` for employee
--
CREATE TABLE `status` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(255) NOT NULL
);

INSERT INTO
  `status` (`name`, `description`)
VALUES
  (
    'ACTIVE',
    'Nhân viên hiện đang làm việc và hoàn thành nhiệm vụ theo hợp đồng lao động'
  ),
  (
    'ON_LEAVE',
    'Nhân viên đã được nghỉ phép theo chính sách của công ty hoặc theo yêu cầu cá nhân bao gồm nghỉ có lương, nghỉ không lương, nghỉ ốm, v.v.'
  ),
  (
    'EXPIRED_CONTRACT',
    'Nhân viên đã hết thời hạn làm việc như trong thoả thuận hợp đồng, đã rời khỏi công ty'
  ),
  ('LAYOFFED', 'Nhân viên đã bị sa thải');

--
-- Table structure for table `employee`
--
CREATE TABLE `staff` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `avatar_url` VARCHAR(255) DEFAULT NULL,
  `gender` enum('MALE', 'FEMALE', 'OTHER') NOT NULL,
  `date_of_birth` date NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `basic_salary` DECIMAL(18, 2) NOT NULL CHECK(basic_salary > 0),
  `started_date` DATE NOT NULL DEFAULT CURRENT_DATE,
  `contract_url` VARCHAR(255) NOT NULL,
  `account_id` INT DEFAULT NULL,
  `department_id` INT DEFAULT NULL,
  `position_id` INT DEFAULT NULL,
  `status_id` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `contract_url` (`contract_url`),
  UNIQUE KEY `account_id` (`account_id`),
  CONSTRAINT `staff_fk_department_id` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE
  SET
    NULL,
    CONSTRAINT `staff_fk_position_id` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON DELETE
  SET
    NULL,
    CONSTRAINT `staff_fk_status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE
  SET
    NULL
);

--
-- Trigger structure for table `employee`
--
DROP TRIGGER IF EXISTS update_department_after_insert_on_employee;

DELIMITER $ $ CREATE TRIGGER update_department_after_insert_on_employee
AFTER
INSERT
  ON employee FOR EACH ROW BEGIN DECLARE dept_id INT;

SET
  dept_id = NEW.department_id;

IF dept_id IS NOT NULL THEN
UPDATE
  department
SET
  total_members = total_members + 1
WHERE
  id = dept_id;

END IF;

END $ $ DELIMITER;

DROP TRIGGER IF EXISTS update_department_after_update_on_employee DELIMITER $ $ CREATE TRIGGER update_department_after_update_on_employee
AFTER
UPDATE
  ON employee FOR EACH ROW BEGIN DECLARE old_dept_id INT;

DECLARE new_dept_id INT;

SET
  old_dept_id = OLD.department_id;

SET
  new_dept_id = NEW.department_id;

-- Kiểm tra xem id của phòng ban mới và cũ có khác nhau không
IF old_dept_id != new_dept_id THEN -- Giảm số lượng thành viên của phòng ban cũ
IF old_dept_id IS NOT NULL THEN
UPDATE
  department
SET
  total_members = total_members - 1
WHERE
  id = old_dept_id;

END IF;

-- Tăng số lượng thành viên của phòng ban mới
IF new_dept_id IS NOT NULL THEN
UPDATE
  department
SET
  total_members = total_members + 1
WHERE
  id = new_dept_id;

END IF;

END IF;

END $ $ DELIMITER;

INSERT INTO
  employee (
    `first_name`,
    `last_name`,
    `gender`,
    `date_of_birth`,
    `email`,
    `phone`,
    `address`,
    `basic_salary`,
    `contract_url`,
    `department_id`,
    `position_id`,
    `status_id`
  )
VALUES
  (
    'John',
    'Doe',
    'MALE',
    '1990-05-15',
    'john.doe@example.com',
    '123456789',
    '123 Main Street, City, Country',
    3000.00,
    'http://example.com/contract/johndoe',
    1,
    1,
    1
  ),
  (
    'Jane',
    'Smith',
    'FEMALE',
    '1992-09-20',
    'jane.smith@example.com',
    '987654321',
    '456 Elm Street, City, Country',
    3200.00,
    'http://example.com/contract/janesmith',
    2,
    2,
    1
  ),
  (
    'Michael',
    'Johnson',
    'MALE',
    '1985-07-10',
    'michael.johnson@example.com',
    '555123456',
    '789 Oak Street, City, Country',
    3500.00,
    'http://example.com/contract/michaeljohnson',
    1,
    3,
    1
  ),
  (
    'Emily',
    'Williams',
    'FEMALE',
    '1988-12-05',
    'emily.williams@example.com',
    '999888777',
    '101 Pine Street, City, Country',
    3300.00,
    'http://example.com/contract/emilywilliams',
    3,
    4,
    1
  ),
  (
    'Daniel',
    'Brown',
    'MALE',
    '1993-03-25',
    'daniel.brown@example.com',
    '222333444',
    '202 Maple Street, City, Country',
    3400.00,
    'http://example.com/contract/danielbrown',
    2,
    5,
    1
  ),
  (
    'Jennifer',
    'Jones',
    'FEMALE',
    '1987-11-18',
    'jennifer.jones@example.com',
    '777666555',
    '303 Walnut Street, City, Country',
    3200.00,
    'http://example.com/contract/jenniferjones',
    1,
    6,
    1
  ),
  (
    'David',
    'Davis',
    'MALE',
    '1991-08-30',
    'david.davis@example.com',
    '444555666',
    '404 Cedar Street, City, Country',
    3300.00,
    'http://example.com/contract/daviddavis',
    3,
    7,
    1
  ),
  (
    'Jessica',
    'Martinez',
    'FEMALE',
    '1989-06-12',
    'jessica.martinez@example.com',
    '666777888',
    '505 Oakwood Street, City, Country',
    3600.00,
    'http://example.com/contract/jessicamartinez',
    2,
    8,
    1
  ),
  (
    'Christopher',
    'Miller',
    'MALE',
    '1990-04-08',
    'christopher.miller@example.com',
    '111222333',
    '606 Pinehurst Street, City, Country',
    3700.00,
    'http://example.com/contract/christophermiller',
    1,
    9,
    1
  ),
  (
    'Amanda',
    'Garcia',
    'FEMALE',
    '1986-10-22',
    'amanda.garcia@example.com',
    '888999000',
    '707 Birchwood Street, City, Country',
    3100.00,
    'http://example.com/contract/amandagarcia',
    3,
    10,
    1
  );