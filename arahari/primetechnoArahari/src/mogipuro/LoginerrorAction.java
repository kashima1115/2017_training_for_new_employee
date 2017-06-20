package mogipuro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class LoginerrorAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		// メッセージの設定
		ActionMessages errors = new ActionMessages();

		errors.add("logerror", new ActionMessage("error.login"));

		saveErrors(request, errors);

		// 検索画面遷移のためのパラメータ設定
		return mapping.findForward("login");

	}

}
