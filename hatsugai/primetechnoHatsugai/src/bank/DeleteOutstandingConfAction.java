package bank;

import java.sql.Connection;
import java.sql.Statement;
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

public class DeleteOutstandingConfAction extends LookupDispatchAction {

	@Override
	protected Map<String,String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
		Map<String, String> map = new HashMap<String, String>();
		map.put("delete", "deletePage");
		map.put("return", "returnPage");
		return map;
	}

	public ActionForward deletePage(ActionMapping mapping
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
		try{
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();
			String sqlstr="DELETE FROM transcation WHERE trans_id='";
			sqlstr+=frm.getTransID() +"'";
			stmt.executeUpdate(sqlstr);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(stmt!=null){
				stmt.close();
			}
			if(con!=null){
				con.close();
			}
		}
		frm.setOtherAccountNumber(null);
		frm.setReservedDate(null);
		frm.setFurigana(null);
		frm.setTransCash(null);
		frm.setTransID(null);
		return mapping.findForward("delete");
	}

	public ActionForward returnPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("return");
	}

}
