package controller.lobby;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GameDao;

@WebServlet("/lobby/gamestart")
public class gamestart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public gamestart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groom_no = Integer.parseInt(request.getParameter("roomno"));
		boolean result = GameDao.getGameDao().giveporder(groom_no);
		if(result) {
			boolean result2 = GameDao.getGameDao().gamestart(groom_no);
			if(result2) {
				response.getWriter().print(1);
			}else {
				response.getWriter().print(2);
			}
		}else {
			response.getWriter().print(2);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
