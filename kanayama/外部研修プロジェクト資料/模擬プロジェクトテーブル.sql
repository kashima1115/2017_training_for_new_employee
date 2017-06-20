create database primetechnoKanayama;
--�g�p�f�[�^�x�[�X��


create table game_user (LOGIN_ID varchar(8) primary key,
PASSWD varchar(8) not null,IP_DATE datetime not null,
UPD_DATE timestamp not null);
--���[�U�[���e�[�u��

create table buy_currency (ID int primary key auto_increment,
LOGIN_ID varchar(8) not null,BUY_CHIP int not null,
IP_DATE datetime not null,UPD_DATE timestamp not null,
foreign key(LOGIN_ID) REFERENCES game_user(LOGIN_ID));
--�ۋ����e�[�u��

create table use_chip (ID int primary key auto_increment,
LOGIN_ID varchar(8) not null,CONSUME_CHIP int not null,
IP_DATE datetime not null,UPD_DATE timestamp not null,
foreign key(LOGIN_ID) REFERENCES game_user(LOGIN_ID));
--�K�`������e�[�u��


insert into game_user values ('1000','1000',now(),now());
--�e�X�g�A�J�E���g(haveChip)

insert into game_user values ('2000','2000',now(),now());
--�e�X�g�A�J�E���g(not haveChip)

insert into buy_currency (LOGIN_ID,BUY_CHIP,IP_DATE,UPD_DATE) values ('1000',40,now(),now())
--�f�o�b�NSQL

http://localhost:8080/primetechnoKanayama/gsystem/login.jsp
--�͋[�v���A���O�C�����

http://localhost:8080/primetechnoKanayama/gsystem/gMenu.jsp
--���j���[�y�[�W�i���O�C�����擾���ĂȂ��ꍇ�A���O�C���y�[�W�j


http://localhost:8080/primetechnoKanayama/gsystem/NewUserCheck.jsp
--���[�U�[�o�^�m�F�y�[�W�i���O�C�����擾���ĂȂ��ꍇ�A���O�C���y�[�W�j

