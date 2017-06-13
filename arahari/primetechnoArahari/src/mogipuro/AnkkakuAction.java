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

public class AnkkakuAction extends LookupDispatchAction {

	// getKeyMethodMapメソッド
	@Override
	protected Map<String, String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
		Map<String, String> map = new HashMap<String, String>();
		map.put("aknext", "nextPage");
		map.put("akback", "backPage");
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
		ResultSet rs = null;

		// formをキャストする
		MakeForm frm = (MakeForm) form;

		// struts-config.xmlに設定したDataSource取得
		DataSource source = getDataSource(request);

		try {
			// strutsを利用
			con = source.getConnection();

			// 自動コミットオフ
			con.setAutoCommit(false);

			// アンケート名登録
			// ステートメント生成
			stmt = con.prepareStatement("INSERT INTO questionnaire"
					+ "(user_id, ank_name, ank_end, inp_date, upd_date) "
					+ "VALUES(?, ?, ?, current_date, now())");

			// パラメータを設定
			stmt.setString(1, logid);
			stmt.setString(2, frm.getAnkName());
			stmt.setString(3,
					frm.getYear() + "-" + frm.getMonth() + "-" + frm.getDay());

			// SQL実行
			stmt.executeUpdate();

			// 質問内容登録
			int i = 0;

			// SQL文実行
			rset = stmt
					.executeQuery("SELECT ank_id FROM questionnaire where ank_name "
							+ "= '" + frm.getAnkName() + "'");

			// SQL実行結果表示
			while (rset.next()) {
				// ステートメント生成
				i = rset.getInt("ank_id");

			}

			stmt.close();
			// ステートメント生成
			stmt = con.prepareStatement("INSERT INTO quest"
					+ "(ank_id, question, inp_date, upd_date) "
					+ "VALUES(?, ?, current_date, now())");

			// パラメータを設定
			stmt.setInt(1, i);
			stmt.setString(2, frm.getQuestion1());

			// SQL実行
			stmt.executeUpdate();

			if (!frm.getQuestion2().isEmpty()) {

				// パラメータを設定
				stmt.setInt(1, i);
				stmt.setString(2, frm.getQuestion2());

				// SQL実行
				stmt.executeUpdate();
			}

			if (!frm.getQuestion3().isEmpty()) {

				// パラメータを設定
				stmt.setInt(1, i);
				stmt.setString(2, frm.getQuestion3());

				// SQL実行
				stmt.executeUpdate();
			}

			if (!frm.getQuestion4().isEmpty()) {

				// パラメータを設定
				stmt.setInt(1, i);
				stmt.setString(2, frm.getQuestion4());

				// SQL実行
				stmt.executeUpdate();
			}

			if (!frm.getQuestion5().isEmpty()) {

				// パラメータを設定
				stmt.setInt(1, i);
				stmt.setString(2, frm.getQuestion5());

				// SQL実行
				stmt.executeUpdate();
			}

			// 選択肢登録
			int q = 0;

			// SQL文実行
			rs = stmt.executeQuery("SELECT quest_id FROM quest where  question"
					+ "= '" + frm.getQuestion1() + "'");

			// SQL実行結果表示
			while (rs.next()) {
				// ステートメント生成
				q = rs.getInt("quest_id");

			}

			// 悪いデータの登録方法
			stmt.close();
			// ステートメント生成
			stmt = con.prepareStatement("INSERT INTO answer"
					+ "(quest_id, choices, inp_date, upd_date) "
					+ "VALUES(?, ?, current_date, now())");

			stmt.setInt(1, q);
			stmt.setString(2, frm.getChoices11());

			// SQL実行
			stmt.executeUpdate();

			stmt.setInt(1, q);
			stmt.setString(2, frm.getChoices12());

			// SQL実行
			stmt.executeUpdate();

			stmt.setInt(1, q);
			stmt.setString(2, frm.getChoices13());

			// SQL実行
			stmt.executeUpdate();

			stmt.setInt(1, q);
			stmt.setString(2, frm.getChoices14());

			// SQL実行
			stmt.executeUpdate();

			if (!frm.getQuestion2().isEmpty()) {
				// 選択肢登録
				rs.close();
				// SQL文実行
				rs = stmt
						.executeQuery("SELECT quest_id FROM quest where  question"
								+ "= '" + frm.getQuestion2() + "'");

				// SQL実行結果表示
				while (rs.next()) {
					// ステートメント生成
					q = rs.getInt("quest_id");

				}

				// 悪いデータの登録方法
				stmt.close();
				// ステートメント生成
				stmt = con.prepareStatement("INSERT INTO answer"
						+ "(quest_id, choices, inp_date, upd_date) "
						+ "VALUES(?, ?, current_date, now())");

				// パラメータを設定
				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices21());

				// SQL実行
				stmt.executeUpdate();

				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices22());

				// SQL実行
				stmt.executeUpdate();

				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices23());

				// SQL実行
				stmt.executeUpdate();

				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices24());

				// SQL実行
				stmt.executeUpdate();
			}

			if (!frm.getQuestion3().isEmpty()) {
				// 選択肢登録
				rs.close();
				// SQL文実行
				rs = stmt
						.executeQuery("SELECT quest_id FROM quest where  question"
								+ "= '" + frm.getQuestion3() + "'");

				// SQL実行結果表示
				while (rs.next()) {
					// ステートメント生成
					q = rs.getInt("quest_id");

				}

				// 悪いデータの登録方法
				stmt.close();
				// ステートメント生成
				stmt = con.prepareStatement("INSERT INTO answer"
						+ "(quest_id, choices, inp_date, upd_date) "
						+ "VALUES(?, ?, current_date, now())");

				// パラメータを設定
				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices31());

				// SQL実行
				stmt.executeUpdate();

				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices32());

				// SQL実行
				stmt.executeUpdate();

				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices33());

				// SQL実行
				stmt.executeUpdate();

				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices34());

				// SQL実行
				stmt.executeUpdate();
			}

			if (!frm.getQuestion4().isEmpty()) {
				// 選択肢登録
				rs.close();
				// SQL文実行
				rs = stmt
						.executeQuery("SELECT quest_id FROM quest where  question"
								+ "= '" + frm.getQuestion4() + "'");

				// SQL実行結果表示
				while (rs.next()) {
					// ステートメント生成
					q = rs.getInt("quest_id");

				}

				// 悪いデータの登録方法
				stmt.close();
				// ステートメント生成
				stmt = con.prepareStatement("INSERT INTO answer"
						+ "(quest_id, choices, inp_date, upd_date) "
						+ "VALUES(?, ?, current_date, now())");

				// パラメータを設定
				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices41());

				// SQL実行
				stmt.executeUpdate();

				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices42());

				// SQL実行
				stmt.executeUpdate();

				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices43());

				// SQL実行
				stmt.executeUpdate();

				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices44());

				// SQL実行
				stmt.executeUpdate();
			}

			if (!frm.getQuestion5().isEmpty()) {
				// 選択肢登録
				rs.close();
				// SQL文実行
				rs = stmt
						.executeQuery("SELECT quest_id FROM quest where  question"
								+ "= '" + frm.getQuestion5() + "'");

				// SQL実行結果表示
				while (rs.next()) {
					// ステートメント生成
					q = rs.getInt("quest_id");

				}

				// 悪いデータの登録方法
				stmt.close();
				// ステートメント生成
				stmt = con.prepareStatement("INSERT INTO answer"
						+ "(quest_id, choices, inp_date, upd_date) "
						+ "VALUES(?, ?, current_date, now())");

				// パラメータを設定
				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices51());

				// SQL実行
				stmt.executeUpdate();

				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices52());

				// SQL実行
				stmt.executeUpdate();

				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices53());

				// SQL実行
				stmt.executeUpdate();

				stmt.setInt(1, q);
				stmt.setString(2, frm.getChoices54());

				// SQL実行
				stmt.executeUpdate();
			}
			stmt.close();
			// ステートメント生成
			stmt = con
					.prepareStatement("update point set point = point -2 where user_id = ?");

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

			if (rs != null) {
				rs.close();
			}

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
