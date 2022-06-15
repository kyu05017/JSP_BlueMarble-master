package controller.lobby;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dao.GameDao;

@WebServlet("/lobby/inuserlist")
public class inuserlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public inuserlist() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		int roomno = Integer.parseInt(request.getParameter("roomno"));
		JSONArray jsonArray = GameDao.getGameDao().getroomlist(roomno);
		response.getWriter().print(jsonArray);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
