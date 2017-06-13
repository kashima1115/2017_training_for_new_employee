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

public class SelectSearchTransHistory extends Action {
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
		String offset;//表示する始点を定める定数
		String stdate=null;//始点の年月日
		String endate=null;//終点の年月日
		String transdate=null;//取引日を格納する変数
		String length;//listの長さを保持する変数
		String view=null;//表示件数を格納する変数
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
			sqlstr+="='"+user_id+"' AND trans_flag='0' OR other_id ='"+user_id+"' AND trans_flag='0' ORDER BY trans_id DESC";
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
				stdate=frm.gettYear()+String.format("%02d", Integer.parseInt(frm.gettMonth()))+String.format("%02d", Integer.parseInt(frm.gettDate()));
	    		endate=frm.getTnYear()+String.format("%02d", Integer.parseInt(frm.getTnMonth()))+String.format("%02d", Integer.parseInt(frm.getTnDate()));
	    		String transdates[]=rs.getString(1).split("-");
	    		transdate=transdates[0]+transdates[1]+transdates[2];
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
	    		if(Integer.parseInt(stdate)>Integer.parseInt(transdate)||Integer.parseInt(endate)<Integer.parseInt(transdate)){
	    			//listには加えない
	    		}else{
	    			list.add(hb);//listに加える
	    		}
			}
			Collections.reverse(list);//古い順から表示するためlist内のデータの順番を逆にする
			//formのviewが40だったらviewを50にする
			if(frm.getView()==40){
				offset=Integer.toString(list.size() - 40);
				view="40";
				length=Integer.toString(list.size() - 40);
			}else{
				//それ以外は全件表示にするためviewをlistの長さにする
				//listの最初から最後まですべて表示させる
				offset="0";
				view=String.valueOf(list.size());
				length="0";
			}
			request.setAttribute("offset",offset);
			request.setAttribute("view", view);
			HttpSession session = request.getSession();
			session.setAttribute("historyList", list);//sessionにlistをセット
			session.setAttribute("length", length);//sessionにlengthをセット
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
