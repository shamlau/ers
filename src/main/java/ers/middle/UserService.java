package ers.middle;

import java.sql.SQLException;

import ers.beans.User;
import ers.data.DataFacade;

public class UserService {

	//Validation methods should go here instead of calling methods from lower tiers
	/*
	 * Take in a user. and compare user to password
	 * iff correct return user object
	 *
	 */
	public static  boolean validLogin(String Username, String password){
		DataFacade facade = new DataFacade();
		User user = facade.getUser(Username);
		if (user.getPassword().equals(password)){
			return true;
		}
		return false;
	}
	
	

	public boolean isManager(String username) throws SQLException{
		DataFacade facade = new DataFacade();
		if(facade.validateManager(username)){
			return true;
		}
		return false;
	}
}
