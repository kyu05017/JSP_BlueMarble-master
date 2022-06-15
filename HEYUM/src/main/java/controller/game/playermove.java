package controller.game;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PlayerDao;
import dto.Player;

/**
 * Servlet implementation class playermove
 */
@WebServlet("/game/playermove")
public class playermove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public playermove() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		int sum = Integer.parseInt(request.getParameter("sum"));
		int groom_no = Integer.parseInt(request.getParameter("groom_no"));
		int currentlocation = 0;
		ArrayList<Player> plist = PlayerDao.playerDao.getplayers(groom_no);
		for(Player temp : plist) {
			if(temp.getP_no()==p_no) {
				currentlocation = temp.getP_location();
			}
		}
		
		boolean result = PlayerDao.playerDao.moveplayer(p_no, currentlocation, sum);
		if(result) {
			String table = "p_no";
			Player player = PlayerDao.playerDao.getplayer(p_no,table,groom_no);
			response.getWriter().print(player.getP_location());
		}else {
			response.getWriter().print(0);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
