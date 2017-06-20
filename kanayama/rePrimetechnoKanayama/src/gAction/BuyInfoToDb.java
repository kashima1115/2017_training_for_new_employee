package gAction;

import gForm.HopeBuyForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class BuyInfoToDb {

	//購入情報をDBに登録するメソッド
	public void buyInfo(DataSource source,HopeBuyForm hbForm,String logId)throws SQLException{
		Connection con=null;
		PreparedStatement pState=null;

		try{
			con = source.getConnection();
			con.setAutoCommit(false);

			//insert文（購入情報をDBに登録）
			String iSql="insert into buy_currency (LOGIN_ID,BUY_CHIP,IP_DATE,UPD_DATE) values (?,?,now(),now())";

			//プリコンパイル
			pState=con.prepareStatement(iSql);

			pState.setString(1, logId);
			int buyChip=Integer.parseInt(hbForm.getBuyChip());
			pState.setInt(2, buyChip);

			pState.executeUpdate();

			//コミット
			con.commit();

		}catch(SQLException e){
			e.printStackTrace();

			try{
				if(con!=null){
					con.rollback();
				}

			}catch(SQLException ex){
				ex.printStackTrace();
			}
			throw e;

		}finally{
			try{
				if(con!=null){
					con.close();
				}

				if(pState!=null){
					pState.close();
				}

			}catch(SQLException e){
				e.printStackTrace();
			}
		}

	}
}
