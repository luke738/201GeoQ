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
        Boolean connected = false;
        while(!connected)
        {
            try
            {
                //javax.websocket.WebSocketContainer container = javax.websocket.ContainerProvider.getWebSocketContainer();
                WebSocketContainer container = WsContainerProvider.getWebSocketContainer();
                String uri = "ws://localhost:8080/GeoQ/ws";
                container.connectToServer(this, new URI(uri));
                connected=true;

            }
            catch(Exception e)
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    @OnOpen
    public void onOpen(Session session)
    {
        try
        {
            session.getBasicRemote().sendText("1bce58f2a779c8479a8cb8c4ba8cc47078e475b0");
            Connection c = new Connection(new Socket("localhost", 4366));
            c.send(new Message("dummy"));
            while(true)
            {
                Message m = c.receive(Message.class);
                if(m.header.equals("die")) break;
               
                session.getAsyncRemote().sendText((String)m.body);
                
            }
            c.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
