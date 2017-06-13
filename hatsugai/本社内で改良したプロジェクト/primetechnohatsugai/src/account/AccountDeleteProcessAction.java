//package account;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionForward;
//import org.apache.struts.action.ActionMapping;
//import org.apache.struts.actions.LookupDispatchAction;
//
//public class AccountDeleteProcessAction extends LookupDispatchAction {
//
//	@Override
//	protected Map<String, String> getKeyMethodMap() {
//		// Mapオブジェクト生成し、メッセージキーとメソッド名格納
//	     Map<String, String> map = new HashMap<String, String>();
//	     map.put("conf", "confPage");
//	     map.put("return", "returnPage");
//	     map.put("deleteOutstandingConf", "deleteOutstandingConfPage");
//		return map;
//	}
//
//	public ActionForward confPage(ActionMapping mapping
//            , ActionForm form
//            , HttpServletRequest request
//            , HttpServletResponse response) throws Exception {
//		//AccountUserDAOでチェック
//		AccountUserDAO dao = new AccountUserDAO();
//
//		return mapping.findForward("conf");//残金振込先入力画面に遷移
//	}
//
//	public ActionForward returnPage(ActionMapping mapping
//            , ActionForm form
//            , HttpServletRequest request
//            , HttpServletResponse response) throws Exception {
//		return mapping.findForward("return");//メニュー画面に遷移
//	}
//}
