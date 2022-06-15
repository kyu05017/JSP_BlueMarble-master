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

import dao.GameDao;
import dao.Member_Dao;

@ServerEndpoint("/waitingroom/{loginid}/{groom_no}")
public class Waitingroom {

	public static Map<String, Session> clients = new Hashtable<String, Session>();
	
	 public static String xssFilter(String str) {
		 
        String result = "";
        
        result = str;
        result = result.replaceAll("[<]", "&lt;");
        result = result.replaceAll("[>]", "&gt;");
        
        return result;
     }
	
	@OnMessage
    public void onMessage(String message, Session session, @PathParam("loginid") String loginid , @PathParam("groom_no") int groom_no) throws IOException {
        try {
        	String cleanmsg = xssFilter(message);
        	JSONArray jsonArray = new JSONArray();
        	JSONObject jsonObject = new JSONObject(cleanmsg);
        	jsonObject.put("loginid", loginid);
        	String nick = Member_Dao.m_dao.getnick(loginid);
	        jsonObject.put("nick", nick);
 //       	jsonObject.put("roomno", Integer.parseInt(roomno));
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
    public void onOpen(Session session, @PathParam("loginid") String loginid , @PathParam("groom_no") int groom_no) {
		clients.put(loginid, session);
		try {

        }
        catch(Exception e) {
        	e.printStackTrace();
        }  
    }
	
	@OnClose
    public void onClose(Session session, @PathParam("loginid") String loginid , @PathParam("groom_no") int groom_no) {
    	clients.remove(loginid);
		try {
			JSONArray jsonArray = new JSONArray();
			JSONArray jsonArray2 = GameDao.getGameDao().getroomlist(groom_no);
	        for(String temp : clients.keySet()) {
		        JSONObject jsonObject = new JSONObject();
		        jsonObject.put("type", "xout");
		        jsonObject.put("inuserlist", jsonArray2);
		        jsonObject.put("loginid", temp);
		        jsonObject.put("roomno", groom_no);
		        String nick = Member_Dao.m_dao.getnick(temp);
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
}
