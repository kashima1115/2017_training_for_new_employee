package bank;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

import bean.CreateAccountForm;

public class CreateAccountAction extends LookupDispatchAction {

	protected Map<String, String> getKeyMethodMap() {
	    // Mapオブジェクト生成し、メッセージキーとメソッド名格納
	     Map<String, String> map = new HashMap<String, String>();
	     map.put("conf", "confPage");
	     map.put("return", "returnPage");

	     return map;
	}

	public ActionForward confPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		// フォーム情報取得
		CreateAccountForm frm = (CreateAccountForm)form;
        ActionMessages errors = new ActionMessages();//エラーリスト
        if(frm.getbYear().equals("")||frm.getfMonth().equals("")||frm.getfDate().equals("")){//生年月日空白チェック
        	errors.add("birthday.required", new ActionMessage("error.birthday.required"));
        	saveErrors(request,errors);
        	return mapping.findForward("return");//口座開設画面へ
        }
        if(frm.getPassword().equals(frm.getPasswordconf())){//パスワード一致チェック
        	if(frm.getPassno().equals(frm.getPassnoconf())){//暗証番号一致チェック
        		return mapping.findForward("conf");//確認画面へ
        	}else{
        		errors.add("passno.wrong", new ActionMessage("error.passno.wrong"));//暗証番号ミス
            	saveErrors(request,errors);
            	return mapping.findForward("return");//口座開設画面へ
        	}
        }else{
        	if(frm.getPassno().equals(frm.getPassnoconf())){//パスワードのみミス
        		errors.add("password.wrong", new ActionMessage("error.password.wrong"));
            	saveErrors(request,errors);
            	return mapping.findForward("return");
        	}else{//両方ミス
        		errors.add("password.wrong", new ActionMessage("error.password.wrong"));
        		errors.add("passno.wrong", new ActionMessage("error.passno.wrong"));
            	saveErrors(request,errors);
            	return mapping.findForward("return");
        	}
        }

	}

	public ActionForward returnPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("return");
	}
}
