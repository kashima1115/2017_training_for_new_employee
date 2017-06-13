package bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bean.CreateAccountForm;

public class InsertUserAccount extends Action {
@Override
	public ActionForward execute(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		//フォーム情報取得
		CreateAccountForm frm =(CreateAccountForm)form;
		//struts-config.xmlに設定したDataSource取得
		DataSource source =getDataSource(request);
		// データベース処理関連変数の定義
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int by=Integer.parseInt(frm.getbYear());
		int fm=Integer.parseInt(frm.getfMonth());
		int fd=Integer.parseInt(frm.getfDate());
		String birthday;
		birthday=by+"-"+String.format("%02d", fm)+"-"+String.format("%02d", fd);
		String uad;
		uad=frm.getuPrefAddress()+frm.getuCityAddress()+frm.getuConstructure();
		String uwad;
		uwad=frm.getwPrefAddress()+frm.getwCityAddress()+frm.getwConstructure();
		String lastAccountNO = null;
		int account;
		String sAccount;
		String accountNO = null;
		try{
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();
            // 自動コミットオフ
            con.setAutoCommit(false);
            String sqlstr="SELECT account_no FROM user ORDER BY account_no DESC LIMIT 1";
            rs=stmt.executeQuery(sqlstr);
            while(rs.next()){
            	lastAccountNO=rs.getString(1);
            }
            account = Integer.parseInt(lastAccountNO.substring(3, 10))+1;
            sAccount=String.format("%07d", account);
            accountNO = "001"+sAccount;
            sqlstr="INSERT INTO user (name,furigana,birthday,user_ad,tel,mobile,mail,property,user_work_nm,"
            		+ "user_work_ad,user_work_tel,account_no,passwd,passno,account_balance,inp_date) VALUES('"
            		+frm.getName() +"','"+ frm.getKatakana() +"','"+ birthday +"','" + uad +"','"+ frm.getTel()
            		+"','"+ frm.getMobile() +"','"+  frm.getMail() +"','"+ frm.getZokusei() +"','"
            		+ frm.getWorknm() +"','"+ uwad +"','"+ frm.getWorktel() +"','"+ accountNO +"','"
            		+ frm.getPassword() +"','"+ frm.getPassno() +"','0',CURRENT_DATE())";
            stmt.executeUpdate(sqlstr);
            //コミット
			con.commit();
			frm.setAccountNumber(accountNO);
		}catch(Exception e){
			e.printStackTrace();
			if(con != null){
				con.rollback();
			}
			throw e;
		}finally{
			if(stmt!=null){
				stmt.close();
			}
			if(rs != null){
				rs.close();
			}
			if(con != null){
				con.close();
			}
		}
	return mapping.findForward("create");
	}

}
