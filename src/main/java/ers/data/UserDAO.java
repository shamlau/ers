package ers.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import ers.beans.User;
import ers.beans.UserRole;

//TODO make this default after you figure out why it doesn't work
class UserDAO {

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
	
	public User getUser(String userName) throws SQLException {
		String sql = "SELECT ERS_USERS_ID, ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, "
				+ "USER_EMAIL, USER_ROLE_ID, USER_ROLE "
				+ "FROM ERS_USERS " + "INNER JOIN ERS_USER_ROLES " + "ON ERS_USER_ROLE_ID=USER_ROLE_ID "
				+ "WHERE ERS_USERNAME=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, userName);
		ResultSet rs = stmt.executeQuery();
		User user = new User();
		while (rs.next()) {
			int userId = rs.getInt(1);
			String username = rs.getString(2);
			String password = rs.getString(3);// maybe we shouldn't
															// store this
			String firstName = rs.getString(4);
			String lastName = rs.getString(5);
			String email = rs.getString(6);
			int roleId = rs.getInt(7);
			String uRole = rs.getString(8);
			UserRole userRole = new UserRole(roleId, uRole);
			user.setUserId(userId);
			user.setUsername(username);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setEmail(email);
			user.setUserRole(userRole);
		}
		return user;

	}

	public String getPassword(int id) throws SQLException{
		String sql = "SELECT ERS_PASSWORD FROM ERS_USERS "
				+ "WHERE ERS_USERS_ID=?";
		String password="";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			password = rs.getString(1);
		}
		System.out.println("password is : " +password);
		return password;
	}
	
	//TODO complete this
	public void updatePassword(int id) throws SQLException{
		String password = getPassword(id);
		String sql = "UPDATE ERS_USERS"
				+ "	SET ERS_PASSWORD=?"
				+ "	WHERE ERS_USERS_ID=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		String hashed=BCrypt.hashpw(password, BCrypt.gensalt());
		System.out.println("new pw is: " + hashed);
		stmt.setString(1, hashed);
		stmt.setInt(2, id);
		stmt.executeQuery();
	}

	// TODO Make this return a user object. select statement selects all fields
	// do validation
	public boolean isManager(String userName) throws SQLException {
		String sql = "SELECT USER_ROLE_ID FROM ERS_USERS WHERE ERS_USERNAME='" + userName + "'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int manager = 1;
		while (rs.next()) {
			manager = rs.getInt(1);
		}
		if (manager == 2) {
			return true;
		} else {
			return false;
		}
	}

}
