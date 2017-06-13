package bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

import bank.form.BankForm;

public class CheckSelectTranscation extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
		//フォーム情報取得
		BankForm frm =(BankForm)form;
		//エラー用メッセージのインスタンス化
		ActionMessages errors = new ActionMessages();
		//振込額チェック
		if(Long.parseLong(frm.getFurikomi())<=0){//振込額が0かマイナス値の場合エラーメッセージ表示
			errors.add("furikomi.longrange",new ActionMessage("error.furikomi.longrange"));
			saveErrors(request,errors);
			return mapping.findForward("transcationerror");//振込申込画面に遷移
		}
		frm.setFurikomigaku(Long.parseLong(frm.getFurikomi()));//form内のfurikomigakuに振込額をセット
		//振込日日付チェック
		Calendar cal=Calendar.getInstance();//インスタンス化
		int month= cal.get(Calendar.MONTH)+1;//当月を取得
		int date=cal.get(Calendar.DATE);//当日を取得
		String dateC =frm.getfYear()+"-"+frm.getfMonth()+"-"+frm.getfDate();//年-月-日になるようにつなげる
		if(Integer.parseInt(frm.getfMonth())<month){//入力した月の値が当月よりも小さかったらエラー
			errors.add("furikomibi.past",new ActionMessage("error.furikomibi.past"));
			saveErrors(request,errors);
			return mapping.findForward("transcationerror");//振込申込画面に遷移
		//入力した月と日が当月で日にちが当日よりも小さかったらエラー
		}else if(Integer.parseInt(frm.getfMonth())==month && Integer.parseInt(frm.getfDate())<date){
			errors.add("furikomibi.past",new ActionMessage("error.furikomibi.past"));
			saveErrors(request,errors);
			return mapping.findForward("transcationerror");//振込申込画面に遷移
		}else{//存在しない日付チェック
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");//yyyy-mm-ddのフォーマットをインスタンス化
			try{
				format.setLenient(false);//厳密なチェックをする
				format.parse(dateC);//dateCをチェック
			}catch(Exception e){//チェックして存在しない日付だった場合エラー
				e.printStackTrace();
				errors.add("furilomibi.notexist" ,new ActionMessage("error.furilomibi.notexist"));
				saveErrors(request,errors);
				return mapping.findForward("transcationerror");//振込申込画面に遷移
			}
		}
		//口座番号チェック（自分の口座番号を入力してしまったらエラー）
		if(frm.getAccountNumber().equals(frm.getOtherAccountNumber())){
			errors.add("otherAccount.notfound",new ActionMessage("error.otherAccount.notfound"));
			saveErrors(request,errors);
			return mapping.findForward("transcationerror");//振込申込画面に遷移
		}
		//データベースから情報取得
		//struts-config.xmlに設定したDataSource取得
		DataSource source =getDataSource(request);
		// データベース処理関連変数の定義
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String furigana="データナシ";//DBからデータを取得できなかったときのための値を入れている
//		String accountNo=null;
		try{
			// DB接続
			con = source.getConnection();
			// ステートメント生成
			stmt = con.createStatement();
			//フリガナ、口座番号、属性（現段階では未使用）を所得する
			String sqlstr ="SELECT furigana,account_no,property from user WHERE account_no='";
			sqlstr+=frm.getOtherAccountNumber() +"'";//入力した口座番号を条件にする
			rs=stmt.executeQuery(sqlstr);
			while(rs.next()){
				furigana=rs.getString(1);//フリガナを代入
//				accountNo=rs.getString(2);
			}
			if(furigana.equals(frm.getKatakana())){//フリガナが一致していた場合、sessionにBankFormをセット
				HttpSession session = request.getSession();
				session.setAttribute("BankForm", form);
				super.saveToken(request);//トークンの発行

				return mapping.findForward("transcationconf");//申込確認画面に遷移
			}else{//フリガナが一致していなかった場合にエラー
				errors.add("otherAccount.notfound" , new ActionMessage("error.otherAccount.notfound"));
				saveErrors(request,errors);
				return mapping.findForward("transcationerror");//振込申込画面に遷移
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{//resultset,statement,connectionをクローズ
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

}
