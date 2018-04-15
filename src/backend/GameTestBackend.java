package backend;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import shared.Connection;
import shared.Message;

public class GameTestBackend
{
    private ServerSocket ss;
    private List<Connection> connections = new ArrayList<>();

    public GameTestBackend() throws IOException
    {
        ss = new ServerSocket(4366);
    }

    public void start()
    {
        Thread gdt = new Thread(()->{GameDummy gd = new GameDummy();});
        gdt.start();
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
                    long millis1 = System.currentTimeMillis();
                    long millis2 = System.currentTimeMillis();
                    while(true)
                    {
                        if(System.currentTimeMillis()-millis > 10000)
                        {
                            millis = System.currentTimeMillis();
                            c.send(new Message("alert", "Time UpParis"));
                        }
                        if(System.currentTimeMillis()-millis1 > 13000) {
                        		millis = System.currentTimeMillis();
                        		millis1 = System.currentTimeMillis();
                        		c.send(new Message("leaderboard", "Show Leaderboard"));
                        }
                        if(System.currentTimeMillis()-millis2 > 18000) {
	                        	millis = System.currentTimeMillis();
	                    		millis1 = System.currentTimeMillis();
	                    		millis2 = System.currentTimeMillis();
	                    		c.send(new Message("next", "Next Question"));
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

