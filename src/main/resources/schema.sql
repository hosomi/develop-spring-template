DROP TABLE IF EXISTS TODO;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS GOODS;
DROP TABLE IF EXISTS COMPANY;
DROP TABLE IF EXISTS USER_DPT;
DROP TABLE IF EXISTS DPT;

/*Todo テーブル作成*/
CREATE TABLE TODO (
    id       IDENTITY,
    title    TEXT      NOT NULL,
    details  TEXT,
    finished BOOLEAN   NOT NULL
);

/*User テーブル作成、認証にも利用。*/
CREATE TABLE USER (
    id IDENTITY,
    loginuserid VARCHAR(10) NOT NULL,
    password VARCHAR(60) NOT NULL,
    screenname VARCHAR(100)
);

/*GOODS テーブル作成。*/
CREATE TABLE GOODS (
    id IDENTITY,
    code VARCHAR(10)  NOT NULL,
    name VARCHAR(100) NOT NULL,
    kana VARCHAR(100) NOT NULL,
    note VARCHAR(255) 
);

/** 部門テーブル作成。*/
CREATE TABLE DPT (
    cddpt      VARCHAR(12) NOT NULL, -- 部門コード
    cdupperdpt VARCHAR(12),          -- 上位部門コード
    nmdpt      VARCHAR(50) NOT NULL, -- 部門名
    nmshortdpt VARCHAR(25) NOT NULL, -- 部門名略称
    showodr    NUMERIC(5),           -- 表示順序
    dtavlst    CHAR(8)     NOT NULL, -- 適用開始日
    dtavled    CHAR(8)     NOT NULL, -- 適用終了日
    dtupdate   TIMESTAMP   NOT NULL, -- 更新/登録日付
);

/** ユーザ部門テーブル作成。*/
CREATE TABLE USER_DPT (
    loginuserid VARCHAR(10) NOT NULL,
    cddpt       VARCHAR(12) NOT NULL, -- 部門コード
    main        BOOLEAN     NOT NULL, -- 主務(true)/兼務(false)
    dtupdate    TIMESTAMP   NOT NULL, -- 更新/登録日付
    PRIMARY KEY(loginuserid, cddpt)
);

/** COMPANY テーブル作成*/
CREATE TABLE COMPANY (
    id IDENTITY,
    name VARCHAR(100) NOT NULL,
    kana VARCHAR(200),
    postal VARCHAR(10),
    address VARCHAR(20),
    tel VARCHAR(20),
    fax VARCHAR(20),
    enable boolean
);

insert into DPT (cddpt, cdupperdpt,nmdpt,nmshortdpt,showodr,dtavlst, dtavled, dtupdate ) values ('0000', null, '株式会社○○○','○○○',1,'19000101', '99991231',SYSDATE);
insert into DPT (cddpt, cdupperdpt,nmdpt,nmshortdpt,showodr,dtavlst, dtavled, dtupdate ) values ('1000', '0000', '人事総務部','総務',2,'19000101', '99991231',SYSDATE);
insert into DPT (cddpt, cdupperdpt,nmdpt,nmshortdpt,showodr,dtavlst, dtavled, dtupdate ) values ('1100', '1000', '人事課','人事',3,'19000101', '99991231',SYSDATE);
insert into DPT (cddpt, cdupperdpt,nmdpt,nmshortdpt,showodr,dtavlst, dtavled, dtupdate ) values ('1200', '1000', '総務課','総務',4,'19000101', '99991231',SYSDATE);
insert into DPT (cddpt, cdupperdpt,nmdpt,nmshortdpt,showodr,dtavlst, dtavled, dtupdate ) values ('1300', '1000', '教育課','教育',5,'19000101', '99991231',SYSDATE);
insert into DPT (cddpt, cdupperdpt,nmdpt,nmshortdpt,showodr,dtavlst, dtavled, dtupdate ) values ('2000', '0000', '経理部','経理',6,'19000101', '99991231',SYSDATE);
insert into DPT (cddpt, cdupperdpt,nmdpt,nmshortdpt,showodr,dtavlst, dtavled, dtupdate ) values ('2100', '2000', '経理課','経理',7,'19000101', '99991231',SYSDATE);
insert into DPT (cddpt, cdupperdpt,nmdpt,nmshortdpt,showodr,dtavlst, dtavled, dtupdate ) values ('3000', '0000', '開発企画部','開発企画',8,'19000101', '99991231',SYSDATE);
insert into DPT (cddpt, cdupperdpt,nmdpt,nmshortdpt,showodr,dtavlst, dtavled, dtupdate ) values ('3100', '3000', '企画課','企画',9,'19000101', '99991231',SYSDATE);
insert into DPT (cddpt, cdupperdpt,nmdpt,nmshortdpt,showodr,dtavlst, dtavled, dtupdate ) values ('3200', '3000', '開発課','開発',10,'19000101', '99991231',SYSDATE);
insert into DPT (cddpt, cdupperdpt,nmdpt,nmshortdpt,showodr,dtavlst, dtavled, dtupdate ) values ('3500', '3000', 'システム課','システム',11,'19000101', '99991231',SYSDATE);

insert into USER_DPT (loginuserid,cddpt,main,dtupdate) values ('test', '1000', true, sysdate);
insert into USER_DPT (loginuserid,cddpt,main,dtupdate) values ('test', '3000', false, sysdate);

insert into company (name,kana,postal,address,tel,fax,enable) values ('株式会社○○キューブ','カブシキカイシャマルマルキューブ','105-0002','東京都港区愛宕◯丁目◯番◯号','03-0000-0001','03-0000-1001',true);
insert into company (name,kana,postal,address,tel,fax,enable) values ('メディカル○○株式会社','メディカルマルマルカブシキカイシャ','105-0012','東京都港区芝大門◯丁目◯番◯号','03-0000-0002','03-0000-1002',true);
insert into company (name,kana,postal,address,tel,fax,enable) values ('無効○○株式会社','無効マルマルカブシキカイシャ','105-0013','東京都港区芝大門◯丁目◯番◯号','03-0000-0003','03-0000-1003',false);

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


INSERT INTO TODO (title,details,finished) VALUES ('TODO1','TODO1内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO2','TODO2内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO3','TODO3内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO4','TODO4内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO5','TODO5内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO6','TODO6内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO7','TODO7内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO8','TODO8内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO9','TODO9内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO10','TODO10内容',true);
INSERT INTO TODO (title,details,finished) VALUES ('TODO11','TODO11内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO12','TODO12内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO13','TODO13内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO14','TODO14内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO15','TODO15内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO16','TODO16内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO17','TODO17内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO18','TODO18内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO19','TODO19内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO20','TODO20内容',true);
INSERT INTO TODO (title,details,finished) VALUES ('TODO21','TODO21内容',true);
INSERT INTO TODO (title,details,finished) VALUES ('TODO22','TODO22内容',true);
INSERT INTO TODO (title,details,finished) VALUES ('TODO23','TODO23内容',true);
INSERT INTO TODO (title,details,finished) VALUES ('TODO24','TODO24内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO25','TODO25内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO26','TODO26内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO27','TODO27内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO28','TODO28内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO29','TODO29内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO30','TODO30内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO31','TODO31内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO32','TODO32内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO33','TODO33内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO34','TODO34内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO35','TODO35内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO36','TODO36内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO37','TODO37内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO38','TODO38内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO39','TODO39内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO40','TODO40内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO41','TODO41内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO42','TODO42内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO43','TODO43内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO44','TODO44内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO45','TODO45内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO46','TODO46内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO47','TODO47内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO48','TODO48内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO49','TODO49内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO50','TODO50内容',false);
INSERT INTO TODO (title,details,finished) VALUES ('TODO51','TODO51内容',false);

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

