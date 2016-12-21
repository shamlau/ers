package ers.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ers.beans.User;
import ers.data.DataFacadeTest;
import ers.middle.*;

public class DispatcherServlet extends HttpServlet {
	// TODO actually break up this servlet into working parts
	boolean validUser;
	Connection conn;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// cont'd from user service
		// this one puts user object onto session scope
		// then i can pull user from anywhere

		String requestURI = req.getRequestURI();
		switch (requestURI) {
		case "/ers/validate.do": {
			String uname = req.getParameter("Username");
			String pword = req.getParameter("Password");
			
			req.getSession().setAttribute("username", uname);
			req.getSession().setAttribute("password", pword);
/**
 * 			The following was commented out to ensure that dispatch servlet was not used more than necessary
 */
//			boolean validUserPw = false;
//			String uname = req.getParameter("Username");
//			String pword = req.getParameter("Password");
//			User user = new BusinessDelegate().getUser(uname);
//			System.out.println(user == null);
//			if (user.getUsername() != null) {
//				if (user.getPassword().equals(pword)) {
//					validUserPw = true;
//				} else {
//					validUserPw = false;
//				}
//			}
//
//			// TODO above would call User controller
//			if (validUserPw) {
//				System.out.println("Logged in");
//				req.getRequestDispatcher("reimb.do").forward(req, resp);
//				System.out.println("valid user");
//			} else {
//				resp.sendRedirect("fail.html");
//				System.out.println("failed user");
//			}
			new UserController().checkUser(req, resp);
		}
//		case "/ers/reimb.do": {
//			try {
//				new ReimbursementController().doPersonalReimb(req, resp);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			break;
//		}
		
//		case "/ers/managerReimb.do": {
//			System.out.println("manager Reimb.do");
//			try {
//				new ReimbursementController().doAllReimb(req, resp);
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//		}
		default: {
			resp.setStatus(404);
		}
		}
	}
}
