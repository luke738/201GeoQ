package shared;

public class GameSettingsSimple
{
    public String startTime;
    public long timeToStart;
    public int numberOfQuestions;

    public GameSettingsSimple(String startTime, long timeToStart, int numberOfQuestions)
    {
        this.startTime = startTime;
        this.timeToStart = timeToStart;
        this.numberOfQuestions = numberOfQuestions;
    }
}
