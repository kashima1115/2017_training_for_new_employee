package gadminaction;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
//管理者メニューページから他のページへと遷移するまでの処理を行うクラス
public class GAdminMenuAction extends LookupDispatchAction {

	@Override
	protected Map<String,String> getKeyMethodMap() {
		Map<String,String> map=new HashMap<String,String>();
		map.put("logoutLabel", "loginPage");
		map.put("newCharSet", "charAddPage");
		return map;
	}
	//ログアウト処理
	public ActionForward loginPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		session.invalidate();
		return mapping.findForward("loginPage");
	}
	//新ユニット登録ページに遷移
	public ActionForward charAddPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("loginPage");
	}

}
