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
	public ActionForward nextPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// セッションでログイン情報を取得
		HttpSession session = request.getSession(true);

		String logid = (String) session.getAttribute("logid");

		// データベース処理関連変数の定義
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Statement stm = null;
		PreparedStatement stmt2 = null;
		Statement st = null;
		ResultSet rst = null;

		// formをキャストする
		MakeForm fom = (MakeForm) form;

		// struts-config.xmlに設定したDataSource取得
		DataSource source = getDataSource(request);

		try {
			// strutsを利用
			con = source.getConnection();

			// 自動コミットオフ
			con.setAutoCommit(false);

			// キャストしたアンケート名をSQLで取得後if文でエラー画面遷移処理
			st = con.createStatement();
			// SQL文実行
			rst = st.executeQuery("SELECT * FROM kaijo where ank_name = '" + fom.getRadio() + "' and user_id = '" + logid + "'");

			if (rst.next()) {

				// メッセージの設定
				ActionMessages errors = new ActionMessages();

				errors.add("kaikaku", new ActionMessage("error.kaikaku"));

				saveErrors(request, errors);
				// 検索画面遷移のためのパラメータ設定
				return mapping.findForward("error");
			}





			// ステートメント生成
			stmt = con.prepareStatement("update answer set answered = answered + 1 where select_id = ?");
			// チェックされた選択肢の回答数を1増やす処理
			for (String select : fom.getChoices()) {

				// パラメータを設定
				stmt.setInt(1, Integer.parseInt(select));
				// SQL実行
				stmt.executeUpdate();
			}

			stmt.close();

			// ステートメント生成
			stmt = con.prepareStatement("update point set point = point + 1 where user_id = ?");
			// 取得したログイン情報のユーザのポイントを1増やす処理
			stmt.setString(1, logid);

			// SQL実行
			stmt.executeUpdate();

			// 回答者数テーブルを更新する処理
			stmt.close();

			stm = con.createStatement();

			int i = 15;

			// SQL文実行
			rset = stm.executeQuery("SELECT ank_id FROM questionnaire where ank_name = '" + fom.getRadio() + "'");

			// SQL実行結果表示
			while (rset.next()) {
				// ステートメント生成
				i = rset.getInt("ank_id");

			}

			// ステートメント生成
			stmt = con.prepareStatement("update co set kaitou = kaitou + 1 where ank_id = ?");

			stmt.setInt(1, i);

			// SQL実行
			stmt.executeUpdate();



			//回答状況テーブルに回答したユーザ名とアンケート名をインサート

			stmt2 = con.prepareStatement("INSERT INTO kaijo"
					+ "(user_id, ank_name, inp_date, upd_date) "
					+ "VALUES(?, ?, current_date, now())");


			// パラメータを設定
			stmt2.setString(1, logid);
			stmt2.setString(2, fom.getRadio());

			// SQL実行
			stmt2.executeUpdate();

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

			if (stmt2 != null) {
				stmt2.close();
			}

			if (rset != null) {
				rset.close();
			}

			if (stm != null) {
				stm.close();
			}

			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}



		// 画面遷移
		return mapping.findForward("next");
	}

	// 前へ遷移するためのbackPageメソッド
	public ActionForward backPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 画面遷移
		return mapping.findForward("back");
	}

}
