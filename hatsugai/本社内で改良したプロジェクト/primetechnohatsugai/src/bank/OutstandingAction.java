package bank;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class OutstandingAction extends LookupDispatchAction {

	@Override
	protected Map<String, String> getKeyMethodMap() {
		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
	     Map<String, String> map = new HashMap<String, String>();
	     map.put("menu", "menuPage");
	     map.put("transHistory", "transHistoryPage");
	     map.put("deleteOutstandingConf", "deleteOutstandingConfPage");
		return map;
	}

	public ActionForward menuPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("menu");//メニュー画面に遷移
	}

	public ActionForward transHistoryPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("transHistory");//取引履歴画面に遷移
	}

	public ActionForward deleteOutstandingConfPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		super.saveToken(request);//トークンの発行
		return mapping.findForward("deleteOutstandingConf");//削除確認画面に遷移
	}

}
