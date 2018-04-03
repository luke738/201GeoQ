package geoQ;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class signupValidation
 */
@WebServlet("/signupValidation")
public class signupValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession curr = request.getSession();
		String name = request.getParameter("username");
	    	String password = request.getParameter("passnord");
	    	String confirmPassword = request.getParameter("confirmpassword");
		
	
	}
}
