package ers.middle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ers.beans.*;

import ers.data.DataFacade;

/**
 * Checks if DAOS can reach the middle tier
 * Tests all utilized DAO methods
 * Will reorganize
 * @author Sam
 *
 */
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
//		String username = "JSMITH";
//		User user = facado.getUser(username);
//		System.out.println(user.getUserRole().getUserRole());
//		facado.insertReimbursement(12.00, "Fries", 1, 2);
		//facado.updateReimbursementStatus(reimbId, newStatusNumb, resolverId);
		//facado.updateReimbursementStatus(2, 3, 7);
	}

}
