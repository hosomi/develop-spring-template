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
    PASSWORD VARCHAR(32) NOT NULL,
);


INSERT INTO USER (LOGINUSERID,PASSWORD) VALUES ('test','testpass');
INSERT INTO USER (LOGINUSERID,PASSWORD) VALUES ('test2','test2pass');

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

