package servlets;

import shared.Connection;
import shared.GameSettings;
import shared.GameSettingsSimple;
import shared.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@WebServlet(name = "Management", urlPatterns = "/Management")
public class Management extends HttpServlet
{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter pw = response.getWriter();
        String password = request.getParameter("password");
        int startTime = Integer.valueOf(request.getParameter("startTime"));
        int timeBetweenGame = Integer.valueOf(request.getParameter("timeBetweenGame"));
        int numQuestions = Integer.valueOf(request.getParameter("numQuestions"));
        int questionTime = Integer.valueOf(request.getParameter("questionTime"));
        int leaderboardTime = Integer.valueOf(request.getParameter("leaderboardTime"));
        String startNow = request.getParameter("startNow");
        System.out.println("*******************************");
        System.out.println(startTime);
        Socket s = new Socket("localhost", 4365);
        Connection c = new Connection(s);
        System.out.println("serv"+startTime);
        GameSettingsSimple set = new GameSettingsSimple(startTime, 0, timeBetweenGame, numQuestions, questionTime, leaderboardTime);
        if(startNow.equals("true"))
        {
            System.out.println("");
            set.startTimeEpoch = (int)LocalDateTime.now().plusSeconds(10).toEpochSecond(ZoneOffset.ofHours(-7));
        }
        c.send(new Message(password, set));
        Boolean valid = c.receive(Boolean.class);
        System.out.println("got");

        pw.println(valid.toString());
        pw.flush();
    }
}
