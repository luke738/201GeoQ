package backend;

import shared.Connection;
import shared.Message;
import shared.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class GameBackend
{
    private ServerSocket ss;
    private List<Connection> connections = new ArrayList<>();
    private GameState state;
    private Database db;

    public GameBackend(GameState state, Database db) throws IOException
    {
        this.state = state;
        ss = new ServerSocket(4366);
        this.db = db;
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
                    		Gson gameGson = new Gson();
                    		while(true)
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
                            
                           
                            String questionString = gameGson.toJson(state.questions[0]);
                            c.send(new Message("first", questionString));
                            
                            while(state.currentQuestion < state.questions.length)
                            {
                                // time is up, show leaderboard
                                if(System.currentTimeMillis() - millis > 10000)
                                {
									millis = System.currentTimeMillis();
									c.send(new Message("leaderboard", "Show Leaderboard"));
                                }
                                // go on to next question
                                if(System.currentTimeMillis() - millis1 > 15000)
                                {
                                    millis = System.currentTimeMillis();
                                    millis1 = System.currentTimeMillis();
                                    state.currentQuestion++;
                                    questionString = gameGson.toJson(state.questions[state.currentQuestion]);
                                    c.send(new Message("next", questionString));
                                }
                            }
                        }
                    }
                    else if(m.header.equals("notDummy"))
                    {
                        String username = (String) m.body;
                        if(db.verify_user(username))
                        {
                            int numEmblems = db.get_num_embs(username);
                            String emblem = "<img src=\"img/jeffrey_miller.jpg\" height=\"16\" width=\"16\"/>";
                            StringBuilder uemb = new StringBuilder();
                            for(int i = 0; i < numEmblems; i++)
                            {
                                uemb.append(emblem);
                            }
                            state.connectedUsers.put(username, new User(username, uemb.toString()));
                        }
                        else
                        {
                            state.connectedUsers.put(username, new User(username));
                        }
                        while(state.currentQuestion < state.questions.length)
                        {

                            Message me = c.receive(Message.class);
                            if(me.header.equals("answer"))
                            {
                                if(me.body.equals(state.questions[state.currentQuestion].correctAnswerString));
                                {
                                    state.connectedUsers.get(username).score++;
                                }
                            }
                        }
                        c.close();
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

