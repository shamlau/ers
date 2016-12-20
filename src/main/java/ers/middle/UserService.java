package ers.middle;

import java.sql.SQLException;

import ers.beans.User;
import ers.data.DataFacade;

public class UserService {


	/**
	 * Validates if the inputed login is a valid login+pw combination
	 * @param Username
	 * @param password
	 * @return
	 */
	public static  boolean validLogin(String Username, String password){
		DataFacade facade = new DataFacade();
		User user = facade.getUser(Username);
		if (user.getPassword().equals(password)){
			return true;
		}
		return false;
	}
	
	/**
	 * Validates if the user is a manager
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public boolean isManager(String username) throws SQLException{
		DataFacade facade = new DataFacade();
		User user = facade.getUser(username);
		if(user.getUserRole().getUserRole().equals("MANAGER")){
			return true;
		}
		return false;
	}
}
