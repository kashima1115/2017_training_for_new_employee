
cd C:\xampp\mysql\bin

mysql -u root

create database primetechnoArahari;

use primetechnoArahari;

スタート画面URL
http://localhost:8080/primetechnoArahari/mogipuro/start.jsp

ユーザ情報テーブル
create table user(user_id varchar(50) primary key not null
, pass varchar(50) not null, inp_date date not null, upd_date date not null);

アンケート名と終了期限テーブル
create table questionnaire(ank_id int primary key auto_increment not null
, user_id varchar(50) not null, ank_name varchar(50) not null, ank_end date not null
, inp_date date not null, upd_date date not null, FOREIGN KEY(user_id) REFERENCES user(user_id));

質問内容テーブル
create table quest(quest_id int primary key auto_increment not null
, ank_id int not null, question varchar(50) 
, inp_date date not null, upd_date date not null, foreign key(ank_id) references questionnaire(ank_id));

選択肢テーブル
create table answer(select_id int primary key auto_increment not null
, quest_id int not null, choices varchar(50) , answered int DEFAULT 0
, inp_date date not null, upd_date date not null, foreign key(quest_id) references quest(quest_id));

ポイント情報テーブル
create table point(point_id int primary key auto_increment not null
, user_id varchar(50) not null, point int default 3,type int
, inp_date date not null, upd_date date not null , foreign key(user_id) references user(user_id));

回答者数テーブル
create table co(co_id int primary key auto_increment not null
, ank_id int not null, kaitou int default 0
, inp_date date not null, upd_date date not null, foreign key(ank_id) references questionnaire(ank_id));

回答情報テーブル
create table kaijo(kaijo_id int primary key auto_increment not null
, user_id varchar(50) not null, ank_name varchar(50) not null
, kaisumi int, inp_date date not null, upd_date date not null);


INSERT INTO answer(quest_id, choices, inp_date, upd_date) VALUES('1', 'わからない', current_date, now());

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

update answer set choices='76-100点' where select_id= '228';

SELECT * FROM questionnaire where ank_end >= current_date ORDER BY ank_end;

未実装機能

作成機能でチェックボックスのほかにラジオボタンの選択
結果閲覧機能でグラフ等を用いた表示　JFreeの使い方
質問項目の追加や選択肢の追加
回答方式にチェックやラジオのほかに自由記述欄の選択
自分の回答したアンケート結果が自分の回答したものだけが表示されるようにする（今はほかのユーザの回答結果も合わせて表示されている）




メッセンジャー機能
外部研修プロジェクト修正
スケジュール確認　メール確認　健康診断日程7月3日
復習　メール受信設定　プロジェクト修正箇所指摘

アンケート作成時ログイン情報に登録する？
新しいテーブルを作る？
アンケート名テーブルを使う？

アンケート結果閲覧で有効サンプル数を表示する　（回答者数）
テーブル作成 プログラミング　
１４：３０に実装　ブラウザバックのエラーを解消
ソースコードにコメント追加　整理
INSERT INTO co(ank_id, inp_date, upd_date)VALUES('1', current_date, now())

SELECT * FROM co where ank_id = '15';

完了画面で更新してもエラーがでず確認画面でエラーメッセージが出るようにした。
自分が作成したアンケートを閲覧できるようにした。
それぞれのページ作成
自分のアンケートに答えられないようにする



：一度答えたアンケートに複数回回答できなくさせる処理

・ユーザ情報テーブルにアンケートの回答状況を保持させる
どのアンケートに回答したかの判定方法がわからない
アンケート名テーブルがユーザ情報テーブルをリレーションしている状態で逆にユーザ情報テーブルがリレーションできるのか？


・新たに回答状況を保持するテーブルを作成する
複数のテーブルからリレーションできるのか？

新たにテーブルを作成し、ユーザー情報とアンケート名を回答したときにインサートするよう処理する。（これで回答したアンケートの判定ができる）
そして回答ページに遷移する際、このテーブルに情報がないアンケートのみ表示させればよいと考える。（if文のrset.next()を使う？）

自分が答えたアンケート一覧を表示する
select * from kaijo where user_id= 'a';

存在しない日付のエラーチェック
4月　6月　9月　11月　2月２９～３１　

DELETE FROM co WHERE ank_id = ?;