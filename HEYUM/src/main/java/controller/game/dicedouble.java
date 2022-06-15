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

import dao.PlayerDao;
import dto.Player;

/**
 * Servlet implementation class dicedouble
 */
@WebServlet("/game/dicedouble")
public class dicedouble extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dicedouble() {
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
			int groom_no = Integer.parseInt(request.getParameter("groom_no"));
			int p_no = Integer.parseInt(request.getParameter("p_no"));
			int p_location = Integer.parseInt(request.getParameter("p_location"));
			String ac_nickname = request.getParameter("ac_nickname");
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("groom_no", groom_no);
			jsonObject.put("ac_nickname", ac_nickname);
			jsonObject.put("type", "dicedouble");
			int move = 0;
			if(p_location>20) {
				move = 20 - p_location +40;
			}else {
				move = 20 - p_location;
			}
			boolean result = PlayerDao.playerDao.moveplayer(p_no, p_location, move);
			if(result) {
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
