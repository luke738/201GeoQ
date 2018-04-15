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
 * Servlet implementation class loginValidation
 */
@WebServlet(name = "LoginValidation", urlPatterns = "/LoginValidation")
public class LoginValidation extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession curr = request.getSession();
		String name = request.getParameter("username");
		String password = request.getParameter("password");

		Socket s = new Socket("localhost", 4370);
        Connection c = new Connection(s);
        c.send(new Message(name, password));
        Boolean valid = c.receive(Boolean.class);

        if(valid)
		{
			curr.setAttribute("username",name);
		}

        PrintWriter pw = response.getWriter();
        pw.println(valid.toString());
        pw.flush();
	}
}