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

import dao.GameDao;
import dao.GameboardDao;
import dao.PlayerDao;
import dto.Player;

/**
 * Servlet implementation class endgame
 */
@WebServlet("/game/endgame")
public class endgame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public endgame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			int groom_no = Integer.parseInt(request.getParameter("groom_no"));
			String bankruptcy = request.getParameter("bankruptcy");
			JSONArray jsonArray = new JSONArray(bankruptcy);
			ArrayList<Player> plist = PlayerDao.playerDao.getplayers(groom_no);
			String winp_nickname = "";
			JSONArray array = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("groom_no", groom_no);
			for(int i=0; i<jsonArray.length(); i++) {
				if(jsonArray.getJSONObject(i).getInt("bankruptcy")==0) {
					winp_nickname = jsonArray.getJSONObject(i).getString("nickname");
				}	
			}
			jsonObject.put("ac_nickname", winp_nickname);
			jsonObject.put("type", "endgame");
			for(Player player : plist) {
				if(player.getAc_nickname().equals(winp_nickname)) {
					boolean result = GameDao.gameDao.gameresultsave(groom_no, player.getAc_no(), "승리");
					if(result == false) response.getWriter().print(1);
				}else {
					boolean result =GameDao.gameDao.gameresultsave(groom_no, player.getAc_no(), "패배");
					if(result == false) response.getWriter().print(1);
				}
			}
			array.put(jsonObject);
			response.getWriter().print(array);
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
