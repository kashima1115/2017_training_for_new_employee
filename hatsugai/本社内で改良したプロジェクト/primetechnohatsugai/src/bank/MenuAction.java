package bank;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class MenuAction extends LookupDispatchAction {

	protected Map<String, String> getKeyMethodMap() {
	    // Mapオブジェクト生成し、メッセージキーとメソッド名格納
	     Map<String, String> map = new HashMap<String, String>();
	     map.put("zandaka", "zandakaPage");
	     map.put("transHistory", "transHistoryPage");
	     map.put("transcation", "transcationPage");
	     map.put("deleteAccount", "deleteAccountPage");


	     return map;
	}

	public ActionForward zandakaPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("zandaka");//残高照会画面に遷移
	}

	public ActionForward transHistoryPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("transHistory");//取引履歴画面に遷移
	}
	public ActionForward transcationPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("transcation");//振り込み申し込み画面に遷移
	}

	public ActionForward deleteAccountPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("deleteAccount");//口座解約確認画面に遷移
	}


}
