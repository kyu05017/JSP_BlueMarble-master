package controller.lobby;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GameDao;
import dto.Account;

@WebServlet("/lobby/gotoroom")
public class gotoroom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public gotoroom() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		Account account = (Account)request.getSession().getAttribute("Login");
		if(type.equals("enter")) {
			int groomno = Integer.parseInt(request.getParameter("roomno"));
			int result = GameDao.getGameDao().enterroom(groomno, "", account.getAc_no());
			response.getWriter().print(result);
		}
		else if(type.equals("make")) {
			String roomname = request.getParameter("roomname");
			int result = GameDao.getGameDao().enterroom(0, roomname, account.getAc_no());
			response.getWriter().print(result);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
