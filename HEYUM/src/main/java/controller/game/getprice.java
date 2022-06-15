package controller.game;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dao.GameboardDao;
import dto.Gameboard;

/**
 * Servlet implementation class getprice
 */
@WebServlet("/game/getprice")
public class getprice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getprice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String buildingclass = request.getParameter("buildingclass");
			int p_location = Integer.parseInt(request.getParameter("p_location"));
			Gameboard gameboard = GameboardDao.gameboardDao.getcityinfo(p_location);
			int sumprice = 0;
			JSONArray jsonArray = new JSONArray(buildingclass);
			for(int i=0; i<jsonArray.length(); i++) {
				if(jsonArray.get(i).toString().equals("1")) {
					sumprice += gameboard.getGb_emptyprice();
				}else if(jsonArray.get(i).toString().equals("2")) {
					sumprice += gameboard.getGb_villaprice();
				}else if(jsonArray.get(i).toString().equals("3")) {
					sumprice += gameboard.getGb_buildingprice();
				}else if(jsonArray.get(i).toString().equals("4")) {
					sumprice += gameboard.getGb_hotelprice();
				}
			}
			response.getWriter().print(sumprice);
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
