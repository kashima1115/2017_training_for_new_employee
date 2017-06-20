package gadminaction;

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

import accessdb.NewAdminUpToDb;
import gForm.NewUserForm;
//管理者ユーザーを登録する処理、若しくは、登録せずに戻る処理を行うクラス
public class NewAdminCheckAction extends LookupDispatchAction {

	@Override
	protected Map<String,String> getKeyMethodMap() {
		Map<String,String> map=new HashMap<String,String>();
		map.put("return", "newAdminPage");
		map.put("regist", "adminCompletePage");

		return map;
	}

	//戻るボタンが押下されたときの処理
	public ActionForward newAdminPage(ActionMapping mapping, ActionForm form,
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

		return mapping.findForward("newAdminPage");
	}

	//管理者ユーザーを登録する処理
	public ActionForward adminCompletePage(ActionMapping mapping, ActionForm form,
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

		//DB接続処理等
		DataSource source = getDataSource(request);
		try{

			//セッションとフォームを取得
			HttpSession session = request.getSession();
			NewUserForm nuForm=(NewUserForm)form;

			//DBに登録するクラスを呼び出す
			NewAdminUpToDb nautd=new NewAdminUpToDb();
			nautd.adminUp(source, nuForm);

			//セッションに管理者IDを格納
			session.setAttribute("logId", nuForm.getNewId());

		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}

		return new RedirectingActionForward("/RedirectAdminCheck.do");
	}
}
