package accessdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import gForm.LoginForm;

//DBからログインIDのauthorityを取得を行うクラス
public class GetAuthority {
	public int checkAuthority(DataSource source,LoginForm liForm)throws SQLException{
		//DB接続処理等
		Connection con=null;
		PreparedStatement pState=null;
		ResultSet rset=null;
		int authority=0;

		try{
			con=source.getConnection();

			//sql文（入力されたログインIDのauthorityを取得）
			String sSql="select AUTHORITY from game_user "+
			"where LOGIN_ID=?";

			pState=con.prepareStatement(sSql);

			pState.setString(1, liForm.getLoginId());

			rset=pState.executeQuery();

			while(rset.next()){
				authority=rset.getInt("AUTHORITY");
			}

		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}finally{

			if(con!=null){
				con.close();
			}

			if(pState!=null){
				pState.close();
			}

			if(rset!=null){
				rset.close();
			}
		}
		//authorityの値をLoginActionに返す
		return authority;
	}
}
