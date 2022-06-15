package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_Dao;


@WebServlet("/member/phoneduplication")
public class phone_duplication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public phone_duplication() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		
		boolean result = Member_Dao.m_dao.phone_check(phone);
		if(result) {
			response.getWriter().print(2);
		}
		else {
			response.getWriter().print(1);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
