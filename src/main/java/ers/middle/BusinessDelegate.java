package ers.middle;

import javax.naming.AuthenticationException;
import ers.beans.User;
import ers.middle.UserService;

/**
 * @author Sam
 *
 */
public class BusinessDelegate {

	public User getUser(String Username){
		return new UserService().getUser(Username);
	}
	
	public boolean isManager(String username){
		boolean ismanager = isManager(username);
		return ismanager;
	}
}
