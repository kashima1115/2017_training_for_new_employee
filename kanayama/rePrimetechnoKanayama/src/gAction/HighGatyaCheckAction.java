package gAction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import gatyabean.Play10Bean;

public class HighGatyaCheckAction extends LookupDispatchAction {

	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("stopLabel", "playPage");
		map.put("gatyaLabel2", "resultPage");
		return map;
	}

	//ガチャ選択画面へ戻る処理
	public ActionForward playPage(ActionMapping mapping, ActionForm form,
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

		return mapping.findForward("playPage");
	}

	//結果画面へと遷移する処理
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

		//10回分の抽選結果を格納するリストを作成
		List<Play10Bean> gList=new ArrayList<Play10Bean>();


		//10回回し、ユニット名とその画像のパスを格納
		for(int i=1;i<=10;i++){
			Play10Bean p10b=new Play10Bean();

			//乱数の取得
			RandomChoose rChoose=new RandomChoose();
			int select=rChoose.randomNum();

			//乱数を基にガチャの景品を取得
			Integer selected=new Integer(select);
			GItemList gil=new GItemList();
			String item=gil.selectedItem(selected);
			String image=gil.selectedImage(selected);

			//beanに格納
			p10b.setItem(item);
			p10b.setImage(image);
			p10b.setSelect(select);

			gList.add(p10b);
		}

		session.setAttribute("gList", gList);

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

		return new RedirectingActionForward("/Redirect10Check.do");
	}
}
