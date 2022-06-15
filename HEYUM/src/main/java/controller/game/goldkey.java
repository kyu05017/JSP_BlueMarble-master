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
import dto.Goldkey;
import dto.Player;

/**
 * Servlet implementation class goldkeymoney
 */
@WebServlet("/game/goldkey")
public class goldkey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public goldkey() {
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
			int gk_no = Integer.parseInt(request.getParameter("gk_no"));
			String ac_nickname = request.getParameter("ac_nickname");
			String table = "p_no";
			Player player = PlayerDao.playerDao.getplayer(p_no, table,groom_no);
			Goldkey goldkey = GameboardDao.gameboardDao.getgoldkey(gk_no);
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("groom_no", groom_no);
			jsonObject.put("ac_nickname", ac_nickname);
			jsonObject.put("type", "goldkey");
			jsonObject.put("resultno", 0);
			jsonObject.put("gk_no", goldkey.getGk_no());
			jsonObject.put("gk_name", goldkey.getGk_name());
			jsonObject.put("gk_content", goldkey.getGk_content());
			jsonObject.put("p_location", 0);
			jsonObject.put("lackmoney", 0);
			jsonObject.put("topay", 0);
			jsonObject.put("gk_value", goldkey.getGk_value());
			jsonObject.put("startpoint", 0);
			jsonObject.put("p_no", p_no);
			jsonObject.put("move", 0);
			String name = goldkey.getGk_name();
			
			// 돈 증가 황금열쇠
			if(name.equals("노벨 평화상") || name.equals("노후연금") || name.equals("복권당첨") || name.equals("자동차 경주 우승") || name.equals("장학금") || name.equals("파티 초대권") || name.equals("생일 축하") || name.equals("불법토토") ) {
				boolean result = PlayerDao.playerDao.changemoney(p_no, goldkey.getGk_value());
				if(result) {
					jsonObject.put("type", "goldkeyearn");
					jsonObject.put("resultno", 1);
					jsonArray.put(jsonObject);
					response.getWriter().print(jsonArray);
				}else {
					response.getWriter().print(0);	// 오류
				}
			// 돈 차감 황금열쇠
			}else if(name.equals("과속운전") || name.equals("병원비") || name.equals("해외유학")) {
				jsonObject.put("type", "goldkeypay");
				if(player.getP_money()<goldkey.getGk_value()) { // 내야할돈 부족
					jsonObject.put("resultno", 2);
					jsonObject.put("lackmoney", goldkey.getGk_value()-player.getP_money());
					jsonArray.put(jsonObject);
					response.getWriter().print(jsonArray);	
				}else {
					PlayerDao.playerDao.changemoney(p_no, -goldkey.getGk_value());
					jsonObject.put("resultno", 3);
					jsonArray.put(jsonObject);
					response.getWriter().print(jsonArray);
				}
				
			// 이동 관련 황금열쇠
				// 자기 위치 기준에서 이동
			}else if(name.equals("뒤로가시오") || name.equals("세계일주")) {
				jsonObject.put("move", goldkey.getGk_value());
				if(name.equals("세계일주")) {
					PlayerDao.playerDao.changemoney(p_no, 300000);
					jsonObject.put("startpoint", 300000);
					jsonObject.put("move", 40);
				}
				jsonObject.put("type", "goldkeymove");
				PlayerDao.playerDao.moveplayer(p_no, player.getP_location(), goldkey.getGk_value());
				Player player2 = PlayerDao.playerDao.getplayer(p_no, table,groom_no);
				jsonObject.put("resultno", 4);
				jsonObject.put("p_location", player2.getP_location());
				
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
				// 특정 위치로 이동
			}else if(name.equals("고속도로") || name.equals("관광여행") || name.equals("사회복지기금 배당") || name.equals("우주여행") || name.equals("무인도로 가시오")) {
				jsonObject.put("type", "goldkeymove2");
				int move = 0;	// 이동해야하는 칸 수
				if(player.getP_location()>goldkey.getGk_value()) {
					PlayerDao.playerDao.changemoney(p_no, 300000);
					jsonObject.put("startpoint", 300000);
					move = goldkey.getGk_value()-player.getP_location() + 40 ;
				}else {
					move = goldkey.getGk_value()-player.getP_location();
				}
				jsonObject.put("move", move);
				PlayerDao.playerDao.moveplayer(p_no, player.getP_location(), move);
				Player player2 = PlayerDao.playerDao.getplayer(p_no, table,groom_no);
				jsonObject.put("resultno", 5);
				jsonObject.put("p_location", player2.getP_location());
				
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
			}else if(name.equals("무인도 탈출용 무전기")) {
				jsonObject.put("type", "item");
				jsonObject.put("resultno", 6);
				jsonArray.put(jsonObject);
				response.getWriter().print(jsonArray);
			}else if(name.equals("건물 방범비") || name.equals("건물 수리비") || name.equals("건물 정기종합소득세")) {
				JSONObject mybclist = GameboardDao.gameboardDao.getbclist(p_no);
				jsonObject.put("type", "paymoney");
				int sum = 0;
				if(name.equals("건물 방범비")) {
					sum += mybclist.getInt("villa")*10000;
					sum += mybclist.getInt("building")*30000;
					sum += mybclist.getInt("hotel")*50000;
				}else if(name.equals("건물 수리비")) {
					sum += mybclist.getInt("villa")*30000;
					sum += mybclist.getInt("building")*50000;
					sum += mybclist.getInt("hotel")*100000;
				}else {
					sum += mybclist.getInt("villa")*50000;
					sum += mybclist.getInt("building")*100000;
					sum += mybclist.getInt("hotel")*150000;
				}
				
				
				if(player.getP_money()<sum) {
					jsonObject.put("resultno", 2);
					jsonObject.put("lackmoney", sum-player.getP_money());
					jsonObject.put("topay", sum);
					jsonArray.put(jsonObject);
					response.getWriter().print(jsonArray);
				}else {
					PlayerDao.playerDao.changemoney(p_no, -sum);
					jsonObject.put("resultno", 7);
					jsonObject.put("topay", sum);
					jsonArray.put(jsonObject);
					response.getWriter().print(jsonArray);
				}
			}else {
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
