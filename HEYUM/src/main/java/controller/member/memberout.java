package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_Dao;

/**
 * Servlet implementation class memberout
 */
@WebServlet("/member/memberout")
public class memberout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public memberout() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		boolean result = Member_Dao.m_dao.memberout(no);
		
		if(result) {
			response.getWriter().print(1);
		}
		else {
			response.getWriter().print(2);
		}
		doGet(request, response);
	}

}
