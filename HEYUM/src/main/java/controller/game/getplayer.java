package controller.game;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.PlayerDao;
import dto.Player;

/**
 * Servlet implementation class getplayer
 */
@WebServlet("/game/getplayer")
public class getplayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getplayer() {
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
			String table = "ac_no";
			int ac_no = Integer.parseInt(request.getParameter("ac_no"));
			int groom_no = Integer.parseInt(request.getParameter("groom_no"));
			Player player = PlayerDao.playerDao.getplayer(ac_no,table,groom_no);
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
			
			response.getWriter().print(jsonObject);
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
