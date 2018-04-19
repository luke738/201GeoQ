package shared;

import java.io.Serializable;

public class GameSettingsSimple implements Serializable
{
    public String startTime;
    public int startTimeEpoch;
    public long timeToStart;
    public int timeBetweenGames;
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

    public GameSettingsSimple(int startTimeEpoch, long timeToStart, int timeBetweenGames, int numberOfQuestions, int questionTime, int leaderboardTime)
    {
        this.startTimeEpoch = startTimeEpoch;
        this.timeToStart = timeToStart;
        this.timeBetweenGames = timeBetweenGames;
        this.numberOfQuestions = numberOfQuestions;
        this.questionTime = questionTime;
        this.leaderboardTime = leaderboardTime;
    }
}
