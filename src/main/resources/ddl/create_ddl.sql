
 
 /* 시험 정보 */
 drop table `e_test_info`;
 
 CREATE TABLE `e_test_info` (
  `TEST_NO` int DEFAULT NULL,
  `TEST_NAME` varchar(20) DEFAULT NULL,
  `RGST_DT` datetime DEFAULT null,
  `RGST_EMPL_NO` int DEFAULT NULL,
  `UPDT_DT` datetime DEFAULT null,
  `UPDT_EMPL_NO` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO exam.e_test_info (TEST_NO, TEST_NAME, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES(1, '2020년 1차', now(), 1, now(), 1);
INSERT INTO exam.e_test_info (TEST_NO, TEST_NAME, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES(2, '2020년 2차', now(), 1, now(), 1);
INSERT INTO exam.e_test_info (TEST_NO, TEST_NAME, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES(3, '2020년 3차', now(), 1, now(), 1);
INSERT INTO exam.e_test_info (TEST_NO, TEST_NAME, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES(4, '2020년 4차', now(), 1, now(), 1);
INSERT INTO exam.e_test_info (TEST_NO, TEST_NAME, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES(5, '2021년 1차', now(), 1, now(), 1);
INSERT INTO exam.e_test_info (TEST_NO, TEST_NAME, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES(6, '2021년 2차', now(), 1, now(), 1);
INSERT INTO exam.e_test_info (TEST_NO, TEST_NAME, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES(7, '2021년 3차', now(), 1, now(), 1);
INSERT INTO exam.e_test_info (TEST_NO, TEST_NAME, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES(8, '2021년 4차', now(), 1, now(), 1);

/* 과목 정보 */
 drop table `e_subject_info`;
 
 CREATE TABLE `e_subject_info` (
  `SUBJECT_CODE` varchar(4) DEFAULT NULL,
  `SUBJECT_NAME` varchar(20) DEFAULT NULL,
  `RGST_DT` datetime DEFAULT null,
  `RGST_EMPL_NO` int DEFAULT NULL,
  `UPDT_DT` datetime DEFAULT null,
  `UPDT_EMPL_NO` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO exam.e_subject_info (SUBJECT_CODE, SUBJECT_NAME, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('KOR', '국어', now(), 1, now(), 1);
INSERT INTO exam.e_subject_info (SUBJECT_CODE, SUBJECT_NAME, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('ENG', '영어', now(), 1, now(), 1);
INSERT INTO exam.e_subject_info (SUBJECT_CODE, SUBJECT_NAME, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('MAT', '수학', now(), 1, now(), 1);
INSERT INTO exam.e_subject_info (SUBJECT_CODE, SUBJECT_NAME, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('ART', '미술', now(), 1, now(), 1);


/* 직원 정보 */
 drop table `e_empl_info`;
 
 CREATE TABLE `e_empl_info` (
  `EMPL_NO` int DEFAULT NULL,
  `EMPL_ID` varchar(20) DEFAULT NULL,
  `EMPL_NM` varchar(20) DEFAULT NULL,
  `BIRTH_DATE` varchar(8) DEFAULT NULL,
  `GENDER` varchar(1) DEFAULT NULL,
  `PHONE_NO` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PWD` varchar(100) DEFAULT NULL,
  `RGST_DT` datetime DEFAULT null,
  `RGST_EMPL_NO` int DEFAULT NULL,
  `UPDT_DT` datetime DEFAULT null,
  `UPDT_EMPL_NO` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO exam.e_empl_info (EMPL_NO, EMPL_ID, EMPL_NM, PWD, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES(1, 'ADMIN', 'ADMIN', '1234', now(), 1, now(), 1);



/* 사용자 정보 */
drop table e_user_info;

CREATE TABLE `e_user_info` (
  `USER_NO` bigint NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(20) NOT NULL,
  `USER_NM` varchar(20) DEFAULT NULL,
  `BIRTH_DATE` varchar(8) DEFAULT NULL,
  `GENDER` varchar(1) DEFAULT NULL,
  `PHONE_NO` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PWD` varchar(100) DEFAULT NULL,
  `RGST_DT` datetime DEFAULT NULL,
  `RGST_EMPL_NO` int DEFAULT NULL,
  `UPDT_DT` datetime DEFAULT NULL,
  `UPDT_EMPL_NO` int DEFAULT null,
   PRIMARY KEY (`USER_NO`),
   KEY `fk_idx_tslndsv02_tslndsv01` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



delete from exam.e_user_info;

INSERT INTO exam.e_user_info (USER_ID, USER_NM, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('USER1', 'USER1',  now(), 1, now(), 1);
INSERT INTO exam.e_user_info (USER_ID, USER_NM, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('USER2', 'USER2',  now(), 1, now(), 1);
INSERT INTO exam.e_user_info (USER_ID, USER_NM, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('USER3', 'USER3',  now(), 1, now(), 1);
INSERT INTO exam.e_user_info (USER_ID, USER_NM, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('USER4', 'USER4',  now(), 1, now(), 1);
INSERT INTO exam.e_user_info (USER_ID, USER_NM, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('USER5', 'USER5',  now(), 1, now(), 1);
INSERT INTO exam.e_user_info (USER_ID, USER_NM, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('USER6', 'USER6',  now(), 1, now(), 1);
INSERT INTO exam.e_user_info (USER_ID, USER_NM, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('USER7', 'USER7',  now(), 1, now(), 1);
INSERT INTO exam.e_user_info (USER_ID, USER_NM, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('USER8', 'USER8',  now(), 1, now(), 1);
INSERT INTO exam.e_user_info (USER_ID, USER_NM, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('USER9', 'USER9',  now(), 1, now(), 1);
INSERT INTO exam.e_user_info (USER_ID, USER_NM, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO) VALUES('USER10', 'USER10',  now(), 1, now(), 1);



/* 시험 성적 정보 */
drop table `e_test_score_info`;

CREATE TABLE `e_test_score_info` (
  `TEST_NO` int DEFAULT NULL,
  `SUBJECT_CODE` varchar(4) DEFAULT NULL,
  `USER_NO` int DEFAULT NULL,
  `SCORE` int DEFAULT null,
  `RGST_DT` datetime DEFAULT null,
  `RGST_EMPL_NO` int DEFAULT NULL,
  `UPDT_DT` datetime DEFAULT null,
  `UPDT_EMPL_NO` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





delete from exam.e_test_score_info ;

INSERT INTO exam.e_test_score_info (TEST_NO, SUBJECT_CODE, USER_NO, SCORE, RGST_DT, RGST_EMPL_NO, UPDT_DT, UPDT_EMPL_NO)
select aa.test_no, bb.SUBJECT_CODE, cc.user_no, floor(10 + rand(100) * 90) as score
, now() as RGST_DT
, 1 as RGST_EMPL_NO
, now() as UPDT_DT
, 1 as UPDT_EMPL_NO
from e_test_info aa
inner join e_subject_info bb 
   on 1=1
inner join e_user_info cc
   on 1=1  
order by aa.test_no, cc.user_no, bb.SUBJECT_CODE
;



