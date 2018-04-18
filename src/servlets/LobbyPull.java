package servlets;

import shared.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

@WebServlet(name = "LobbyPull", urlPatterns = "/LobbyPull")
public class LobbyPull extends HttpServlet
{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Connection c = new Connection(new Socket("localhost", 4366));
        c.send("lobbyPull");
        PrintWriter pw = response.getWriter();
        pw.println(c.receive(String.class));
        pw.flush();
    }
}
