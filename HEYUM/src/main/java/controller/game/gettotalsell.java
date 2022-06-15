package controller.game;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dao.GameboardDao;

/**
 * Servlet implementation class gettotalsell
 */
@WebServlet("/game/gettotalsell")
public class gettotalsell extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gettotalsell() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			String mycitylist = request.getParameter("mycitylist");
			int groom_no = Integer.parseInt(request.getParameter("groom_no"));
			JSONArray jsonArray = new JSONArray(mycitylist);
			int totalsell = 0;
			for(int i=0; i<jsonArray.length(); i++) {
				totalsell += GameboardDao.gameboardDao.sellcity(jsonArray.getJSONObject(i).getInt("gb_location"), groom_no);
			}
			response.getWriter().print(totalsell);
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
