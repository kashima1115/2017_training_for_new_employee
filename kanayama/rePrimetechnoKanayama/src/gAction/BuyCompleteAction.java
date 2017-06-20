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

public class BuyCompleteAction extends LookupDispatchAction {

	@Override
	protected Map<String, String> getKeyMethodMap() {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("menuLabel", "gMenuPage");
		 map.put("logoutLabel", "gLoginPage");

		return map;
	}

	public ActionForward gMenuPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//石の所持数表示を更新
		HttpSession session = request.getSession();

		if(session!=null){
			session.removeAttribute("HopeBuyForm");
			session.removeAttribute("afterChip");
		}

		return mapping.findForward("gMenuPage");
	}

	public ActionForward gLoginPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//ログアウト処理
		HttpSession session = request.getSession();
		session.invalidate();
		return mapping.findForward("gLoginPage");
	}

}
