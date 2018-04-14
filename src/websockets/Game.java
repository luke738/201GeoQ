package websockets;

import java.io.IOException;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws")
public class Game {

	// static because every client that connects will get a new instance of the server socket class
	// but you want all of the clients that connect to use the same instance of the server socket
	private static Vector<Session> sessionVector = new Vector<Session>();
	
	@OnOpen
	public void open(Session session) {
		System.out.println("Client Connected");
		sessionVector.add(session);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		//System.out.println(message);
	
		// check answer
		if(message.substring(0, 7).equals("Time Up")) {
			try {
				for(Session s : sessionVector)
	            {
	                s.getBasicRemote().sendText(message.substring(7));
	            }
				System.out.println(message.substring(7));
			} catch (IOException ioe) {
				System.out.println("ioe: " + ioe.getMessage());
			}
		}
		else if(message.equals("Next Question")) {
			try
	        {
	            for(Session s : sessionVector)
	            {
	                s.getBasicRemote().sendText(message);
	            }
	        }
	        catch (IOException ioe)
	        {
	            ioe.printStackTrace();
	        }
		}
			
		/*
		try {
			if(message.equals(answer)) {
				session.getBasicRemote().sendText("right");
			} else {
				session.getBasicRemote().sendText("wrong");
			}

		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		} */
	}
	
	@OnClose
	public void OnClose(Session session) {
		System.out.println("Client Disconnected");
		sessionVector.remove(session);
	}
	
}
