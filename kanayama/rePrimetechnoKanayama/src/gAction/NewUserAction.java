package gAction;

import gForm.NewUserForm;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class NewUserAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//ログイン画面に遷移（Validationに引っかからないように対処）
		if(this.isCancelled(request)){
			HttpSession session = request.getSession();
			if(session!=null){
				session.removeAttribute("NewUserForm");
			}
			return mapping.findForward("loginPage");
		}


		//トークンの生成（ブラウザバックによる二重登録防止）
		 super.saveToken(request);

		//以下はログインIDが重複しているかのチェック
		//入力フォーム情報を取得
		NewUserForm nuForm=(NewUserForm)form;
		//データソースを取得
		DataSource source = getDataSource(request);

		int count=0;

		try{//ログインIDが被っているのならエラーメッセージ表示
			NewUserInfoToDb nuitb=new NewUserInfoToDb();
			count=nuitb.userInfo(source, nuForm);

		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}

		if(count>0){
			ActionMessages errors = new ActionMessages();
			errors.add("newid_double_error",new ActionMessage("error.newId.double"));
			saveErrors(request, errors);

			return mapping.findForward("newUserPage");
		}

		return mapping.findForward("checkPage");
	}
}
