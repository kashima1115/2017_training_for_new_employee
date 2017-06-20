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
//ログアウト処理、若しくは、管理者メニュー画面に遷移する処理を行うクラス
public class NewAdminCompleteAction extends LookupDispatchAction {

	@Override
	protected Map<String,String> getKeyMethodMap() {
		Map<String,String> map=new HashMap<String,String>();
		map.put("adminMenu", "adminMenuPage");
		map.put("logoutLabel", "loginPage");
		return map;
	}

	//管理メニュー画面に遷移する処理を行う
	public ActionForward adminMenuPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//入力されたフォーム情報をセッションを削除
		HttpSession session = request.getSession();
		if(session!=null){
			session.removeAttribute("NewUserForm");
		}

		return mapping.findForward("adminMenuPage");
	}

	//ログアウト処理を行う
	public ActionForward loginPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		session.invalidate();
		return mapping.findForward("loginPage");
	}
}
