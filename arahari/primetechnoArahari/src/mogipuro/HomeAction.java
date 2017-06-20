package mogipuro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

public class HomeAction extends LookupDispatchAction {

	// getKeyMethodMapメソッド
	@Override
	protected Map<String, String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
		Map<String, String> map = new HashMap<String, String>();
		map.put("ank", "menuPage");
		map.put("kai", "kenPage");
		map.put("etu", "itiPage");
		map.put("del", "delPage");
		map.put("logout", "outPage");
		return map;
	}

	// 次へ遷移するためのnextPageメソッド
	public ActionForward menuPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Connection con = null;
		ResultSet rst = null;
		Statement stmt = null;

		// struts-config.xmlに設定したDataSource取得
		DataSource source = getDataSource(request);
		// セッションでログインID取得
		HttpSession session = request.getSession(true);

		String logid = (String) session.getAttribute("logid");

		try {
			// strutsを利用
			con = source.getConnection();

			// 自動コミットオフ
			con.setAutoCommit(false);
			// 取得したログインIDでポイント情報を取得し２ｐ未満のエラーチェック
			stmt = con.createStatement();

			// SQL文実行
			rst = stmt.executeQuery("SELECT * FROM point where  user_id" + "= '" + logid + "'");

			while (rst.next()) {

				if (rst.getInt("point") >= 2) {

					// 画面遷移
					return mapping.findForward("ank");
				} else {

					// メッセージの設定
					ActionMessages errors = new ActionMessages();

					errors.add("point", new ActionMessage("error.point"));

					saveErrors(request, errors);
					// 検索画面遷移のためのパラメータ設定
					return mapping.findForward("error");
				}

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
			if (con != null) {
				con.close();
			}
		}

		// メッセージの設定
		ActionMessages errors = new ActionMessages();

		errors.add("point", new ActionMessage("error.point"));

		saveErrors(request, errors);
		// 検索画面遷移のためのパラメータ設定
		return mapping.findForward("error");

	}

	// 前へ遷移するためのbackPageメソッド
	public ActionForward kenPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// セッションでログインID取得
		HttpSession session = request.getSession(true);

		String logid = (String) session.getAttribute("logid");



		// struts-config.xmlに設定したDataSource取得
		DataSource source = getDataSource(request);

		// データベース処理関連変数の定義
		List<MakeForm> list = new ArrayList<MakeForm>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();
			// 自分が作成したアンケートと過去の日付を除いた終了期限順でSQL実行
			String sqlStr = "SELECT * FROM questionnaire where not user_id" + "= '" + logid
					+ "' and ank_end >= current_date ORDER BY ank_end";

			rs = stmt.executeQuery(sqlStr);

			// 取得データ格納
			while (rs.next()) {
				MakeForm mbn = new MakeForm();

				mbn.setAnkName(rs.getString("ank_name"));
				mbn.setAnkEnd(rs.getString("ank_end"));

				list.add(mbn);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		// 取得した商品情報をリクエストにセット
		session.setAttribute("ankList", list);

		// 画面遷移
		return mapping.findForward("kai");

	}

	// 次へ遷移するためのnextPageメソッド
	public ActionForward itiPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// struts-config.xmlに設定したDataSource取得
		DataSource source = getDataSource(request);
		// セッションでログインID取得
		HttpSession session = request.getSession(true);

		// データベース処理関連変数の定義
		List<MakeForm> list = new ArrayList<MakeForm>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();
			// 全アンケート情報取得
			String sqlStr = "SELECT * FROM questionnaire";

			rs = stmt.executeQuery(sqlStr);

			// 取得データ格納
			while (rs.next()) {
				MakeForm mbn = new MakeForm();

				mbn.setAnkName(rs.getString("ank_name"));

				list.add(mbn);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		// 取得した商品情報をリクエストにセット
		session.setAttribute("etuList", list);

		// 画面遷移
		return mapping.findForward("etu");
	}


	// 次へ遷移するためのnextPageメソッド
	public ActionForward delPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// struts-config.xmlに設定したDataSource取得
		DataSource source = getDataSource(request);
		// セッションでログインID取得
		HttpSession session = request.getSession(true);

		String logid = (String) session.getAttribute("logid");

		// データベース処理関連変数の定義

		List<MakeForm> list3 = new ArrayList<MakeForm>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rset = null;
		ResultSet rst = null;

		try {
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();

			// 自分のアンケート情報取得
			String sqlStr3 = "select * from questionnaire where user_id" + "= '" + logid + "'";

			rst = stmt.executeQuery(sqlStr3);

			// 取得データ格納
			while (rst.next()) {
				MakeForm mn = new MakeForm();

				mn.setEndName(rst.getString("ank_name"));

				list3.add(mn);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (rset != null) {
				rset.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		// 取得した商品情報をリクエストにセット

		session.setAttribute("makeList", list3);
		// 画面遷移
		return mapping.findForward("del");
	}







	// 前へ遷移するためのbackPageメソッド
	public ActionForward outPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// セッションを終了してログアウト
		HttpSession session = request.getSession();
		session.removeAttribute("LoginForm");

		// 画面遷移
		return mapping.findForward("logout");
	}

}
