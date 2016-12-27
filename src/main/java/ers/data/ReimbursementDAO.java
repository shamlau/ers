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

class ReimbursementDAO {

	private Connection conn;

	public ReimbursementDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public void close() throws SQLException {
		conn.close();
	}

	/**
	 * Inserts a new reimbursement into the database
	 * 
	 * @param amount
	 * @param description
	 * @param authorId
	 * @param typeId
	 * @throws SQLException
	 */
	public void insertReimbursement(double amount, String description, int authorId, int typeId) throws SQLException {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String sql = "INSERT INTO ERS_REIMBURSEMENT "
				+ "(REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_STATUS_ID,"
				+ "REIMB_TYPE_ID) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, 1); // can be one because triggers will always increment
							// so pk not violated
		stmt.setDouble(2, amount);
		stmt.setTimestamp(3, now);
		stmt.setString(4, description);
		stmt.setInt(5, authorId);
		stmt.setInt(6, 1);// always set to pending initially
		stmt.setInt(7, typeId);
		stmt.execute();
	}// insertReimbursments

	/**
	 * Updates reimbursement to new status
	 * 
	 * @param reimbId
	 * @param newStatusNumb
	 * @throws SQLException
	 */
	public void updateReimbursementStatus(int reimbId, int newStatusNumb, int resolverId) throws SQLException {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String sql = "UPDATE ERS_REIMBURSEMENT " + "SET REIMB_STATUS_ID = ?, REIMB_RESOLVED = ?, REIMB_RESOLVER = ?"
				+ "WHERE REIMB_ID= ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, newStatusNumb);
		stmt.setTimestamp(2, now);
		stmt.setInt(3, resolverId);
		stmt.setInt(4, reimbId);
		stmt.execute();
	}// updateReimbursementStatus

	/**
	 * Creates list of all reimbursements
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Reimbursement> selectAllReimbursements() throws SQLException {
		String sql = "SELECT R.REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_DESCRIPTION, REIMB_RECEIPT, "
				+ "REIMB_AUTHOR, REIMB_RESOLVER, R.REIMB_STATUS_ID, R.REIMB_TYPE_ID, REIMB_STATUS, REIMB_TYPE, "
				+ "ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID, USER_ROLE  "
				+ "FROM ERS_REIMBURSEMENT R LEFT OUTER JOIN ERS_REIMBURSEMENT_STATUS S "
				+ "ON R.REIMB_STATUS_ID=S.REIMB_STATUS_ID LEFT OUTER JOIN ERS_REIMBURSEMENT_TYPE T "
				+ "ON R.REIMB_TYPE_ID=T.REIMB_TYPE_ID INNER JOIN ERS_USERS U "
				+ "ON U.ERS_USERS_ID=R.REIMB_AUTHOR INNER JOIN ERS_USER_ROLES UR "
				+ "ON UR.ERS_USER_ROLE_ID=U.USER_ROLE_ID";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Reimbursement> lr = new ArrayList<Reimbursement>();
		mapRows(rs, lr);
		return lr;
	}

	/**
	 * Selects all Reimbursements by specified user by specfied username
	 * 
	 * @param Username
	 * @return
	 * @throws SQLException
	 */
	public List<Reimbursement> selectAllReimbursementsFromUser(String Username) throws SQLException {
		String sql = "SELECT R.REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_DESCRIPTION, REIMB_RECEIPT, "
				+ "REIMB_AUTHOR, REIMB_RESOLVER, R.REIMB_STATUS_ID, R.REIMB_TYPE_ID, REIMB_STATUS, REIMB_TYPE, "
				+ "ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID, USER_ROLE  "
				+ "FROM ERS_REIMBURSEMENT R LEFT OUTER JOIN ERS_REIMBURSEMENT_STATUS S "
				+ "ON R.REIMB_STATUS_ID=S.REIMB_STATUS_ID LEFT OUTER JOIN ERS_REIMBURSEMENT_TYPE T "
				+ "ON R.REIMB_TYPE_ID=T.REIMB_TYPE_ID INNER JOIN ERS_USERS U "
				+ "ON U.ERS_USERS_ID=R.REIMB_AUTHOR INNER JOIN ERS_USER_ROLES UR "
				+ "ON UR.ERS_USER_ROLE_ID=U.USER_ROLE_ID " + "WHERE ERS_USERNAME='" + Username + "'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		// stmt.setString(1, Username);
		List<Reimbursement> lr = new ArrayList<Reimbursement>();
		mapRows(rs, lr);
		return lr;
	}

	/**
	 * So this maps if the beans contain objects of each other will test if
	 * something else works
	 * 
	 * @param rs
	 * @param list
	 * @throws SQLException
	 */
	public void mapRows(ResultSet rs, List<Reimbursement> list) throws SQLException {
		while (rs.next()) {
			// INFO from Reimbursements
			int id = rs.getInt("REIMB_ID");
			double amount = rs.getDouble("REIMB_AMOUNT");
			Timestamp submitted = rs.getTimestamp("REIMB_SUBMITTED");
			Timestamp resolved = rs.getTimestamp("REIMB_RESOLVED");
			String description = rs.getString("REIMB_DESCRIPTION");
			Blob recipt = rs.getBlob("REIMB_RECEIPT");
			int authorId = rs.getInt("REIMB_AUTHOR");
			int resolverId = rs.getInt("REIMB_RESOLVER");
			int statusId = rs.getInt("REIMB_STATUS_ID");
			int typeId = rs.getInt("REIMB_TYPE_ID");
			String rstatus = rs.getString("REIMB_STATUS");
			String rtype = rs.getString("REIMB_TYPE");
			// INFO from Users
			String username = rs.getString("ERS_USERNAME");
			String firstName = rs.getString("USER_FIRST_NAME");
			String lastName = rs.getString("USER_LAST_NAME");
			String email = rs.getString("USER_EMAIL");
			String password = rs.getString("ERS_PASSWORD");
			int roleId = rs.getInt("USER_ROLE_ID");
			String uRole = rs.getString("USER_ROLE");

			// Creating new objects
			// Create and fill Status obj
			ReimbursementStatus status = new ReimbursementStatus();
			status.setReimbStatusId(statusId);
			status.setReimbStatus(rstatus);
			// Create and fill Type obj
			ReimbursementType type = new ReimbursementType();
			type.setReimbTypeId(typeId);
			type.setReimbType(rtype);
			// Create and fill Role Object
			UserRole userRole = new UserRole(roleId, uRole);
			// Create and fill author
			User author = new User();
			author.setUserId(authorId);
			author.setUsername(username);
			author.setPassword(password);
			author.setFirstName(firstName);
			author.setLastName(lastName);
			author.setUserRole(userRole);
			author.setEmail(email);
			User resolver = new User();
			resolver.setUserId(resolverId);// We only have id

			// Create and fill reimbursements
			Reimbursement reimb = new Reimbursement();
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
}