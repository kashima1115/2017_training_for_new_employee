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
		int by=Integer.parseInt(frm.getbYear());//誕生年をint型に変換
		int fm=Integer.parseInt(frm.getfMonth());//誕生月をint型に変換
		int fd=Integer.parseInt(frm.getfDate());//誕生日をint型に変換
		String birthday;//String型変数birthdayを宣言
		birthday=by+"-"+String.format("%02d", fm)+"-"+String.format("%02d", fd);//生年月日(yyyy,mm,dd)に整える
		String uad;//口座開設者の自宅住所をまとめるためのString型変数uadを宣言
		uad=frm.getuPrefAddress()+frm.getuCityAddress()+frm.getuConstructure();//自宅住所をまとめる
		String uwad;//口座開設者の勤務先住所をまとめるためのString型変数uwadを宣言
		uwad=frm.getwPrefAddress()+frm.getwCityAddress()+frm.getwConstructure();//勤務先住所をまとめる
		String lastAccountNO = null;//DB上の最後の口座番号を入れるString型変数lastAccountNOを宣言
		int account;//lastAccountNOをint型に変換するための変数accountを宣言
		String sAccount;//accountを7桁表示に整えるための変数sAccountを宣言
		String accountNO = null;//口座開設者に与えられる口座番号を格納するString型変数accountNOを宣言
		try{
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();
            // 自動コミットオフ
            con.setAutoCommit(false);
            //番号が一番大きい口座番号を取得
            String sqlstr="SELECT account_no FROM user ORDER BY account_no DESC LIMIT 1";
            rs=stmt.executeQuery(sqlstr);
            while(rs.next()){
            	lastAccountNO=rs.getString(1);//DB内の最大の口座番号をlastAccountNOに代入
            }
            //支店コード無しの口座番号を取得するため抜き出す
            account = Integer.parseInt(lastAccountNO.substring(3, 10))+1;//1追加し新たな口座番号とする
            //7桁の番号になるように整える
            sAccount=String.format("%07d", account);
            //支店コードを加える
            accountNO = "001"+sAccount;
            /*名前、フリガナ、生年月日、自宅住所、電話番号、携帯電話番号、メールアドレス、個人法人属性、
            勤務先名、勤務先住所、勤務先電話番号、口座番号、パスワード、パスワード（確認用）、暗証番号、暗証番号（確認用）
            口座残高（デフォルトは0円）、入力日付をsqlstrに代入*/
            sqlstr="INSERT INTO user (name,furigana,birthday,user_ad,tel,mobile,mail,property,user_work_nm,"
            		+ "user_work_ad,user_work_tel,account_no,passwd,passno,account_balance,inp_date) VALUES('"
            		+frm.getName() +"','"+ frm.getKatakana() +"','"+ birthday +"','" + uad +"','"+ frm.getTel()
            		+"','"+ frm.getMobile() +"','"+  frm.getMail() +"','"+ frm.getZokusei() +"','"
            		+ frm.getWorknm() +"','"+ uwad +"','"+ frm.getWorktel() +"','"+ accountNO +"','"
            		+ frm.getPassword() +"','"+ frm.getPassno() +"','0',CURRENT_DATE())";
            stmt.executeUpdate(sqlstr);//SQL実行（ステートメントで）
            //コミット
			con.commit();
			//ログイン画面に戻ったときに口座番号がフォームに出る
			frm.setAccountNumber(accountNO);
		}catch(Exception e){
			e.printStackTrace();
			if(con != null){
				//ロールバック
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
	return mapping.findForward("create");//口座開設完了画面へ遷移
	}

}
