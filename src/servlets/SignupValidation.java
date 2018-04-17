package servlets;
import shared.Connection;
import shared.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignupValidation
 */
@WebServlet(name = "SignupValidation", urlPatterns = "/SignupValidation")
public class SignupValidation extends HttpServlet
{
	private static final long serialVersionUID = 1L;
  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession curr = request.getSession();
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmpassword");

		String resp = "passNoMatch";
		if(password.equals(confirmPassword))
		{
			System.out.println();
			Socket s = new Socket("localhost", 4371);
			Connection c = new Connection(s);
			c.send(new Message(name, password));
			resp = c.receive(String.class);

			if(resp.equals("accept"))
			{
				curr.setAttribute("username", name);
			}
		}

		PrintWriter pw = response.getWriter();
		pw.print(resp);
		pw.flush();
	}
}
