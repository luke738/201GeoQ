package backend;

import shared.Connection;
import shared.Message;
import shared.User;
import sun.print.resources.serviceui;

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
                    		Gson gameGson = new Gson();
                    		while(true)
                        {
//                            while(LocalDateTime.now().isBefore(state.settings.startTime))
//                            {
                                try
                                {
                                    Thread.sleep(7000);
                                }
                                catch(InterruptedException e)
                                {
                                    e.printStackTrace();
                                }
//                            }
                            
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
                                    if(state.currentQuestion == state.questions.length) {
                                    		System.out.println("this is not done");
                                    		state.currentQuestion = 0;
                                    		c.send(new Message("end", "End Game"));
                                    		break;
                                    }
                                    else {
                                    		questionString = gameGson.toJson(state.questions[state.currentQuestion]);
                                    		c.send(new Message("next", questionString));
                                    }
                                }
                            }
                        }
                    }
                    else if(m.header.equals("notDummy"))
                    {
                        String username = (String) m.body;
                        state.connectedUsers.put(username, new User(username));
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

