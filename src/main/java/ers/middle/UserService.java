package ers.middle;

import java.sql.SQLException;

import ers.data.DataFacade;

public class UserService {

	public static boolean validLogin(String Username, String password) throws SQLException{
		DataFacade facade = new DataFacade();
		if(facade.validateUsers(Username, password)){
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
