package account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import account.form.AccountForm;

public class SELECTpass extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		//フォーム情報取得
		AccountForm frm =(AccountForm)form;
		//struts-config.xmlに設定したDataSource取得
		DataSource source =getDataSource(request);
		// データベース処理関連変数の定義
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//エラーメッセージ格納用
		ActionMessages errors = new ActionMessages();
		String accountnumber=null;
		String password=null;
		String passno=null;
		//空白チェック
		if(frm.getAccountNumber().equals("")||frm.getPassword().equals("")||frm.getPassno().equals("")){
			errors.add("passwordd.required", new ActionMessage("error.passwordd.required"));
			return mapping.findForward("accountdeleteprocessform");
		}
		//DB接続
		try{
			// DB接続
			con = source.getConnection();
			//sql文を作成
			String sql ="SELECT account_no,passwd,passno from user WHERE account_no=?";
			// ステートメント生成
			pstmt = con.prepareStatement(sql);
			//値の設定
			pstmt.setString(1, frm.getAccountNumber());
			//SQL実行
			rs=pstmt.executeQuery();
			//値の取り出し
			while(rs.next()){
				accountnumber=rs.getString(1);
				password=rs.getString(2);
				passno=rs.getString(3);
			}
			//formの値とsqlで取得した値の比較
			if(!accountnumber.equals(frm.getAccountNumber()) ||!password.equals(frm.getPassword()) || !passno.equals(frm.getPassno())){
				errors.add("password.nohit", new ActionMessage("error.password.nohit"));
				return mapping.findForward("accountdeleteprocessform");
			}else{
				return mapping.findForward("transcationform");//残金振込先入力画面へ遷移
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(con!=null){
				con.close();
			}
		}

	}

}
