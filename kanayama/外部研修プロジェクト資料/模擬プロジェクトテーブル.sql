create database primetechnoKanayama;
--使用データベース名


create table game_user (LOGIN_ID varchar(8) primary key,
PASSWD varchar(8) not null,IP_DATE datetime not null,
UPD_DATE timestamp not null);
--ユーザー情報テーブル

create table buy_currency (ID int primary key auto_increment,
LOGIN_ID varchar(8) not null,BUY_CHIP int not null,
IP_DATE datetime not null,UPD_DATE timestamp not null,
foreign key(LOGIN_ID) REFERENCES game_user(LOGIN_ID));
--課金情報テーブル

create table use_chip (ID int primary key auto_increment,
LOGIN_ID varchar(8) not null,CONSUME_CHIP int not null,
IP_DATE datetime not null,UPD_DATE timestamp not null,
foreign key(LOGIN_ID) REFERENCES game_user(LOGIN_ID));
--ガチャ消費テーブル


insert into game_user values ('1000','1000',now(),now());
--テストアカウント(haveChip)

insert into game_user values ('2000','2000',now(),now());
--テストアカウント(not haveChip)

insert into buy_currency (LOGIN_ID,BUY_CHIP,IP_DATE,UPD_DATE) values ('1000',40,now(),now())
--デバックSQL

http://localhost:8080/primetechnoKanayama/gsystem/login.jsp
--模擬プロ、ログイン画面

http://localhost:8080/primetechnoKanayama/gsystem/gMenu.jsp
--メニューページ（ログイン情報取得してない場合、ログインページ）


http://localhost:8080/primetechnoKanayama/gsystem/NewUserCheck.jsp
--ユーザー登録確認ページ（ログイン情報取得してない場合、ログインページ）

