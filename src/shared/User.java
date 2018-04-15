package shared;

import java.io.Serializable;

public class User implements Serializable
{
    public String username; //HTML will not be rendered
    public String emblems; //HTML to represent Miller emblems, client-side will display it blindly
    public int score;
    public String tieSymbol; //the servlet can do the tie-checking and add some sort of emoji to indicate who won the tie.
    public int timeTaken; //In milliseconds or something like that

    public User(String username)
    {
        //Get this user from the DB by using their username. For now, simplify
        this.username = username;
        emblems = "";
        score = 0;
        tieSymbol = "";
        timeTaken = 0;

    }

    public User(String username, String emblems)
    {
        this.username = username;
        this.emblems = emblems;
        score = 0;
        tieSymbol = "";
        timeTaken = 0;
    }

    public User(String username, String emblems, int score, int timeTaken)
    {
        this.username = username;
        this.emblems = emblems;
        this.score = score;
        this.timeTaken = timeTaken;
        tieSymbol = "";
    }

    public User(String username, int score, int timeTaken)
    {
        this.username = username;
        this.score = score;
        this.timeTaken = timeTaken;
        emblems = "";
        tieSymbol = "";
    }
}
