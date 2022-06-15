package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_Dao;

/**
 * Servlet implementation class findid
 */
@WebServlet("/member/findid")
public class findid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public findid() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String type = request.getParameter("type");
		
	
		if(type.equals("email")) {
			String email= request.getParameter("email");
			String id = Member_Dao.m_dao.find_email(email);
			
			if(id == null) {
				response.getWriter().print(2);
			}
			else {
				response.getWriter().print(id);
			}
			return;
		}
		if(type.equals("phone")) {
			String phone= request.getParameter("phone");
			
			String id = Member_Dao.m_dao.find_phone(phone);	
			if(id == null) {
				response.getWriter().print(2);
			}
			else {
				response.getWriter().print(id);
			}
			return;
		}
		
	}

}
