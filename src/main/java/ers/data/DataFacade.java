package ers.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ers.beans.*;

public class DataFacade {

	//Use this once you're ready to connectConnection conn=ServiceLocator.getErsDatabase().getConnection();
	//put yo methods here
/*	public ResultSet UserDaoTest(String userName) throws SQLException{
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
	}*/
	
	public List<Reimbursement> selectAllReimbs() throws SQLException{
		Connection conn;
		conn=ServiceLocator.getErsDatabase().getConnection();
		List<Reimbursement> list = new ReimbursementDAO(conn).selectAllReimbursements();
		return list;
	}
	
	public List<Reimbursement> selectReimbsByUser(String Username) throws SQLException{
		Connection conn;
		conn=ServiceLocator.getErsDatabase().getConnection();
		List<Reimbursement> list = new ReimbursementDAO(conn).selectAllReimbursementsFromUser(Username);
		return list;
	}
	
	public boolean validateUsers(String username, String password) throws SQLException{
		Connection conn;
		conn=ServiceLocator.getErsDatabase().getConnection();
		boolean booly = new UserDAO(conn).validUser(username, password);
		return booly;
	}
	
	public boolean validateManager(String username) throws SQLException{
		Connection conn;
		conn=ServiceLocator.getErsDatabase().getConnection();
		boolean boo = new UserDAO(conn).isManager(username);
		return boo;
	}
}
