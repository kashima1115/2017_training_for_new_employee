
cd C:\xampp\mysql\bin

mysql -u root

create database primetechnoArahari;

use primetechnoArahari;

�X�^�[�g���URL
http://localhost:8080/primetechnoArahari/mogipuro/start.jsp

���[�U���e�[�u��
create table user(user_id varchar(50) primary key not null
, pass varchar(50) not null, inp_date date not null, upd_date date not null);

�A���P�[�g���ƏI�������e�[�u��
create table questionnaire(ank_id int primary key auto_increment not null
, user_id varchar(50) not null, ank_name varchar(50) not null, ank_end date not null
, inp_date date not null, upd_date date not null, FOREIGN KEY(user_id) REFERENCES user(user_id));

������e�e�[�u��
create table quest(quest_id int primary key auto_increment not null
, ank_id int not null, question varchar(50) 
, inp_date date not null, upd_date date not null, foreign key(ank_id) references questionnaire(ank_id));

�I�����e�[�u��
create table answer(select_id int primary key auto_increment not null
, quest_id int not null, choices varchar(50) , answered int DEFAULT 0
, inp_date date not null, upd_date date not null, foreign key(quest_id) references quest(quest_id));

�|�C���g���e�[�u��
create table point(point_id int primary key auto_increment not null
, user_id varchar(50) not null, point int default 3,type int
, inp_date date not null, upd_date date not null , foreign key(user_id) references user(user_id));

�񓚎Ґ��e�[�u��
create table co(co_id int primary key auto_increment not null
, ank_id int not null, kaitou int default 0
, inp_date date not null, upd_date date not null, foreign key(ank_id) references questionnaire(ank_id));

�񓚏��e�[�u��
create table kaijo(kaijo_id int primary key auto_increment not null
, user_id varchar(50) not null, ank_name varchar(50) not null
, kaisumi int, inp_date date not null, upd_date date not null);


INSERT INTO answer(quest_id, choices, inp_date, upd_date) VALUES('1', '�킩��Ȃ�', current_date, now());

select * from user;
select * from point;
select * from questionnaire;
select * from quest;
select * from answer;
select * from co;
select * from kaijo;

select * from kaijo where ank_name = 'asas';

INSERT INTO questionnaire(user_id, ank_name, ank_end, inp_date, upd_date)VALUES('a', 'test', '2017-05-24', current_date, now());

DROP TABLE answer;

update point set point = point + 1 where user_id = 'q';

delete from user where user_id = 'a';

INSERT INTO point(user_id, inp_date, upd_date) VALUES('a', current_date, now());

		
DELETE FROM questionnaire WHERE ank_name = 'a';


DELETE FROM quest WHERE quest_id = '45' or quest_id = '44';

update answer set choices='76-100�_' where select_id= '228';

SELECT * FROM questionnaire where ank_end >= current_date ORDER BY ank_end;

�������@�\

�쐬�@�\�Ń`�F�b�N�{�b�N�X�̂ق��Ƀ��W�I�{�^���̑I��
���ʉ{���@�\�ŃO���t����p�����\���@JFree�̎g����
���⍀�ڂ̒ǉ���I�����̒ǉ�
�񓚕����Ƀ`�F�b�N�⃉�W�I�̂ق��Ɏ��R�L�q���̑I��
�����̉񓚂����A���P�[�g���ʂ������̉񓚂������̂������\�������悤�ɂ���i���͂ق��̃��[�U�̉񓚌��ʂ����킹�ĕ\������Ă���j




���b�Z���W���[�@�\
�O�����C�v���W�F�N�g�C��
�X�P�W���[���m�F�@���[���m�F�@���N�f�f����7��3��
���K�@���[����M�ݒ�@�v���W�F�N�g�C���ӏ��w�E

�A���P�[�g�쐬�����O�C�����ɓo�^����H
�V�����e�[�u�������H
�A���P�[�g���e�[�u�����g���H

�A���P�[�g���ʉ{���ŗL���T���v������\������@�i�񓚎Ґ��j
�e�[�u���쐬 �v���O���~���O�@
�P�S�F�R�O�Ɏ����@�u���E�U�o�b�N�̃G���[������
�\�[�X�R�[�h�ɃR�����g�ǉ��@����
INSERT INTO co(ank_id, inp_date, upd_date)VALUES('1', current_date, now())

SELECT * FROM co where ank_id = '15';

������ʂōX�V���Ă��G���[���ł��m�F��ʂŃG���[���b�Z�[�W���o��悤�ɂ����B
�������쐬�����A���P�[�g���{���ł���悤�ɂ����B
���ꂼ��̃y�[�W�쐬
�����̃A���P�[�g�ɓ������Ȃ��悤�ɂ���



�F��x�������A���P�[�g�ɕ�����񓚂ł��Ȃ������鏈��

�E���[�U���e�[�u���ɃA���P�[�g�̉񓚏󋵂�ێ�������
�ǂ̃A���P�[�g�ɉ񓚂������̔�����@���킩��Ȃ�
�A���P�[�g���e�[�u�������[�U���e�[�u���������[�V�������Ă����Ԃŋt�Ƀ��[�U���e�[�u���������[�V�����ł���̂��H


�E�V���ɉ񓚏󋵂�ێ�����e�[�u�����쐬����
�����̃e�[�u�����烊���[�V�����ł���̂��H

�V���Ƀe�[�u�����쐬���A���[�U�[���ƃA���P�[�g�����񓚂����Ƃ��ɃC���T�[�g����悤��������B�i����ŉ񓚂����A���P�[�g�̔��肪�ł���j
�����ĉ񓚃y�[�W�ɑJ�ڂ���ہA���̃e�[�u���ɏ�񂪂Ȃ��A���P�[�g�̂ݕ\��������΂悢�ƍl����B�iif����rset.next()���g���H�j

�������������A���P�[�g�ꗗ��\������
select * from kaijo where user_id= 'a';

���݂��Ȃ����t�̃G���[�`�F�b�N
4���@6���@9���@11���@2���Q�X�`�R�P�@

DELETE FROM co WHERE ank_id = ?;