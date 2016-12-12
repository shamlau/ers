package ers.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Lookup and cache services. Reduces perforamce overhead of looking up services
 * 
 * @author Sam
 *
 */

public class ServiceLocator {

	private static DataSource ers;
	private static Properties env;// environment props

	static {
		
		InputStream stream = ServiceLocator.class.getClassLoader().getResourceAsStream("jndi.properties");
		env = new Properties();
		try {
			System.out.println("loadin props");
			env.load(stream);// loads properties from file
			System.out.println("props loaded.. " + env.getProperty("ersdb"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public synchronized static DataSource getErsDatabase() {
		if (ers == null)
			ers = lookupErs();
		return ers;
	}

	private static DataSource lookupErs() {
		try {
			Context ctxt = new InitialContext(env);
			DataSource ds = (DataSource) ctxt.lookup(env.getProperty("ersdb"));
			System.out.println(ds);
			return ds;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;// datasource could not be found
		}
	}
}
