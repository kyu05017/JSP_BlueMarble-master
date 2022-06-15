package controller.game;

import java.io.IOException;
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
 * Servlet implementation class buycity
 */
@WebServlet("/game/buycity")
public class buycity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buycity() {
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
			int location = Integer.parseInt(request.getParameter("location"));
			int p_no = Integer.parseInt(request.getParameter("p_no"));
			int groom_no = Integer.parseInt(request.getParameter("groom_no"));
			String buildingclass = request.getParameter("buildingclass");
			String ac_nickname = request.getParameter("ac_nickname");
			int sumprice = Integer.parseInt(request.getParameter("sumprice"));
			String table = "p_no";
			Player player = PlayerDao.playerDao.getplayer(p_no, table,groom_no);
			Gameboard gameboard = GameboardDao.gameboardDao.getcityinfo(location);
			JSONArray jsonArray = new JSONArray(buildingclass);
			
			JSONArray jsonArray2 = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("groom_no", groom_no);
			jsonObject.put("ac_nickname", ac_nickname);
			jsonObject.put("type", "buycity");
			jsonObject.put("location", location);
			jsonObject.put("cityname", gameboard.getGb_cityname());
			jsonObject.put("resultno", 0);
			jsonObject.put("buildingclass", jsonArray);
			
			if(player.getP_money()<sumprice) {
				jsonObject.put("resultno", 1);
				jsonArray2.put(jsonObject);
				response.getWriter().print(jsonArray2);
				return;
			}else {
				for(int i=0; i<jsonArray.length(); i++) {
					boolean result =GameboardDao.gameboardDao.buycity(p_no, location, jsonArray.getInt(i), groom_no);
					if(result) {
					}else {
						jsonObject.put("resultno", 2);
						jsonArray2.put(jsonObject);
						response.getWriter().print(jsonArray2);
						return;
					}
				}
				boolean result2 = PlayerDao.playerDao.changemoney(p_no, -sumprice);
				if(result2) {
					jsonObject.put("resultno", 3);
					jsonArray2.put(jsonObject);
					response.getWriter().print(jsonArray2);
					return;
				}else {
					jsonObject.put("resultno", 2);
					jsonArray2.put(jsonObject);
					response.getWriter().print(jsonArray2);
					return;
				}
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
