package ers.data;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ers.beans.Reimbursement;
import ers.beans.ReimbursementStatus;
import ers.beans.ReimbursementType;
import ers.beans.*;


public class ReimbursementDAO {

	private Connection conn;

	public ReimbursementDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public void close() throws SQLException {
		conn.close();
	}
	
	
	//Works
	public void insertReimbursement(double amount, String description, int authorId, int typeId) throws SQLException{
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String sql = "INSERT INTO ERS_REIMBURSEMENT "
				+ "(REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_STATUS_ID,"
				+ "REIMB_TYPE_ID) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, 1);
		stmt.setDouble(2, amount);
		stmt.setTimestamp(3, now);
		stmt.setString(4, description);
		stmt.setInt(5, authorId);
		stmt.setInt(6, 1);//always set to pending
		stmt.setInt(7, typeId);
		stmt.execute();
	}
	
	//TODO test
	public void updateReimbursementStatus(int reimbId, int newStatusNumb) throws SQLException{
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String sql = "UPDATE ERS_REIMBURSEMENT "
				+ "SET REIMB_STATUS_ID = ?, REIMB_RESOLVED = ? "
				+ "WHERE REIMB_ID= ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, newStatusNumb);
		stmt.setTimestamp(2, now);
		stmt.setInt(3, reimbId);
		stmt.execute();
	}

	
	public ResultSet selectReimbursements() throws SQLException {
		String sql = "SELECT REIMB_ID, REIMB_AMOUNT FROM ERS_REIMBURSEMENT";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			System.out.println("id: " + rs.getString(1) + " amount: " + rs.getString(2));
		}
		return rs;

	}
	

	


	public ResultSet selectNecessaryPartsOfReimbursments(int num) throws SQLException {
		// int idNum=user.getUserId();
		String sql = "SELECT REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_STATUS_ID, REIMB_TYPE_ID FROM ERS_REIMBURSEMENT INNER JOIN ERS_USERS  ON ERS_USERS_ID=REIMB_AUTHOR WHERE REIMB_AUTHOR =?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, num);
		ResultSet rs = stmt.executeQuery();
		return rs;
	}

	public ResultSet fullReimbUserJoin(String Username) throws SQLException {
		String sql = "SELECT REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID, ERS_USERS_ID, ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID FROM ERS_REIMBURSEMENT INNER JOIN ERS_USERS ON ERS_USERS_ID=REIMB_AUTHOR WHERE ERS_USERNAME='"
				+ Username + "'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			//System.out.println(rs.getString(1));
		}
		return rs;

	}


	public List<Reimbursement> rsToList(ResultSet r) throws SQLException{
		List<Reimbursement> results = new ArrayList<Reimbursement>();
		while (r.next()){
			int id=r.getInt("reimbId");
			
		}
		return results;
	}

	/**
	 * Creates list of all reimbursements
	 * @return
	 * @throws SQLException
	 */
	//I think this works
	public List<Reimbursement> selectAllReimbursements() throws SQLException{
		String sql= "SELECT R.REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_DESCRIPTION, REIMB_RECEIPT, "
				+ "REIMB_AUTHOR, REIMB_RESOLVER, R.REIMB_STATUS_ID, R.REIMB_TYPE_ID, REIMB_STATUS, REIMB_TYPE, "
				+ "ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID, USER_ROLE  "
				+ "FROM ERS_REIMBURSEMENT R LEFT OUTER JOIN ERS_REIMBURSEMENT_STATUS S "
				+ "ON R.REIMB_STATUS_ID=S.REIMB_STATUS_ID LEFT OUTER JOIN ERS_REIMBURSEMENT_TYPE T "
				+ "ON R.REIMB_TYPE_ID=T.REIMB_TYPE_ID INNER JOIN ERS_USERS U "
				+ "ON U.ERS_USERS_ID=R.REIMB_AUTHOR INNER JOIN ERS_USER_ROLES UR "
				+ "ON UR.ERS_USER_ROLE_ID=U.USER_ROLE_ID";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs =stmt.executeQuery();	
		List<Reimbursement> lr = new ArrayList<Reimbursement>();
		mapRows(rs, lr);
		return lr;
	}
	
	/**
	 * Selects all Reimbursements by specified user by specfied username
	 * @param Username
	 * @return
	 * @throws SQLException
	 */
	public List<Reimbursement> selectAllReimbursementsFromUser(String Username) throws SQLException{
		String sql= "SELECT R.REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_DESCRIPTION, REIMB_RECEIPT, "
				+ "REIMB_AUTHOR, REIMB_RESOLVER, R.REIMB_STATUS_ID, R.REIMB_TYPE_ID, REIMB_STATUS, REIMB_TYPE, "
				+ "ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID, USER_ROLE  "
				+ "FROM ERS_REIMBURSEMENT R LEFT OUTER JOIN ERS_REIMBURSEMENT_STATUS S "
				+ "ON R.REIMB_STATUS_ID=S.REIMB_STATUS_ID LEFT OUTER JOIN ERS_REIMBURSEMENT_TYPE T "
				+ "ON R.REIMB_TYPE_ID=T.REIMB_TYPE_ID INNER JOIN ERS_USERS U "
				+ "ON U.ERS_USERS_ID=R.REIMB_AUTHOR INNER JOIN ERS_USER_ROLES UR "
				+ "ON UR.ERS_USER_ROLE_ID=U.USER_ROLE_ID "
				+ "WHERE ERS_USERNAME='"+Username+"'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs =stmt.executeQuery();	
		//stmt.setString(1, Username);
		List<Reimbursement> lr = new ArrayList<Reimbursement>();
		mapRows(rs, lr);
		return lr;
	}
	
	
	/**
	 * So this maps if the beans contain objects of each other will test if something else works
	 * @param rs
	 * @param list
	 * @throws SQLException
	 */
	public void mapRows(ResultSet rs, List<Reimbursement> list) throws SQLException{
		while(rs.next()){
			//INFO from Reimbursements
			int id = rs.getInt("REIMB_ID");
			double amount = rs.getDouble("REIMB_AMOUNT");
			Timestamp submitted = rs.getTimestamp("REIMB_SUBMITTED");
			Timestamp resolved= rs.getTimestamp("REIMB_RESOLVED");
			String description = rs.getString("REIMB_DESCRIPTION");
			Blob recipt = rs.getBlob("REIMB_RECEIPT");
			int authorId = rs.getInt("REIMB_AUTHOR");
			int resolverId = rs.getInt("REIMB_RESOLVER");
			int statusId = rs.getInt("REIMB_STATUS_ID");
			int typeId = rs.getInt("REIMB_TYPE_ID");
			String rstatus = rs.getString("REIMB_STATUS");
			String rtype = rs.getString("REIMB_TYPE");
			//INFO from Users
			String username = rs.getString("ERS_USERNAME");
			String firstName = rs.getString("USER_FIRST_NAME");
			String lastName = rs.getString("USER_LAST_NAME");
			String email = rs.getString("USER_EMAIL");
			String password = rs.getString("ERS_PASSWORD");//maybe we shouldn't store this
			int roleId = rs.getInt("USER_ROLE_ID");
			String uRole = rs.getString("USER_ROLE");
			
			//Creating new objects
			//Create and fill Status obj
			ReimbursementStatus status = new ReimbursementStatus();
			status.setReimbStatusId(statusId);
			status.setReimbStatus(rstatus);
			//Create and fill Type obj
			ReimbursementType type = new ReimbursementType();
			type.setReimbTypeId(typeId);
			type.setReimbType(rtype);
			//Create and fill Role Object
			UserRole userRole = new UserRole(roleId, uRole);
			//Create and fill author
			User author = new User();
			author.setUserId(authorId);
			author.setUsername(username);
			author.setPassword(password);
			author.setFirstName(firstName);
			author.setLastName(lastName);
			author.setUserRole(userRole);
			author.setEmail(email);
			User resolver = new User();
			resolver.setUserId(resolverId);//We only have id

			//Create and fill reimbursements
			Reimbursement reimb=new Reimbursement();			
			reimb.setReimbId(id);
			reimb.setReimbAmount(amount);
			reimb.setSubmitted(submitted);
			reimb.setResolved(resolved);
			reimb.setDescription(description);
			reimb.setRecipt(recipt);
			reimb.setAuthor(author);
			reimb.setResolver(resolver);
			reimb.setStatus(status);
			reimb.setType(type);
			list.add(reimb);
		}
	}
	

	// TODO figure what to do with multiple wild cards
	public void updateResolver(int ReimbId, int resolverId) throws SQLException {
		Date now = new Date(System.currentTimeMillis());
		String sql = "UPDATE ERS_REIMBURSEMENT SET REIMB_RESOLVED=" + now + ",REIMB_RESOLVER=? WHERE REIMB_ID=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, resolverId);

		stmt.executeQuery();
	}

	// TODO put in update blob
	// TODO put in update description

	// Works
	public ResultSet fullerReimbursmentSelect() throws SQLException {
		String sql = "SELECT REIMB_ID, ERS_USERNAME, USER_FIRST_NAME || ' ' || USER_LAST_NAME AS FULLNAME, "
				+ "REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_STATUS, REIMB_TYPE "
				+ "FROM ERS_REIMBURSEMENT R LEFT OUTER JOIN ERS_REIMBURSEMENT_STATUS S "
				+ "ON R.REIMB_STATUS_ID=S.REIMB_STATUS_ID " + "LEFT OUTER JOIN ERS_REIMBURSEMENT_TYPE T "
				+ "ON R.REIMB_TYPE_ID=T.REIMB_TYPE_ID " + "INNER JOIN ERS_USERS U "
				+ "ON U.ERS_USERS_ID=R.REIMB_AUTHOR";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDate(4) + " "
					+ rs.getDate(5) + " " + rs.getString(6) + " " + rs.getString(7));
		}
		
		return rs;
	}

	// TODO this works but you might wanna remove username + full name
	public ResultSet reimbByUser(String username) throws SQLException {
		String sql = "SELECT REIMB_ID, ERS_USERNAME, USER_FIRST_NAME || ' ' || USER_LAST_NAME AS FULLNAME, "
				+ "REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_STATUS, REIMB_TYPE " + "FROM ERS_REIMBURSEMENT R "
				+ "LEFT OUTER JOIN ERS_REIMBURSEMENT_STATUS S " + "ON R.REIMB_STATUS_ID=S.REIMB_STATUS_ID "
				+ "LEFT OUTER JOIN ERS_REIMBURSEMENT_TYPE T " + "ON R.REIMB_TYPE_ID=T.REIMB_TYPE_ID "
				+ "INNER JOIN ERS_USERS U " + "ON U.ERS_USERS_ID=R.REIMB_AUTHOR " + "WHERE ERS_USERNAME='" + username
				+ "'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDate(4) + " "
					+ rs.getDate(5) + " " + rs.getString(6) + " " + rs.getString(7));
		}
		return rs;
	}

	// Works
	public ResultSet reimbByUserAndStatus(String username, String status) throws SQLException {
		String sql = "SELECT REIMB_ID, ERS_USERNAME, USER_FIRST_NAME || ' ' || USER_LAST_NAME AS FULLNAME, "
				+ "REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_TYPE " + "FROM ERS_REIMBURSEMENT R "
				+ "LEFT OUTER JOIN ERS_REIMBURSEMENT_STATUS S " + "ON R.REIMB_STATUS_ID=S.REIMB_STATUS_ID "
				+ "LEFT OUTER JOIN ERS_REIMBURSEMENT_TYPE T " + "ON R.REIMB_TYPE_ID=T.REIMB_TYPE_ID "
				+ "INNER JOIN ERS_USERS U " + "ON U.ERS_USERS_ID=R.REIMB_AUTHOR " + "WHERE ERS_USERNAME='" + username
				+ "'AND REIMB_STATUS='" + status + "'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDate(4) + " "
					+ rs.getDate(5) + " " + rs.getString(6));
		}
		return rs;

	}

}