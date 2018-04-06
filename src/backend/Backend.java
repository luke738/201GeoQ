package backend;

import java.io.IOException;

public class Backend
{
    public static void main(String[] args)
    {
        try
        {
            LeaderboardTestBackend lbtb = new LeaderboardTestBackend();
            Thread lt = new Thread(lbtb::start);
            lt.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            ChatTestBackend ctb = new ChatTestBackend();
            Thread ct = new Thread(ctb::start);
            ct.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
