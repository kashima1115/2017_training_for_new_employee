package gAction;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class Gatya10ResultAction extends LookupDispatchAction {

	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("menuLabel", "menuPage");
		map.put("logoutLabel", "loginPage");
		map.put("gatyaLabel", "checkPage");
		map.put("gatyaLabel2", "checkPage");
		return map;
	}

	//ガチャ選択画面へ戻る処理
	public ActionForward menuPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		if (session!=null) {
			session.removeAttribute("gList");
		}
		return mapping.findForward("menuPage");
	}
	//ログアウト処理
	public ActionForward loginPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
		return mapping.findForward("loginPage");
	}
	//再度ガチャを行う処理
	public ActionForward checkPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		if (session!=null) {
			session.removeAttribute("gList");
		}

		return mapping.findForward("checkPage");
	}

}
