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
import dto.Gameboard;
import dto.Player;


/**
 * Servlet implementation class spacetravel
 */
@WebServlet("/game/spacetravel")
public class spacetravel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public spacetravel() {
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
			int gb_location = Integer.parseInt(request.getParameter("gb_location"));
			int mover = Integer.parseInt(request.getParameter("mover"));
			Gameboard gameboard = GameboardDao.gameboardDao.getcityinfo(gb_location);
			String ac_nickname = request.getParameter("ac_nickname");
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("groom_no", groom_no);
			jsonObject.put("ac_nickname", ac_nickname);
			jsonObject.put("type", "spacetravel");
			jsonObject.put("mover", mover);
			jsonObject.put("p_location", 10);
			jsonObject.put("cityname", gameboard.getGb_cityname());
			int move = 0;
			if(10>gb_location) {
				move = gb_location - 10 + 40;
				PlayerDao.playerDao.changemoney(p_no, 300000);
				jsonObject.put("startpoint", 300000);
			}else {
				move = gb_location - 10;
				jsonObject.put("startpoint", 0);
			}
			PlayerDao.playerDao.moveplayer(p_no, 10, move);
			jsonObject.put("move", move);
			// 모든 유저 위치 저장용
			JSONArray playerlocation = new JSONArray();
			ArrayList<Player> plist = PlayerDao.playerDao.getplayers(groom_no);
			for(Player temp : plist) {
				JSONObject object = new JSONObject();
				object.put("nickname", temp.getAc_nickname());
				object.put("location", temp.getP_location());
				object.put("p_order", temp.getP_order());
				playerlocation.put(object);
			}
			jsonObject.put("alllocation", playerlocation);
			jsonArray.put(jsonObject);
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
