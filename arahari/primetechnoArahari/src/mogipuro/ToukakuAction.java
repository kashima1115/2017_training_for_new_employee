package mogipuro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

public class ToukakuAction extends LookupDispatchAction {

	// getKeyMethodMapメソッド
	@Override
	protected Map<String, String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
		Map<String, String> map = new HashMap<String, String>();
		map.put("nextkaku", "nextPage");
		map.put("backkaku", "backPage");
		return map;
	}

	// 次へ遷移するためのnextPageメソッド
	public ActionForward nextPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// データベース処理関連変数の定義
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement state = null;
		ResultSet rset = null;
		Statement stm = null;

		HttpSession session = request.getSession(true);

		String id = (String) session.getAttribute("id");

		// formをキャストする
		QuestionnaireForm fom = (QuestionnaireForm) form;

		// struts-config.xmlに設定したDataSource取得
		DataSource source = getDataSource(request);

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

			// ステートメント生成
			stmt = con.prepareStatement("INSERT INTO user"
					+ "(user_id, pass, inp_date, upd_date) "
					+ "VALUES(?, ?, current_date, now())");

			// パラメータを設定
			stmt.setString(1, fom.getId());
			stmt.setString(2, fom.getPass());

			// SQL実行
			stmt.executeUpdate();

			// ステートメント生成
			state = con.prepareStatement("INSERT INTO point"
					+ "(user_id,  inp_date, upd_date) "
					+ "VALUES(?,  current_date, now())");

			// パラメータを設定
			state.setString(1, fom.getId());

			// SQL実行
			state.executeUpdate();

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

			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		session.removeAttribute("QuestionnaireForm");

		// 画面遷移
		return mapping.findForward("next");
	}

	// 前へ遷移するためのbackPageメソッド
	public ActionForward backPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 画面遷移
		return mapping.findForward("back");
	}

}
