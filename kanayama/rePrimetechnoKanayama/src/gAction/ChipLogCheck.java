package gAction;

import gForm.LoginForm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;


public class ChipLogCheck {

	//購入履歴があるか確認するメソッド
	public int buyLogCheck(DataSource source,LoginForm liForm){

		int count=0;
		Connection con=null;
		Statement state=null;
		ResultSet rset=null;

		try{
			con = source.getConnection();
			state=con.createStatement();

			//select文（当該loginIdに購入履歴があるか)
			String sSql="select count(*) from buy_currency "+
			"where LOGIN_ID='"+liForm.getLoginId()+"'";

			//sql文実行
			rset=state.executeQuery(sSql);

			while(rset.next()){
				count=rset.getInt("count(*)");
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{

				if(con!=null){
					con.close();
				}

				if(state!=null){
					state.close();
				}

				if(rset!=null){
					rset.close();
				}

			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return count;
	}

//石の使用履歴があるか確認するメソッド
	public int useLogCheck(DataSource source,LoginForm liForm){
		int count=0;
		Connection con=null;
		Statement state=null;
		ResultSet rset=null;

		try{
			con = source.getConnection();
			state=con.createStatement();

			//select文（当該loginIdに使用履歴があるか)
			String sSql="select count(*) from use_chip "+
			"where LOGIN_ID='"+liForm.getLoginId()+"'";

			//sql文実行
			rset=state.executeQuery(sSql);

			while(rset.next()){
				count=rset.getInt("count(*)");
			}


		}catch(SQLException e){
			e.printStackTrace();
		}finally{

			try{

				if(con!=null){
					con.close();
				}

				if(state!=null){
					state.close();
				}

				if(rset!=null){
					rset.close();
				}

			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}


		return count;
	}

	//当該ログインIDの石の購入量を取得
	public int buyAmount(DataSource source,LoginForm liForm){

		int amount=0;
		Connection con=null;
		Statement state=null;
		ResultSet rset=null;

		try{
			con = source.getConnection();
			state=con.createStatement();

			//select文（当該loginIdの過去の購入量の情報を取得)
			String sSql="select sum(BUY_CHIP) from buy_currency "+
			"where LOGIN_ID='"+liForm.getLoginId()+"'";

			//sql文実行
			rset=state.executeQuery(sSql);

			while(rset.next()){
				amount=rset.getInt("sum(BUY_CHIP)");
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{

				if(con!=null){
					con.close();
				}

				if(state!=null){
					state.close();
				}

				if(rset!=null){
					rset.close();
				}

			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return amount;
	}

	public int useAmount(DataSource source,LoginForm liForm){
		int amount=0;
		Connection con=null;
		Statement state=null;
		ResultSet rset=null;

		try{
			con = source.getConnection();
			state=con.createStatement();

			//select文（当該loginIdの過去の消費量の情報を取得)
			String sSql="select sum(CONSUME_CHIP) from use_chip "+
			"where LOGIN_ID='"+liForm.getLoginId()+"'";

			//sql文実行
			rset=state.executeQuery(sSql);

			while(rset.next()){
				amount=rset.getInt("sum(CONSUME_CHIP)");
			}


		}catch(SQLException e){
			e.printStackTrace();
		}finally{

			try{

				if(con!=null){
					con.close();
				}

				if(state!=null){
					state.close();
				}

				if(rset!=null){
					rset.close();
				}

			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return amount;
	}
}
