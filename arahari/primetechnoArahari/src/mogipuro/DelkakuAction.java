package mogipuro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class DelkakuAction extends LookupDispatchAction {

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

		// formをキャストする
		MakeForm frm = (MakeForm) form;

			// struts-config.xmlに設定したDataSource取得
			DataSource source = getDataSource(request);

			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			ResultSet rset = null;
			ResultSet rst = null;

			ResultSet rt = null;

			PreparedStatement stmt4 = null;

			try {
				// DB接続
				con = source.getConnection();

				// 自動コミットオフ
				con.setAutoCommit(false);

				// ステートメント生成
				stmt = con.createStatement();


				// ラジオボタンの情報を取得してアンケートIDを取得
				int i = 0;

				String sqlStr = "SELECT * FROM questionnaire where ank_name" + "= '" + frm.getRadio() + "'";
				// SQL文実行
				rs = stmt.executeQuery(sqlStr);

				// SQL実行結果表示
				while (rs.next()) {
					// ステートメント生成
					i = rs.getInt("ank_id");

				}
				// 取得したアンケートIDを元に質問と質問IDを取得
				int q = 0;
				// SQL文実行
				rset = stmt.executeQuery("SELECT * FROM quest where ank_id " + "= '" + i + "'");

				while (rset.next()) {

					// ステートメント生成
					q = rset.getInt("quest_id");


				}


//取得したデータを利用しDBから4つのテーブルのデータを削除

				// ステートメント生成
				stmt4 = con.prepareStatement("DELETE FROM co WHERE ank_id = ?;");

				stmt4.setInt(1, i);

				// SQL実行
				stmt4.executeUpdate();

				stmt4.close();

				// ステートメント生成
				stmt4 = con.prepareStatement("DELETE FROM answer WHERE quest_id = ?;");

				stmt4.setInt(1, q);

				// SQL実行
				stmt4.executeUpdate();

				stmt4.close();

				// ステートメント生成
				stmt4 = con.prepareStatement("DELETE FROM quest WHERE ank_id = ?;");

				stmt4.setInt(1, i);

				// SQL実行
				stmt4.executeUpdate();

				stmt4.close();

				// ステートメント生成
				stmt4 = con.prepareStatement("DELETE FROM questionnaire WHERE ank_id = ?;");

				stmt4.setInt(1, i);

				// SQL実行
				stmt4.executeUpdate();

				// コミット
				con.commit();


			} catch (SQLException ex) {
				ex.printStackTrace();
				throw ex;
			} finally {
				if (rt != null) {
					rt.close();
				}
				if (rst != null) {
					rst.close();
				}
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
