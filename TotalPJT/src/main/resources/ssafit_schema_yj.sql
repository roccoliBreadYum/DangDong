CREATE DATABASE ssafit_db DEFAULT CHARACTER SET utf8mb4;

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
	`category` VARCHAR(40) NULL, -- 분류가 뭐지?
	`comment` TEXT NULL,
	`img` VARCHAR(40) NULL,
	PRIMARY KEY (`id`)
);


-- 여기부터는 참고


-- DROP DATABASE IF EXISTS ssafy_board;
-- CREATE DATABASE ssafy_board DEFAULT CHARACTER SET utf8mb4;

-- USE ssafy_board;

-- CREATE TABLE board (
-- 	id INT AUTO_INCREMENT,
--     writer VARCHAR(20) NOT NULL,
--     title VARCHAR(50) NOT NULL,
--     content TEXT,
--     view_cnt INT DEFAULT 0,
--     reg_date TIMESTAMP DEFAULT now(),
--     PRIMARY KEY(id)
-- );

-- CREATE TABLE `curriculum` (
-- 	`code` INT NOT NULL PRIMARY KEY,
--     `name` VARCHAR(40) NOT NULL
-- )ENGINE=InnoDB;


-- CREATE TABLE IF NOT EXISTS `users` (
--   `id` varchar(40) NOT NULL,
--   `password` varchar(40) NOT NULL,
--   `name` varchar(40) NOT NULL,
--   `curriculum_code` INT NOT NULL,
--   CONSTRAINT `curriculum_fk` FOREIGN KEY (`curriculum_code`) REFERENCES `curriculum`(`code`),
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB;

-- INSERT INTO board(title, writer, content) 
-- VALUES ("아직은 괜찮아1","양띵균","Spring Boot 아직은 괜찮아~~~"),
-- 	   ("아직은 괜찮아2","양띵균","Vue.js 곧 배운다. 커밍쑨~~"),
--        ("이제는 알아야해1", "양띵균", "");

-- INSERT INTO `curriculum` 
-- VALUES (100, 'Python'), (200, 'Java'), (300, 'Embedded'), (400, 'Mobile');

-- INSERT INTO users
-- VALUES ("ssafy", "1234", "김싸피", 200);


-- CREATE TABLE IF NOT EXISTS `files` (
--   `file_id` VARCHAR(40) NOT NULL PRIMARY KEY,
--   `file_name` VARCHAR(40) NOT NULL,
--   `board_id` INT NOT NULL,
--   CONSTRAINT `board_fk` FOREIGN KEY(`board_id`) REFERENCES `board`(`id`)
-- );

-- commit;

-- SELECT * FROM files;

-- 	INSERT INTO files (file_id, file_name, board_id) 
-- 		VALUES (`123`, `123`, `123`);



-- 여기까지는 참고







-- CREATE TABLE `stores` (
-- 	`가게id`	not null	NOT NULL,
-- 	`가게이름`	not null	NULL,
-- 	`주소`	not null	NULL,
-- 	`전화번호`	not null	NULL,
-- 	`소개글`	null	NULL,
-- 	`평점`	null	NULL,
-- 	`즐겨찾기 수`	default 0	NULL,
-- 	`생성일`	DEFAULT CURRENT_TIMESTAMP	NULL,
-- 	`휴면계정`	default false	NULL,
-- 	`push알림 동의`	default false	NULL,
-- 	`운영 시간`	null	NULL,
-- 	`휴무일`	null	NULL,
-- 	`로고이미지`	null	NULL,
-- 	`운동id`	not null	NOT NULL
-- );

-- CREATE TABLE `ticket_categorys` (
-- 	`이용권 id`	not null	NOT NULL,
-- 	`분류`	not null	NULL
-- );

-- CREATE TABLE `Bookmark` (
-- 	`회원id`	not null	NOT NULL,
-- 	`강사id`	not null	NOT NULL
-- );

-- CREATE TABLE `reviews` (
-- 	`리뷰 아이디`	not null	NOT NULL,
-- 	`내용`	null	NULL,
-- 	`평점`	not null	NULL,
-- 	`회원id`	not null	NOT NULL,
-- 	`가게id`	not null	NOT NULL
-- );

-- CREATE TABLE `teachers` (
-- 	`강사id`	not null	NOT NULL,
-- 	`가게id`	not null	NOT NULL,
-- 	`소개글`	null	NULL,
-- 	`이미지`	null	NULL
-- );

-- CREATE TABLE `now_tickets` (
-- 	`현황id`	not null	NOT NULL,
-- 	`생성일`	default now()	NULL,
-- 	`만료일`	default	NULL,
-- 	`잔여수량`	not null	NULL,
-- 	`회원id`	not null	NOT NULL,
-- 	`이용권 id`	not null	NOT NULL,
-- 	`가게id`	not null	NOT NULL
-- );

-- CREATE TABLE `now_reservation` (
-- 	`예약id`	not null	NOT NULL,
-- 	`상태`	VARCHAR(255)	NULL,
-- 	`가게id`	not null	NOT NULL,
-- 	`회원id`	not null	NOT NULL,
-- 	`예약관리 id`	auto	NOT NULL
-- );

-- CREATE TABLE `exercise_category` (
-- 	`운동id`	not null	NOT NULL,
-- 	`분류`	not null	NULL
-- );

CREATE TABLE `teacher_reservations` (
	`예약관리 id`	auto	NOT NULL,
	`날짜`	not null	NULL,
	`시작시간`	not null	NULL,
	`종료시간`	not null	NULL,
	`수용인원`	not null	NULL,
	`강사id`	not null	NOT NULL
);

CREATE TABLE `operating_hours` (
	`요일(0부터 7까지의 정수로 표현)`	not null	NOT NULL,
	`오픈 시간`	null	NULL,
	`마감 시간`	null	NULL,
	`휴무일`	default true	NULL,
	`가게아이디`	not null	NULL
);

ALTER TABLE `users` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
	`회원id`
);

ALTER TABLE `stores` ADD CONSTRAINT `PK_STORES` PRIMARY KEY (
	`가게id`
);

ALTER TABLE `ticket_categorys` ADD CONSTRAINT `PK_TICKET_CATEGORYS` PRIMARY KEY (
	`이용권 id`
);

ALTER TABLE `Bookmark` ADD CONSTRAINT `PK_BOOKMARK` PRIMARY KEY (
	`회원id`,
	`강사id`
);

ALTER TABLE `reviews` ADD CONSTRAINT `PK_REVIEWS` PRIMARY KEY (
	`리뷰 아이디`
);

ALTER TABLE `teachers` ADD CONSTRAINT `PK_TEACHERS` PRIMARY KEY (
	`강사id`
);

ALTER TABLE `now_tickets` ADD CONSTRAINT `PK_NOW_TICKETS` PRIMARY KEY (
	`현황id`
);

ALTER TABLE `now_reservation` ADD CONSTRAINT `PK_NOW_RESERVATION` PRIMARY KEY (
	`예약id`
);

ALTER TABLE `exercise_category` ADD CONSTRAINT `PK_EXERCISE_CATEGORY` PRIMARY KEY (
	`운동id`
);

ALTER TABLE `teacher_reservations` ADD CONSTRAINT `PK_TEACHER_RESERVATIONS` PRIMARY KEY (
	`예약관리 id`
);

ALTER TABLE `operating_hours` ADD CONSTRAINT `PK_OPERATING_HOURS` PRIMARY KEY (
	`요일(0부터 7까지의 정수로 표현)`
);

ALTER TABLE `Bookmark` ADD CONSTRAINT `FK_users_TO_Bookmark_1` FOREIGN KEY (
	`회원id`
)
REFERENCES `users` (
	`회원id`
);

ALTER TABLE `Bookmark` ADD CONSTRAINT `FK_teachers_TO_Bookmark_1` FOREIGN KEY (
	`강사id`
)
REFERENCES `teachers` (
	`강사id`
);

