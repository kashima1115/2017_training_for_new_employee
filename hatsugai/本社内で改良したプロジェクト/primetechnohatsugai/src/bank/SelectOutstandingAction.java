package bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bank.form.BankForm;
import bean.OutstandingBean;

public class SelectOutstandingAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
	//フォーム情報取得
	BankForm frm =(BankForm)form;
	//struts-config.xmlに設定したDataSource取得
	DataSource source =getDataSource(request);
	// データベース処理関連変数の定義
	Connection con = null;
	Statement stmt = null;//DBのtranscation用
	Statement stmt2 = null;//DBのuser用
	ResultSet rs = null;//DBのtranscation用
	ResultSet rs2 = null;//DBのuser用
	String userID=null;//振込元顧客ID
	String otherID=null;//振込先顧客ID
	List<OutstandingBean> list = new ArrayList<OutstandingBean>();//OutstandingBeanをインスタンス化
	try{
		// DB接続
		con = source.getConnection();
		// ステートメント生成
		stmt = con.createStatement();
		//顧客IDをform内の口座番号から取得
		String sqlstr="SELECT user_id FROM user WHERE account_no='"+frm.getAccountNumber()+"'";
		rs=stmt.executeQuery(sqlstr);
		while(rs.next()){
			userID=rs.getString(1);//顧客IDを取得、代入
		}
		if(rs != null){
			rs.close();
		}
		//DBのtranscationテーブルから決済フラグが1（未決済）のデータを取得
		sqlstr="SELECT trans_date,other_id,trans_cash,trans_id FROM transcation WHERE user_id='";
		sqlstr+=userID+ "' AND trans_flag='1' ORDER BY trans_date";
		rs=stmt.executeQuery(sqlstr);
		while(rs.next()){
			OutstandingBean ob = new OutstandingBean();
			ob.setReservedDate(rs.getString(1));//予約日を代入
			otherID=rs.getString(2);//振込先の顧客IDを代入
			ob.setOtherID(otherID);
			ob.setTransCash(rs.getString(3));//振り込み額を代入
			ob.setTransID(rs.getString(4));//取引IDを代入
			//振込先のフリガナと口座番号を取得
			String sqlstr2="SELECT furigana,account_no FROM user WHERE user_id='";
			sqlstr2+=otherID+ "'";
			stmt2=con.createStatement();
			rs2=stmt2.executeQuery(sqlstr2);
			while(rs2.next()){
				ob.setFurigana(rs2.getString(1));//振込先のフリガナを代入
				ob.setOtherAccountNumber(rs2.getString(2));//振込先の口座番号を代入
			}
			if(rs2 != null){
    			rs2.close();
    		}
    		if(stmt2 != null){
    			stmt2.close();
    		}
    		list.add(ob);//listに追加

		}

		HttpSession session = request.getSession();//セッション呼び出し
		session.setAttribute("outstandingList", list);//セッションに追加
	}catch(Exception e){
		e.printStackTrace();
		throw e;
	}finally{
		if(rs != null){
			rs.close();
		}
		if(stmt != null){
				stmt.close();
		}
		if(con != null){
			con.close();
		}
	}

	return mapping.findForward("outstanding");//未決済振込照会画面に遷移
	}

}
