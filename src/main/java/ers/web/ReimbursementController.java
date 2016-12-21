package ers.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ers.beans.Reimbursement;
import ers.beans.User;
import ers.data.DataFacade;

public class ReimbursementController {

	public void doPersonalReimb(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		DataFacade facade = new DataFacade();
		List<Reimbursement> reimbList = facade.selectAllReimbs();
		//TODO be able to get the user object here then uncomment it
//		request.getSession().setAttribute(arg0, arg1);
		request.setAttribute("reimbursements", reimbList);
//		System.out.println(request.getParameter("Username"));
		request.setAttribute("uname", request.getParameter("Username"));
		request.getRequestDispatcher("reimbursementViews.jsp").forward(request, response);
	}

	public void doAllReimb(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		DataFacade facade = new DataFacade();
		List<Reimbursement> reimbList = facade.selectAllReimbs();
		request.setAttribute("reimbursements", reimbList);
		request.getRequestDispatcher("fullReimbs.jsp").forward(request,response);

	}
	
	void approveReimbursement(HttpServletRequest request, HttpServletResponse response){
		DataFacade facade = new DataFacade();
		int reimbId=(int) request.getAttribute("reimbId");
		//TODO find a way to get the resolver id
		facade.updateReimbursementStatus(reimbId, 3, 7);
	}
	
	void denyReimbursement(HttpServletRequest request, HttpServletResponse response){
		DataFacade facade = new DataFacade();
		int reimbId=(int) request.getAttribute("reimbId");
		//TODO Check if 2 is reimburs approve
		facade.updateReimbursementStatus(reimbId, 2, 7);
	}
	
	void doMain(HttpServletRequest request, HttpServletResponse response){
		DataFacade facade = new DataFacade();
		request.getSession().getAttribute("u");
	}
}
