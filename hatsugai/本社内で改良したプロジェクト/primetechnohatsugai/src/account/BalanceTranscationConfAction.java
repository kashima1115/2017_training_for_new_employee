package account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class BalanceTranscationConfAction extends LookupDispatchAction {
	@Override
	protected Map<String, String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
	     Map<String, String> map = new HashMap<String, String>();
	     map.put("kaiyaku", "kaiyakuPage");
	     map.put("return", "returnPage");
	     map.put("menu", "menuPage");
		return map;
	}

	public ActionForward kaiyakuPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {

		return mapping.findForward("kaiyaku");//解約完了画面に遷移
	}

	public ActionForward returnPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("return");//残金振込先入力画面に遷移
	}

	public ActionForward menuPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("menu");//メニュー画面に遷移
	}

}
