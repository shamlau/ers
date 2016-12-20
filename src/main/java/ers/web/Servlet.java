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
import ers.middle.*;

public class Servlet extends HttpServlet {
//TODO actually break up this servlet into working parts
	boolean validUser;
	Connection conn;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//cont'd from user service
		//this one puts user object onto session scope
		//then i can pull user from anywhere
		String uname=req.getParameter("Username");
		String pword=req.getParameter("Password");
		validUser=UserService.validLogin(uname,pword);
		
		if(validUser){
			req.getRequestDispatcher("postLogin.jsp").forward(req, resp);
			System.out.println("valid user");
		}else{
			resp.sendRedirect("fail.html");
			System.out.println("failed user");
		}
	}
}
