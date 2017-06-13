package mogipuro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class TourokuAction extends Action {

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

			// 入力データを取得し、文字コード変換
			String id = request.getParameter("id");

			try {
				// strutsを利用
				con = source.getConnection();

				// 自動コミットオフ
				con.setAutoCommit(false);

				stm = con.createStatement();

				// SQL文実行
				rset = stm
						.executeQuery("SELECT user_id FROM user where user_id = '"
								+ id + "'");

				if (rset.next()) {

					// メッセージの設定
					ActionMessages errors = new ActionMessages();

					errors.add("tyouhuku", new ActionMessage("tyouhuku.id"));

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

			HttpSession session = request.getSession(true);

			// セッションしてほかのformで値を使えるようにする

			session.setAttribute("id", id);

			// 画面遷移
			return mapping.findForward("next");
		}

	}

}
