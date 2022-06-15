package controller.game;

import java.io.IOException;
import java.text.DecimalFormat;
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
import dto.Building;
import dto.Player;



/**
 * Servlet implementation class pay
 */
@WebServlet("/game/fee")
public class fee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fee() {
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
			int fee= Integer.parseInt(request.getParameter("fee"));
			int p_no = Integer.parseInt(request.getParameter("p_no"));
			int groom_no = Integer.parseInt(request.getParameter("groom_no"));
			int gb_location = Integer.parseInt(request.getParameter("gb_location"));
			String ac_nickname = request.getParameter("ac_nickname");
			ArrayList<Building> blist = GameboardDao.gameboardDao.getbuilding(gb_location, groom_no);
			String table = "p_no";
			Player player2 = PlayerDao.playerDao.getplayer(blist.get(0).getP_no(),table,groom_no);
			Player player = PlayerDao.playerDao.getplayer(p_no,table,groom_no);
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("groom_no", groom_no);
			jsonObject.put("ac_nickname", ac_nickname);
			jsonObject.put("type", "fee");
			jsonObject.put("fee", fee);
			jsonObject.put("resultno", 0);
			jsonObject.put("recievenickname", player2.getAc_nickname());
			jsonObject.put("lackmoney", 0);
			if(player.getP_money()>fee) {
				boolean result = PlayerDao.playerDao.changemoney(p_no, -fee);
				if(result) {
					boolean result2 = PlayerDao.playerDao.changemoney(player2.getP_no(), fee);
					if(result2) {
						jsonObject.put("resultno", 1);
						jsonArray.put(jsonObject);
						response.getWriter().print(jsonArray);
						return;
					}else {
						jsonObject.put("resultno", 3);
						jsonArray.put(jsonObject);
						response.getWriter().print(jsonArray);
						return;
					}
					
				}
			}else {
				int lackmoney = fee - player.getP_money();
				jsonObject.put("resultno", 2);
				jsonObject.put("lackmoney", lackmoney);
				jsonArray.put(jsonObject);
				response.getWriter().print(jsonArray);
				return;
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
