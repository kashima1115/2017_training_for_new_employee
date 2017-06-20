package gAction;

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

public class GatyaCheckAction extends LookupDispatchAction {

	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("gatyaLabel", "resultPage");
		map.put("stopLabel", "gatyaPage");
		return map;
	}

	public ActionForward resultPage(ActionMapping mapping, ActionForm form,
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

		//sessionの取得
		HttpSession session = request.getSession();

		//乱数の取得
		RandomChoose rChoose=new RandomChoose();
		int select=rChoose.randomNum();

		//乱数を基にガチャの景品を取得
		Integer selected=new Integer(select);
		GItemList gil=new GItemList();
		String item=gil.selectedItem(selected);
		String image=gil.selectedImage(selected);

		//景品をリクエストにセット
		session.setAttribute("item", item);
		session.setAttribute("image",image);
		session.setAttribute("select",select);


		//DBにガチャ使用データを登録
		DataSource source = getDataSource(request);
		try{
			String logId=(String)session.getAttribute("logId");
			int chipRate=(int)session.getAttribute("chipRate");
			UseInfoToDb uitd=new UseInfoToDb();
			uitd.useInfo(source,logId,chipRate);
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}

		//チップ（石）の所持数表示を更新
		int haveChip=(int)session.getAttribute("afterUseChip");
		session.setAttribute("haveChip", haveChip);

		if(session!=null){
			session.removeAttribute("afterUseChip");
		}


		return new RedirectingActionForward("/RedirectGatyaCheck.do");
	}

	public ActionForward gatyaPage(ActionMapping mapping, ActionForm form,
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


		//やめるボタンが押下ときの処理
		HttpSession session = request.getSession();
		if(session!=null){
			session.removeAttribute("afterUseChip");
			session.removeAttribute("chipRate");
		}
		return mapping.findForward("gatyaPage");
	}
}
