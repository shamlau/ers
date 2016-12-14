package ers.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ers.beans.User;

//TODO make this default after you figure out why it doesn't work
public class UserDAO {

	private Connection conn;

	public UserDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public void close() throws SQLException {
		conn.close();
	}
	
	public String getFullName(User user) {
		return user.getFirstName() + " " + user.getLastName();
	}

	public String getUserRole(User user) {
		int roleid = user.getRoleId();

		return null;
	}

	public String getFullName(int id) throws SQLException {
		String sql = "SELECT USER_FIRST_NAME, USER_LAST_NAME FROM ERS_USERS WHERE ERS_USERS_ID =" + id;
		String fullName = "";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			fullName = rs.getString(1) + " " + rs.getString(2);
		}
		return fullName;
	}

	// TODO perhaps you should generate this method somewhere else
	public boolean validUser(String userName, String password) throws SQLException {
		String sql = "SELECT ERS_USERNAME FROM ERS_USERS WHERE ERS_USERNAME='" + userName + "' AND ERS_PASSWORD='"
				+ password + "'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int count = 0;
		while (rs.next()) {
			count++;
		}
		if (count != 1) {
			return false;
		} else {
			return true;
		}
	}



	//Works
	//get All reimbursements by a user use your joins
	public ResultSet ReimbByUser(String userName) throws SQLException{
		String sql= "SELECT REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_DESCRIPTION, REIMB_STATUS_ID, REIMB_TYPE_ID"
				+ " FROM ERS_USERS INNER JOIN ERS_REIMBURSEMENT ON ERS_USERS.ERS_USERS_ID=ERS_REIMBURSEMENT.REIMB_AUTHOR "
				+ "WHERE ERS_USERNAME='"+userName+"'";
		PreparedStatement stmt=conn.prepareStatement(sql);
		ResultSet rs=stmt.executeQuery();
		while (rs.next()){
			System.out.println(rs.getInt(1)+ " " + rs.getInt(2)+ " "+ rs.getDate(3)+ " "+ rs.getDate(4)+ " "
					+ rs.getString(5)+ " " + rs.getInt(6)+ " "+ rs.getInt(7));
		}
		return rs;
	}
	
	
}
