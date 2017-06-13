package bank;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class HeaderAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping//画面遷移
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		//フォーム全消去
		HttpSession session = request.getSession();
		session.removeAttribute("BankForm");
		session.removeAttribute("HistoryBean");
		session.removeAttribute("OutstandingBean");
		session.removeAttribute("CreateAccountForm");
		// 次の画面遷移のためのパラメータ設定
		return mapping.findForward("logout");//ログアウト画面へ遷移
   }
}
