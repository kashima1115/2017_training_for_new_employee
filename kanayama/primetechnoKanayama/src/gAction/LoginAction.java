package gAction;

import gForm.LoginForm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

public class LoginAction extends Action {
	int buyChip;
	int useChip;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if(this.isCancelled(request)){
			HttpSession session = request.getSession();
			if(session!=null){
				session.removeAttribute("LoginForm");
			}

			return mapping.findForward("newUserPage");
		}

		//入力フォーム情報を取得
		LoginForm liForm=(LoginForm)form;

		//DB接続処理等
		DataSource source = getDataSource(request);
		Connection con=null;
		Statement state=null;
		ResultSet rset=null;

		try{
			con = source.getConnection();
			state=con.createStatement();

			//select文（DBに該当するアカウントがあるか探す）
			String sSql="select count(*) from game_user "+
			"where LOGIN_ID='"+liForm.getLoginId()+"' and "+
			"PASSWD='"+liForm.getPass()+"'";

			rset=state.executeQuery(sSql);

			int count=0;
			while(rset.next()){
				count=rset.getInt("count(*)");
			}

			if(count==0){
				ActionMessages errors = new ActionMessages();
				errors.add("notFoundAccount",new ActionMessage("error.notFoundAccount"));
				saveErrors(request, errors);

				return mapping.findForward("loginPage");
			}

			//当該ログインIDが石の所持数データを取得

			ChipLogCheck clc=new ChipLogCheck();
			int countLog=clc.buyLogCheck(source,liForm);

			if(countLog==0){
				buyChip=0;
			}else if(countLog>0){
				buyChip=clc.buyAmount(source, liForm);
			}

			countLog=clc.useLogCheck(source, liForm);
			if(countLog==0){
				useChip=0;
			}else if(countLog>0){
				useChip=clc.useAmount(source, liForm);
			}

			HttpSession session = request.getSession();
			int haveChip=buyChip-useChip;
			session.setAttribute("haveChip",haveChip);

			//ログインIDをセッションにセット
			String logId=liForm.getLoginId();
			session.setAttribute("logId", logId);


		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}finally{

			if(con!=null){
				con.close();
			}

			if(state!=null){
				state.close();
			}

			if(rset!=null){
				rset.close();
			}
		}


		return mapping.findForward("menuPage");
	}
}
