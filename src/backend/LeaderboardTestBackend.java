package backend;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardTestBackend
{
    private List<LeaderboardDataElement> boardData;

    private LeaderboardTestBackend()
    {
        boardData = new ArrayList<>();
        boardData.add(new LeaderboardDataElement("ttrojan", 0, 1234));
        String jmillerEmblem = "<img src=\"img/jeffrey_miller.jpg\" height=\"16\" width=\"16\"/>";
        boardData.add(new LeaderboardDataElement("<div id='shouldBePlaintext'>jmiller</div>", jmillerEmblem, 45, 456));
        boardData.add(new LeaderboardDataElement("stregis", 80, 125437834));
        boardData.add(new LeaderboardDataElement("orahimi", 55, 12453434));
        boardData.add(new LeaderboardDataElement("manipusg", 40, 12543434));
        boardData.add(new LeaderboardDataElement("nathanzh", 60, 1000000));
        boardData.add(new LeaderboardDataElement("kaagarwa", 60, 100000));
        boardData.add(new LeaderboardDataElement("many1", 0, 10));
        boardData.add(new LeaderboardDataElement("many2", 0, 10));
        boardData.add(new LeaderboardDataElement("many3", 0, 10));
        boardData.add(new LeaderboardDataElement("many4", 0, 10));
        boardData.add(new LeaderboardDataElement("many5", 0, 10));
        boardData.add(new LeaderboardDataElement("many6", 0, 10));
        boardData.add(new LeaderboardDataElement("many7", 0, 10));
        boardData.add(new LeaderboardDataElement("many8", 0, 10));
        boardData.add(new LeaderboardDataElement("many9", 0, 10));
        boardData.add(new LeaderboardDataElement("many10", 0, 10));
    }

    public static void main(String[] args)
    {

    }
}
