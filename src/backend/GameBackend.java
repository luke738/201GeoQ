package backend;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import shared.Connection;
import shared.Message;
import shared.User;

public class GameBackend
{
    private ServerSocket ss;
    private List<Connection> connections = new ArrayList<>();
    private GameState state;

    public GameBackend(GameState state) throws IOException
    {
        this.state = state;
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
                    Message m = c.receive(Message.class);
                    if(m.header.equals("dummy"))
                    {
                        while(LocalDateTime.now().isBefore(state.settings.startTime))
                        {
                            try
                            {
                                Thread.sleep(500);
                            }
                            catch(InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        long millis = System.currentTimeMillis();
                        long millis1 = System.currentTimeMillis();
                        long millis2 = System.currentTimeMillis();
                        while(true)
                        {
                            if(System.currentTimeMillis() - millis > 10000)
                            {
                                millis = System.currentTimeMillis();
                                c.send(new Message("alert", "Time UpParis"));
                            }
                            if(System.currentTimeMillis() - millis1 > 13000)
                            {
                                millis = System.currentTimeMillis();
                                millis1 = System.currentTimeMillis();
                                c.send(new Message("next", "Next Question"));
                            }
                            if(System.currentTimeMillis() - millis2 > 18000)
                            {
                                millis = System.currentTimeMillis();
                                millis1 = System.currentTimeMillis();
                                millis2 = System.currentTimeMillis();
                                c.send(new Message("leaderboard", "Show Leaderboard"));
                            }

                        }
                    }
                    else if(m.header.equals("notDummy"))
                    {
                        state.connectedUsers.put((String) m.body, new User((String) m.body));
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

