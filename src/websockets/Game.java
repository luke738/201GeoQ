package websockets;

import shared.Connection;
import shared.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws", configurator = GetHttpSessionConfigurator.class)
public class Game
{

	// static because every client that connects will get a new instance of the server socket class
	// but you want all of the clients that connect to use the same instance of the server socket
	private static Vector<Session> sessionVector = new Vector<>();
	private String username;
	private Connection c;
	
	@OnOpen
	public void open(Session session, EndpointConfig config)
    {
		System.out.println("Client Connected");
		sessionVector.add(session);
        username = "";
        System.out.println("OnOpentest");
        if(!config.getUserProperties().get("httpSession").equals(0))
        {
            HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
            if(httpSession.getAttribute("username")!=null)
            {
                username = (String) httpSession.getAttribute("username");
            }
            else
            {
                username = (String) httpSession.getAttribute("key");
            }
        }
	}
	
	@OnMessage
	public void onMessage(String message, Session session)
    {
        if(!session.getUserProperties().containsKey("dummy"))
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
        if((boolean)session.getUserProperties().get("dummy"))
        {
         
            try
            {
                for(Session s : sessionVector)
                {
                    s.getBasicRemote().sendText(message);
                }
            }
            catch(IOException ioe)
            {
                System.out.println("ioe: " + ioe.getMessage());
            }
        }
        else
        {
            if(!session.getUserProperties().containsKey("usernameSent"))
            {
                try
                {
                    c = new Connection(new Socket("localhost", 4366));
                    c.send(new Message("notDummy", username));
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
	}
	
	@OnClose
	public void OnClose(Session session) {
		System.out.println("Client Disconnected");
		sessionVector.remove(session);
	}
	
}
