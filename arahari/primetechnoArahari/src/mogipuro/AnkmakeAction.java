package mogipuro;

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

public class AnkmakeAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (this.isCancelled(request)) {
			// キャンセルボタン押下時の処理
			return mapping.findForward("back");
		} else {


			Connection con = null;
			Statement stm = null;
			ResultSet rset = null;

			// struts-config.xmlに設定したDataSource取得
			DataSource source = getDataSource(request);

			// formをキャストする
			MakeForm frm = (MakeForm) form;


			try {
				// strutsを利用
				con = source.getConnection();

				// 自動コミットオフ
				con.setAutoCommit(false);

				stm = con.createStatement();

				// SQL文実行
				rset = stm.executeQuery("SELECT * FROM questionnaire where ank_name = '" + frm.getAnkName() + "'");

				if (rset.next()) {

					// メッセージの設定
					ActionMessages errors = new ActionMessages();

					errors.add("error_name", new ActionMessage("error.name"));

					saveErrors(request, errors);
					// 検索画面遷移のためのパラメータ設定
					return mapping.findForward("error");
				}

				// コミット
				con.commit();

			} catch (Exception e) {

				// ロールバック
				if (con != null) {
					con.rollback();
				}

				// 例外をthrow
				throw e;

			} finally {

				if (rset != null) {
					rset.close();
				}

				if (con != null) {
					con.close();
				}
			}

			// 画面遷移
			return mapping.findForward("next");
		}

	}

}
