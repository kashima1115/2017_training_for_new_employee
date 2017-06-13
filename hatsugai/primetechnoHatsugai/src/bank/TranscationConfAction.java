package bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import bank.form.BankForm;

public class TranscationConfAction extends LookupDispatchAction {
	protected Map<String, String> getKeyMethodMap() {
	    // Mapオブジェクト生成し、メッセージキーとメソッド名格納
	     Map<String, String> map = new HashMap<String, String>();
	     map.put("menu", "menuPage");
	     map.put("transcation", "transcationPage");
	     map.put("return", "returnPage");

	     return map;
	}

	public ActionForward transcationPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
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
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		int month= cal.get(Calendar.MONTH)+1;
		int date=cal.get(Calendar.DATE);
		int fy=Integer.parseInt(frm.getfYear());
		int fm=Integer.parseInt(frm.getfMonth());
		int fd=Integer.parseInt(frm.getfDate());
		String flag;
		String furikomibi;
		Long zandaka = null;
		Long last_zandaka=null;
		if(year==fy && month==fm && date==fd){
			flag="0";
			furikomibi=frm.getfYear()+"-"+String.format("%02d", fm)+"-"+String.format("%02d", fd);
		}else{
			flag="1";
			furikomibi=frm.getfYear()+"-"+String.format("%02d", fm)+"-"+String.format("%02d", fd);
		}
		try{
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();
            // 自動コミットオフ
            con.setAutoCommit(false);
			String sqlstr="SELECT user_id,account_balance FROM user WHERE account_no ='";//振込元の顧客IDを取得する
			sqlstr+=frm.getAccountNumber()+ "'";
			rs=stmt.executeQuery(sqlstr);
			while(rs.next()){
				my_id=rs.getString(1);
				last_zandaka=rs.getLong(2);
			}
			if(rs != null){
				rs.close();
			}
			sqlstr="SELECT user_id FROM user WHERE account_no ='";//振込先の顧客IDを取得
			sqlstr+=frm.getOtherAccountNumber()+ "'";
			rs=stmt.executeQuery(sqlstr);
			while(rs.next()){
				other_id=rs.getString(1);
			}
			if(rs != null){
				rs.close();
			}
			sqlstr="INSERT INTO transcation(user_id,other_id,trans_cash,trans_flag,trans_date,inp_date) VALUES('";//取引情報テーブルにインサート
			sqlstr+=my_id+ "','" + other_id +"','"+ frm.getFurikomi() +"','"+flag+ "','"+furikomibi+"',CURRENT_DATE())";
			stmt.executeUpdate(sqlstr);
			if(stmt!=null){
				stmt.close();
			}

			if (flag.equals("0")) {
				stmt = con.createStatement();
				sqlstr = "SELECT account_balance FROM user WHERE account_no ='"
						+ frm.getOtherAccountNumber() + "'";//振込先の残高を取得
				rs = stmt.executeQuery(sqlstr);
				while (rs.next()) {
					zandaka = rs.getLong(1);
				}
				zandaka += Long.parseLong(frm.getFurikomi());
				sqlstr = "UPDATE user SET account_balance=('" + zandaka
						+ "') WHERE account_no ='"
						+ frm.getOtherAccountNumber() + "'";//振込先の残高を更新
				stmt.executeUpdate(sqlstr);
				if (stmt != null) {
					stmt.close();
				}
				stmt = con.createStatement();
				zandaka = last_zandaka - Long.parseLong(frm.getFurikomi());
				sqlstr = "UPDATE user SET account_balance=('" + zandaka
						+ "') WHERE account_no ='" + frm.getAccountNumber()
						+ "'";//振込元の残高を更新
				stmt.executeUpdate(sqlstr);
			}
			//コミット
			con.commit();
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
				con.rollback();
			}
			throw e;

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
		return mapping.findForward("transcation");
	}

	public ActionForward menuPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("menu");
	}

	public ActionForward returnPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("return");
	}

}
