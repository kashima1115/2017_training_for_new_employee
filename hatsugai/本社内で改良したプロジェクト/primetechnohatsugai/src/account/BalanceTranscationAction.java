package account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

import account.form.AccountForm;

public class BalanceTranscationAction extends LookupDispatchAction {
	@Override
	protected Map<String, String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
	     Map<String, String> map = new HashMap<String, String>();
	     map.put("conf", "confPage");
	     map.put("return", "returnPage");
		return map;
	}

	public ActionForward confPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {

		//フォーム情報取得
		AccountForm frm =(AccountForm)form;
		//エラーメッセージ格納用
		ActionMessages errors = new ActionMessages();
		//空白チェック
		if(frm.getBankcode().equals("")||frm.getBlanchcode().equals("")||frm.getOtherAccountNumber().equals("")){
			errors.add("accountnumber.required", new ActionMessage("error.accountnumber.required"));
			return mapping.findForward("");
		}

		return mapping.findForward("conf");//残金振込先確認画面に遷移
	}

	public ActionForward returnPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("return");//メニュー画面に遷移
	}

}
