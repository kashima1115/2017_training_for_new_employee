package gAction;

import gForm.PlayGForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class PlayGAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//戻るボタンが押下されたときの処理
		if(this.isCancelled(request)){
		return mapping.findForward("gMenuPage");
	}

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
}
