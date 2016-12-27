package ers.web;

import org.mindrot.jbcrypt.BCrypt;


/**
 * Tested JBCrypt here and it works but it doesn't work in the actual project
 * @author Sam
 *
 */
public class TestJB {
public static void main(String[] args) {
	String password ="123";
	String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
	System.out.println(hashed);
	String login = "123";
	System.out.println(password);
	System.out.println(BCrypt.checkpw(login, hashed));

}
}
