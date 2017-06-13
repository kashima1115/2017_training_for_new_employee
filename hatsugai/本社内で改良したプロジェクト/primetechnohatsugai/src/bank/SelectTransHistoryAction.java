package bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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

		//アクセス当日の日付がフォームに出るようにあらかじめ代入しておく
		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String month = String.valueOf(cal.get(Calendar.MONTH)+1);
		String date = String.valueOf(cal.get(Calendar.DATE));
		frm.settYear(year);//年
		frm.setTnYear(year);
		frm.settMonth(month);//月
		frm.setTnMonth(month);
		frm.settDate(date);//日
		frm.setTnDate(date);
		//struts-config.xmlに設定したDataSource取得
		DataSource source =getDataSource(request);
		// データベース処理関連変数の定義
		Connection con = null;
		Statement stmt = null;//取引データ用
		Statement stmt2 = null;//顧客データ用
		ResultSet rs = null;//取引データ用
		ResultSet rs2 = null;//顧客データ用
		String user_id=null;//ログイン中の顧客のID
		String inID = null;//お金が入るほうの顧客ID
		String outID=null;//お金が出て行くほうの顧客ID
		String furigana = null;//取引相手のフリガナ
		String accountNo=null;//取引相手の口座番号
		String cash=null;//取引後の残高を入れる
		String offset;//最初に20行表示させるための変数
		String length;//listの長さを保持する変数
		String view;//次の画面で表示させる表の長さを決める変数
		//HistroyBeanのlistをインスタンス化
		List<HistoryBean> list = new ArrayList<HistoryBean>();
		try{
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();
			//ログイン中の顧客の顧客IDと残高を取得
			String sqlstr="SELECT user_id,account_balance FROM user WHERE account_no='"+frm.getAccountNumber()+"'";
			rs=stmt.executeQuery(sqlstr);
			while(rs.next()){
				user_id=rs.getString(1);//顧客ID
				cash=rs.getString(2);//残高
			}
			if(rs != null){
    			rs.close();
    		}
			//ログイン中の顧客が関わった取引情報を取得(決済フラグが0、つまり決済済みの取引のみ)
			//残高の計算のため新しい順から所得している
			sqlstr="SELECT trans_date,user_id,other_id,trans_cash FROM transcation WHERE user_id";
			sqlstr+="='"+user_id+"' AND trans_flag='0' OR other_id ='"+user_id+"' AND trans_flag='0' "
					+ "ORDER BY trans_id DESC";
			rs=stmt.executeQuery(sqlstr);
			while(rs.next()){
				HistoryBean hb = new HistoryBean();
				hb.setTransDate(rs.getString(1));//取引日をセット
				hb.setTransCash(rs.getString(4));//取引額をセット
				outID=rs.getString(2);//取引元の顧客IDを代入
				inID=rs.getString(3);//取引先の顧客IDを代入
				hb.setLastBalance(cash);//最新の残高をhbへ
				if(inID.equals(user_id)){//入出フラグ判定
					hb.setIOFlag("i");//入金のフラグ
					hb.setOtherID(outID);//取引相手の顧客IDをセット
					//次の取引に渡す残高を計算する
					int calk=Integer.parseInt(cash)-Integer.parseInt(rs.getString(4));
					cash=String.valueOf(calk);//次の取引に渡す残高を代入
				}else{
					hb.setIOFlag("o");//出金のフラグ
					hb.setOtherID(inID);//取引相手の顧客IDをセット
					//次の取引に渡す残高を計算する
					int calk=Integer.parseInt(cash)+Integer.parseInt(rs.getString(4));
					cash=String.valueOf(calk);//次の取引に渡す残高を代入
				}
				//取引相手のフリガナと口座番号を取得する
				String sqlstr2 ="SELECT furigana,account_no FROM user WHERE user_id='";
				if(inID.equals(user_id)){//入金される側の顧客IDがログイン中の顧客IDだった場合
					sqlstr2+=outID+"'";
				}else{//入金する側の顧客IDがログイン中の顧客IDだった場合
					sqlstr2+=inID+"'";
				}
				stmt2= con.createStatement();
				rs2=stmt2.executeQuery(sqlstr2);
				while(rs2.next()){
					furigana=rs2.getString(1);//フリガナ（相手）を取得
					accountNo=rs2.getString(2);//口座番号（相手）を取得
					hb.setFurigana(furigana);//フリガナ（相手）をセット
					hb.setOtherAccountNo(accountNo);//口座番号（相手）をセット
				}
				if(rs2 != null){
	    			rs2.close();
	    		}
	    		if(stmt2 != null){
	    			stmt2.close();
	    		}
	    		list.add(hb);//listに加える
			}
			Collections.reverse(list);//古い順から表示するためlist内のデータの順番を逆にする
			//listが20行以下ならoffsetは0、listが20行より上ならoffsetはその長さから20を引いた数
			offset=Integer.toString(list.size() - 20);//最新の20件を表示
			length=Integer.toString(list.size()-20);//nextボタンを表示する条件を保持
			view="20";

			request.setAttribute("offset",offset);//offsetをrequestにセット
			request.setAttribute("view", view);//viewをrequestにセット
			frm.setView(Integer.parseInt(view));
			HttpSession session = request.getSession();//セッションを呼び出す
			session.setAttribute("historyList", list);//sessionにlistをセット
			session.setAttribute("length", length);//lengthをsessionにセット
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
		return mapping.findForward("transHistory");//取引履歴照会画面に遷移
	}
}
