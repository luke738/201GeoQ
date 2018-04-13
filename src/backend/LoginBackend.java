package backend;

import shared.Connection;
import shared.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginBackend
{
    private ServerSocket ss;

    public LoginBackend() throws IOException
    {
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
                    //Return hardcoded settings
                    try
                    {
                        Connection c = new Connection(s);
                        //Update the time here, but can't be done with hardcoded settings
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
        //For now, there is one valid username hardcoded in
        password = toSHA1("adminsalt"+password);
        return username.equals("admin") && password.equals("24876cfa406436c5277db086dbed32cf616771cb");
    }
}
