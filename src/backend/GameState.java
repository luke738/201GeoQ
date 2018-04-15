package backend;

import shared.GameSettings;
import shared.Question;
import shared.User;

import java.util.Map;
import java.util.TreeMap;

public class GameState
{
    GameSettings settings;
    Question[] questions;
    Map<String, User> connectedUsers;
    int currentQuestion;

    public GameState(GameSettings settings)
    {
        this.settings = settings;
        questions = new Question[settings.numQuestions];
        connectedUsers = new TreeMap<>();
        currentQuestion = 0;
    }
}
