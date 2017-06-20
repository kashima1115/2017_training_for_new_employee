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

public class GMenu extends LookupDispatchAction {

	@Override
	protected Map<String, String> getKeyMethodMap() {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("buyChip", "buyPage");
		 map.put("playG", "playPage");
		 map.put("logoutLabel", "loginPage");
		return map;
	}

	public ActionForward buyPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("buyPage");
	}

	public ActionForward playPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("playPage");
	}

	public ActionForward loginPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//ログアウト処理

		HttpSession session = request.getSession();
		session.invalidate();
		return mapping.findForward("loginPage");
	}
}
