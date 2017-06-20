package account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import account.form.AccountForm;

public class DeleteAccount extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		//フォーム情報取得
		AccountForm frm =(AccountForm)form;
		//struts-config.xmlに設定したDataSource取得
		DataSource source =getDataSource(request);
		// データベース処理関連変数の定義
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userID = null;
		String otherID = null;
		String zandaka = null;
		String lastAccountNO = null;
		int dAccount = 0;
		String dsAccount = null;
		String dAccountNO = null;
		try{
			//DB接続
			con = source.getConnection();
			//オートコミットオフ
			con.setAutoCommit(false);
			//SQL文を作成(顧客番号と残高を取り寄せ）
			String sql="SELECT user_id,account_balance FROM user WHERE account_no=?";
			//プリペアードステートメント生成
			pstmt = con.prepareStatement(sql);
			//値の設定
			pstmt.setString(1, frm.getAccountNumber());
			//SQL実行
			rs=pstmt.executeQuery();
			//値の取り出し
			while(rs.next()){
				userID=rs.getString(1);
				zandaka=rs.getString(2);
			}
			//一旦rsとpstmtをクローズ
			if(pstmt != null){
				pstmt.close();
			}
			if(rs != null){
				rs.close();
			}
			//番号が一番大きい口座番号を取得
            sql="SELECT account_no FROM user WHERE account_no LIKE '%000' ORDER BY account_no DESC LIMIT 1";
            //プリペアードステートメント再生成
            pstmt=con.prepareStatement(sql);
            //SQL実行
            rs=pstmt.executeQuery();
            while(rs.next()){
            	lastAccountNO=rs.getString(1);//DB内の最大の口座番号をlastAccountNOに代入
            }
            //支店コード無しの口座番号を取得するため抜き出す
            dAccount = Integer.parseInt(lastAccountNO.substring(3, 10))+1;//1追加し新たな口座番号とする
            //7桁の番号になるように整える
            dsAccount=String.format("%07d", dAccount);
            //支店コードを加える(000は管理用の支店コード)
            dAccountNO = "000"+dsAccount;
			//一旦rsとpstmtをクローズ
            if(pstmt != null){
				pstmt.close();
			}
			if(rs != null){
				rs.close();
			}
            //SQL文を新たに代入(残高振込の銀行情報を挿入)
			sql="INSERT INTO user(name,furigana,birthday,user_ad,property,account_no,passwd,passno,account_balance"
					+ ",tel,inp_date) VALUES(?,?,?,?,?,?,?,?,?,?,CURRENT_DATE())";
			//プリペアードステートメント再生成
            pstmt=con.prepareStatement(sql);
            //口座番号を設定(名前の箇所に挿入)
			int accountNumber=Integer.parseInt(frm.getOtherAccountNumber());
			String account=frm.getBlanchcode()+String.format("%07d", accountNumber);
            pstmt.setString(1, frm.getBankcode()+account);//金融機関コードと支店コード、口座番号はnameに入れる
			pstmt.setString(2, "ソウキンサキ");//furiganaに入れるダミーデータ
			pstmt.setString(3, "2000-01-01");//birthdayに入れるダミーデータ
			pstmt.setString(4, "送金先");//user_adに入れるダミーデータ
			pstmt.setInt(5, 1);//個人・法人属性は1(法人)を入れる
			pstmt.setString(6, dAccountNO);//口座番号はダミーで入れる
			pstmt.setString(7, "password");//passwdに入れるダミーデータ
			pstmt.setInt(8, 0000);//passnoに入れるダミーデータ
			pstmt.setInt(9, 0);//残高には0を入れる
			pstmt.setInt(10, 0000000000);//telにダミーデータを入れる
			//SQL実行
			pstmt.executeUpdate();
			//一旦pstmtをクローズ
			if(pstmt != null){
				pstmt.close();
			}
			//新たに作成した銀行のデータが入った顧客データから顧客IDを取得する
			sql="SELECT user_id FROM user WHERE account_no = ?";
			//プリペアードステートメント再生成
			pstmt=con.prepareStatement(sql);
			//値の設定
			pstmt.setString(1, dAccountNO);
			//SQL実行
			rs=pstmt.executeQuery();
			while(rs.next()){
				otherID=rs.getString(1);//振込先の顧客ID(銀行のもの)を取得
			}
			//一旦pstmtをクローズ
			if(pstmt != null){
				pstmt.close();
			}

			//振込の処理
			sql="INSERT INTO transcation(user_id,other_id,trans_cash,trans_flag,trans_date,inp_date) VALUES(?,?"
					+ ",?,?,?,CURRENT_DATE())";
			//プリペアードステートメント再生成
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userID);//顧客IDを入れる
			pstmt.setString(2, otherID);//振込先の顧客ID(銀行)を入れる
			pstmt.setString(3, zandaka);//現在の残高を入れる
			pstmt.setInt(4, 0);//直ちに振り込み処理をするため0を入れる
			//今日の日付を入れるためDateクラスとSimpleDateFormatクラスを使用
			Date nowDate = new Date();
			String strDate = new SimpleDateFormat("yyyy-MM-dd",Locale.US).format(nowDate);
			pstmt.setString(5, strDate);
			//SQL実行
			pstmt.executeUpdate();
			//一旦pstmtをクローズ
			if(pstmt != null){
				pstmt.close();
			}
			sql="UPDATE user SET birthday=?, user_ad=?, tel=?, mobile=?, mail=?, user_work_nm=?, user_work_ad=?, user_work_tel=?, passwd=?, passno=?, account_balance=? WHERE user_id = ?";
			//ステートメント再生成
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "2000-01-01");//birthdayのデータをダミーで上書き
			pstmt.setString(2, "削除済み");//user_adのデータをダミーで上書き
			pstmt.setInt(3, 0000000000);//telのデータを0000000000で上書き
			pstmt.setString(4, null);//mobileのデータをnullで上書き
			pstmt.setString(5, null);//mailのデータをnullで上書き
			pstmt.setString(6, null);//user_work_nmのデータをnullで上書き
			pstmt.setString(7, null);//user_work_adのデータをnullで上書き
			pstmt.setString(8, null);//user_work_telのデータをnullで上書き
			pstmt.setString(9, "deleted");//passwordのデータをdeletedと上書き
			pstmt.setInt(10, 0000);//passnoのデータを0000で上書き
			pstmt.setLong(11, 0);//account_balanceを0で上書き
			pstmt.setString(12, userID);//条件をuserIDにする
			//SQL実行
			pstmt.executeUpdate();
			//コミット
			con.commit();
			//formの各値をすべて消去する
			frm.reset(mapping, request);

		}catch(Exception e){
			e.printStackTrace();
			if(con != null){
				//SQL異常時にはロールバック
				con.rollback();
			}

		}finally{
			if(pstmt != null){
				pstmt.close();
			}
			if(rs != null){
				rs.close();
			}
			if(con != null){
				con.close();
			}
		}
		return mapping.findForward("deleteresult");//解約完了画面に遷移
	}

}
