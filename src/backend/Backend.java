package backend;

import shared.GameSettings;
import shared.Question;

import java.io.IOException;
import java.time.LocalDateTime;

public class Backend
{
    public static void main(String[] args)
    {
        //Initalize DB object
        //Get settings from DB and initalize GameState, hardcoded here:
    		Database db = new Database();
    		Question[] questions = new Question[10];
    		Question curr = null;
    		for(int i=1;i<11;i++) {
    			curr = db.retreive_image_data(i);
    			questions[i-1] = curr;
    		}
        GameState state = new GameState(new GameSettings(LocalDateTime.now().plusHours(1), 24, 10), questions);
        
        try
        {
            LeaderboardBackend lbtb = new LeaderboardBackend(state);
            Thread lt = new Thread(lbtb::start);
            lt.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            ChatBackend ctb = new ChatBackend();
            Thread ct = new Thread(ctb::start);
            ct.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            SettingsBackend sb = new SettingsBackend(state);
            Thread st = new Thread(sb::start);
            st.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            LoginBackend lb = new LoginBackend(db);
            Thread st = new Thread(lb::start);
            st.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            SignupBackend lb = new SignupBackend(db);
            Thread st = new Thread(lb::start);
            st.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        try 
        {
        		GameBackend gtb = new GameBackend(state, db);
        		Thread st = new Thread(gtb::start);
        		st.start();
        } catch (IOException e) {
        		e.printStackTrace();
        }
        
        System.out.println("All backends started.");
    }
}
