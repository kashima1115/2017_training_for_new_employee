package mogipuro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class DelkanAction extends LookupDispatchAction {

	// getKeyMethodMapメソッド
	@Override
	protected Map<String, String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
		Map<String, String> map = new HashMap<String, String>();
		map.put("kannext", "nextPage");
		map.put("logout", "backPage");
		return map;
	}

	// 次へ遷移するためのnextPageメソッド
	public ActionForward nextPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(true);
		session.removeAttribute("MakeForm");

		// 画面遷移
		return mapping.findForward("home");
	}

	// 前へ遷移するためのbackPageメソッド
	public ActionForward backPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// セッションを切りログアウト
		HttpSession session = request.getSession();
		session.removeAttribute("LoginForm");
		session.removeAttribute("MakeForm");

		// 画面遷移
		return mapping.findForward("logout");
	}

}
