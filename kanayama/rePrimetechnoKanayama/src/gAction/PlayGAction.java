package gAction;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

import gForm.PlayGForm;

public class PlayGAction extends LookupDispatchAction {
	 @Override
	    protected Map<String, String> getKeyMethodMap() {
	       // Mapオブジェクト生成し、メッセージキーとメソッド名格納
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("gatyaLabel", "rate5Page");
	        map.put("gatyaLabel2", "rate50Page");
	        map.put("return", "menuPage");
	        return map;
	    }

	 //通常のガチャページへの遷移等の処理
	 public ActionForward rate5Page(ActionMapping mapping
	            , ActionForm form
	            , HttpServletRequest request
	            , HttpServletResponse response) throws Exception {
		//トークンの生成（ブラウザバックによる二重登録防止）
		 super.saveToken(request);

		//確認ボタンが押下されたときの処理
		//ガチャ実行後の所持チップ（石）を計算
		HttpSession session = request.getSession();
		PlayGForm pgForm=(PlayGForm)form;
		int haveChip=(int)session.getAttribute("haveChip");
		int chipRate=pgForm.getChipRate1();
		int afterUseChip=haveChip-chipRate;
		session.setAttribute("afterUseChip", afterUseChip);
		session.setAttribute("chipRate", chipRate);

		//チップ（石）の数が不足している場合、エラーメッセージを表示
		if(afterUseChip<0){
			ActionMessages errors = new ActionMessages();
			errors.add("haveChip_short",new ActionMessage("error.haveChip.short"));
			saveErrors(request, errors);
		}

		 return mapping.findForward("PlayCheckPage");
	 }

	 //10連ガチャを行うページへの遷移等の処理
	 public ActionForward rate50Page(ActionMapping mapping
	            , ActionForm form
	            , HttpServletRequest request
	            , HttpServletResponse response) throws Exception {

		//トークンの生成（ブラウザバックによる二重登録防止）
		 super.saveToken(request);

		HttpSession session = request.getSession();
		PlayGForm pgForm=(PlayGForm)form;
		int haveChip=(int)session.getAttribute("haveChip");
		int chipRate=pgForm.getChipRate2();
		int afterUseChip=haveChip-chipRate;
		session.setAttribute("afterUseChip", afterUseChip);
		session.setAttribute("chipRate", chipRate);

		//チップ（石）の数が不足している場合、エラーメッセージを表示
		if(afterUseChip<0){
			ActionMessages errors = new ActionMessages();
			errors.add("haveChip_short",new ActionMessage("error.haveChip.short"));
			saveErrors(request, errors);
		}

		return mapping.findForward("highPlayCheckPage");
	 }

	 //メニュー画面に戻る処理
	 public ActionForward menuPage(ActionMapping mapping
	            , ActionForm form
	            , HttpServletRequest request
	            , HttpServletResponse response) throws Exception {
		 HttpSession session = request.getSession();
		 if(session!=null){
			 session.removeAttribute("PlayGForm");
		 }
		 return mapping.findForward("gMenuPage");
	 }
}
