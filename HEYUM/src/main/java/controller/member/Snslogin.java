package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Member_Dao;
import dto.Account;

/**
 * Servlet implementation class snslogin
 */
@WebServlet("/member/snslogin")
public class Snslogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public Snslogin(){super();}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type"));
		
		if(type == 2) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String phone = request.getParameter("phone");
			String nickname = id.split("@")[0];
			String name = request.getParameter("name");
			
			Account account = new Account(0, id, pw, id, name, nickname,"default.jpg", phone, type, null, null , 0 , 0);
			
			Account result = Member_Dao.m_dao.snsLoginCheck(account);
			
			HttpSession session = request.getSession();
			session.setAttribute("Login",result);
			response.getWriter().print(1);

		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doGet(request, response);}
}
