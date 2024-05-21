DROP DATABASE IF EXISTS`ssafy_db`;

CREATE DATABASE `ssafy_db` default character set utf8mb4;

USE `ssafy_db`;

CREATE TABLE IF NOT EXISTS `users` (
	`id` VARCHAR(20) NOT NULL,
	`password` VARCHAR(40) NOT NULL,
	`name` VARCHAR(40) NOT NULL,
	`email` VARCHAR(40) NULL,
	`address` VARCHAR(40) NULL,
	`membership_rate` INT NOT NULL DEFAULT 0,
	`gender` INT NOT NULL, -- 남자: 0 여자: 1
	`nick_name` VARCHAR(40) NULL,
	`birth` TIMESTAMP NOT NULL,
	`reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`coin` INT NOT NULL DEFAULT 0,
	`category` INT NOT NULL DEFAULT 0, -- 0: 사용자, 1: 관리
	`comment` TEXT NULL,
	`img` VARCHAR(100) NULL,
	PRIMARY KEY (`id`)
);

INSERT INTO `users` (`id`, `password`, `name`, `email`, `address`, `membership_rate`, `gender`, `nick_name`, `birth`, `coin`, `category`, `comment`, `img`) VALUES
('user1', 'password1', 'John Doe', 'john@example.com', '123 Main St, City, Country', 10, 0, 'JohnD', '1990-01-01', 100, 0, 'Nice to meet you!', 'john.jpg'),
('user2', 'password2', 'Jane Smith', 'jane@example.com', '456 Park Ave, City, Country', 20, 1, 'JaneS', '1992-05-15', 200, 0, 'Hello!', 'jane.jpg'),
('user3', 'password3', 'Michael Johnson', 'michael@example.com', '789 Elm St, City, Country', 30, 0, 'MichaelJ', '1985-08-20', 300, 0, 'Welcome!', 'michael.jpg'),
('user4', 'password4', 'Emily Brown', 'emily@example.com', '101 Oak St, City, Country', 40, 1, 'EmilyB', '1988-11-10', 400, 0, 'Nice day!', 'emily.jpg'),
('user5', 'password5', 'David Lee', 'david@example.com', '111 Pine St, City, Country', 50, 0, 'DavidL', '1982-03-25', 500, 0, 'Good morning!', 'david.jpg'),
('user6', 'password6', 'Sarah Wilson', 'sarah@example.com', '222 Cedar St, City, Country', 60, 1, 'SarahW', '1995-07-12', 600, 0, 'Good evening!', 'sarah.jpg'),
('user7', 'password7', 'Matthew Martinez', 'matthew@example.com', '333 Maple St, City, Country', 70, 0, 'MatthewM', '1980-09-30', 700, 0, 'Have a nice day!', 'matthew.jpg'),
('user8', 'password8', 'Olivia Anderson', 'olivia@example.com', '444 Walnut St, City, Country', 80, 1, 'OliviaA', '1998-12-05', 800, 0, 'Good night!', 'olivia.jpg'),
('user9', 'password9', 'Daniel Taylor', 'daniel@example.com', '555 Birch St, City, Country', 90, 0, 'DanielT', '1987-06-18', 900, 0, 'Take care!', 'daniel.jpg'),
('user10', 'password10', 'Emma Garcia', 'emma@example.com', '666 Spruce St, City, Country', 100, 1, 'EmmaG', '1993-04-07', 1000, 0, 'See you soon!', 'emma.jpg');


CREATE TABLE IF NOT EXISTS `exercise_category` (
    `exercise_id` INT NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`exercise_id`)
);

INSERT INTO `exercise_category` (`category`)
VALUES
    ('Pilates'),
    ('Fitness'),
    ('Boxing'),
    ('CrossFit'),
    ('Climbing'),
    ('Swimming'),
    ('Taekwondo'),
    ('Jiu-Jitsu'), -- 주짓수
    ('Yoga')
;



CREATE TABLE IF NOT EXISTS `stores` (
    `store_id` INT NOT NULL AUTO_INCREMENT,
    `exercise_id` INT NOT NULL, # 외래키 (exercise_category)
    `store_name` VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `phone_number` VARCHAR(20) NOT NULL,
    `description` TEXT NULL,
    `favorite_count` INT DEFAULT 0,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `opening_hours` VARCHAR(255) NULL, # 운영시간
    `logo_image` VARCHAR(255) NULL,
    `owner_id` VARCHAR(20) NOT NULL,
	# `rating` DECIMAL(3, 2) NULL CHECK (rating >= 0.0 AND rating <= 5.0), # 평점
    # `dormant_account` BOOLEAN DEFAULT FALSE, #휴면계정
    # `push_notification_agreement` BOOLEAN DEFAULT FALSE, # push 알림동의
    # `day_off` VARCHAR(255) NULL, # 휴무일
    PRIMARY KEY (`store_id`),
    FOREIGN KEY (`exercise_id`) REFERENCES `exercise_category`(`exercise_id`),
	FOREIGN KEY (`owner_id`) REFERENCES `users`(`id`) ON DELETE CASCADE -- 사용자가 삭제되면 삭제..? 
);

INSERT INTO `stores` (`exercise_id`, `store_name`, `address`, `phone_number`, `description`, `favorite_count`, `created_at`, `opening_hours`, `logo_image`, `owner_id`) VALUES
(1, 'Pilates Studio', '123 Main St, City, Country', '123-456-7890', 'A great place for Pilates!', 100, CURRENT_TIMESTAMP, '9:00 AM - 8:00 PM', 'pilates_logo.jpg', 'user1'),
(2, 'Fitness Center', '456 Park Ave, City, Country', '456-789-0123', 'Your one-stop fitness destination!', 200, CURRENT_TIMESTAMP, '6:00 AM - 10:00 PM', 'fitness_logo.jpg', 'user1'),
(3, 'Boxing Gym', '789 Elm St, City, Country', '789-012-3456', 'Train like a boxer!', 300, CURRENT_TIMESTAMP, '10:00 AM - 9:00 PM', 'boxing_logo.jpg', 'user1'),
(4, 'CrossFit Box', '101 Oak St, City, Country', '101-234-5678', 'Get fit with CrossFit!', 400, CURRENT_TIMESTAMP, '7:00 AM - 7:00 PM', 'crossfit_logo.jpg', 'user1'),
(5, 'Climbing Gym', '111 Pine St, City, Country', '111-222-3333', 'Reach new heights!', 500, CURRENT_TIMESTAMP, '12:00 PM - 10:00 PM', 'climbing_logo.jpg', 'user1'),
(6, 'Swimming Pool', '222 Cedar St, City, Country', '222-333-4444', 'Dive into fitness!', 600, CURRENT_TIMESTAMP, '8:00 AM - 8:00 PM', 'swimming_logo.jpg', 'user1'),
(7, 'Taekwondo Dojang', '333 Maple St, City, Country', '333-444-5555', 'Learn Taekwondo!', 700, CURRENT_TIMESTAMP, '2:00 PM - 9:00 PM', 'taekwondo_logo.jpg', 'user1'),
(8, 'Jiu-Jitsu Academy', '444 Walnut St, City, Country', '444-555-6666', 'Train in Jiu-Jitsu!', 800, CURRENT_TIMESTAMP, '10:00 AM - 7:00 PM', 'jiujitsu_logo.jpg', 'user1'),
(9, 'Yoga Studio', '555 Birch St, City, Country', '555-666-7777', 'Find your inner peace with yoga!', 900, CURRENT_TIMESTAMP, '6:00 AM - 9:00 PM', 'yoga_logo.jpg', 'user1'),
(1, 'Running Club', '666 Spruce St, City, Country', '666-777-8888', 'Run with us!', 1000, CURRENT_TIMESTAMP, '5:00 AM - 8:00 PM', 'running_logo.jpg', 'user1');

CREATE TABLE IF NOT EXISTS `teacher` (
    `teacher_id` INT NOT NULL AUTO_INCREMENT,
    `store_id` INT NOT NULL,
    `name` varchar(30) NOT NULL,
    `comment` text NULL,
    PRIMARY KEY (`teacher_id`),
    FOREIGN KEY (`store_id`) REFERENCES `stores`(`store_id`) ON DELETE CASCADE
);

INSERT INTO `teacher` (`store_id`, `name`, `comment`) 
VALUES 
(1, '김영희', '피트니스 전문가로 다양한 운동 프로그램을 진행합니다.'),
(2, '이철수', '헬스 트레이닝에 관심이 많은 트레이너입니다.'),
(3, '박민수', '크로스핏을 좋아하는 운동 인스트럭터입니다.'),
(4, '정미경', '바디빌딩과 관련된 다양한 팁을 제공합니다.'),
(5, '홍길동', '유연성을 향상시키는 운동을 가르치는 전문가입니다.'),
(6, '이영희', '요가 강사로 활동하고 있습니다.'),
(7, '김철수', '태권도를 전문적으로 가르치는 강사입니다.'),
(8, '박지영', '피트니스에 대한 전문적인 지식을 갖춘 운동 강사입니다.'),
(9, '정영수', '스포츠 심리학에 대해 깊이 있는 이해를 가지고 있습니다.'),
(1, '이민지', '다이어트에 관심이 많은 운동 강사입니다.');


CREATE TABLE `favorites` (
`user_id` VARCHAR(20) NOT NULL,
`store_id` INT NOT NULL,
PRIMARY KEY (`store_id`, `user_id`),
FOREIGN KEY (`store_id`) REFERENCES `stores`(`store_id`) ON DELETE CASCADE,
FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);


INSERT INTO `favorites` (`user_id`, `store_id`)
VALUES
('user1', 1),
('user1', 2),
('user1', 3),
('user1', 4),
('user2', 2),
('user3', 3),
('user4', 4),
('user5', 5),
('user6', 6),
('user7', 7),
('user8', 8),
('user9', 9),
('user10', 1);

CREATE TABLE `tickets` (
    `ticket_id` INT NOT NULL AUTO_INCREMENT,
    `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 생성일
	`expiration_date` TIMESTAMP NULL, -- 만료일 -- 정기권이 아니면 NULL
    `total_quantity` INT NOT NULL DEFAULT 0, -- 초기횟수 -- 다회권이 아니면 0
	`remaining_quantity` INT NOT NULL DEFAULT 0, -- 잔여횟수 -- 다회권이 아니면 0
    `category` INT NOT NULL, -- 이용권 분류 0:다회권 / 1:정기권
    `user_id` VARCHAR(20) NOT NULL,
    `store_id` INT NOT NULL,
    PRIMARY KEY (`ticket_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE, -- 사용자가 삭제되면 삭제..? 
    FOREIGN KEY (`store_id`) REFERENCES `stores`(`store_id`) ON DELETE CASCADE
 );

INSERT INTO `tickets` (`reg_date`, `expiration_date`, `total_quantity`, `remaining_quantity`, `category`, `user_id`, `store_id`)
VALUES
    ('2024-05-10 12:00:00', '2024-06-10 12:00:00', 10, 10, 0, 'user1', 1), -- 다회권, 10회권, 만료일까지 10회 남음
    ('2024-05-10 12:00:00', '2024-07-10 12:00:00', 1, 1, 1, 'user2', 2),  -- 정기권, 1개월 유효, 만료일까지 1회 남음
    ('2024-05-10 12:00:00', NULL, 20, 20, 0, 'user3', 3); -- 다회권, 단순히 횟수만 제공, 만료일 없음


CREATE TABLE IF NOT EXISTS `lesson` (
    `lesson_id` INT NOT NULL AUTO_INCREMENT,
    `teacher_id` INT NOT NULL,
    `store_id` INT NOT NULL,
    `date` TIMESTAMP NOT NULL,
    `user_cnt` INT NOT NULL DEFAULT 0,
    `capacity` INT NOT NULL DEFAULT 0,
    PRIMARY KEY (`lesson_id`),
    FOREIGN KEY (`teacher_id`) REFERENCES `teacher`(`teacher_id`) ON DELETE CASCADE,
    FOREIGN KEY (`store_id`) REFERENCES `stores`(`store_id`) ON DELETE CASCADE
);

INSERT INTO `lesson` (`teacher_id`, `store_id`, `date`, `user_cnt`, `capacity`)
VALUES
(1, 1, '2024-05-10 09:00:00', 3, 10),
(2, 1, '2024-05-10 10:30:00', 2, 8),
(3, 1, '2024-05-10 13:00:00', 0, 12),
(4, 2, '2024-05-10 15:30:00', 5, 6),
(5, 2, '2024-05-11 11:00:00', 7, 15),
(6, 3, '2024-05-11 10:00:00', 1, 7),
(7, 3, '2024-05-11 14:00:00', 10, 10),
(8, 3, '2024-05-12 16:30:00', 2, 8),
(9, 4, '2024-05-12 12:30:00', 3, 9),
(10, 1, '2024-05-12 08:30:00', 5, 10);


CREATE TABLE IF NOT EXISTS `reservation` (
	`reservation_id` INT NOT NULL AUTO_INCREMENT,
	`user_id` VARCHAR(20) NOT NULL,
	`store_id` INT NOT NULL,
	`lesson_id` INT NOT NULL,
	`ticket_id` INT NOT NULL,
    `date` TIMESTAMP NOT NULL,
	PRIMARY KEY (`reservation_id`),
	FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`store_id`) REFERENCES `stores`(`store_id`) ON DELETE CASCADE,
    FOREIGN KEY (`lesson_id`) REFERENCES `lesson`(`lesson_id`) ON DELETE CASCADE,
	FOREIGN KEY (`ticket_id`) REFERENCES `tickets`(`ticket_id`) ON DELETE CASCADE
);


INSERT INTO `reservation` (`user_id`, `store_id`, `lesson_id`, `ticket_id`, `date`)
VALUES
('user1', 1, 1, 1, '2024-05-10 08:00:00'), -- user1이 2024년 5월 10일 8시 수업을 10회권으로 예약함
('user2', 1, 2, 1, '2024-05-10 10:15:00'), -- user2이 2024년 5월 10일 10시 15분 수업을 10회권으로 예약함
('user3', 1, 3, 1, '2024-05-10 12:50:00'), -- user3이 2024년 5월 10일 12시 50분 수업을 10회권으로 예약함
('user4', 2, 4, 2, '2024-05-10 15:00:00'), -- user4이 2024년 5월 10일 15시 수업을 정기권으로 예약함
('user5', 2, 5, 2, '2024-05-11 10:45:00'), -- user5이 2024년 5월 11일 10시 45분 수업을 정기권으로 예약함
('user6', 3, 6, 3, '2024-05-11 09:30:00'), -- user6이 2024년 5월 11일 9시 30분 수업을 20회권으로 예약함
('user7', 3, 7, 3, '2024-05-11 13:45:00'), -- user7이 2024년 5월 11일 13시 45분 수업을 20회권으로 예약함
('user8', 3, 8, 3, '2024-05-12 16:15:00'), -- user8이 2024년 5월 12일 16시 15분 수업을 20회권으로 예약함
('user9', 4, 9, 1, '2024-05-12 12:15:00'), -- user9이 2024년 5월 12일 12시 15분 수업을 10회권으로 예약함
('user10', 1, 10, 1, '2024-05-12 08:15:00'); -- user10이 2024년 5월 12일 8시 15분 수업을 10회권으로 예약함

INSERT INTO `reservation` (`user_id`, `store_id`, `lesson_id`, `ticket_id`, `date`)
VALUES
('user1', 1, 1, 1, '2024-05-17 08:00:00'), -- user1이 2024년 5월 10일 8시 수업을 10회권으로 예약함
('user1', 1, 2, 1, '2024-05-18 10:15:00'), -- user2이 2024년 5월 10일 10시 15분 수업을 10회권으로 예약함
('user1', 1, 3, 1, '2024-05-19 12:50:00'), -- user3이 2024년 5월 10일 12시 50분 수업을 10회권으로 예약함
('user1', 2, 4, 2, '2024-05-20 15:00:00'), -- user4이 2024년 5월 10일 15시 수업을 정기권으로 예약함
('user1', 2, 5, 2, '2024-05-21 10:45:00'); -- user5이 2024년 5월 11일 10시 45분 수업을 정기권으로 예약함


CREATE TABLE `refresh_token` (
	`refresh_token_id` VARCHAR(255) NOT NULL,
	`user_id` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`refresh_token_id`),
	FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS `sell_ticket` (
	`ticket_id` INT NOT NULL AUTO_INCREMENT,
    `store_id` INT NOT NULL,
    `name` varchar(255) NOT NULL,
	`category` INT NOT NULL, -- 정기권 1, 다회권 0
	`expire_date` INT NULL, -- category 1일때만
	`quantity` INT NULL, -- category 2일때만
    `price` int NOT NULL,
	PRIMARY KEY (`ticket_id`),
    FOREIGN KEY (`store_id`) REFERENCES `stores`(`store_id`) ON DELETE CASCADE
);

INSERT INTO `sell_ticket` (`store_id`, `name`, `category`, `expire_date`, `quantity`, `price`) VALUES
(1, 'Monthly Pass', 1, 30, NULL, 50000),
(1, '10 Visit Pass', 0, NULL, 10, 45000),
(1, 'Quarterly Pass', 1, 90, NULL, 120000),
(1, '20 Visit Pass', 0, NULL, 20, 80000),
(1, 'Weekly Pass', 1, 7, NULL, 20000),
(1, '5 Visit Pass', 0, NULL, 5, 25000),
(2, 'Yearly Pass', 1, 365, NULL, 450000),
(2, '50 Visit Pass', 0, NULL, 50, 180000),
(2, 'Monthly Pass', 1, 30, NULL, 52000),
(2, '15 Visit Pass', 0, NULL, 15, 70000),
(2, 'Bi-Annual Pass', 1, 180, NULL, 250000),
(2, '30 Visit Pass', 0, NULL, 30, 120000),
(3, 'Monthly Pass', 1, 30, NULL, 49000),
(3, '8 Visit Pass', 0, NULL, 8, 36000),
(3, 'Quarterly Pass', 1, 90, NULL, 115000),
(3, '25 Visit Pass', 0, NULL, 25, 90000),
(3, 'Weekly Pass', 1, 7, NULL, 21000),
(3, '12 Visit Pass', 0, NULL, 12, 55000),
(4, 'Monthly Pass', 1, 30, NULL, 51000),
(4, '6 Visit Pass', 0, NULL, 6, 28000);


CREATE TABLE `store_pictures` (
    `picture_id` VARCHAR(255) NOT NULL,
    `store_id` INT NOT NULL,
    `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`picture_id`),
    FOREIGN KEY (`store_id`) REFERENCES `stores`(`store_id`) ON DELETE CASCADE
 );


commit;



commit;
