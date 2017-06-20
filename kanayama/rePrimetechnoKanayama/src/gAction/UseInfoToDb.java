package gAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class UseInfoToDb {

	//チップ（石）の消費情報をDBに登録するメソッド
	public void useInfo(DataSource source,String logId,int chipRate)throws SQLException{
		Connection con=null;
		PreparedStatement pState=null;

		try{
			con = source.getConnection();
			con.setAutoCommit(false);

			//insert文（チップの使用情報を登録）
			String iSql="insert into use_chip "+
			"(LOGIN_ID,CONSUME_CHIP,IP_DATE,UPD_DATE) values "+
			"(?,?,now(),now())";

			//プリコンパイル
			pState=con.prepareStatement(iSql);

			pState.setString(1, logId);
			pState.setInt(2, chipRate);

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
