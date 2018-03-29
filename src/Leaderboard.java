import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "Leaderboard", urlPatterns = "/Leaderboard")
public class Leaderboard extends HttpServlet
{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int gameid = Integer.valueOf(request.getParameter("gameid"));
        //Either check the gameid explicitly or move directly to getting the data for the leaderboard from the game
        //logic. Not entirely sure how that will work, so this is where I'm going to hardcode the data in.
        ////////DEV CODE ONLY////////
        List<LeaderboardDataElement> boardData = new ArrayList<>();
        boardData.add(new LeaderboardDataElement("ttrojan", "15", 1234));
        boardData.add(new LeaderboardDataElement("jmiller", "45", 456));
        boardData.add(new LeaderboardDataElement("stregis", "80", 125437834));
        boardData.add(new LeaderboardDataElement("orahimi", "55", 12453434));
        boardData.add(new LeaderboardDataElement("manipusg", "40", 12543434));
        boardData.add(new LeaderboardDataElement("nathanzh", "60", 1000000));
        boardData.add(new LeaderboardDataElement("kaagarwa", "60", 100000));
        ////////END DEV. CODE////////
        boardData.sort(Comparator.comparingInt((LeaderboardDataElement o) -> -Integer.valueOf(o.score)).thenComparingInt(o -> o.timeTaken));
        //Loop to see if any ties happened and add emoji to indicate winner/loser to user
        for(int i = 0; i < boardData.size()-1; i++)
        {
            if(boardData.get(i).score.equals(boardData.get(i+1).score))
            {
                boardData.get(i).score += "&#x23F3";
                boardData.get(i+1).score += "&#x231B";
            }
        }
        Gson gson = new Gson();
        String outData = gson.toJson(boardData);
        PrintWriter pw = response.getWriter();
        pw.println(outData);
        pw.flush();
    }
}
