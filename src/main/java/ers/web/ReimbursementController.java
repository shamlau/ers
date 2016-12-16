package ers.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ers.beans.Reimbursement;
import ers.data.DataFacade;

public class ReimbursementController {

	
	public void doAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException{
		DataFacade facade = new DataFacade();
		List<Reimbursement> reimbList = facade.selectAllReimbs();
		request.setAttribute("reimbursements", reimbList);
		request.getRequestDispatcher("reimbursementViews.jsp").forward(request, response);
	}
}
