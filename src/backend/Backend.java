package backend;

import java.io.IOException;

public class Backend
{
    public static void main(String[] args)
    {
        try
        {
            LeaderboardBackend lbtb = new LeaderboardBackend();
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
            SettingsBackend sb = new SettingsBackend();
            Thread st = new Thread(sb::start);
            st.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        try 
        {
        		GameTestBackend gtb = new GameTestBackend();
        		Thread st = new Thread(gtb::start);
        		st.start();
        } catch (IOException e) {
        		e.printStackTrace();
        }
    }
}
