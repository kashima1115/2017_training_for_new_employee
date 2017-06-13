package bank;

import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bank.form.BankForm;

public class DeleteOutstandingData extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		//フォーム情報取得
		BankForm frm =(BankForm)form;
		//トークンのチェック
		if(super.isTokenValid(request,true)){//2重押しなどがされていない場合の処理
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
				String sqlstr="DELETE FROM transcation WHERE trans_id='";//transcation内の該当するデータを削除する
				sqlstr+=frm.getTransID() +"'";//フォームに入っている取引番号
				stmt.executeUpdate(sqlstr);//SQL実行
			}catch(Exception e){//例外処理
				e.printStackTrace();
				throw e;
			}finally{//最終処理
				if(stmt!=null){
					stmt.close();
				}
				if(con!=null){
					con.close();
				}
			}
		}else{//2重押しなどがされていた場合
			return mapping.findForward("error");//エラー画面に遷移
		}
		frm.setOtherAccountNumber(null);//以下、消去した未決済のデータをフォームからなくす
		frm.setReservedDate(null);
		frm.setFurigana(null);
		frm.setTransCash(null);
		frm.setTransID(null);
		return mapping.findForward("delete");//削除完了画面へ遷移
	}
}
