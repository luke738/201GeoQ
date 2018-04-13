package backend;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.apache.tomcat.websocket.WsContainerProvider;

import shared.Connection;
import shared.Message;

@ClientEndpoint
public class GameDummy
{

    public GameDummy()
    {
        try
        {
            //javax.websocket.WebSocketContainer container = javax.websocket.ContainerProvider.getWebSocketContainer();
            WebSocketContainer container = WsContainerProvider.getWebSocketContainer();
            String uri = "ws://localhost:8080/GeoQ/ws";
            container.connectToServer(this, new URI(uri));

        }
        catch(Exception e)
        {
            System.out.println("Exception in GameDummy");
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session)
    {
        session.getUserProperties().put("ID", "DummySession");
        try
        {
            Connection c = new Connection(new Socket("localhost", 4366));
            while(true)
            {
                Message m = c.receive(Message.class);
                if(m.header.equals("die")) break;
                if(m.header.equals("alert"))
                {
                    session.getAsyncRemote().sendText((String)m.body);
                }
                if(m.header.equals("next")) {
                		session.getAsyncRemote().sendText((String)m.body);
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
