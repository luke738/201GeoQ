package websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

@ServerEndpoint(value = "/Chat", configurator = GetHttpSessionConfigurator.class)
public class Chat
{
    private static List<Session> sessions = new ArrayList<>();
    private String username;

    @OnOpen
    public void open(Session session, EndpointConfig config)
    {
        sessions.add(session);
        session.getUserProperties().put("validUser", false);
        username = "";
        System.out.println("OnOpentest");
        if(!config.getUserProperties().get("httpSession").equals(0))
        {
            HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
            if(httpSession.getAttribute("username")!=null)
            {
                username = (String) httpSession.getAttribute("username");
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session)
    {
        if(session.getUserProperties().get("dummy")==null)
        {
            if(message.equals("1bce58f2a779c8479a8cb8c4ba8cc47078e475b0"))
            {
                session.getUserProperties().put("dummy", true);
            }
            else
            {
                session.getUserProperties().put("dummy", false);
            }
        }
        try
        {
            if(username.equals("") && !(boolean)session.getUserProperties().get("dummy"))
            {
                session.getBasicRemote().sendText("Log in to send a message!");
                return;
            }
            for(Session s : sessions)
            {
                if((boolean)session.getUserProperties().get("dummy")) s.getBasicRemote().sendText(message);
                else s.getBasicRemote().sendText(username + ": " + message);
            }
        }
        catch(IOException ioe)
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