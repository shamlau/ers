package ers.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeDAO {

	private Connection conn;

	private TypeDAO(Connection conn){
		super();
		this.conn=conn;
	}
	
	//TODO test
	public String typeFromReimbursement(int ReimbursementId) throws SQLException{
		String sql ="Select REIMB_TYPE FROM ERS_REIMBURSEMENT_TYPE INNER JOIN ERS_REIMBURSEMENT ON"
				+ " ERS_REIMBURSEMENT.REIMB_TYPE_ID=ERS_REIMBURSEMENT_TYPE.REIMB_TYPE_ID WHERE ERS_REIMBURSEMENT.REIMB_ID=?";		
		String type="";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1, ReimbursementId);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			type=rs.getString(1);
		}
		return type;
	}

}
