package bank;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CreateAccountResultAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping//画面遷移
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		// 次の画面遷移のためのパラメータ設定
       return mapping.findForward("tologin");//ログイン画面へ遷移
   }

}
