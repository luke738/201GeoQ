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
    private GameSettings settings;

    public SettingsBackend() throws IOException
    {
        ss = new ServerSocket(4369);
        //Hardcode settings, game starts 1 hour from launch
        settings = new GameSettings(LocalDateTime.now().plusHours(1), 10);
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
                        //Update the time here, but can't be done with hardcoded settings
                        c.send(settings.toJSON());
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
