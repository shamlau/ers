package ers.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// error handling logic
		Exception e = (Exception) req.getAttribute("javax.servlet.error.exception");
		int statusCode= (Integer) req.getAttribute("javax.servlet.error.status_code");
		if(e !=null || e instanceof IllegalArgumentException){
			System.out.println(e.getMessage());
			resp.sendRedirect("http://wallpapercave.com/wp/r3BPFdb.jpg");
		}else if (statusCode == 404){
			resp.sendRedirect("ers404.html");
		}else if (statusCode == 403){
			//add reimbursment here
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
