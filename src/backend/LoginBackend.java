package backend;

import shared.Connection;
import shared.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class LoginBackend
{
    private ServerSocket ss;
    private Database db;

    public LoginBackend(Database db) throws IOException
    {
        this.db = db;
        ss = new ServerSocket(4370);
    }

    public void start()
    {
        System.out.println("LoginBackend running.");
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
                        Message m = c.receive(Message.class);
                        c.send(validate(m.header, (String)m.body));
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

    public static String getHexString(byte[] b)
    {
        String result = "";
        for (int i=0; i < b.length; i++)
        {
            result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }

    public static String toSHA1(String toHash)
    {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return getHexString(md.digest(toHash.getBytes()));
    }

    public Boolean validate(String username, String password)
    {
        //Find out if this is a valid username from the DB
        //For now, there are two valid usernames hardcoded in
        if(!db.verify_user(username)) return false;
        String[] userPwInfo = db.get_pass(username);
        password = toSHA1(userPwInfo[1]+password);
        return password.equals(userPwInfo[0]);
    }
}
