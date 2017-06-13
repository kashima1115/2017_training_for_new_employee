package gAction;

import gForm.HopeBuyForm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.RedirectingActionForward;
import org.apache.struts.actions.LookupDispatchAction;

public class BuyCheckAction extends LookupDispatchAction {

	@Override
	protected Map<String, String> getKeyMethodMap() {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("return", "hopePage");
		 map.put("buyChip", "buyComplete");
		return map;
	}

	public ActionForward hopePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//ブラウザバックによる不正な操作防止
		if(!super.isTokenValid(request, true)){
			HttpSession session = request.getSession();
			session.invalidate();
			ActionMessages errors = new ActionMessages();
			errors.add("abnormal",new ActionMessage("abnormalOperate"));
			saveErrors(request, errors);
			return mapping.findForward("loginPage");
		}

		//購入後枚数を格納しているセッションを削除
		HttpSession session = request.getSession();
		if(session!=null){
			session.removeAttribute("afterChip");
		}
		return mapping.findForward("hopePage");
	}

	public ActionForward buyComplete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//ブラウザバックによる不正な操作防止
		if(!super.isTokenValid(request, true)){
			HttpSession session = request.getSession();
			session.invalidate();
			ActionMessages errors = new ActionMessages();
			errors.add("abnormal",new ActionMessage("abnormalOperate"));
			saveErrors(request, errors);
			return mapping.findForward("loginPage");
		}


		//DBに購入データを登録
		DataSource source = getDataSource(request);
		try{

			HttpSession session = request.getSession();
			String logId=(String)session.getAttribute("logId");
			HopeBuyForm hbForm=(HopeBuyForm)form;
			BuyInfoToDb bitd=new BuyInfoToDb();
			bitd.buyInfo(source,hbForm,logId);
			int haveChip=(int)session.getAttribute("afterChip");
			session.setAttribute("haveChip", haveChip);

		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}

		//DBへの二重登録防止
		return new RedirectingActionForward("/RedirectBuyCheck.do");
	}

}
