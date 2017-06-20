package gadminaction;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import gAction.NewUserInfoToDb;
import gForm.NewUserForm;

//ログインID重複のエラーチェックと画面遷移処理
public class NewAdminAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//トークン生成（ブラウザバックによる二重登録防止）
		super.saveToken(request);

		/*DBにあるログインIDが重複していないかチェック
		 * （重複チェック処理は、通常のユーザーでの重複チェック処理の一部を流用）
		 * 重複がなければ、確認画面に遷移
		 */

		//入力フォーム情報を取得
		NewUserForm nuForm=(NewUserForm)form;
		//データソースを取得
		DataSource source = getDataSource(request);
		//重複チェック用変数
		int count=0;

		try{
			NewUserInfoToDb nuitb=new NewUserInfoToDb();
			count=nuitb.userInfo(source, nuForm);

		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}

		//重複発生時、入力フォーム画面でエラーメッセージ表示
		if(count>0){
			ActionMessages errors = new ActionMessages();
			errors.add("newid_double_error",new ActionMessage("error.newId.double"));
			saveErrors(request, errors);

			return mapping.findForward("thisPage");
		}

		return mapping.findForward("newAdminCheckPage");
	}
}
