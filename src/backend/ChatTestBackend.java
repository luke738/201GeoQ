package backend;

import shared.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ChatTestBackend
{
    private ServerSocket ss;
    private List<Connection> connections;

    public ChatTestBackend() throws IOException
    {
        ss = new ServerSocket(4368);
    }

    public void start()
    {
        while(true)
        {
            try
            {
                Socket s = ss.accept();
                Connection c = new Connection(s);
                connections.add(c);
                Thread t = new Thread(() ->
                {
                    while(true)
                    {

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
