package bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

import bank.form.BankForm;

public class LoginAction extends LookupDispatchAction {

	protected Map<String, String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
		Map<String, String> map = new HashMap<String, String>();
		map.put("login", "loginPage");
		map.put("createAccount", "createAccountPage");

		return map;
	}

	public ActionForward loginPage(ActionMapping mapping
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
		ActionMessages errors = new ActionMessages();
		//Map<String,String> passmaster = new HashMap<String,String>();//DBのIDとpass格納用
		String pass ="";
		String name ="";

		//空白チェック
		if(frm.getAccountNumber().equals("") || frm.getPassword().equals("")){
			errors.add("password.required", new ActionMessage("error.password.required"));
			saveErrors(request,errors);
			return mapping.findForward("loginerror");
		}

		try{
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();
			String sqlstr ="SELECT passwd,name FROM user WHERE account_no='";
			sqlstr+=frm.getAccountNumber() +"'";
			rs=stmt.executeQuery(sqlstr);
			while(rs.next()){
				pass=rs.getString(1);
				name=rs.getString(2);
			}
			try{
				if(rs!=null){
					rs.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//idが見つからないときは・・・
			if(pass.equals(frm.getPassword())){
				frm.setName(name);
				return mapping.findForward("login");
			}else{
				errors.add("password.notaccount", new ActionMessage("error.password.notaccount"));
				saveErrors(request,errors);
				return mapping.findForward("loginerror");
			}

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
	}

	public ActionForward createAccountPage(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		return mapping.findForward("createAccount");
	}

}
