package backend;

import shared.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardBackend
{
    private GameState state;
    private ServerSocket ss;

    public LeaderboardBackend(GameState state) throws IOException
    {
        this.state = state;
        ss = new ServerSocket(4367);
    }

    public void start()
    {
        System.out.println("LeaderboardBackend running.");
        while(true)
        {
            try
            {
                Socket s = ss.accept();
                Thread t = new Thread(() ->
                {
                    try
                    {
                        Connection c = new Connection(s);
                        ArrayList<User> arr = new ArrayList<>(state.connectedUsers.values());
                        c.send(arr);
                        c.close();
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                });
                t.start();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
