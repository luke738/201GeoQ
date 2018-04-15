package servlets;

import shared.Connection;
import shared.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "Leaderboard", urlPatterns = "/Leaderboard")
public class Leaderboard extends HttpServlet
{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession curr = request.getSession();
        String username = (String)curr.getAttribute("username");

        Socket s = new Socket("localhost", 4367);
        Connection c = new Connection(s);
        Object obj = c.receiveObject();
        List<User> boardData = (List<User>)obj;
        c.close();

        //Sort by score then time, and drop anyone under the top 10
        boardData.sort(Comparator.comparingInt((User o) -> -o.score).thenComparingInt(o -> o.timeTaken));
        User user = new User("",0,0);
        for(User aBoardData : boardData)
        {
            if(aBoardData.username.equals(username)) user = aBoardData;
        }
        if(boardData.size()>10) boardData = boardData.subList(0, 10);
        boardData.add(user);
        boardData.sort(Comparator.comparingInt((User o) -> -o.score).thenComparingInt(o -> o.timeTaken));
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

            if(boardData.get(i).username.equals(username))
            {
                boardData.get(i).username = "<b>" + username + "</b>";
            }
        }
        if(boardData.get(boardData.size()-1).username.equals(username))
        {
            boardData.get(boardData.size()-1).username = "<b>" + username + "</b>";
        }
        Gson gson = new Gson();
        String outData = gson.toJson(boardData);
        PrintWriter pw = response.getWriter();
        pw.println(outData);
        pw.flush();
    }
}
