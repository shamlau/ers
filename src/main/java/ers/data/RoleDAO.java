package ers.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import ers.beans.UserRole;

public class RoleDAO {

	@Resource(name= "ers/datasource")
	private DataSource ersData;
	
	private Connection conn;
	private static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="ers";
	private static final String PASS= "welcome1";
	

	public RoleDAO() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		conn=DriverManager.getConnection(URL,USER,PASS);
		//conn=ersData.getConnection();
	}
	
	RoleDAO(Connection conn){
		super();
		this.conn=conn;
	}
	
	public void close() throws SQLException{
		conn.close();
	}

	//just testing to make sure this worked
	public ResultSet selectTest(int id) throws SQLException{
		String sql = "Select USER_ROLE FROM ERS_USER_ROLES WHERE ERS_USER_ROLE_ID=?";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString(1));
		}
		return rs;

	}


	//Works
	public String roleFromUser(int UserId) throws SQLException{
		String sql ="SELECT USER_ROLE FROM ERS_USER_ROLES INNER JOIN ERS_USERS ON"
				+ " ERS_USER_ROLES.ERS_USER_ROLE_ID=ERS_USERS.USER_ROLE_ID WHERE ERS_USERS_ID=?";		
		String role="";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1, UserId);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			role=rs.getString(1);
		}
		return role;
	}

	
	


}
