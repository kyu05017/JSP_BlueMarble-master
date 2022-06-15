package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_Dao;

/**
 * Servlet implementation class nick_duplication
 */
@WebServlet("/member/nickduplication")
public class nick_duplication extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public nick_duplication() {super();}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String nick = request.getParameter("nick");
		
		boolean result = Member_Dao.m_dao.nick_check(nick);
		if(result) {
			response.getWriter().print(2);
		}
		else {
			response.getWriter().print(1);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
