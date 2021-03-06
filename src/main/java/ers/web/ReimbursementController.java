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

	/**
	 * Creates a table that allows a user to to view his/her reimbursements and submit a new one
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	public void doPersonalReimb(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		DataFacade facade = new DataFacade();
		List<Reimbursement> reimbList = facade.selectAllReimbursements();
		request.getSession().setAttribute("reimbursements", reimbList);
		if(request.getParameter("Username")!=null){
			request.getSession().setAttribute("uname", request.getParameter("Username"));
		}else{
			User user = (User) request.getSession().getAttribute("user");
			String username = user.getUsername(); 
			request.getSession().setAttribute("uname",username);
		}
		//System.out.println(request.getSession().getAttribute("user"));
		request.getRequestDispatcher("reimbursementViews.jsp").forward(request, response);
	}

	/**
	 * Creates the table for managers of all reimbursements by employees
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	public void doAllReimb(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		DataFacade facade = new DataFacade();
		List<Reimbursement> reimbList = facade.selectAllReimbursements();
		request.setAttribute("reimbursements", reimbList);
		request.getRequestDispatcher("fullReimbs.jsp").forward(request,response);

	}
	
	void approveReimbursement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DataFacade facade = new DataFacade();
		//System.out.println("reimbid gotten from deny" + request.getAttribute("reimbId"));
		int reimbId= Integer.parseInt(request.getParameter("reimbId"));
		User user = (User) request.getSession().getAttribute("user");
		//System.out.println("we got to where we wanted"+ user.getUsername());
		//System.out.println("reimbid: " +reimbId+ " resolverId: " + user.getUserId());
		facade.updateReimbursementStatus(reimbId, 3, user.getUserId());//3 is id for approve but this is not loosely coupled
		try {
			new ReimbursementController().doAllReimb(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void denyReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		DataFacade facade = new DataFacade();
		int reimbId= Integer.parseInt(request.getParameter("reimbId"));
		User user = (User) request.getSession().getAttribute("user");
		//System.out.println("reimbid: " +reimbId+ " resolverId: " + user.getUserId());
		facade.updateReimbursementStatus(reimbId, 2, user.getUserId());//2 is id for deny
		try {
			new ReimbursementController().doAllReimb(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void insertReimbursement(HttpServletRequest request, HttpServletResponse response){
		DataFacade facade = new DataFacade();
		//System.out.println(request.getParameter("reimbAmount"));
		double amount = Double.parseDouble(request.getParameter("reimbAmount"));
		String description = request.getParameter("description");
		String type = request.getParameter("type");
		int typeid = facade.getTypeId(type);
		request.getSession().getAttribute("user").getClass();
		User user = (User) request.getSession().getAttribute("user");
		int authorId = user.getUserId();
		//System.out.println("amount : "+ amount+ " desc: "+ description + " type: " + typeid +" userid: " + authorId);
		facade.insertReimbursement(amount, description, authorId, typeid);
		try {
			new ReimbursementController().doPersonalReimb(request, response);//return to personal reimbursements after submitting a reimb
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
