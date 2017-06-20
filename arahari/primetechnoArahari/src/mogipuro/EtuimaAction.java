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

public class EtuimaAction extends LookupDispatchAction {

	// getKeyMethodMapメソッド
	@Override
	protected Map<String, String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
		Map<String, String> map = new HashMap<String, String>();
		map.put("esnext", "nextPage");
		map.put("back", "backPage");
		return map;
	}

	// 次へ遷移するためのnextPageメソッド
	public ActionForward nextPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// formをキャストする
		MakeForm frm = (MakeForm) form;
		// 何もチャックされていないときにエラーメッセージが出る処理
		if (frm.getRadio() == null || frm.getRadio().equals("")) {
			// メッセージの設定
			ActionMessages errors = new ActionMessages();

			errors.add("select", new ActionMessage("error.select"));

			saveErrors(request, errors);
			// 検索画面遷移のためのパラメータ設定
			return mapping.findForward("error");

		} else {

			// struts-config.xmlに設定したDataSource取得
			DataSource source = getDataSource(request);

			// データベース処理関連変数の定義
			List<MakeForm> list = new ArrayList<MakeForm>();
			List<MakeForm> list2 = new ArrayList<MakeForm>();

			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			ResultSet rset = null;
			ResultSet rst = null;
			Statement stmt2 = null;
			ResultSet rt = null;
			Statement stmt3 = null;

			try {
				// DB接続
				con = source.getConnection();
				// ステートメント生成
				stmt = con.createStatement();
				stmt2 = con.createStatement();
				stmt3 = con.createStatement();
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
					MakeForm mbn = new MakeForm();
					// ステートメント生成
					q = rset.getInt("quest_id");
					// 取得データ格納
					mbn.setQuestion(rset.getString("question"));
					list.add(mbn);
					// 取得した質問IDを元に選択肢と選択された回数を取得
					// SQL文実行
					rst = stmt2.executeQuery("SELECT * FROM answer where quest_id " + "= '" + q + "'");

					while (rst.next()) {
						MakeForm bn = new MakeForm();
						// 取得データ格納
						bn.setChoice(rst.getString("choices"));
						bn.setSelect(rst.getString("select_id"));
						bn.setAnswered(rst.getInt("answered"));
						list.add(bn);
					}

				}
				// アンケートIDを元に回答者数を取得
				rt = stmt3.executeQuery("SELECT * FROM co where ank_id " + "= '" + i + "'");

				while (rt.next()) {
					MakeForm mn = new MakeForm();

					// 取得データ格納
					mn.setKaitou(rt.getInt("kaitou"));
					list2.add(mn);
				}

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
			HttpSession session = request.getSession(true);
			// 取得した商品情報をリクエストにセット
			session.setAttribute("kekkaList", list);
			session.setAttribute("kaitouList", list2);

			// 画面遷移
			return mapping.findForward("next");
		}
	}

	// 前へ遷移するためのbackPageメソッド
	public ActionForward backPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		session.removeAttribute("MakeForm");

		// 画面遷移
		return mapping.findForward("back");
	}

}
