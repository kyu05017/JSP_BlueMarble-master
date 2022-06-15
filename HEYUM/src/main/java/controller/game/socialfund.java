package controller.game;

import java.io.IOException;
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
 * Servlet implementation class socialfund
 */
@WebServlet("/game/socialfund")
public class socialfund extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public socialfund() {
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
			String ac_nickname = request.getParameter("ac_nickname");
			String type = request.getParameter("type");
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("ac_nickname", ac_nickname);
			jsonObject.put("groom_no", groom_no);
			jsonObject.put("resultno", 0);
			jsonObject.put("lackmoney", 0);
			if(type.equals("give")) {
				jsonObject.put("type", "givesocialfund");
				String table = "p_no";
				Player player = PlayerDao.playerDao.getplayer(p_no, table,groom_no);
				if(player.getP_money()<200000) {
					jsonObject.put("resultno", 1);
					int lackmoney = 200000-player.getP_money();
					jsonObject.put("lackmoney", lackmoney);
					jsonArray.put(jsonObject);
					response.getWriter().print(jsonArray);
				}else {
					boolean result = PlayerDao.playerDao.changemoney(p_no, -200000);
					if(result) {
						jsonObject.put("resultno", 3);
						jsonArray.put(jsonObject);
						response.getWriter().print(jsonArray);
					}else {
						jsonObject.put("resultno", 2);
						jsonArray.put(jsonObject);
						response.getWriter().print(jsonArray);
					}
				}
				
			}else {
				int socialfundmoney = Integer.parseInt(request.getParameter("socialfundmoney"));
				jsonObject.put("type", "receivesocialfund");
				jsonArray.put(jsonObject);
				boolean result = PlayerDao.playerDao.changemoney(p_no, socialfundmoney);
				if(result) {
					response.getWriter().print(jsonArray);
				}else {
					response.getWriter().print(2);
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
