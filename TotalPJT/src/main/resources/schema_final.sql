DROP DATABASE IF EXISTS `ssafy_db`;

CREATE DATABASE `ssafy_db` DEFAULT CHARACTER SET utf8mb4;

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
    `birth` DATE NOT NULL, -- TIMESTAMP에서 DATE로 변경
    `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `coin` INT NOT NULL DEFAULT 0,
    `category` INT NOT NULL DEFAULT 0, -- 0: 사용자, 1: 관리자
    `comment` TEXT NULL,
    `img` VARCHAR(100) NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `users` (`id`, `password`, `name`, `email`, `address`, `membership_rate`, `gender`, `nick_name`, `birth`, `coin`, `category`, `comment`, `img`) VALUES
('user1', 'password1', '황정현', 'john@example.com', '서울 강남구 테헤란로 212', 10, 0, '로꼴리', '1996-03-14', 200, 0, '뭘봐요, 구경났어요?', 'hwang.jpg'),
('user2', 'password2', '송예진', 'jane@example.com', '456 Park Ave, City, Country', 20, 1, '송송', '1999-11-23', 200, 0, '빵이 좋아요', 'song.jpg');

CREATE TABLE IF NOT EXISTS `exercise_category` (
    `exercise_id` INT NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`exercise_id`)
);

INSERT INTO `exercise_category` (`category`)
VALUES
    ('필라테스'),
    ('헬스'),
    ('복싱'),
    ('크로스핏'),
    ('클라이밍'),
    ('수영'),
    ('태권도'),
    ('주짓수'),
    ('요가');

CREATE TABLE IF NOT EXISTS `stores` (
    `store_id` INT NOT NULL AUTO_INCREMENT,
    `exercise_id` INT NOT NULL, -- 외래키 (exercise_category)
    `store_name` VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `phone_number` VARCHAR(20) NOT NULL,
    `description` TEXT NULL,
    `favorite_count` INT DEFAULT 0,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `opening_hours` VARCHAR(255) NULL, -- 운영시간
    `logo_image` VARCHAR(255) NULL,
    `owner_id` VARCHAR(20) NOT NULL,
    `coin` INT DEFAULT 0,
    `thumbnail_img` VARCHAR(255),
    PRIMARY KEY (`store_id`),
    FOREIGN KEY (`exercise_id`) REFERENCES `exercise_category`(`exercise_id`),
    FOREIGN KEY (`owner_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);

INSERT INTO `stores` (`exercise_id`, `store_name`, `address`, `phone_number`, `description`, `favorite_count`, `created_at`, `opening_hours`, `logo_image`, `owner_id`, `coin`, `thumbnail_img`) VALUES
(1, '온더필라테스', '서울특별시 서초구 강남대로47길 23, 2층', '010-5643-3553', '기구 필라테스 센터', 175, CURRENT_TIMESTAMP, '7:00 AM - 10:00 PM', 'onthepilates_img', 'user1', 30, '1.jpg'),
(1, '라베나필라테스', '서울특별시 강남구 도산대로 217, 2층', '02-511-5225', '프리미엄 필라테스 센터', 25, CURRENT_TIMESTAMP, '7:00 AM - 10:00 PM', 'ravenapilates_img', 'user1', 10, '2.jpg'),
(1, '발리짐 앤 필라테스', '서울 강남구 논현로 614, 2층', '010-8234-6271', '필라테스 전문', 221, CURRENT_TIMESTAMP, '9:00 AM - 8:00 PM', 'valleygym_img', 'user1', 10, '3.png'),
(1, '옳음필라테스', '서울 강남구 테헤란로33길 42, B1층', '010-4868-9089', '옳음필라테스 전문', 137, CURRENT_TIMESTAMP, '9:00 AM - 8:00 PM', 'orumpilates_img', 'user1', 50, '4.PNG'),
(1, '필라테스퀸', '서울 강남구 논현로85길 37, 4층', '02-538-9996', '필라테스 퀸', 45, CURRENT_TIMESTAMP, '9:00 AM - 8:00 PM', 'pilatesqueen_img', 'user1', 10, '5.jpg'),
(2, '바디스페이스', '서울특별시 강남구 도산대로 123, 3층', '02-3456-7890', '헬스 전문 센터', 67, CURRENT_TIMESTAMP, '6:00 AM - 11:00 PM', 'bodyspace_img', 'user1', 30, '6.png'),
(2, '애니타임피트니스', '서울 강남구 논현로 406 1층', '02-6956-1320', '헬스 전문 센터', 237, CURRENT_TIMESTAMP, '0:00 AM - 23:59 PM', 'anytime_img', 'user1', 20, '7.jpg'),
(2, '스웻온서울', '서울특별시 강남구 삼성로 456, 5층', '02-9876-5432', '프리미엄 피트니스', 30, CURRENT_TIMESTAMP, '6:00 AM - 10:00 PM', 'sweatonseoul_img', 'user1', 50, '8.PNG'),
(3, '알로하복싱짐', '서울 강남구 학동로 235 영일회관 3층', '070-8881-1212', '복싱 전문 체육관', 61, CURRENT_TIMESTAMP, '8:00 AM - 11:00 PM', 'alohaboxing_img', 'user1', 40, '9.jpg'),
(3, '에이블복싱GYM', '서울특별시 강남구 논현로 403, B2층', '02-556-1002', '프리미엄 복싱 체육관', 142, CURRENT_TIMESTAMP, '9:00 AM - 8:00 PM', 'ableboxinggym_img', 'user1', 40, '10.PNG'),
(4, '크로스핏카노', '서울 강남구 언주로93길 32-8 지하 1층', '010-5307-7504', '크로스핏 전문 체육관', 79, CURRENT_TIMESTAMP, '7:00 AM - 9:00 PM', 'crossfitcano_img', 'user1', 30, '11.PNG'),
(4, '크로스핏 강남', '서울특별시 강남구 테헤란로 789, 2층', '02-555-6744', '프리미엄 크로스핏', 257, CURRENT_TIMESTAMP, '6:00 AM - 10:00 PM', 'crossfitgangnam_img', 'user1', 20, '12.jpg'),
(5, '온사이트클라이밍', '서울 강남구 테헤란로 505 싹타워 지하1층', '02-4567-8901', '클라이밍 전문', 126, CURRENT_TIMESTAMP, '10:00 AM - 11:00 PM', 'onsiteclimbing_img', 'user1', 10, '13.png'),
(5, '클라임이모션', '서울 강남구 논현로76길 27', '02-552-2532', '프리미엄 클라이밍', 22, CURRENT_TIMESTAMP, '10:00 AM - 11:00 PM', 'climbemotion_img', 'user1', 50, '14.jpg'),
(6, '역삼청소년수련관 수영장', '서울 강남구 논현로64길 7', '02-550-3600', '청소년 수영장', 90, CURRENT_TIMESTAMP, '9:00 AM - 8:00 PM', 'youthcenterpool_img', 'user1', 40,'15.jpg'),
(7, '경희대석사삼성태권도', '서울 강남구 선릉로132길 62 지하1층', '02-3442-6490', '태권도 전문 도장', 292, CURRENT_TIMESTAMP, '9:00 AM - 8:00 PM', 'kyungheedtaekwondo_img', 'user1', 50,'16.jpg'),
(7, '효태권도', '서울 강남구 학동로63길 8', '02-545-3556', '프리미엄 태권도', 49, CURRENT_TIMESTAMP, '1:00 PM - 9:00 PM', 'hyotaekwondo_img', 'user1', 10,'17.jpg'),
(8, '주짓수부라더스', '서울 강남구 삼성로 430 3층', '02-3446-3446', '주짓수 도장', 109, CURRENT_TIMESTAMP, '9:00 AM - 9:00 PM', 'jiujitsubrothers_img', 'user1', 50,'18.jpg'),
(8, '존프랭클주짓수', '서울특별시 강남구 선릉로 131길 4, 2층', '010-2582-5103', '프리미엄 주짓수', 102, CURRENT_TIMESTAMP, '8:00 AM - 10:00 PM', 'johnfrankl_img', 'user1', 50,'19.jpg'),
(9, '무니요가 역삼역점', '서울 강남구 논현로85길 5-12 6층 601호', '0507-1327-8398', '요가 전문 스튜디오', 16, CURRENT_TIMESTAMP, '9:00 AM - 8:00 PM', 'moonyyoga_img', 'user1', 30,'20.jpg'),
(9, '아메리카요가 삼성중앙역점', '서울 강남구 봉은사로 467 세창빌딩 4층', '02-516-5151', '프리미엄 요가', 37, CURRENT_TIMESTAMP, '9:00 AM - 8:00 PM', 'americayoga_img', 'user1', 40, '21.png');


CREATE TABLE IF NOT EXISTS `teacher` (
    `teacher_id` INT NOT NULL AUTO_INCREMENT,
    `store_id` INT NOT NULL,
    `name` varchar(30) NOT NULL,
    `comment` text NULL,
    PRIMARY KEY (`teacher_id`),
    FOREIGN KEY (`store_id`) REFERENCES `stores`(`store_id`) ON DELETE CASCADE
);

INSERT INTO `teacher` (`store_id`, `name`, `comment`) VALUES
(1, '김태형', '기구 필라테스 전문가로 다양한 레슨 경험 보유'),
(1, '이민정', '프리미엄 필라테스 강사로 개별 맞춤 수업 진행'),
(2, '박성훈', '헬스 트레이너로 전문적인 PT 제공'),
(2, '최지우', '피트니스 전문가로 다양한 운동 지도 경험'),
(3, '정우성', '복싱 코치로 체력 증진과 다이어트 효과 제공'),
(3, '김하늘', '복싱 트레이너로 개인 맞춤형 트레이닝'),
(4, '이준호', '크로스핏 전문가로 고강도 운동 지도'),
(4, '서예지', '크로스핏 강사로 체계적인 운동 프로그램 제공'),
(5, '한지민', '클라이밍 강사로 다양한 클라이밍 코스 지도'),
(5, '이동욱', '클라이밍 전문가로 초보자부터 고급자까지 지도'),
(6, '김지원', '수영 강사로 어린이와 청소년 수영 지도'),
(6, '정해인', '수영 전문가로 성인 수영 클래스 진행'),
(7, '박보검', '태권도 사범으로 체계적인 태권도 교육 제공'),
(7, '김태리', '태권도 트레이너로 기초부터 고급 기술 지도'),
(8, '김남길', '주짓수 코치로 다양한 주짓수 기술 지도'),
(8, '이연희', '주짓수 전문가로 맞춤형 주짓수 트레이닝'),
(10, '이동건', '복싱 강사로 다양한 복싱 프로그램 진행'),
(10, '이상민', '복싱 전문가로 프리미엄 복싱 수업 제공'),
(1, '류준열', '필라테스 전문가로 체계적인 운동 지도'),
(2, '김유정', '피트니스 전문가로 개인 맞춤형 프로그램 제공');


CREATE TABLE `favorites` (
`user_id` VARCHAR(20) NOT NULL,
`store_id` INT NOT NULL,
PRIMARY KEY (`store_id`, `user_id`),
FOREIGN KEY (`store_id`) REFERENCES `stores`(`store_id`) ON DELETE CASCADE,
FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);


INSERT INTO `favorites` (`user_id`, `store_id`) VALUES
('user1', 1),
('user1', 2),
('user1', 3),
('user2', 4),
('user2', 5),
('user2', 6),
('user1', 7),
('user1', 8),
('user2', 9),
('user2', 10);

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
(1, '온더필라테스 1개월', 1, 30, NULL, 49000),
(1, '온더필라테스 10회권', 0, NULL, 10, 200000),
(1, '온더필라테스 3개월', 1, 30, NULL, 130000),
(1, '온더필라테스 20회권', 0, NULL, 20, 390000),
(1, '온더필라테스 30회권', 0, NULL, 30, 580000),
(2, '라베나필라테스 1개월', 1, 30, NULL, 160000),
(2, '라베나필라테스 10회권', 0, NULL, 10, 200000),
(2, '라베나필라테스 20회권', 0, NULL, 20, 350000),
(3, '발리짐 앤 필라테스 1개월', 1, 30, NULL, 140000),
(3, '발리짐 앤 필라테스 15회권', 0, NULL, 15, 300000),
(4, '옳음필라테스 1개월', 1, 30, NULL, 130000),
(4, '옳음필라테스 5회권', 0, NULL, 5, 100000),
(5, '필라테스퀸 1개월', 1, 30, NULL, 120000),
(5, '필라테스퀸 10회권', 0, NULL, 10, 250000),
(6, '바디스페이스 1개월', 1, 30, NULL, 100000),
(6, '바디스페이스 20회권', 0, NULL, 20, 400000);



CREATE TABLE `tickets` (
    `ticket_id` INT NOT NULL AUTO_INCREMENT,
    `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 생성일
	`expiration_date` TIMESTAMP NULL, -- 만료일 -- 정기권이 아니면 NULL
    `total_quantity` INT NOT NULL DEFAULT 0, -- 초기횟수 -- 다회권이 아니면 0
	`remaining_quantity` INT NOT NULL DEFAULT 0, -- 잔여횟수 -- 다회권이 아니면 0
    `category` INT NOT NULL, -- 이용권 분류 0:다회권 / 1:정기권
    `user_id` VARCHAR(20) NOT NULL,
    `store_id` INT NOT NULL,
    `buy_ticket_id` INT NOT NULL,
    `ticket_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`ticket_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE, -- 사용자가 삭제되면 삭제..? 
    FOREIGN KEY (`store_id`) REFERENCES `stores`(`store_id`) ON DELETE CASCADE,
    FOREIGN KEY (`buy_ticket_id`) REFERENCES `sell_ticket`(`ticket_id`) ON DELETE CASCADE
 );


INSERT INTO `tickets` (`reg_date`, `expiration_date`, `total_quantity`, `remaining_quantity`, `category`, `user_id`, `store_id`, `buy_ticket_id`, `ticket_name`)
VALUES
('2024-05-23 10:16:16', '2024-06-21 15:00:00', '0', '0', '1', 'user1', '1', '1', '온더필라테스 1개월 정기권'),
('2024-05-23 10:17:32', NULL, '30', '30', '0', 'user1', '1', '5', '온더필라테스 30회권'),
('2024-05-23 10:18:22', NULL, '20', '20', '0', 'user1', '6', '16', '바디스페이스 20회권'),
('2024-05-23 10:19:02', NULL, '10', '10', '0', 'user2', '2', '7', '라베나필라테스 10회권');




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
(17, 9, '2024-05-24 11:00:00', 1, 10),
(17, 9, '2024-05-24 13:00:00', 3, 10),
(17, 9, '2024-05-24 15:00:00', 10, 10),
(18, 9, '2024-05-24 11:00:00', 5, 10),
(18, 9, '2024-05-24 20:00:00', 10, 10),
(1, 1, '2024-05-24 11:00:00', 10, 10),
(1, 1, '2024-05-24 13:30:00', 9, 10),
(1, 1, '2024-05-24 15:00:00', 10, 10),
(1, 1, '2024-05-24 17:30:00', 5, 10),
(1, 1, '2024-05-24 19:00:00', 7, 10),
(1, 1, '2024-05-24 21:00:00', 1, 10),
(2, 1, '2024-05-24 11:00:00', 0, 10),
(2, 1, '2024-05-24 13:30:00', 10, 10),
(2, 1, '2024-05-24 15:30:00', 0, 10),
(2, 1, '2024-05-24 17:30:00', 3, 10),
(1, 1, '2024-05-25 11:00:00', 1, 10),
(1, 1, '2024-05-25 13:30:00', 3, 10),
(1, 1, '2024-05-25 15:00:00', 2, 10),
(1, 1, '2024-05-25 17:30:00', 10, 10),
(1, 1, '2024-05-25 19:00:00', 3, 10),
(1, 1, '2024-05-25 21:00:00', 2, 10),
(2, 1, '2024-05-26 11:00:00', 2, 10),
(2, 1, '2024-05-26 13:30:00', 10, 10),
(2, 1, '2024-05-26 15:30:00', 7, 10),
(2, 1, '2024-05-26 17:30:00', 4, 10),
(1, 1, '2024-05-26 11:00:00', 3, 10),
(1, 1, '2024-05-26 13:30:00', 10, 10),
(1, 1, '2024-05-26 15:00:00', 1, 10),
(1, 1, '2024-05-26 17:30:00', 9, 10),
(1, 1, '2024-05-26 19:00:00', 7, 10),
(1, 1, '2024-05-26 21:00:00', 10, 10),
(2, 1, '2024-05-24 11:00:00', 0, 10),
(2, 1, '2024-05-24 13:30:00', 10, 10),
(2, 1, '2024-05-24 15:30:00', 0, 10),
(2, 1, '2024-05-24 17:30:00', 3, 10),
(3, 2, '2024-05-24 11:30:00', 4, 10),
(3, 2, '2024-05-24 13:30:00', 2, 10),
(4, 2, '2024-05-24 15:00:00', 5, 10),
(4, 2, '2024-05-24 20:30:00', 8, 10),
(4, 2, '2024-05-24 22:00:00', 9, 10),
(3, 2, '2024-05-25 11:30:00', 10, 10),
(3, 2, '2024-05-25 13:30:00', 4, 10),
(4, 2, '2024-05-25 15:00:00', 10, 10),
(4, 2, '2024-05-25 20:30:00', 2, 10),
(4, 2, '2024-05-25 22:00:00', 10, 10),
(11, 6, '2024-05-24 11:00:00', 3, 10),
(11, 6, '2024-05-24 13:30:00', 2, 10),
(11, 6, '2024-05-24 15:00:00', 7, 10),
(11, 6, '2024-05-24 17:30:00', 1, 10),
(11, 6, '2024-05-24 19:30:00', 10, 10),
(12, 6, '2024-05-24 19:00:00', 10, 10),
(12, 6, '2024-05-24 21:30:00', 4, 10),
(12, 6, '2024-05-24 23:00:00', 10, 10);



CREATE TABLE IF NOT EXISTS `reservation` (
	`reservation_id` INT NOT NULL AUTO_INCREMENT,
    `payment` INT, 
	`user_id` VARCHAR(20) NOT NULL,
	`store_id` INT NOT NULL,
	`lesson_id` INT NOT NULL,
	`ticket_id` INT NULL,
    `coin` INT,
    `date` TIMESTAMP NOT NULL,
	PRIMARY KEY (`reservation_id`),
	FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`store_id`) REFERENCES `stores`(`store_id`) ON DELETE CASCADE,
    FOREIGN KEY (`lesson_id`) REFERENCES `lesson`(`lesson_id`) ON DELETE CASCADE
	-- FOREIGN KEY (`ticket_id`) REFERENCES `tickets`(`ticket_id`) ON DELETE CASCADE
);


INSERT INTO `reservation` (`user_id`, `store_id`, `lesson_id`, `ticket_id`, `date`)
VALUES
('user1', '9', '2', '0', '2024-05-24 13:00:00'),
('user1', '6', '42', '0', '2024-05-24 13:30:00'),
('user1', '2', '32', '0', '2024-05-24 13:30:00'),
('user1', '1', '12', '0', '2024-05-25 13:30:00');


CREATE TABLE `refresh_token` (
	`refresh_token_id` VARCHAR(255) NOT NULL,
	`user_id` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`refresh_token_id`),
	FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);



CREATE TABLE `store_pictures` (
    `picture_id` VARCHAR(255) NOT NULL,
    `store_id` INT NOT NULL,
    `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`picture_id`),
    FOREIGN KEY (`store_id`) REFERENCES `stores`(`store_id`) ON DELETE CASCADE
 );
 
-- INSERT INTO `store_pictures` (`picture_id`, `store_id`, `create_date`)
-- VALUES




commit;
