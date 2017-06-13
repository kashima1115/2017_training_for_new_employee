package bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.apache.struts.actions.LookupDispatchAction;

import bank.form.BankForm;

public class TranscationAction extends LookupDispatchAction{

	protected Map<String, String> getKeyMethodMap() {
	    // Mapオブジェクト生成し、メッセージキーとメソッド名格納
	     Map<String, String> map = new HashMap<String, String>();
	     map.put("transcationconf", "transcationconfPage");
	     map.put("zandaka", "zandakaPage");
	     map.put("transHistory", "transHistoryPage");
	     map.put("menu", "menuPage");

	     return map;
	}

	public ActionForward transcationconfPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		//フォーム情報取得
		BankForm frm =(BankForm)form;
		//エラー用
		ActionMessages errors = new ActionMessages();
		//振込額チェック
		if(Long.parseLong(frm.getFurikomi())<=0){
			errors.add("furikomi.longrange",new ActionMessage("error.furikomi.longrange"));
			saveErrors(request,errors);
			return mapping.findForward("transcationerror");
		}
		frm.setFurikomigaku(Long.parseLong(frm.getFurikomi()));
		//振込日日付チェック
		Calendar cal=Calendar.getInstance();
		int month= cal.get(Calendar.MONTH)+1;
		int date=cal.get(Calendar.DATE);
		String dateC =frm.getfYear()+"-"+frm.getfMonth()+"-"+frm.getfDate();
		if(Integer.parseInt(frm.getfMonth())<month){
			errors.add("furikomibi.past",new ActionMessage("error.furikomibi.past"));
			saveErrors(request,errors);
			return mapping.findForward("transcationerror");
		}else if(Integer.parseInt(frm.getfMonth())==month && Integer.parseInt(frm.getfDate())<date){
			errors.add("furikomibi.past",new ActionMessage("error.furikomibi.past"));
			saveErrors(request,errors);
			return mapping.findForward("transcationerror");
		}else{//不正な日付チェック
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			try{
				format.setLenient(false);
				format.parse(dateC);
			}catch(Exception e){
				e.printStackTrace();
				errors.add("furikomibi.past" ,new ActionMessage("error.furikomibi.past"));
				saveErrors(request,errors);
				return mapping.findForward("transcationerror");
			}
		}
		//口座番号チェック（自分の口座番号を入力してしまったら）
		if(frm.getAccountNumber().equals(frm.getOtherAccountNumber())){
			errors.add("otherAccount.notfound",new ActionMessage("error.otherAccount.notfound"));
			saveErrors(request,errors);
			return mapping.findForward("transcationerror");
		}
		//データベースから情報取得
		//struts-config.xmlに設定したDataSource取得
		DataSource source =getDataSource(request);
		// データベース処理関連変数の定義
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String furigana="データナシ";
//		String accountNo=null;
		try{
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();
			String sqlstr ="SELECT furigana,account_no,property from user WHERE account_no='";
			sqlstr+=frm.getOtherAccountNumber() +"'";
			rs=stmt.executeQuery(sqlstr);
			while(rs.next()){
				furigana=rs.getString(1);
//				accountNo=rs.getString(2);
			}
			if(furigana.equals(frm.getKatakana())){
				HttpSession session = request.getSession();
				session.setAttribute("BankForm", form);

				return mapping.findForward("transcationconf");
			}else{
				errors.add("otherAccount.notfound" , new ActionMessage("error.otherAccount.notfound"));
				saveErrors(request,errors);
				return mapping.findForward("transcationerror");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(con != null){
				con.close();
			}

		}

	}

	public ActionForward zandakaPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("zandaka");
	}
	public ActionForward transHistoryPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("transHistory");
	}
	public ActionForward menuPage(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		return mapping.findForward("menu");
	}
}
