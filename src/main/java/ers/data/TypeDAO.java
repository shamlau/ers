package ers.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class TypeDAO {

	private Connection conn;

	public TypeDAO(Connection conn){
		super();
		this.conn=conn;
	}
	
	public String typeFromReimbursement(int ReimbursementId) throws SQLException{
		String sql ="Select REIMB_TYPE FROM ERS_REIMBURSEMENT_TYPE INNER JOIN ERS_REIMBURSEMENT ON"
				+ " ERS_REIMBURSEMENT.REIMB_TYPE_ID=ERS_REIMBURSEM	ENT_TYPE.REIMB_TYPE_ID WHERE ERS_REIMBURSEMENT.REIMB_ID=?";		
		String type="";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1, ReimbursementId);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			type=rs.getString(1);
		}
		return type;
	}
	
	public int getTypeId (String type) throws SQLException{
		String typename = type.toUpperCase();
		int typeid = 0;
		String sql = "SELECT REIMB_TYPE_ID FROM ERS_REIMBURSEMENT_TYPE "
				+ "WHERE REIMB_TYPE=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, typename);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			typeid=rs.getInt(1);
		}
		return typeid;
	}

}
