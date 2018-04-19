package backend;

import shared.Connection;
import shared.GameSettings;
import shared.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SettingsBackend
{
    private ServerSocket ss;
    private GameState state;

    public SettingsBackend(GameState state) throws IOException
    {
        ss = new ServerSocket(4369);
        this.state = state;
    }

    public void start()
    {
        System.out.println("SettingsBackend running.");
        while(true)
        {
            try
            {
                Socket s = ss.accept();
                Thread t = new Thread(() ->
                {
                    //Return hardcoded settings
                    try
                    {
                        Connection c = new Connection(s);
                        c.send(state.settings.toJSON());
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
