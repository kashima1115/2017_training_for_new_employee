package bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bank.form.BankForm;
import bean.HistoryBean;

public class SelectTransHistoryAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		//フォーム情報取得
		BankForm frm =(BankForm)form;
		//struts-config.xmlに設定したDataSource取得
		DataSource source =getDataSource(request);
		// データベース処理関連変数の定義
		Connection con = null;
		Statement stmt = null;
		Statement stmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String user_id=null;
		String inID = null;
		String outID=null;
		String furigana = null;
		String accountNo=null;
		String cash=null;
		List<HistoryBean> list = new ArrayList<HistoryBean>();
		try{
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();
			String sqlstr="SELECT user_id,account_balance FROM user WHERE account_no='"+frm.getAccountNumber()+"'";
			rs=stmt.executeQuery(sqlstr);
			while(rs.next()){
				user_id=rs.getString(1);
				cash=rs.getString(2);
			}
			if(rs != null){
    			rs.close();
    		}
			sqlstr="SELECT trans_date,user_id,other_id,trans_cash FROM transcation WHERE user_id";
			sqlstr+="='"+user_id+"' AND trans_flag='0' OR other_id ='"+user_id+"' AND trans_flag='0' ORDER BY trans_id DESC";
			rs=stmt.executeQuery(sqlstr);
			while(rs.next()){
				HistoryBean hb = new HistoryBean();
				hb.setTransDate(rs.getString(1));
				hb.setTransCash(rs.getString(4));
				outID=rs.getString(2);
				inID=rs.getString(3);
				hb.setLastBalance(cash);//最新の残高をhbへ
				if(inID.equals(user_id)){//入出フラグ判定
					hb.setIOFlag("i");
					hb.setOtherID(outID);
					int calk=Integer.parseInt(cash)-Integer.parseInt(rs.getString(4));
					cash=String.valueOf(calk);//次の取引履歴に渡す残高をここで計算
				}else{
					hb.setIOFlag("o");
					hb.setOtherID(inID);
					int calk=Integer.parseInt(cash)+Integer.parseInt(rs.getString(4));
					cash=String.valueOf(calk);
				}
				String sqlstr2 ="SELECT furigana,account_no FROM user WHERE user_id='";
				if(inID.equals(user_id)){
					sqlstr2+=outID+"'";
				}else{
					sqlstr2+=inID+"'";
				}
				stmt2= con.createStatement();
				rs2=stmt2.executeQuery(sqlstr2);
				while(rs2.next()){
					furigana=rs2.getString(1);
					accountNo=rs2.getString(2);
					hb.setFurigana(furigana);
					hb.setOtherAccountNo(accountNo);
				}
				if(rs2 != null){
	    			rs2.close();
	    		}
	    		if(stmt2 != null){
	    			stmt2.close();
	    		}
	    		list.add(hb);
			}
			Collections.reverse(list);
			HttpSession session = request.getSession();
			session.setAttribute("historyList", list);
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
		return mapping.findForward("transHistory");
	}
}
