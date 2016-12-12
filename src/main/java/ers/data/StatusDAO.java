package ers.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ers.beans.ReimbursementStatus;

class StatusDAO {
	private Connection conn;

	StatusDAO(Connection conn){
		super();
		this.conn=conn;
	}
	
	
	//Works
	public String statusFromReimbursement(int ReimbursementId) throws SQLException{
		String sql ="Select REIMB_STATUS FROM ERS_REIMBURSEMENT_STATUS INNER JOIN ERS_REIMBURSEMENT ON"
				+ " ERS_REIMBURSEMENT.REIMB_STATUS_ID=ERS_REIMBURSEMENT_STATUS.REIMB_STATUS_ID WHERE ERS_REIMBURSEMENT.REIMB_ID=?";		
		String status="";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1, ReimbursementId);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			status=rs.getString(1);
		}
		return status;
	}


	public void close() throws SQLException {
		conn.close();
	}
}
