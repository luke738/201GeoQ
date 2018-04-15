package shared;

public class Question
{
    public double latitude;
    public double longitude;
    public double heading;
    public double pitch;
    public String[] answers;
    public int correctAnswer;

    public Question(double latitude, double longitude, double heading, double pitch, String[] answers, int correctAnswer)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.heading = heading;
        this.pitch = pitch;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public Question withoutAnswer()
    {
        return new Question(latitude, longitude, heading, pitch, answers, -1);
    }
}
