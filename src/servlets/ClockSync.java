package servlets;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

@WebServlet(name = "ClockSync", urlPatterns = "/ClockSync")
public class ClockSync extends HttpServlet
{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(); //This could be an existing session
        if(!session.isNew())
        {
            session.invalidate(); //Return to home page logs out a logged in user
            session = request.getSession(); //Get the true new session
        }
        //Connect to backend here and get settings data


        String outData = "";
        PrintWriter pw = response.getWriter();
        pw.println(outData);
        pw.flush();
    }
}
