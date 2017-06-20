package mogipuro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class AnkkanAction extends LookupDispatchAction {

	// getKeyMethodMapメソッド
	@Override
	protected Map<String, String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
		Map<String, String> map = new HashMap<String, String>();
		map.put("home", "nextPage");
		map.put("logout", "backPage");
		return map;
	}

	// 次へ遷移するためのnextPageメソッド
	public ActionForward nextPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// データベース処理関連変数の定義
		List<LoginForm> list = new ArrayList<LoginForm>();

		Connection con = null;
		ResultSet rset = null;
		ResultSet rst = null;
		Statement stmt = null;

		// struts-config.xmlに設定したDataSource取得
		DataSource source = getDataSource(request);
		// セッションでログインIDを取得
		HttpSession session = request.getSession(true);

		String logid = (String) session.getAttribute("logid");

		try {
			// strutsを利用
			con = source.getConnection();

			// 自動コミットオフ
			con.setAutoCommit(false);
			// 取得したログインIDからポイント情報を取得しhome画面に遷移
			stmt = con.createStatement();

			// SQL文実行
			rst = stmt.executeQuery("SELECT * FROM point where  user_id" + "= '" + logid + "'");

			while (rst.next()) {
				LoginForm mbn = new LoginForm();
				// 取得データ格納
				mbn.setPoint(rst.getInt("point"));
				list.add(mbn);
			}

			session.setAttribute("pointList", list);

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

		session.removeAttribute("MakeForm");

		// 画面遷移
		return mapping.findForward("home");
	}

	// 前へ遷移するためのbackPageメソッド
	public ActionForward backPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// セッションを終了しログアウト
		HttpSession session = request.getSession();
		session.removeAttribute("LoginForm");

		// 画面遷移
		return mapping.findForward("logout");
	}

}
