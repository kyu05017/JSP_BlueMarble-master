package controller.game;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.GameboardDao;
import dao.PlayerDao;
import dto.Player;

/**
 * Servlet implementation class bankruptcy
 */
@WebServlet("/game/bankruptcy")
public class bankruptcy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bankruptcy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			int groom_no = Integer.parseInt(request.getParameter("groom_no"));
			int p_no = Integer.parseInt(request.getParameter("p_no"));
			GameboardDao.gameboardDao.deleteallmyb(groom_no, p_no);
			String ac_nickname = request.getParameter("ac_nickname");
			String bankruptcycheck1 = request.getParameter("bankruptcycheck");
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("groom_no", groom_no);
			jsonObject.put("ac_nickname", ac_nickname);
			jsonObject.put("type", "bankruptcy");
			boolean check = false;
			JSONArray bankruptcycheck = new JSONArray(bankruptcycheck1);
			
			if(bankruptcycheck.length()==1) {
				check = true;
			}else {
				for(int i=0; i<bankruptcycheck.length(); i++) {
					if(bankruptcycheck.getJSONObject(i).getString("nickname").equals(ac_nickname)) {
						bankruptcycheck.getJSONObject(i).put("bankruptcy", 1);
					}
				}
				
			}
			
			
			boolean result = PlayerDao.playerDao.bankruptcy(p_no);
			if(result) {
				ArrayList<Player> plist = PlayerDao.playerDao.getplayers(groom_no);
				JSONArray playerlocation = new JSONArray();
				JSONArray array = new JSONArray();
				for(Player player : plist) {
					JSONObject object = new JSONObject();
					JSONObject object2 = new JSONObject();
					object.put("location", player.getP_location());
					object.put("nickname", player.getAc_nickname());
					
					if(check) {
						object2.put("nickname", player.getAc_nickname());
						if(player.getP_no()==p_no) {
							object2.put("bankruptcy", 1);
						}else {
							object2.put("bankruptcy", 0);
						}
						array.put(object2);
						jsonObject.put("bankruptcycheck", array);
					}else {
						jsonObject.put("bankruptcycheck", bankruptcycheck);
					}
					
					playerlocation.put(object);
				}
				jsonObject.put("alllocation", playerlocation);
				
				jsonArray.put(jsonObject);
				response.getWriter().print(jsonArray);
			}else {
				response.getWriter().print(1); 	// DB 오류
			}
		}catch(Exception e) {e.printStackTrace();}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
