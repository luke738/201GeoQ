package backend;

import shared.Connection;
import shared.Message;
import shared.Question;
import shared.User;
import sun.print.resources.serviceui;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
                                    Thread.sleep(100);
                                }
                                catch(InterruptedException e)
                                {
                                    e.printStackTrace();
                                }
                            }

                            try
                            {
                                Thread.sleep(5000);
                            }
                            catch(InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                            
                            long millis = System.currentTimeMillis();
                            long millis1 = System.currentTimeMillis();
                           
                            String questionString = gameGson.toJson(state.questions[0]);
                            c.send(new Message("first", questionString));
                            
                            while(state.currentQuestion < state.questions.length)
                            {
                                // time is up, show leaderboard
                                if(System.currentTimeMillis() - millis > state.settings.questionTime*1000)
                                {
									millis = System.currentTimeMillis();
									c.send(new Message("leaderboard", "Show Leaderboard"));
                                }
                                // go on to next question
                                if(System.currentTimeMillis() - millis1 > (state.settings.questionTime+state.settings.leaderboardTime)*1000)
                                {
                                    millis = System.currentTimeMillis();
                                    millis1 = System.currentTimeMillis();
                                    state.currentQuestion++;
                                    if(state.currentQuestion == state.questions.length) {
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

                            try
                            {
                                Thread.sleep(10000);
                            }
                            catch(InterruptedException e)
                            {
                                e.printStackTrace();
                            }

                            state.settings.startTime = state.settings.startTime.plusHours(state.settings.timeBetweenGames);
                            db.update_settings(state.settings);
                            if(state.numUsers()>0)
                            {
                                User winner = new ArrayList<>(state.users()).get(0);
                                for(User u : state.users())
                                {
                                    if(winner.score < u.score)
                                    {
                                        winner = u;
                                    }
                                }
                                db.update_jeff_embs(winner.username, db.get_num_embs(winner.username) + 1);
                            }

                            Question[] questions = new Question[10];
                            Question curr = null;
                            for(int i=1;i<11;i++) {
                                curr = db.retreive_image_data(i);
                                questions[i-1] = curr;
                            }
                            state.questions = questions;
                            state.clearUsers();
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
                            System.out.println(username);
                            state.addUser(new User(username, uemb.toString()));
                        }
                        else
                        {
                            System.out.println(username);
                            state.addUser(new User(username));
                        }
                        while(state.currentQuestion < state.questions.length)
                        {
                            Message me = c.receive(Message.class);
                            System.out.println("1"+state.getUser(username).score);
                            if(me.header.equals("answer"))
                            {
                                if((Integer)me.body!=-1 && (Integer)me.body==state.questions[state.currentQuestion].correctAnswer)
                                {
                                    User u = state.getUser(username);
                                    u.score++;
                                    state.setUser(u);
                                    System.out.println("a"+state.getUser(username).score);
                                }
                            }
                            System.out.println("2"+state.getUser(username).score);
                        }
                    }
                    else if(m.header.equals("lobbyPull"))
                    {
                        while(LocalDateTime.now().isBefore(state.settings.startTime))
                        {
                            try
                            {
                                Thread.sleep(100);
                            }
                            catch(InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        c.send("go");
                    }
                    c.close();
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

