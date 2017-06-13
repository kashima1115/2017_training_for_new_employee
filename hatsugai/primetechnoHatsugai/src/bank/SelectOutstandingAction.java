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
	Statement stmt = null;
	Statement stmt2 = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	String userID=null;
	String otherID=null;
	List<OutstandingBean> list = new ArrayList<OutstandingBean>();
	try{
		// DB接続
		con = source.getConnection();
		// ステートメント生成
		stmt = con.createStatement();
		String sqlstr="SELECT user_id FROM user WHERE account_no='"+frm.getAccountNumber()+"'";
		rs=stmt.executeQuery(sqlstr);
		while(rs.next()){
			userID=rs.getString(1);
		}
		if(rs != null){
			rs.close();
		}
		sqlstr="SELECT trans_date,other_id,trans_cash,trans_id FROM transcation WHERE user_id='";
		sqlstr+=userID+ "' AND trans_flag='1' ORDER BY trans_date";
		rs=stmt.executeQuery(sqlstr);
		while(rs.next()){
			OutstandingBean ob = new OutstandingBean();
			ob.setReservedDate(rs.getString(1));
			otherID=rs.getString(2);
			ob.setOtherID(otherID);
			ob.setTransCash(rs.getString(3));
			ob.setTransID(rs.getString(4));
			String sqlstr2="SELECT furigana,account_no FROM user WHERE user_id='";
			sqlstr2+=otherID+ "'";
			stmt2=con.createStatement();
			rs2=stmt2.executeQuery(sqlstr2);
			while(rs2.next()){
				ob.setFurigana(rs2.getString(1));
				ob.setOtherAccountNumber(rs2.getString(2));
			}
			if(rs2 != null){
    			rs2.close();
    		}
    		if(stmt2 != null){
    			stmt2.close();
    		}
    		list.add(ob);

		}

		HttpSession session = request.getSession();
		session.setAttribute("outstandingList", list);
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

	return mapping.findForward("outstanding");
	}

}
