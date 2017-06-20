package mogipuro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

public class DelitiAction extends LookupDispatchAction {

	// getKeyMethodMapメソッド
	@Override
	protected Map<String, String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
		Map<String, String> map = new HashMap<String, String>();
		map.put("ditinext", "nextPage");
		map.put("back", "backPage");
		return map;
	}

	// 次へ遷移するためのnextPageメソッド
	public ActionForward nextPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// formをキャストする
		MakeForm frm = (MakeForm) form;
		// 何もチャックされていないときにエラーメッセージが出る処理
		if (frm.getRadio() == null || frm.getRadio().equals("")) {
			// メッセージの設定
			ActionMessages errors = new ActionMessages();

			errors.add("select", new ActionMessage("error.select"));

			saveErrors(request, errors);
			// 検索画面遷移のためのパラメータ設定
			return mapping.findForward("error");

		} else{

			// 画面遷移
			return mapping.findForward("next");
		}
	}

	// 前へ遷移するためのbackPageメソッド
	public ActionForward backPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		session.removeAttribute("MakeForm");

		// 画面遷移
		return mapping.findForward("back");
	}

}