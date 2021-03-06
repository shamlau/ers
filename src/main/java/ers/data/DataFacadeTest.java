package ers.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import ers.beans.Reimbursement;
import ers.beans.ReimbursementStatus;
import ers.beans.ReimbursementType;
import ers.beans.User;
import ers.data.ReimbursementDAO;
import monfox.toolkit.snmp.agent.modules.SnmpV2Mib.SysOREntry;;

/**
 * Old class that tested DAOS
 * Used JDBC but new project uses JNDI
 * @author Sam
 *
 */
public class DataFacadeTest {
	//Connection conn;
/*	private static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="ers";
	private static final String PASS= "welcome1";
	Connection conn;	
	public boolean validateUser(String userName,String password) throws ClassNotFoundException, SQLException{
		
		UserDAO dao=new UserDAO(conn);
		Class.forName("oracle.jdbc.OracleDriver");
		conn=DriverManager.getConnection(URL,USER,PASS);
		return dao.validUser(userName, password);
		
	
	}

	
	public static List<Reimbursement> result (ResultSet r) throws SQLException{
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
		
	}*/
	
	public static void printList(List<Reimbursement> l){
		for(Reimbursement r: l){
			System.out.println(r.getReimbAmount());
		}
	}


	public static void main(String[] args) throws Exception {
		
		//TODO organize this stuff
		

		Connection conn=ServiceLocator.getErsDatabase().getConnection();
		/**
		 * This hashes the passwords. We've Done it. DO NOT DO THIS AGAIN.
		 */
//		UserDAO dao = new UserDAO(conn);
//		for(int i=1;i<=7;i++){
//			dao.updatePassword(i);
//		}
		//UserDAO dao = new UserDAO(conn);
		//dao.updatePassword(1);
		//String Login="123";
		TypeDAO dao = new TypeDAO(conn);
		System.out.println(dao.getTypeId("BUSINESS"));
		//System.out.println(BCrypt.checkpw(Login, dao.getPassword(1)));
		//System.out.println(BCrypt.hashpw(Login, BCrypt.gensalt()));
		//System.out.println(dao.getPassword(1));
//		ReimbursementDAO dao = new ReimbursementDAO(conn);
//		List<Reimbursement> list=dao.selectAllReimbursements();
//		for (Reimbursement r: list){
//			System.out.println(r);
//		}
//		
//		UserDAO dao2 = new UserDAO(conn);
//		System.out.println(dao2.isManager("JSDMITH"));
//		UserDAO dao3 = new UserDAO(conn);
//		System.out.println(dao3.getFullName(1));
//		UserDAO dao = new UserDAO(conn);
//		System.out.println(dao.getUser("JSMITH"));
//		dao.close();
//		dao2.close();
/*		Connection conn;
		Connection conn2=ServiceLocator.getErsDatabase().getConnection();
		
		Class.forName("oracle.jdbc.OracleDriver");
		conn=DriverManager.getConnection(URL,USER,PASS);
		RoleDAO dao = new RoleDAO();
		//dao.selectTest(1);
		//System.out.println("Role DAO worked");
		dao.close();
		
		RoleDAO dao4=new RoleDAO(conn2);
		System.out.println("Status test: "+ dao4.roleFromUser(7));
		dao4.close();
		
		ReimbursementDAO dao2= new ReimbursementDAO(conn);
		ResultSet rs1=dao2.fullReimbUserJoin("JSMITH");
		System.out.println("rs get row "+ rs1.getRow());
		int count=0;
		System.out.println("rs next "+ rs1.next());
		while(rs1.next()){
			count++;
			System.out.println("anythin?");
			System.out.println("ReimbDAO test" + rs1.getString(2));
		}
		System.out.println("count: " +count);
		List<Reimbursement> li=new ArrayList<Reimbursement>();
		List<Reimbursement> l2= result(rs1);
		//printList(l2);
		//dao2.selectReimbursments(1);
		//dao2.close();
		
		// List<Reimbursement> listo=new List<Reimbursement>();
		
		UserDAO dao3=new UserDAO(conn);
		System.out.println(dao3.getFullName(3));
		
		DataFacadeTest d=new DataFacadeTest();
		System.out.println(dao3.validUser("JSMITH", "123"));
		dao2.close();
		dao3.close();*/
	}
}
