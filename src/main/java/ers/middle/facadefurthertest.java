package ers.middle;

import java.sql.ResultSet;
import java.sql.SQLException;

import ers.data.DataFacade;

public class facadefurthertest {

	public static void main(String[] args) throws SQLException {
		DataFacade facado=new DataFacade();
		//System.out.println(facado.UserDaoTest("JSMITH"));
		ResultSet rs=facado.UserDaoTest("JSMITH");
		System.out.println(rs.getRow());
		//facado.ReimbdaoTest("JSMITH","PENDING");
	}

}
