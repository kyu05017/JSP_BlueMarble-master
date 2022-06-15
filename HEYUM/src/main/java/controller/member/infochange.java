package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_Dao;

/**
 * Servlet implementation class change_nick
 */
@WebServlet("/member/infochange")
public class infochange extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public infochange() {super();}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		int no = Integer.parseInt(request.getParameter("no"));
		if(type.equals("nick")) {

			String nickname = request.getParameter("nick");
			
			boolean result = Member_Dao.m_dao.nick_change(nickname, no);
			if(result) {
				response.getWriter().print(nickname);
				return;
			}
			else {
				response.getWriter().print("2");
				return;
			}
		}
		else if(type.equals("email")) {
			
			String email = request.getParameter("email");
			
			boolean result = Member_Dao.m_dao.email_change(email, no);
			if(result) {
				response.getWriter().print(email);
				return;
			}
			else {
				response.getWriter().print("2");
				return;
			}
		}
		else if(type.equals("phone")) {
			
			String phone = request.getParameter("phone");
			boolean result = Member_Dao.m_dao.phone_change(phone, no);
			if(result) {
				response.getWriter().print(phone);
				return;
			}
			else {
				response.getWriter().print("2");
				return;
			}
		}
		
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doGet(request, response);}

}
