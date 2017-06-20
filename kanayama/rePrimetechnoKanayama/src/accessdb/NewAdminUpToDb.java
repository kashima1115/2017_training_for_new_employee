package accessdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import gForm.NewUserForm;
//入力フォームの情報をDBに登録するクラス
public class NewAdminUpToDb {
	public void adminUp(DataSource source,NewUserForm nuForm)throws SQLException{

		//DB接続処理等
		Connection con=null;
		PreparedStatement pState=null;

		try{
			con=source.getConnection();

			//自動コミットメントオフ
			con.setAutoCommit(false);

			//insert文（入力情報をDBに登録）
			String iSql="insert into game_user "+
			"values (?,1,?,now(),now())";

			pState=con.prepareStatement(iSql);

			pState.setString(1, nuForm.getNewId());
			pState.setString(2, nuForm.getNewPass());

			//sql文実行
			pState.executeUpdate();

			//コミット
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
