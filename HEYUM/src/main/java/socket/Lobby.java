package socket;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.Member_Dao;

@ServerEndpoint("/lobby/{loginid}")
public class Lobby {
	
 //   private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
    public static Map<String, Session> clients = new Hashtable<String, Session>();
    
    public static String xssFilter(String str) {
        String result = "";
        
        result = str;
        result = result.replaceAll("[<]", "&lt;");
        result = result.replaceAll("[>]", "&gt;");
        
        return result;
     }
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("loginid") String loginid) throws IOException {
        try {
        	String cleanmsg = xssFilter(message);
        	JSONArray jsonArray = new JSONArray();
        	JSONObject jsonObject = new JSONObject(cleanmsg);
        	String profile = Member_Dao.m_dao.getpro(loginid);
        	String nick = Member_Dao.m_dao.getnick(loginid);
        	jsonObject.put("loginid", loginid);
        	jsonObject.put("profile", profile);
        	jsonObject.put("nick", nick);
        	jsonArray.put(jsonObject);
        	synchronized(clients) {
	            for(Session client : clients.values()) {
	            	 client.getBasicRemote().sendText(jsonArray.toString());
	            }
	        }	
        }
        catch (Exception e) {
        	System.out.println(e);
		}  
    }
    
    @OnOpen
    public void onOpen(Session session, @PathParam("loginid") String loginid) {
		clients.put(loginid, session);
		try {
			JSONArray jsonArray = new JSONArray();    
	        for(String temp : clients.keySet()) {
		        JSONObject jsonObject = new JSONObject();
		        jsonObject.put("type", "userlist");
		        jsonObject.put("loginid", temp);
		        String profile = Member_Dao.m_dao.getpro(temp);
		        String nick = Member_Dao.m_dao.getnick(temp);
		        jsonObject.put("profile", profile);
		        jsonObject.put("nick", nick);
		        jsonArray.put(jsonObject);
	        }  
	    	synchronized(clients) {
	            for(Session client : clients.values()) {
	            	 client.getBasicRemote().sendText(jsonArray.toString());
	            }
	        }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }  
    }
    
    @OnClose
    public void onClose(Session session, @PathParam("loginid") String loginid) {
    	clients.remove(loginid);
		try {
			JSONArray jsonArray = new JSONArray();  
	        for(String temp : clients.keySet()) {
		        JSONObject jsonObject = new JSONObject();
		        jsonObject.put("type", "userlist");
		        jsonObject.put("loginid", temp);
		        String profile = Member_Dao.m_dao.getpro(temp);
		        String nick = Member_Dao.m_dao.getnick(temp);
		        jsonObject.put("nick", nick);
		        jsonObject.put("profile", profile);
		        jsonArray.put(jsonObject);
	        }  
	    	synchronized(clients) {
	            for(Session client : clients.values()) {
	            	 client.getBasicRemote().sendText(jsonArray.toString());
	            }
	        }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
/*
	
   @OnMessage
   public void OnMessage(String msg, Session session) throws IOException {
      
      synchronized(clients) {
            for(Session client : clients.keySet()) {
                client.getBasicRemote().sendText(cleanmsg);
            }
        } 
   } 
 */
