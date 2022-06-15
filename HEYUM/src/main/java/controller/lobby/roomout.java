package controller.lobby;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GameDao;
import dto.Account;

@WebServlet("/lobby/roomout")
public class roomout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public roomout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groomno = Integer.parseInt(request.getParameter("roomno"));
		Account account = (Account)request.getSession().getAttribute("Login");
		boolean result = GameDao.getGameDao().outroom(groomno, account.getAc_no());
		if(result) {
			response.getWriter().print(1);
		}
		else {
			response.getWriter().print(2);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
