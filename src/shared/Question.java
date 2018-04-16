package shared;

public class Question
{
    public double latitude;
    public double longitude;
    public int heading;
    public int pitch;
    public String[] answers;
    public int correctAnswer;
    public String correctAnswerString;

    public Question(double latitude, double longitude, int heading, int pitch, String[] answers, int correctAnswer)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.heading = heading;
        this.pitch = pitch;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        
        if (correctAnswer == -1) {
            correctAnswerString = "";
        }
        else {
            correctAnswerString = answers[correctAnswer];
        }
    }

    public Question withoutAnswer()
    {
        return new Question(latitude, longitude, heading, pitch, answers, -1);
    }
}
