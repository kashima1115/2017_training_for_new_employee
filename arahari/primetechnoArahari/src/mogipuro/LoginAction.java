package mogipuro;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class LoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (this.isCancelled(request)) {
			// キャンセルボタン押下時の処理
			return mapping.findForward("back");
		} else {

			// データベース処理関連変数の定義
			List<LoginForm> list = new ArrayList<LoginForm>();

			Connection con = null;
			Statement stm = null;
			ResultSet rset = null;
			ResultSet rst = null;
			Statement stmt = null;

			// struts-config.xmlに設定したDataSource取得
			DataSource source = getDataSource(request);

			// 入力データを取得し、文字コード変換
			String logid = request.getParameter("logid");
			String logpass = request.getParameter("logpass");

			try {
				// strutsを利用
				con = source.getConnection();

				// 自動コミットオフ
				con.setAutoCommit(false);

				stm = con.createStatement();
				stmt = con.createStatement();

				// SQL文実行
				rset = stm.executeQuery("SELECT * FROM user where user_id "
						+ "= '" + logid + "' and pass = '" + logpass + "'");

				if (rset.next()) {

					// SQL文実行
					rst = stmt
							.executeQuery("SELECT * FROM point where  user_id"
									+ "= '" + logid + "'");

					while (rst.next()) {
						LoginForm mbn = new LoginForm();

						// 取得データ格納
						mbn.setPoint(rst.getInt("point"));
						list.add(mbn);
					}

					HttpSession session = request.getSession(true);

					// 取得した商品情報をリクエストにセット
					session.setAttribute("pointList", list);

					// セッションしてほかのformで値を使えるようにする

					session.setAttribute("logid", logid);

					return mapping.findForward("home");

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

			// メッセージの設定
			ActionMessages errors = new ActionMessages();

			errors.add("nai", new ActionMessage("nai.aka"));

			saveErrors(request, errors);
			// 検索画面遷移のためのパラメータ設定
			return mapping.findForward("error");
		}

	}

}
