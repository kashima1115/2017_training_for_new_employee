package gAction;

import gForm.NewUserForm;

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

public class NewUserCheckAction extends LookupDispatchAction {

	@Override
	protected Map<String,String> getKeyMethodMap() {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("return", "newUserPage");
		 map.put("regist", "finishPage");

		return map;
	}

	public ActionForward newUserPage(ActionMapping mapping, ActionForm form,
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

		return mapping.findForward("newUserPage");
	}

	public ActionForward finishPage(ActionMapping mapping, ActionForm form,
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

		//新規ユーザー情報のDB登録処理
		//入力フォーム情報を取得
		NewUserForm nuForm=(NewUserForm)form;
		//データソースを取得
		DataSource source = getDataSource(request);

		try{//入力情報をDBに登録
			NewUserUpToDb nuutd=new NewUserUpToDb();
			nuutd.userInsert(source, nuForm);

			//入力された情報をセッションにセットして、画面に表示できるようにする
			HttpSession session = request.getSession();
			session.setAttribute("logId", nuForm.getNewId());
			//チップ（石）の初期の所持数
			int haveChip=0;
			session.setAttribute("haveChip", haveChip);

			//入力されたform情報をセッションから削除
			session.removeAttribute("NewUserForm");


		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}

		//redirectを使ってDBの二重登録に対処
		return new RedirectingActionForward("/RedirectCheck.do");
	}

}
