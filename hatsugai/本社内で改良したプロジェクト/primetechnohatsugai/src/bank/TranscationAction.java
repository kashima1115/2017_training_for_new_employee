package bank;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class TranscationAction extends LookupDispatchAction{

	protected Map<String, String> getKeyMethodMap() {
	    // Mapオブジェクト生成し、メッセージキーとメソッド名格納
	     Map<String, String> map = new HashMap<String, String>();
	     map.put("transcationconf", "transcationconfPage");
	     map.put("zandaka", "zandakaPage");
	     map.put("transHistory", "transHistoryPage");
	     map.put("menu", "menuPage");

	     return map;
	}

	public ActionForward transcationconfPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("transcationconf");//CheckSelectTranscationに移動
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
		return mapping.findForward("transHistory");//取引履歴照会画面に遷移
	}
	public ActionForward menuPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("menu");//メニュー画面に遷移
	}
}
