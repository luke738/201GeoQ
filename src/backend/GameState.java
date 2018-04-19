package backend;

import shared.GameSettings;
import shared.Question;
import shared.User;

import java.util.*;

public class GameState
{
    GameSettings settings;
    Question[] questions;
    private Map<String, User> connectedUsers;
    int currentQuestion;

    public GameState(GameSettings settings, Question[] questions)
    {
        this.settings = settings;
        this.questions = questions;
        connectedUsers = Collections.synchronizedMap(new TreeMap<>());
        currentQuestion = 0;
    }

    public User getUser(String username)
    {
        System.out.println("Got!");
        return connectedUsers.get(username);
    }

    public void setUser(User user)
    {
        System.out.println("Changed!");
        connectedUsers.replace(user.username, user);
    }

    public void addUser(User user)
    {
        System.out.println("Added!");
        connectedUsers.put(user.username, user);
    }

    public void clearUsers()
    {
        System.out.println("Cleared!");
        connectedUsers.clear();
    }

    public int numUsers()
    {
        System.out.println("Numbered!");
        return connectedUsers.size();
    }

    public Collection<User> users()
    {
        System.out.println("Values!");
        return connectedUsers.values();
    }
}
