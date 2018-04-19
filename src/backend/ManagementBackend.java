package backend;

import shared.Connection;
import shared.GameSettings;
import shared.GameSettingsSimple;
import shared.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneOffset;
import java.util.List;

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
                        Message m = c.receive(Message.class);
                        if(!validate("admin", m.header))
                        {
                            c.send(false);
                            return;
                        }
                        state.settings = new GameSettings((GameSettingsSimple)m.body);
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

    public Boolean validate(String username, String password)
    {
        //Find out if this is a valid username from the DB
        //For now, there are two valid usernames hardcoded in
        if(!db.verify_user(username)) return false;
        List<String> userPwInfo = db.get_password_info(username);
        password = LoginBackend.toSHA1(userPwInfo.get(1)+password);
        return password.equals(userPwInfo.get(0));
    }
}
