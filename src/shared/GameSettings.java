package shared;

import com.google.gson.Gson;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class GameSettings
{
    public LocalDateTime startTime; //For page title, headers, etc
    public int numQuestions;
    public Gson gson;

    public GameSettings(LocalDateTime startTime, int numQuestions)
    {
        this.startTime = startTime;
        this.numQuestions = numQuestions;
        gson = new Gson();
    }

    public String toJSON()
    {
        String sTime = startTime.format(DateTimeFormatter.ofPattern("hh:mm:ssa"))+" PST";
        long tts = ChronoUnit.SECONDS.between(startTime, Instant.now());
        return gson.toJson(new Object(){
            String startTime = sTime;
            long timeToStart = tts;
            int numQuest = numQuestions;
        });
    }
}
