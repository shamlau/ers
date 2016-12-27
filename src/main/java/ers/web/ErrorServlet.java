package ers.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Attempt at redirecting users if they reach a error page
 * Will continue to work on it
 * @author Sam
 *
 */
public class ErrorServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// error handling logic
		Exception e = (Exception) req.getAttribute("javax.servlet.error.exception");
		int statusCode= (Integer) req.getAttribute("javax.servlet.error.status_code");
		if (statusCode == 404){
			resp.sendRedirect("/ers404.html");
		}else if (statusCode == 500){
			resp.sendRedirect("ers500.html");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
