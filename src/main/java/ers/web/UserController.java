package ers.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ers.beans.User;
import ers.middle.BusinessDelegate;
import ers.middle.UserService;

public class UserController {

	/**
	 * Checks users to see if they are valid users
	 * or managers and sends them to respective tables
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void checkUser(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String username = (String) req.getSession().getAttribute("username");
		String password = (String) req.getSession().getAttribute("password");
		System.out.println("UN: "+ username);
		System.out.println("PW: "+ password);
		User user = new BusinessDelegate().getUser(username);
		
		if(user.getUsername()!=null){//This user exists
			if(user.getPassword().equals(password)){//This is a valid login
				System.out.println(user.getUserRole().getUserRole());
				if(user.getUserRole().getUserRole().equals("MANAGER")){//if this is a manager
					//req.getRequestDispatcher("managerTable.jsp");//not a file now but this should be a jsp?
					System.out.println("manager reached");
					try {
						new ReimbursementController().doAllReimb(req, resp);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println("code gets to full reimb.jsp");
					//req.getRequestDispatcher("fullreimb.jsp").forward(req, resp);
					
				}else{
					System.out.println("valid user");
					try {
						new ReimbursementController().doPersonalReimb(req, resp);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					//req.getRequestDispatcher("reimbursementViews.jsp");//not a file now but this should be a jsp?
				}
			}else{//invalid password+username combination
				resp.sendRedirect("fail.html");
				System.out.println("failed user pw combo");
			}
		}else{
			resp.sendRedirect("fail.html");
			System.out.println("failed user");

		}
		
	}
}
