package bank;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import bank.form.BankForm;

public class TransHistoryAction extends LookupDispatchAction {
	protected Map<String, String> getKeyMethodMap() {
	    // Mapオブジェクト生成し、メッセージキーとメソッド名格納
	     Map<String, String> map = new HashMap<String, String>();
	     map.put("back", "backPage");
	     map.put("next", "nextPage");
	     map.put("menu", "menuPage");
	     map.put("transcation", "transcationPage");
	     map.put("zandaka", "zandakaPage");
	     map.put("outstanding", "outstandingPage");

	     return map;
	}

	public ActionForward backPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		// フォームからoffset情報取得
		BankForm frm =(BankForm)form;
        int offset = frm.getOffset().intValue();

        // offset情報を更新
        request.setAttribute("offset", Integer.toString(offset - 20));

		return mapping.findForward("back");
	}

	public ActionForward nextPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		// フォームからoffset情報取得
		BankForm frm =(BankForm)form;

        int offset = 0;
        if (frm.getOffset() != null) {
            offset = frm.getOffset().intValue();
        }

        // offset情報を更新
        request.setAttribute("offset", Integer.toString(offset + 20));

		return mapping.findForward("next");
	}

	public ActionForward zandakaPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		request.removeAttribute("offset");
		HttpSession session = request.getSession();
		session.removeAttribute("HistoryBean");
		return mapping.findForward("zandaka");
	}

	public ActionForward transcationPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		request.removeAttribute("offset");
		HttpSession session = request.getSession();
		session.removeAttribute("HistoryBean");
		return mapping.findForward("transcation");
	}

	public ActionForward outstandingPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		request.removeAttribute("offset");
		HttpSession session = request.getSession();
		session.removeAttribute("HistoryBean");
		return mapping.findForward("outstanding");
	}

	public ActionForward menuPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		request.removeAttribute("offset");
		HttpSession session = request.getSession();
		session.removeAttribute("HistoryBean");
		return mapping.findForward("menu");
	}

}
