package ers.beans;

public class UserRole {
	int userRoleID;
	String userRole;

	public int getUserRoleID() {
		return userRoleID;
	}

	public void setUserRoleID(int userRoleID) {
		this.userRoleID = userRoleID;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public UserRole(int userRoleID, String userRole) {
		super();
		this.userRoleID = userRoleID;
		this.userRole = userRole;
	}

}
