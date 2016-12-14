package ers.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataFacade {

	//Use this once you're ready to connectConnection conn=ServiceLocator.getErsDatabase().getConnection();
	//put yo methods here
	public ResultSet UserDaoTest(String userName) throws SQLException{
		Connection conn=null;
		ResultSet rs;
		
			conn=ServiceLocator.getErsDatabase().getConnection();
			rs=new UserDAO(conn).ReimbByUser(userName);
			conn.close();
			return rs;
	}
	
	public ResultSet ReimbdaoTest(String username, String Status)throws SQLException{
		Connection conn;
		ResultSet rs;
		conn=ServiceLocator.getErsDatabase().getConnection();
		rs=new ReimbursementDAO(conn).fullerReimbursmentSelect();
		conn.close();
		return rs;
	}
}
