package mogipuro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class KaiankAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (this.isCancelled(request)) {
			// キャンセルボタン押下時の処理
			return mapping.findForward("back");
		} else {

			// formをキャストする
			MakeForm frm = (MakeForm) form;
			// 質問に一つもチェックされていないときのエラーチェック
			if (frm.getChoices() == null || frm.getChoices().equals("")) {
				// メッセージの設定
				ActionMessages errors = new ActionMessages();

				errors.add("check", new ActionMessage("error.check"));

				saveErrors(request, errors);
				// 検索画面遷移のためのパラメータ設定
				return mapping.findForward("error");

			} else {

				// 画面遷移
				return mapping.findForward("next");

			}

		}

	}
}
