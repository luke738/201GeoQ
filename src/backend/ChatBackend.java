package backend;

import shared.Connection;
import shared.GameSettings;
import shared.Message;
import websockets.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChatBackend
{
    private ServerSocket ss;
    private List<Connection> connections = new ArrayList<>();
    private GameState state;

    public ChatBackend(GameState state) throws IOException
    {
        ss = new ServerSocket(4368);
        this.state = state;
    }

    public void start()
    {
        Thread cdt = new Thread(()->{ChatDummy cd = new ChatDummy();});
        cdt.start();
        System.out.println("ChatBackend running.");
        while(true)
        {
            try
            {
                Socket s = ss.accept();
                Connection c = new Connection(s);
                connections.add(c);
                Thread t = new Thread(() ->
                {
                    long millis = System.currentTimeMillis();
                    while(true)
                    {
                        if((System.currentTimeMillis()-millis)>10000 && (System.currentTimeMillis()/1000)%10==0)
                        {
                            millis = System.currentTimeMillis();
                            c.send(new Message("alert", "The current number of users is "+state.numUsers()+"."));
                        }

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
