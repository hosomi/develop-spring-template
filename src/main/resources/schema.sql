DROP TABLE IF EXISTS TODO;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS GOODS;



/*Todo テーブル作成*/
CREATE TABLE TODO (
    id IDENTITY
    ,title TEXT NOT NULL
    ,details TEXT
    ,finished BOOLEAN NOT NULL
);

/*User テーブル作成、認証にも利用。*/
CREATE TABLE USER (
    id IDENTITY,
	LOGINUSERID VARCHAR(10) NOT NULL,
    PASSWORD VARCHAR(60) NOT NULL,
);

/*GOODS テーブル作成。*/
CREATE TABLE GOODS (
    id IDENTITY,
    code VARCHAR(10) NOT NULL,
    name VARCHAR(100) NOT NULL,
    kana VARCHAR(100) NOT NULL,
    note VARCHAR(255) 
);

/*
INSERT INTO USER (LOGINUSERID,PASSWORD) VALUES ('test','testpass');
INSERT INTO USER (LOGINUSERID,PASSWORD) VALUES ('test2','test2pass');
INSERT INTO USER (LOGINUSERID,PASSWORD) VALUES ('test3','test3pass');
INSERT INTO USER (LOGINUSERID,PASSWORD) VALUES ('test4','test4pass');
INSERT INTO USER (LOGINUSERID,PASSWORD) VALUES ('test5','test5pass');
INSERT INTO USER (LOGINUSERID,PASSWORD) VALUES ('test6','test6pass');
INSERT INTO USER (LOGINUSERID,PASSWORD) VALUES ('test7','test7pass');
INSERT INTO USER (LOGINUSERID,PASSWORD) VALUES ('test8','test8pass');
INSERT INTO USER (LOGINUSERID,PASSWORD) VALUES ('test9','test9pass');
INSERT INTO USER (LOGINUSERID,PASSWORD) VALUES ('test10','test10pass');
*/

INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000001','商品名１','ショウヒンメイイチ', '商品名１の備考など');
INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000002','商品名２','ショウヒンメイニ', '商品名２の備考など');
INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000003','商品名３','ショウヒンメイサン', '商品名３の備考など');
INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000004','商品名４','ショウヒンメイヨン', '商品名４の備考など');
INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000005','商品名５','ショウヒンメイゴ', '商品名５の備考など');
INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000006','商品名６','ショウヒンメイロク', '商品名６の備考など');
INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000007','商品名７','ショウヒンメイナナ', '商品名７の備考など');
--INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000008','商品名８','ショウヒンメイハチ', '商品名８の備考など');
--INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000009','商品名９','ショウヒンメイキュウ', '商品名９の備考など');
--INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000010','商品名１０','ショウヒンメイジュウ', '商品名１０の備考など');
--INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000011','商品名１１','ショウヒンメイジュウイチ', '商品名１１の備考など');
--INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000012','商品名１２','ショウヒンメイジュウニ', '商品名１２の備考など');
--INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000013','商品名１３','ショウヒンメイジュウサン', '商品名１３の備考など');
--INSERT INTO GOODS (code,name,kana,note) VALUES ('0000000014','商品名１４','ショウヒンメイジュウヨン', '商品名１４の備考など');
INSERT INTO GOODS (code,name,kana,note) VALUES ('1000000000','新しい商品１','アタラシイショウヒンイチ', '新しい商品１の備考など');
INSERT INTO GOODS (code,name,kana,note) VALUES ('2000000000','新しい商品２','アタラシイショウヒンニ', '新しい商品２の備考など');

/*
 Spring security デフォルトの認証テーブル

CREATE TABLE USERS (
    USERNAME VARCHAR(10) NOT NULL,
    PASSWORD VARCHAR(32) NOT NULL,
    ENABLED SMALLINT,
    PRIMARY KEY(USERNAME)
);

CREATE TABLE AUTHORITIES (
    USERNAME VARCHAR(10) NOT NULL,
    AUTHORITY VARCHAR(10) NOT NULL,
    FOREIGN KEY(USERNAME) REFERENCES USERS
);

INSERT INTO USERS (USERNAME,PASSWORD,ENABLED) VALUES ('testadmin','password1',1);
INSERT INTO USERS (USERNAME,PASSWORD,ENABLED) VALUES ('testuser','password2',1);

INSERT INTO AUTHORITIES (USERNAME,AUTHORITY) VALUES ('testadmin','ROLE_ADMIN');
INSERT INTO AUTHORITIES (USERNAME,AUTHORITY) VALUES ('testuser','ROLE_GUEST');
*/

