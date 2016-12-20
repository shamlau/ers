package ers.middle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ers.beans.*;

import ers.data.DataFacade;

public class facadefurthertest {

	public static void main(String[] args) throws SQLException {
		DataFacade facado = new DataFacade();
		//List<Reimbursement> listo = facado.selectAllReimbs();
//		List<Reimbursement> listo = facado.selectReimbsByUser("JSMITH");
//		for (Reimbursement e : listo){
//		System.out.println(e.getAuthor());
//		}
		//System.out.println(facado.validateUsers("JSMITH","12w3"));
		//System.out.println(facado.validateManager("HEADHONCHO"));
		String username = "JSMITH";
		User user = facado.getUser(username);
		System.out.println(user.getUserRole().getUserRole());
		//facado.insertReimbursement(12.50, "Burgers", 1, 3);
		//facado.updateReimbursementStatus(reimbId, newStatusNumb, resolverId);
		facado.updateReimbursementStatus(2, 3, 7);
	}

}
