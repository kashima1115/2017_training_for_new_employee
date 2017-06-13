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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import bank.form.BankForm;

public class SelectLoginAction extends Action {
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
		ActionMessages errors = new ActionMessages();//エラーメッセージ格納用
		//Map<String,String> passmaster = new HashMap<String,String>();//DBのIDとpass格納用
		String pass ="";//パスワード（デフォルトは空白）
		String name ="";//名前（デフォルトは空白）

		//空白チェック（口座番号・パスワードどちらかが空白の場合エラー）
		if(frm.getAccountNumber().equals("") || frm.getPassword().equals("")){
			errors.add("password.required", new ActionMessage("error.password.required"));
			saveErrors(request,errors);
			return mapping.findForward("loginerror");//ログイン画面に遷移
		}

		try{
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();
			//formに入力された口座番号を元にパスワードと名前を取得
			String sqlstr ="SELECT passwd,name FROM user WHERE account_no='";
			sqlstr+=frm.getAccountNumber() +"'";
			rs=stmt.executeQuery(sqlstr);
			while(rs.next()){
				pass=rs.getString(1);//パスワードを代入
				name=rs.getString(2);//名前を代入
			}
			try{
				if(rs!=null){
					rs.close();
				}
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}
			//formに入力したパスワードとDBのパスワードを照会する
			if(pass.equals(frm.getPassword())){//パスワードが合致していた場合
				frm.setName(name);//画面の右上に表示する名前をここで代入
				return mapping.findForward("login");//メニュー画面へ遷移
			}else{//パスワードが合致しなかった場合
				errors.add("password.notaccount", new ActionMessage("error.password.notaccount"));
				saveErrors(request,errors);//エラーメッセージを保存
				return mapping.findForward("loginerror");//ログイン画面へ遷移
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

}
