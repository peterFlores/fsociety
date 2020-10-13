CREATE DATABASE FSOCIETY;

CREATE TABLE USERS (
    USER_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    USER_NAME VARCHAR(50) ,
    USER_NICKNAME VARCHAR(50) NOT NULL,
    USER_MAIL VARCHAR(80) NOT NULL,
    USER_PASSWORD VARCHAR(255) NOT NULL,
    USER_IMAGE_PATH VARCHAR(255) ,
    USER_BIRTHDATE DATE,
    USER_GENDER VARCHAR(2),
    USER_ROLE VARCHAR(10) DEFAULT 'SOCIAL' NOT NULL,
    USER_CREATED_AT TIMESTAMP  NOT NULL,
    USER_STATUS VARCHAR(10) DEFAULT 'ONLINE' NOT NULL COMMENT 'BANEANDO, ONLINE, OFFLINE'
);


CREATE TABLE POST (
    POST_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    POST_CONTENT VARCHAR(500) NOT NULL,
    POST_IMAGE_PATH VARCHAR(255),
    POST_CREATED_AT TIMESTAMP NOT NULL,
    USER_ID INT NOT NULL,
    POST_COMMENT INT,
    FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID) ON DELETE  CASCADE,
    FOREIGN KEY (POST_COMMENT) REFERENCES POST (POST_ID)  ON DELETE  CASCADE 
);

CREATE TABLE REACTION (
    USER_ID INT NOT NULL,
    TYPE_REACTION VARCHAR(10) NOT NULL COMMENT 'LIKE, DISLIKE',
    POST_ID INT NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID),
    FOREIGN KEY (POST_ID) REFERENCES POST (POST_ID) 
);

CREATE TABLE CHAT (
    USER_ONE INT NOT NULL,
    USER_TWO INT NOT NULL,
    CHAT_STATUS VARCHAR(10) NOT NULL COMMENT 'REQUEST, ACEPTED, CLOSED'
);

CREATE TABLE VISIT (
    USER_ID INT NOT NULL,
    VISIT_DATE DATETIME NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID) 
);

CREATE TABLE PAGE_VISITORS (
    VISIT_DATE DATETIME NOT NULL,
    IP_VISITOR VARCHAR(20) NOT NULL,
    PAGE_DESCRIPTION VARCHAR(20) NOT NULL
);



--PRODEDURES USER

--CREATE
DELIMITER $$

CREATE PROCEDURE create_user(PUSER_NICKNAME VARCHAR(50), PUSER_MAIL VARCHAR(80), PUSER_PASSWORD VARCHAR(255))

BEGIN
	INSERT INTO USERS (USER_NICKNAME,USER_MAIL,USER_PASSWORD,USER_CREATED_AT) VALUES (PUSER_NICKNAME,PUSER_MAIL,PUSER_PASSWORD,NOW());
END$$

DELIMITER ;

CALL create_user('Celso','Celso_ROJAS@GMAIL','TITITI');


--UPDATE
SET @@SESSION.sql_mode='NO_ZERO_DATE,NO_ZERO_IN_DATE';

DELIMITER $$

CREATE PROCEDURE update_user(PUSER_NAME VARCHAR(50),PUSER_NICKNAME VARCHAR(50),PUSER_BIRTHDATE DATE,PUSER_GENDER VARCHAR(2),PUSER_ID INT)

BEGIN
    UPDATE USERS
    SET USER_NAME = PUSER_NAME, USER_NICKNAME= PUSER_NICKNAME, USER_BIRTHDATE = PUSER_BIRTHDATE, USER_GENDER = PUSER_GENDER
	WHERE USER_ID = PUSER_ID;

END$$

DELIMITER ;

CALL update_user('PAPS','PAN',STR_TO_DATE('24-05-2020', '%d-%M-%Y'),'MALE',1);

--DELETE

DELIMITER $$

CREATE PROCEDURE delete_user(PUSER_ID INT)

BEGIN
    DELETE FROM USERS
	WHERE USER_ID = PUSER_ID;

END$$

DELIMITER ;

CALL delete_user(1);


--Profile Picture

DELIMITER $$
CREATE PROCEDURE UPDATE_PICTURE_USER (PUSER_ID INT,  PUSER_IMAGE_PATH VARCHAR(255))
BEGIN
UPDATE USERS
  SET 
  USER_IMAGE_PATH = PUSER_IMAGE_PATH
  WHERE
  USER_ID = PUSER_ID;
END$$
DELIMITER ;

CALL UPDATE_PICTURE_USER(2,'FER');



--TOP 5VISIT

DELIMITER $$
CREATE PROCEDURE TOP_5_VISIT()
    BEGIN 
    SELECT U.USER_ID, U.USER_NAME, U.USER_IMAGE_PATH,U.USER_CREATED_AT,U.USER_STATUS, COUNT(V.USER_ID) AS SUMA
    FROM USERS U 
    INNER JOIN VISIT V ON U.USER_ID = V.USER_ID
    WHERE ( date(V.VISIT_DATE) = curdate()) 
    GROUP BY U.USER_ID, U.USER_NAME, U.USER_IMAGE_PATH ORDER BY SUMA DESC LIMIT 5; 
    END
     $$

--Alter Table VISITORS
ALTER TABLE visit ADD COLUMN USER_ID_VISITOR int(11);

ALTER TABLE VISIT ADD CONSTRAINT FK_VISITOR FOREIGN KEY (USER_ID_VISITOR) REFERENCES USERS(USER_ID);

--Procedure to REGISTER_VISIT using last 5 min validation :)
DELIMITER $$
CREATE PROCEDURE REGISTER_VISIT (IN PUSER_ID int, IN PUSER_ID_VISITOR int)
CONTAINS SQL
    BEGIN 

     DECLARE LAST_5MIN_VISITS INT DEFAULT 0;

     SELECT COUNT(*) INTO LAST_5MIN_VISITS
     FROM VISIT WHERE TIMESTAMPDIFF(MINUTE, VISIT_DATE, SYSDATE()) < 5 
     AND USER_ID_VISITOR = PUSER_ID_VISITOR AND PUSER_ID =  PUSER_ID;

    IF LAST_5MIN_VISITS = 0 THEN
      INSERT INTO VISIT  VALUES (PUSER_ID, SYSDATE(), PUSER_ID_VISITOR);
      SELECT 1;
    ELSE
      SELECT 0;
    END IF;
    END$$

DELIMITER ;

--CREATE POST

DELIMITER $$
CREATE PROCEDURE CREATE_POST(PPOST_CONTENT VARCHAR(500),PPOST_IMAGE_PATH VARCHAR(255),PUSER_ID INT)
    BEGIN
	INSERT INTO POST (POST_CONTENT,POST_IMAGE_PATH,USER_ID,POST_CREATED_AT) VALUES (PPOST_CONTENT,PPOST_IMAGE_PATH,PUSER_ID,NOW());
    END
     $$
     
CALL CREATE_POST('PRUEBAPOST PRUEBAPOST','TESTPATH',4);
     

--DELETE POST

DELIMITER $$

CREATE PROCEDURE DELETE_POST(PPOST_ID INT)

BEGIN
    DELETE FROM POST
	WHERE POST_ID = PPOST_ID;

END$$

DELIMITER ;


--CREATE PAGE VISITOR
DELIMITER $$

CREATE PROCEDURE create_visitpage(PIP_VISITOR VARCHAR(20), PPAGE_DESCRIPTION VARCHAR(20))

BEGIN
	INSERT INTO PAGE_VISITORS (VISIT_DATE, IP_VISITOR, PAGE_DESCRIPTION) VALUES (SYSDATE(), PIP_VISITOR,PPAGE_DESCRIPTION);
END$$

DELIMITER ;