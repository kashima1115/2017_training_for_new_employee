package gAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import gForm.NewUserForm;

public class NewUserUpToDb {
	//入力されたユーザー情報をDBに登録するメソッド
	public void userInsert(DataSource source,NewUserForm nuForm)throws SQLException{
		Connection con=null;
		PreparedStatement pState=null;

		try{
			con=source.getConnection();
			con.setAutoCommit(false);

			//insert文（入力された情報をDBに登録）
			String iSql="insert into game_user "+
			"(LOGIN_ID,PASSWD,IP_DATE,UPD_DATE) "+
			"values (?,?,now(),now())";

			//プリコンパイル
			pState=con.prepareStatement(iSql);

			pState.setString(1, nuForm.getNewId());
			pState.setString(2, nuForm.getNewPass());

			//sql文実行
			pState.executeUpdate();

			con.commit();

		}catch(SQLException e){
			e.printStackTrace();

			if(con!=null){
				con.rollback();
			}

			throw e;

		}finally{

			if(con!=null){
				con.close();
			}

			if(pState!=null){
				pState.close();
			}
		}

	}
}
