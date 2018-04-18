package shared;

import com.google.gson.Gson;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class GameSettings implements Serializable
{
    public LocalDateTime startTime; //For page title, headers, etc
    public int timeBetweenGames;
    public int numQuestions;
    public int questionTime;
    public int leaderboardTime;
    public transient Gson gson;

    public GameSettings(LocalDateTime startTime, int timeBetweenGames, int numQuestions, int questionTime, int leaderboardTime)
    {
        this.startTime = startTime;
        this.timeBetweenGames = timeBetweenGames;
        this.numQuestions = numQuestions;
        this.questionTime = questionTime;
        this.leaderboardTime = leaderboardTime;
        gson = new Gson();
    }

    public GameSettings(GameSettingsSimple set)
    {
        System.out.println("CONVERTING");
        System.out.println("***"+set.startTimeEpoch);
        startTime = LocalDateTime.ofEpochSecond(set.startTimeEpoch, 0, ZoneOffset.ofHours(-7));
        timeBetweenGames = set.timeBetweenGames;
        numQuestions = set.numberOfQuestions;
        questionTime = set.questionTime;
        leaderboardTime = set.leaderboardTime;
        gson = new Gson();
    }

    public String toJSON()
    {
        if(gson==null) gson = new Gson();
        String sTime = startTime.format(DateTimeFormatter.ofPattern("hh:mm:ssa"))+" PST";
        long tts = ChronoUnit.SECONDS.between(LocalDateTime.now(), startTime);
        return gson.toJson(new GameSettingsSimple(sTime, tts, numQuestions, questionTime, leaderboardTime));
    }
}
