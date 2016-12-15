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

	public String getFullName(int id) throws SQLException {
		String sql = "SELECT USER_FIRST_NAME, USER_LAST_NAME FROM ERS_USERS WHERE ERS_USERS_ID =?";
		String fullName = "";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
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
	
	public boolean isManager(String userName) throws SQLException{
		String sql = "SELECT USER_ROLE_ID FROM ERS_USERS WHERE ERS_USERNAME='"+userName+"'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int manager = 1;
		while (rs.next()){
			manager = rs.getInt(1);
		}
		if(manager == 2){
			return true;
		}else{
			return false;
		}
	}
	
}
