package controller.game;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class gameboard
 */
@WebServlet("/game/gameboard")
public class gameboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gameboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int groom_no = Integer.parseInt(request.getParameter("groom_no"));
			ArrayList<Gameboard> gblist = GameboardDao.gameboardDao.getgameboardlist();
			response.setCharacterEncoding("UTF-8");
			JSONArray jsonArray = new JSONArray();
			for(Gameboard temp : gblist){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("gb_location",temp.getGb_location());
				jsonObject.put("gb_cityname", temp.getGb_cityname());
				jsonArray.put(jsonObject);
			} 
				
			ArrayList<Player> plist = PlayerDao.playerDao.getplayers(groom_no);
		
			JSONArray jsonArray2 = new JSONArray();
			for(Player player : plist) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("nickname", player.getAc_nickname());
				jsonObject.put("location", player.getP_location());
				jsonObject.put("p_order", player.getP_order());
				jsonArray2.put(jsonObject);
			}
			response.getWriter().print(jsonArray+"@@"+jsonArray2);
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
