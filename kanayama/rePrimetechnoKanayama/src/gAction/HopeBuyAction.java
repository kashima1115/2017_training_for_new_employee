package gAction;

import gForm.HopeBuyForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class HopeBuyAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//戻るボタンが押下されたときの処理
		if(this.isCancelled(request)){
			HttpSession session = request.getSession();
			if(session!=null){
				session.removeAttribute("HopeBuyForm");
			}
			return mapping.findForward("menuPage");
		}

		//トークンの生成（ブラウザバックによる二重登録防止）
		 super.saveToken(request);

		//以下、確認ボタンが押下されたときの処理
		HopeBuyForm hbForm=(HopeBuyForm)form;
		HttpSession session = request.getSession();

		//所持している石の数+追加する石の数＝購入後の石の数
		int haveChip=(int)session.getAttribute("haveChip");
		int buyChip=Integer.parseInt(hbForm.getBuyChip());
		int afterChip=haveChip+buyChip;

		session.setAttribute("afterChip", afterChip);


		return mapping.findForward("buyCheck");
	}

}
