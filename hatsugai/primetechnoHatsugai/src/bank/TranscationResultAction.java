package bank;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class TranscationResultAction extends LookupDispatchAction {
	protected Map<String, String> getKeyMethodMap() {
	    // Mapオブジェクト生成し、メッセージキーとメソッド名格納
	     Map<String, String> map = new HashMap<String, String>();
	     map.put("menu", "menuPage");
	     map.put("continueTranscation", "continueTranscationPage");

	     return map;
	}

	public ActionForward menuPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("menu");
	}

	public ActionForward continueTranscationPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("continueTranscation");
	}
}
