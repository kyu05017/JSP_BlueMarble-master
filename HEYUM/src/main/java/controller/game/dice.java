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



@WebServlet("/game/dice")
public class dice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public dice() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			int p_no = Integer.parseInt(request.getParameter("p_no"));
			int sum = Integer.parseInt(request.getParameter("sum"));
			int groom_no = Integer.parseInt(request.getParameter("groom_no"));
			String ac_nickname = request.getParameter("ac_nickname");
			int currentlocation = 0;
			ArrayList<Player> plist = PlayerDao.playerDao.getplayers(groom_no);
			for(Player temp : plist) {
				if(temp.getP_no()==p_no) {
					currentlocation = temp.getP_location();
				}
			}
			boolean result = PlayerDao.playerDao.moveplayer(p_no, currentlocation, sum);
			JSONArray jsonArray = new JSONArray();
			if(result) {
				if(currentlocation+sum>40) {
					boolean result2 = PlayerDao.playerDao.changemoney(p_no, 300000);
					if(result2 == false) {
						response.getWriter().print(0);
					}
				}
				
				ArrayList<Player> players = PlayerDao.playerDao.getplayers(groom_no);
				for(Player temp : players) {
					JSONObject jsonObject = new JSONObject();
					// 주사위를 굴렸을때 출발지를 지났다면 월급
					if(currentlocation+sum>40) {
						jsonObject.put("startpoint", 300000);
					}else {
						jsonObject.put("startpoint", 0);
					}
					
					jsonObject.put("nickname", temp.getAc_nickname() );
					jsonObject.put("location", temp.getP_location());
					jsonObject.put("p_order", temp.getP_order());
					jsonObject.put("groom_no", groom_no);
					jsonObject.put("type", "dice");
					jsonObject.put("sum", sum);
					jsonObject.put("player", ac_nickname);
					jsonArray.put(jsonObject);
				}	
			}else {
				response.getWriter().print(0);
			}
		
			response.getWriter().print(jsonArray);
		}catch(Exception e) {e.printStackTrace();}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
