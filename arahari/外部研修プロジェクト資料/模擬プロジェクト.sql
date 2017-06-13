
cd C:\xampp\mysql\bin

mysql -u root

create database primetechnoArahari;

use primetechnoArahari;




create table user(user_id varchar(50) primary key not null
, pass varchar(50) not null, inp_date date not null, upd_date date not null);

create table questionnaire(ank_id int primary key auto_increment not null
, user_id varchar(50) not null, ank_name varchar(50) not null, ank_end date not null
, inp_date date not null, upd_date date not null, FOREIGN KEY(user_id) REFERENCES user(user_id));

create table quest(quest_id int primary key auto_increment not null
, ank_id int not null, question varchar(50) 
, inp_date date not null, upd_date date not null, foreign key(ank_id) references questionnaire(ank_id));

create table answer(select_id int primary key auto_increment not null
, quest_id int not null, choices varchar(50) , answered int DEFAULT 0
, inp_date date not null, upd_date date not null, foreign key(quest_id) references quest(quest_id));

create table point(point_id int primary key auto_increment not null
, user_id varchar(50) not null, point int default 3,type int
, inp_date date not null, upd_date date not null , foreign key(user_id) references user(user_id));



INSERT INTO answer(quest_id, choices, inp_date, upd_date) VALUES('1', 'わからない', current_date, now());

select * from user;
select * from point;
select * from questionnaire;
select * from quest;
select * from answer;


INSERT INTO questionnaire(user_id, ank_name, ank_end, inp_date, upd_date)VALUES('a', 'test', '2017-05-24', current_date, now());

DROP TABLE answer;

update point set point = point + 1 where user_id = 'q';

delete from user where user_id = 'a';

INSERT INTO point(user_id, inp_date, upd_date) VALUES('a', current_date, now());




	// validateメソッドの実装
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		// ActionErrorsオブジェクト取得
		ActionErrors errors = super.validate(mapping, request);



		 String point = request.getParameter("point");

		if(Integer.parseInt(point) < 2){
			errors.add("point_error",
					new ActionMessage("error.point"));
		}


		// ActionErrorsオブジェクトをreturn
		return errors;
	}
		HttpSession session = request.getSession(true);
		int point = (int) session.getAttribute("point");

		if(point < 2){
			errors.add("point_error",
					new ActionMessage("error.point"));
		}
		// A
		
		
DELETE FROM questionnaire WHERE ank_name = 'a';


DELETE FROM quest WHERE quest_id = '45' or quest_id = '44';

update answer set choices='76-100点' where select_id= '228';

SELECT * FROM questionnaire where ank_end >= current_date ORDER BY ank_end;

未実装機能
完了画面で更新するとエラー
完了画面からブラウザバックで登録しようとするとエラー
作成機能でチェックボックスのほかにラジオボタンの選択
結果閲覧機能でグラフ等を用いた表示
質問項目の追加や選択肢の追加
回答方式にチェックやラジオのほかに自由記述欄の選択
URLを直接打ち込んだ際のエラー

プレゼン発表