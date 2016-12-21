package ers.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ers.beans.User;
import ers.middle.BusinessDelegate;
import ers.middle.UserService;

public class UserController {

	public void sendAndReceive(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User user = new BusinessDelegate().getUser(username);
		if(user.getUsername()!=null){//This user exists
			if(user.getPassword().equals(password)){//This is a valid login
				if(user.getUserRole().getUserRole()=="MANAGER"){//if this is a manager
					req.getRequestDispatcher("managerTable.jsp");//not a file now but this should be a jsp?
				}else{
					req.getRequestDispatcher("employeeTable.jsp");//not a file now but this should be a jsp?
				}
			}else{//invalid password+username combination
				resp.sendRedirect("fail.html");
				System.out.println("failed user");
			}
		}else{
			resp.sendRedirect("fail.html");
			System.out.println("failed user");

		}
		
	}
}
