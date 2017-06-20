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

public class NewUserCompleteAction extends LookupDispatchAction {

	@Override
	protected Map<String,String> getKeyMethodMap() {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("menuLabel","gMenuPage");
		 map.put("logoutLabel", "loginPage");
		return map;
	}

	public ActionForward gMenuPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//メニュー画面に遷移
		return mapping.findForward("gMenuPage");
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
