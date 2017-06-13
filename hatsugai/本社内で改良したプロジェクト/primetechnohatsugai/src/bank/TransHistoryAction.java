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
	     map.put("search", "searchPage");
	     map.put("resetSearch", "resetSearchPage");

	     return map;
	}

	public ActionForward backPage(ActionMapping mapping//戻るボタンを押したときの動作
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		// フォームからoffset、view情報取得
		BankForm frm =(BankForm)form;
        int offset = frm.getOffset().intValue();
        int view = frm.getView().intValue();

        //offset < viewならviewもその分の短さにする
        if(offset < view){
        	view=offset;
        }

        // offset情報を更新、view分前のデータから表示されるようにする
        request.setAttribute("offset", Integer.toString(offset - frm.getView()));
        request.setAttribute("view", Integer.toString(view));
		return mapping.findForward("back");//取引履歴照会画面に遷移
	}

	public ActionForward nextPage(ActionMapping mapping//次へボタンを押したときの動作
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		// フォームからoffset情報取得
		BankForm frm =(BankForm)form;

        int offset = 0;
        if (frm.getOffset() != null) {
            offset = frm.getOffset().intValue();
        }

        // offset情報を更新view分だけ増加
        request.setAttribute("offset", Integer.toString(offset + frm.getView()));
        request.setAttribute("view", Integer.toString(frm.getView()));

		return mapping.findForward("next");//取引履歴照会画面に遷移
	}

	public ActionForward zandakaPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		request.removeAttribute("offset");//offsetをrequestから消去
		// フォームからoffset情報取得
		BankForm frm =(BankForm)form;
		frm.setOffset(null);//offsetをリセット
		HttpSession session = request.getSession();
		session.removeAttribute("HistoryBean");//セッションから取引履歴のデータを消去
		return mapping.findForward("zandaka");//残高照会画面に遷移
	}

	public ActionForward transcationPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		request.removeAttribute("offset");//offsetをrequestから消去
		// フォームからoffset情報取得
		BankForm frm =(BankForm)form;
		frm.setOffset(null);//offsetをリセット
		HttpSession session = request.getSession();
		session.removeAttribute("HistoryBean");//セッションから取引履歴のデータを消去
		return mapping.findForward("transcation");//振込申し込み画面に遷移
	}

	public ActionForward outstandingPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		request.removeAttribute("offset");//offsetをrequestから消去
		// フォームからoffset情報取得
		BankForm frm =(BankForm)form;
		frm.setOffset(null);//offsetをリセット
		HttpSession session = request.getSession();
		session.removeAttribute("HistoryBean");//セッションから取引履歴のデータを消去
		return mapping.findForward("outstanding");//未決済取引照会画面に遷移
	}

	public ActionForward menuPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		request.removeAttribute("offset");//offsetをrequestから消去
		// フォームからoffset情報取得
		BankForm frm =(BankForm)form;
		frm.setOffset(null);//offsetをリセット
		HttpSession session = request.getSession();
		session.removeAttribute("HistoryBean");//セッションから取引履歴のデータを消去
		return mapping.findForward("menu");//メニュー画面に遷移
	}

	public ActionForward searchPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		request.removeAttribute("offset");//offsetをrequestから消去
		// フォームからoffset情報取得
		BankForm frm =(BankForm)form;
		frm.setOffset(null);//offsetをリセット
		HttpSession session = request.getSession();
		session.removeAttribute("HistoryBean");//セッションから取引履歴のデータを消去
		return mapping.findForward("search");//取引履歴照会画面に遷移
	}

	public ActionForward resetSearchPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		request.removeAttribute("offset");//offsetをrequestから消去
		request.removeAttribute("view");//viewをrequestから消去
		// フォームからoffset情報取得
		BankForm frm =(BankForm)form;
		frm.setOffset(null);//offsetをリセット
		frm.settYear(null);
		frm.settMonth(null);
		frm.settDate(null);
		frm.setTnYear(null);
		frm.setTnMonth(null);
		frm.setTnDate(null);
		HttpSession session = request.getSession();
		session.removeAttribute("HistoryBean");//セッションから取引履歴のデータを消去
		return mapping.findForward("resetSearch");//取引履歴照会画面に遷移
	}

}
