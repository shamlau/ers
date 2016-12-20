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
	
	//move the checking to one level up
	User getUser(String Username){
		DataFacade facade = new DataFacade();
		User user = facade.getUser(Username);
		return user;
	}
	/*
	 * System.out.println(user);
		if(user!=null){
			if (user.getPassword().equals(password) && user!=null){
				System.out.println("valid login went through");
				return true;
			}else{
				return false;
			}
		}else{
		System.out.println("returning false");
		return false;
	 */
	
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
