package backend;

import org.apache.tomcat.websocket.WsContainerProvider;
import shared.Connection;
import shared.Message;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import javax.websocket.*;

@ClientEndpoint
public class ChatDummy
{

    public ChatDummy()
    {
        Boolean connected = false;
        while(!connected)
        {
            try
            {
                WebSocketContainer container = WsContainerProvider.getWebSocketContainer();
                String uri = "ws://localhost:8080/GeoQ/Chat";
                container.connectToServer(this, new URI(uri));
                connected = true;
            }
            catch(Exception e)
            {
                try
                {
                    Thread.sleep(10000); //Don't go overboard on reattempting connection
                }
                catch(InterruptedException ex)
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
            session.getBasicRemote().sendText("1bce58f2a779c8479a8cb8c4ba8cc47078e475b0"); //This is a SHA1 hash of Connection.java
            Connection c = new Connection(new Socket("localhost", 4368));
            while(true)
            {
                Message m = c.receive(Message.class);
                if(m.header.equals("die")) break;
                if(m.header.equals("alert"))
                {
                    session.getAsyncRemote().sendText("Server Alert: " + m.body);
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
