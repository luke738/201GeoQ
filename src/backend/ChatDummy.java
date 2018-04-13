package backend;

import org.apache.tomcat.websocket.WsContainerProvider;
import shared.Connection;
import shared.Message;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

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
                //Do nothing
            }
        }
    }

    @OnOpen
    public void onOpen(Session session)
    {
        session.getUserProperties().put("ID", "DummySession");
        try
        {
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
