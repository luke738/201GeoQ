import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Leaderboard", urlPatterns = "/Leaderboard")
public class Leaderboard extends HttpServlet
{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int gameid = Integer.valueOf(request.getParameter("gameid"));
        //Either check the gameid explicitly or move directly to getting the data for the leaderboard from the game
        //logic. Not entirely sure how that will work, so this is where I'm going to hardcode the data in.
        ////////DEV CODE ONLY////////

        ////////END DEV. CODE////////
    }
}
