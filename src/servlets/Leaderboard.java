package servlets;

import backend.LeaderboardDataElement;
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

        ////////END DEV. CODE////////
        //Sort by score then time, and drop anyone under the top 10
        boardData.sort(Comparator.comparingInt((LeaderboardDataElement o) -> -o.score).thenComparingInt(o -> o.timeTaken));
        if(boardData.size()>10) boardData = boardData.subList(0, 10);
        //Loop to see if any ties happened and add emoji to indicate winner/loser to user
        for(int i = 0; i < boardData.size()-1; i++)
        {
            //Break embedded HTML tags
            boardData.get(i).username = boardData.get(i).username.replaceAll("<", "&lt;");
            boardData.get(i).username = boardData.get(i).username.replaceAll(">", "&gt;");
            //Add tie emoji
            if(boardData.get(i).score == boardData.get(i+1).score)
            {
                boardData.get(i).tieSymbol = "&#x23F3" + boardData.get(i).tieSymbol;
                boardData.get(i+1).tieSymbol += "&#x231B";
            }
        }
        Gson gson = new Gson();
        String outData = gson.toJson(boardData);
        PrintWriter pw = response.getWriter();
        pw.println(outData);
        pw.flush();
    }
}
