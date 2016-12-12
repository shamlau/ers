package ers.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ers.data.DataFacadeTest;
import ers.data.UserDAO;

public class Servlet extends HttpServlet {
//TODO actually break up this servlet into working parts
	private static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="ers";
	private static final String PASS= "welcome1";
	Connection conn;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			conn=DriverManager.getConnection(URL,USER,PASS);
			String uname=req.getParameter("Username");
			String pword=req.getParameter("Password");
			//DataFacadeTest df=new DataFacadeTest();
			//TODO figure out who to remove DAO from Servlet and call facade?
			//boolean validUser=df.validateUser(uname,pword);
			UserDAO dao3=new UserDAO(conn);
			boolean validUser=dao3.validUser(uname, pword);
			if(validUser){
				//req.getRequestDispatcher("postLogin.jsp").forward(req, resp);
				resp.sendRedirect("postLogin.jsp");
				//System.out.println("valid user");
			}else{
				resp.sendRedirect("fail.html");
				//System.out.println("failed user");
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
