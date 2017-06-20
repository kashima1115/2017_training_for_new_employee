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

public class EtukekkaAction extends LookupDispatchAction {

	// getKeyMethodMapメソッド
	@Override
	protected Map<String, String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
		Map<String, String> map = new HashMap<String, String>();
		map.put("home", "nextPage");
		map.put("etuback", "backPage");
		map.put("eiback", "eiPage");
		map.put("ekback", "ekPage");
		map.put("ejback", "ejPage");
		map.put("emback", "emPage");
		return map;
	}

	// 次へ遷移するためのnextPageメソッド
	public ActionForward nextPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// セッションを終了してhome画面に遷移
		HttpSession session = request.getSession(true);
		session.removeAttribute("MakeForm");

		// 画面遷移
		return mapping.findForward("home");
	}

	// 前へ遷移するためのbackPageメソッド
	public ActionForward backPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 画面遷移
		return mapping.findForward("etuback");
	}

	// 前へ遷移するためのbackPageメソッド
	public ActionForward eiPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 画面遷移
		return mapping.findForward("eiback");
	}

	// 前へ遷移するためのbackPageメソッド
	public ActionForward ekPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 画面遷移
		return mapping.findForward("ekback");
	}

	// 前へ遷移するためのbackPageメソッド
	public ActionForward ejPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 画面遷移
		return mapping.findForward("ejback");
	}

	// 前へ遷移するためのbackPageメソッド
	public ActionForward emPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 画面遷移
		return mapping.findForward("emback");
	}

}
