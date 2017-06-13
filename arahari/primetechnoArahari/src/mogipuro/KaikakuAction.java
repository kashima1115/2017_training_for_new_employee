package mogipuro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class KaikakuAction extends LookupDispatchAction {

	// getKeyMethodMapメソッド
	@Override
	protected Map<String, String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
		Map<String, String> map = new HashMap<String, String>();
		map.put("kknext", "nextPage");
		map.put("back", "backPage");
		return map;
	}

	// 次へ遷移するためのnextPageメソッド
	public ActionForward nextPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession(true);

		String logid = (String) session.getAttribute("logid");

		// データベース処理関連変数の定義
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		// formをキャストする
		MakeForm fom = (MakeForm) form;

		// struts-config.xmlに設定したDataSource取得
		DataSource source = getDataSource(request);

		try {
			// strutsを利用
			con = source.getConnection();

			// 自動コミットオフ
			con.setAutoCommit(false);

			// ステートメント生成
			stmt = con
					.prepareStatement("update answer set answered = answered + 1 where select_id = ?");

			for (String select : fom.getChoices()) {

				// パラメータを設定
				stmt.setInt(1, Integer.parseInt(select));
				// SQL実行
				stmt.executeUpdate();
			}

			stmt.close();

			// ステートメント生成
			stmt = con
					.prepareStatement("update point set point = point + 1 where user_id = ?");

			stmt.setString(1, logid);

			// SQL実行
			stmt.executeUpdate();

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

			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		session.removeAttribute("MakeForm");

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
