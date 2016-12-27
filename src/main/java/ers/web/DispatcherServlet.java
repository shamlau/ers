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

	boolean validUser;
	Connection conn;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestURI = req.getRequestURI();
		switch (requestURI) {
		
		/**
		 * Checks user after logging in
		 */
		case "/ers/validate.do": {
			String uname = req.getParameter("Username");
			String pword = req.getParameter("Password");
			req.getSession().setAttribute("username", uname);
			req.getSession().setAttribute("password", pword);
			new UserController().checkUser(req, resp);
			break;
		}

		/**
		 * Following 2 cases are for the reimbursement approve/deny buttons
		 */
		case "/ers/reimbApprove.do": {
			new ReimbursementController().approveReimbursement(req, resp);
			break;
		}

		case "/ers/reimbDeny.do": {
			new ReimbursementController().denyReimbursement(req, resp);
			break;
		}

		/**
		 * This is for the submission form
		 */
		case "/ers/submit.do": {
			//System.out.println("submit.do");
			new ReimbursementController().insertReimbursement(req, resp);
			break;
		}

		default: {
			resp.setStatus(404);
		}
		}
	}
}
