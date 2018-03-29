public class LeaderboardDataElement
{
    public String username; //Can include HTML to represent Miller emblems, client-side will display it blindly
    public String score; //Yes, string. I'm thinking the servlet can do the tie-checking and add some sort of emoji to indicate who won the tie.
    public int timeTaken; //In milliseconds or something like that

    public LeaderboardDataElement(String username, String score, int timeTaken)
    {
        this.username = username;
        this.score = score;
        this.timeTaken = timeTaken;
    }
}
