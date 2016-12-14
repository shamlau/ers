package ers.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ers.beans.Reimbursement;
import ers.beans.ReimbursementStatus;
import ers.beans.ReimbursementType;
import ers.beans.User;
import ers.data.ReimbursementDAO;;
public class DataFacadeTest {
	//Connection conn;
	private static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
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
		
	}
	
	public static void printList(List<Reimbursement> l){
		for(Reimbursement r: l){
			System.out.println(r.getReimbAmount());
		}
	}


	public static void main(String[] args) throws Exception {
		
		//TODO organize this stuff
		Connection conn;
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
		ResultSet rs=dao2.fullReimbUserJoin("JSMITH");
		List<Reimbursement> li=new ArrayList<Reimbursement>();
		List<Reimbursement> l2= result(rs);
		printList(l2);
		//dao2.selectReimbursments(1);
		//dao2.close();
		
		// List<Reimbursement> listo=new List<Reimbursement>();
		
		UserDAO dao3=new UserDAO(conn);
		System.out.println(dao3.getFullName(3));
		
		DataFacadeTest d=new DataFacadeTest();
		System.out.println(dao3.validUser("JSMITH", "123"));
		dao2.close();
		dao3.close();
	}
}
