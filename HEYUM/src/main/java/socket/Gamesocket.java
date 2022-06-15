package socket;


import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
 
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/gamesocket")
public class Gamesocket {
	
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
    
    public static String xssFilter(String str) {
        String result = "";
        
        result = str;
        result = result.replaceAll("[<]", "&lt;");
        result = result.replaceAll("[>]", "&gt;");
        return result;
     }
    
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        try {
        	String cleanmsg = xssFilter(message);
	    	synchronized(clients) {
	            for(Session client : clients) {
	            	 client.getBasicRemote().sendText(cleanmsg);
	            }
	        }
        }
        catch (Exception e) {
        	System.out.println(e);
		}  
    }
    
    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
		try {

        }
        catch(Exception e) {
        	e.printStackTrace();
        }      
    }
    
    @OnClose
    public void onClose(Session session) {
    	clients.remove(session);
    }
}