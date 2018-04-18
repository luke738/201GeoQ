package shared;

public class GameSettingsSimple
{
    public String startTime;
    public long timeToStart;
    public int numberOfQuestions;
    public int questionTime;
    public int leaderboardTime;

    public GameSettingsSimple(String startTime, long timeToStart, int numberOfQuestions, int questionTime, int leaderboardTime)
    {
        this.startTime = startTime;
        this.timeToStart = timeToStart;
        this.numberOfQuestions = numberOfQuestions;
        this.questionTime = questionTime;
        this.leaderboardTime = leaderboardTime;
    }
}
