package websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/Chat")
public class Chat
{
    private static List<Session> sessions = new ArrayList<>();

    @OnOpen
    public void open(Session session)
    {
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session)
    {
        try
        {
            for(Session s : sessions)
            {
                s.getBasicRemote().sendText(message);
            }
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            close(session);
        }

    }

    @OnClose
    public void close(Session session)
    {
        sessions.remove(session);
    }

    @OnError
    public void error(Throwable error)
    {
        error.printStackTrace();
    }
}