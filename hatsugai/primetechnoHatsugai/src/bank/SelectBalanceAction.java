package bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bank.form.BankForm;

public class SelectBalanceAction extends Action {
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
		ResultSet rs = null;
		String balance="";
		try{
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();
			String sqlstr="SELECT account_balance FROM user WHERE account_no";
			sqlstr+="='"+frm.getAccountNumber()+ "'";
			rs=stmt.executeQuery(sqlstr);
			while(rs.next()){
				balance=rs.getString(1);
			}
			frm.setBalance(balance);
		}catch(Exception e){
			e.printStackTrace();
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

		// 次の画面遷移のためのパラメータ設定
       return mapping.findForward("balance");
   }
}
