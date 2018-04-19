package backend;

import shared.Connection;
import shared.GameSettings;
import shared.GameSettingsSimple;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.ZoneOffset;

public class ManagementBackend
{
    private ServerSocket ss;
    private GameState state;
    private Database db;

    public ManagementBackend(GameState state, Database db) throws IOException
    {
        ss = new ServerSocket(4365);
        this.state = state;
        this.db = db;
    }

    public void start()
    {
        System.out.println("ManagementBackend running.");
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
                        state.settings = new GameSettings(c.receive(GameSettingsSimple.class));
                        db.update_settings(state.settings);
                        c.send(true);
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
