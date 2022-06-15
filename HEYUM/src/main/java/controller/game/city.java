package controller.game;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.GameboardDao;
import dao.PlayerDao;
import dto.Building;

/**
 * Servlet implementation class city
 */
@WebServlet("/game/city")
public class city extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public city() {
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
			int location = Integer.parseInt(request.getParameter("location"));
			int groom_no = Integer.parseInt(request.getParameter("groom_no"));
			String ac_nickname = request.getParameter("ac_nickname");
			ArrayList<Building> blist = GameboardDao.gameboardDao.getbuilding(location, groom_no);
			JSONArray jsonArray = new JSONArray();
			String city = GameboardDao.gameboardDao.getcityinfo(location).getGb_cityname();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("ac_nickname", ac_nickname);
			jsonObject.put("groom_no", groom_no);
			jsonObject.put("cityname", city);
			jsonObject.put("type", "city");
			jsonObject.put("fee", 0);
			jsonObject.put("resultno", 0);
			if(blist.isEmpty()) {
				if(city.equals("황금열쇠")) {
					jsonObject.put("resultno", 3);
				}else if(city.equals("무인도")) {
					jsonObject.put("resultno", 4);
				}else if(city.equals("우주여행")) {
					jsonObject.put("resultno", 5);
				}else if(city.equals("사회복지기금수령처")) {
					jsonObject.put("resultno", 6);
				}else if(city.equals("사회복지기금 접수처")) {
					jsonObject.put("resultno", 7);
				}else if(city.equals("출발")) {
					jsonObject.put("resultno", 8);
				}else {
					jsonObject.put("resultno", 1);
				}
			}else {
				if(blist.get(0).getP_no()!=p_no) {
					int fee = GameboardDao.gameboardDao.getfee(location,groom_no);
					jsonObject.put("resultno", 9);
					jsonObject.put("fee", fee);
				}else {
					jsonObject.put("resultno", 2);
				}
			}
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
