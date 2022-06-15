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
 * Servlet implementation class turnend
 */
@WebServlet("/game/turnend")
public class turnend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public turnend() {
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
			int p_no = Integer.parseInt(request.getParameter("p_no"));
			int groom_no = Integer.parseInt(request.getParameter("groom_no"));
			ArrayList<Player> plist = PlayerDao.playerDao.getplayers(groom_no);
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("groom_no", groom_no);
			jsonObject.put("type", "turn");
			int order =0;

			for(int i=0; i<plist.size(); i++) {
				// 턴 끝내기
				for(Player player : plist) {
					if(player.getP_no()==p_no) {
						order = player.getP_order();
						boolean result = PlayerDao.playerDao.turn(p_no);
						if(result){
						}else {
							response.getWriter().print(2);
							return;
						}
					}
				}
				// 턴 시작하기
				if(order==plist.size()) {
					for(Player player : plist) {
						if(player.getP_order()==1) {
							boolean result = PlayerDao.playerDao.turn(player.getP_no());
							if(result) {
								// 만약 파산한 사람이면 바로 턴 넘기기
								if(player.getP_location()==40) {
									p_no = player.getP_no();
									order = player.getP_order();
								}else {
									jsonObject.put("ac_nickname", player.getAc_nickname());	// 턴 시작하는사람 닉네임
									jsonObject.put("p_no", player.getP_no());
									jsonArray.put(jsonObject);
									response.getWriter().print(jsonArray);
									return;
								}
							}else {
								response.getWriter().print(2);
								return;
							}
						}
					}
				}else {
					for(Player player : plist) {
						if(player.getP_order()==order+1) {
							boolean result = PlayerDao.playerDao.turn(player.getP_no());
							if(result) {
								// 만약 파산한 사람이면 바로 턴 넘기기
								if(player.getP_location()==40) {
									p_no = player.getP_no();
									order = player.getP_order();
								}else {									
									jsonObject.put("ac_nickname", player.getAc_nickname());
									jsonObject.put("p_no", player.getP_no());
									jsonArray.put(jsonObject);
									response.getWriter().print(jsonArray);
									return;
								}
							}else {
								response.getWriter().print(2);
								return;
							}
						}
					}
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
