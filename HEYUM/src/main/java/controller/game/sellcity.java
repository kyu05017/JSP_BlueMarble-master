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

/**
 * Servlet implementation class sellcity
 */
@WebServlet("/game/sellcity")
public class sellcity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sellcity() {
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
			int sum = Integer.parseInt(request.getParameter("sum"));
			int p_no = Integer.parseInt(request.getParameter("p_no"));
			String ac_nickname = request.getParameter("ac_nickname");
			String mycitylist = request.getParameter("mycitylist");
			JSONArray jsonArray = new JSONArray(mycitylist);
			JSONArray jsonArray2 = new JSONArray();
			
			boolean result = PlayerDao.playerDao.changemoney(p_no, sum);
			if(result) {
				boolean check = true;
				for(int i=0; i<jsonArray.length(); i++) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("ac_nickname", ac_nickname);
					jsonObject.put("groom_no", groom_no);
					jsonObject.put("type", "sellcity");
					jsonObject.put("sum", sum);
					jsonObject.put("cityname", jsonArray.getJSONObject(i).getString("cityname"));
					jsonArray2.put(jsonObject);
					boolean result2 = GameboardDao.gameboardDao.deletebuilding(jsonArray.getJSONObject(i).getInt("gb_location"), p_no);
					if(result2 == false) {
						check = false;
					}
				}
				if(check) {
					response.getWriter().print(jsonArray2);
				}else {
					response.getWriter().print(1);	
				}
			}else {
				response.getWriter().print(1);
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
