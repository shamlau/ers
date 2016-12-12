package ers.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ers.beans.Reimbursement;
import ers.beans.ReimbursementStatus;
import ers.beans.ReimbursementType;
import ers.beans.User;

public class ReimbursementDAO {

	private Connection conn;

	public ReimbursementDAO(Connection conn){
		super();
		this.conn=conn;
	}
	
	public void close() throws SQLException{
		conn.close();
	}


	/**
	 * full insert prolly never gets implemented since you would never file a reimbursement that already has a resolver
	 * @param reimbursment
	 * @throws SQLException
	 */
	public void fullInsert(Reimbursement reimbursment) throws SQLException{
		Timestamp now= new Timestamp(System.currentTimeMillis());
		String sql ="insert into ERS_REIMBURSEMENT values (?,?,?,?,?,"+
	"?,?,?,?,?)";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1, reimbursment.getReimbId());
		stmt.setInt(2, reimbursment.getReimbAmount());
		stmt.setTimestamp(3, now);
		stmt.setTimestamp(4, now);
		stmt.setString(5, reimbursment.getDescription());
		stmt.setBlob(6,reimbursment.getRecipt());
		stmt.setInt(7, reimbursment.getAuthor());
		stmt.setInt(8, reimbursment.getResolver());
		stmt.setInt(9, reimbursment.getStatusId());
		stmt.setInt(10, reimbursment.getTypeId());
		stmt.execute();
	}
	
	//TODO test this
	public void insertReimbursement(Reimbursement reimb) throws SQLException{
		Timestamp now= new Timestamp(System.currentTimeMillis());
		String sql= "insert into ERS_REIMBURSEMENT (REIMB_ID,REIMB_AMOUNT, REIMB_SUBMITTED"+
	", REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_STATUS_TYPE_ID)"
	+ " values (?,?,?,?,?,?)";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1, reimb.getReimbId());
		stmt.setInt(2, reimb.getReimbAmount());
		stmt.setTimestamp(3, now);
		stmt.setInt(4, reimb.getAuthor());
		stmt.setInt(5, reimb.getStatusId());
		stmt.setInt(6, reimb.getTypeId());
		stmt.execute();
	}
	
	public ResultSet selectReimbursements() throws SQLException{
		String sql= "SELECT REIMB_ID, REIMB_AMOUNT FROM ERS_REIMBURSEMENT";
		PreparedStatement stmt= conn.prepareStatement(sql);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			System.out.println("id: "+ rs.getString(1)+ " amount: "+ rs.getString(2));
		}
		return rs;
		
	}
	
	public ResultSet selectNecessaryPartsOfReimbursments(int num) throws SQLException{
		//int idNum=user.getUserId();
		String sql= "SELECT REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_STATUS_ID, REIMB_TYPE_ID FROM ERS_REIMBURSEMENT INNER JOIN ERS_USERS  ON ERS_USERS_ID=REIMB_AUTHOR WHERE REIMB_AUTHOR = "+ num;
		PreparedStatement stmt=conn.prepareStatement(sql);
		ResultSet rs=stmt.executeQuery();
		return rs;
	}
	
	public ResultSet fullReimbUserJoin(String Username) throws SQLException{
		String sql= "SELECT REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID, ERS_USERS_ID, ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID FROM ERS_REIMBURSEMENT INNER JOIN ERS_USERS ON ERS_USERS_ID=REIMB_AUTHOR WHERE ERS_USERNAME='"+Username+"'";
		PreparedStatement stmt=conn.prepareStatement(sql);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString(1));
		}
		return rs;

		
	}
	
/*	public List<String> result (ResultSet r){
		
		return null;
		
	}*/
	
	
	//TODO finish List resultset
	public List<Reimbursement> result (ResultSet r) throws SQLException{
		List<Reimbursement> results = new ArrayList<Reimbursement>();
		while(r.next()){
			int id=r.getInt("reimbId");
			int amount=r.getInt("reimbAmount");
			Timestamp submitted=r.getTimestamp("submitted");
			int statusId=r.getInt("statusId");
			int author=r.getInt("author");
			int typeId=r.getInt("typeId");
			String uFirstName=r.getString("firstName");
			String uLastName=r.getString("lastName");
			String uemail=r.getString("email");
			Reimbursement reimb=new Reimbursement();
			User reimbAuthor= new User();
			ReimbursementStatus status=new ReimbursementStatus();
			ReimbursementType type=new ReimbursementType();
			
			reimb.setReimbId(id);
			reimb.setReimbAmount(amount);
			reimb.setSubmitted(submitted);
			
			reimbAuthor.setUserId(author);
			reimbAuthor.setFirstName(uFirstName);
			reimbAuthor.setLastName(uLastName);
			reimbAuthor.setFirstName(uFirstName);
			reimbAuthor.setLastName(uLastName);
			reimbAuthor.setEmail(uemail);
			reimb.setAuthor(id);
			
			status.setReimbStatusId(statusId);
			type.setReimbTypeId(typeId);
			
			results.add(reimb);
		}
		
		return results;
		
	}
	
	public void printList(List<Reimbursement> l){
		for(Reimbursement r: l){
			System.out.println(r.getAuthor());
		}
	}
	
	public void updateResolver(Reimbursement reimb)throws SQLException{
		String sql="update ERS_REIMBURSEMENT "+
	" Set ";
		//TODO put in update resolver
	}
	//TODO put in update blob
	//TODO put in update description 
	//TODO select all pending, approved, declined reimb?
	//TODO select all reimb period

	


}