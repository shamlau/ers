package ers.middle;

import java.sql.SQLException;

import ers.data.DataFacade;

public class facadefurthertest {

	public static void main(String[] args) throws SQLException {
		DataFacade facado=new DataFacade();
		System.out.println(facado.UserDaoTest("JSMITH"));
		facado.ReimbdaoTest("JSMITH","PENDING");
	}

}
