package bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bank.form.BankForm;

public class InsertTranscationData extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		//トークンのチェック
		if(super.isTokenValid(request,true)){//2重押しなどがされていない場合の処理
			//フォーム情報取得
			BankForm frm =(BankForm)form;
			//struts-config.xmlに設定したDataSource取得
			DataSource source =getDataSource(request);
			// データベース処理関連変数の定義
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			String my_id=null;//振込元の顧客ID
			String other_id=null;//振込先の顧客ID
			//振込みが当日かそうでないかのフラグ管理のためにCalendarクラスを使用
			Calendar cal=Calendar.getInstance();//Calendarクラスのインスタンス化
			int year=cal.get(Calendar.YEAR);//実行時の年を代入
			int month= cal.get(Calendar.MONTH)+1;//実行時の月を代入
			int date=cal.get(Calendar.DATE);//実行時の日を代入
			int fy=Integer.parseInt(frm.getfYear());//formに入力した年をint型に変換
			int fm=Integer.parseInt(frm.getfMonth());//formに入力した月をint型に変換
			int fd=Integer.parseInt(frm.getfDate());//formに入力した日をint型に変換
			String flag;//振込み決済のフラグ
			String furikomibi;//振込み日を入れる変数
			Long zandaka = null;//残高を入れる変数
			Long last_zandaka=null;//残高を入れる変数
			if(year==fy && month==fm && date==fd){//振込み日が実行時と同じ日付の場合
				flag="0";
				//yyyy-mm-ddのフォーマットになるように整える
				furikomibi=frm.getfYear()+"-"+String.format("%02d", fm)+"-"+String.format("%02d", fd);
			}else{//振込み日が実行時よりも未来の場合
				flag="1";
				//yyyy-mm-ddのフォーマットになるように整える
				furikomibi=frm.getfYear()+"-"+String.format("%02d", fm)+"-"+String.format("%02d", fd);
			}
			try{
				// DB接続
				con = source.getConnection();
				// ステートメント生成
				stmt = con.createStatement();
				// 自動コミットオフ
				con.setAutoCommit(false);
				//振込元の顧客IDと残額を取得する
				String sqlstr="SELECT user_id,account_balance FROM user WHERE account_no ='";
				sqlstr+=frm.getAccountNumber()+ "'";//form内の口座番号が条件
				rs=stmt.executeQuery(sqlstr);
				while(rs.next()){
					my_id=rs.getString(1);//振り込み元の顧客IDを代入
					last_zandaka=rs.getLong(2);//最新の残高を代入
				}
				if(rs != null){
					rs.close();
				}
				//振込先の顧客IDを取得
				sqlstr="SELECT user_id FROM user WHERE account_no ='";
				sqlstr+=frm.getOtherAccountNumber()+ "'";
				rs=stmt.executeQuery(sqlstr);
				while(rs.next()){
					other_id=rs.getString(1);//振込先の顧客IDを代入
				}
				if(rs != null){
					rs.close();
				}
				//取引テーブルにデータを挿入(INSERT)
				sqlstr="INSERT INTO transcation(user_id,other_id,trans_cash,trans_flag,trans_date,inp_date) VALUES('";
				sqlstr+=my_id+ "','" + other_id +"','"+ frm.getFurikomi() +"','"+flag+ "','"+furikomibi+"',CURRENT_DATE())";
				stmt.executeUpdate(sqlstr);
				if(stmt!=null){
					stmt.close();
				}
				//残高の更新処理
				if (flag.equals("0")) {//当日の振込みのみを対象
					stmt = con.createStatement();
					//振込先の残高を取得
					sqlstr = "SELECT account_balance FROM user WHERE account_no ='"
							+ frm.getOtherAccountNumber() + "'";
					rs = stmt.executeQuery(sqlstr);
					while (rs.next()) {
						zandaka = rs.getLong(1);//振込先の残高を代入
					}
					//振込先の残高に取引金額を加算する
					zandaka += Long.parseLong(frm.getFurikomi());
					//振込先の残高を更新する
					sqlstr = "UPDATE user SET account_balance=('" + zandaka
							+ "') WHERE account_no ='"
							+ frm.getOtherAccountNumber() + "'";
					stmt.executeUpdate(sqlstr);
					if (stmt != null) {
						stmt.close();
					}
					stmt = con.createStatement();
					//振込元の残高から取引金額を減算する
					zandaka = last_zandaka - Long.parseLong(frm.getFurikomi());
					//振込元の残高を更新する
					sqlstr = "UPDATE user SET account_balance=('" + zandaka
							+ "') WHERE account_no ='" + frm.getAccountNumber()
							+ "'";
					stmt.executeUpdate(sqlstr);
				}
				//コミット
				con.commit();
				//formの各値を変更する(残高以外は消去する)
				frm.setBalance(String.valueOf(zandaka));
				frm.setKatakana(null);
				frm.setOtherAccountNumber(null);
				frm.setFurikomi(null);
				frm.setfYear(null);
				frm.setfMonth(null);
				frm.setfDate(null);
			}catch(Exception e){
				e.printStackTrace();
				if(con != null){
					//SQL異常時にはロールバック
					con.rollback();
				}

			}finally{
				if(stmt != null){
					stmt.close();
				}
				if(rs != null){
					rs.close();
				}
				if(con != null){
					con.close();
				}
			}
		}else{//2重押しなどがされたときの処理
			return mapping.findForward("error");//エラー画面に遷移
		}
		return mapping.findForward("transcation");//振込手続き完了画面に遷移

	}
}
