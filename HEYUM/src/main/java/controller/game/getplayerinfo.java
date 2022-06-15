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
 * Servlet implementation class getplayerinfo
 */
@WebServlet("/game/getplayerinfo")
public class getplayerinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getplayerinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			int groom_no = Integer.parseInt(request.getParameter("groom_no"));
			ArrayList<Player> plist = PlayerDao.playerDao.getplayers(groom_no);
			JSONArray jsonArray = new JSONArray();
			for(Player player : plist) {				
				JSONArray mycitylist = GameboardDao.gameboardDao.getmycity(player.getP_no(), groom_no);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("p_no", player.getP_no());
				jsonObject.put("p_money", player.getP_money());
				jsonObject.put("p_location", player.getP_location());
				jsonObject.put("p_turn", player.getP_turn());
				jsonObject.put("p_uisland", player.getP_uisland());
				jsonObject.put("p_order", player.getP_order());
				jsonObject.put("groom_no", player.getGroom_no()	);
				jsonObject.put("ac_no", player.getAc_no());
				jsonObject.put("ac_nickname", player.getAc_nickname());
				jsonObject.put("ac_profileimg", player.getAc_profileimg());
				jsonObject.put("mycitylist", mycitylist);
				jsonArray.put(jsonObject);
			}
			
			response.getWriter().print(jsonArray);
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
