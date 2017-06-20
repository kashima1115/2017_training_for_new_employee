package gAction;

import gForm.NewUserForm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class NewUserInfoToDb {

	//ログインIDに重複があるか、DBに接続して確認する
	public int userInfo(DataSource source,NewUserForm nuForm)throws SQLException{
		int count=0;

		Connection con=null;
		Statement state=null;
		ResultSet rset=null;

		try{
			con = source.getConnection();
			state=con.createStatement();

			//select文（ログインIDの被りがある場合、カウントされる）
			String sSql="select count(*) from game_user "+
			"where LOGIN_ID='"+nuForm.getNewId()+"'";

			//sql文実行
			rset=state.executeQuery(sSql);

			while(rset.next()){
				count=rset.getInt("count(*)");
			}



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


		return count;
	}
}
