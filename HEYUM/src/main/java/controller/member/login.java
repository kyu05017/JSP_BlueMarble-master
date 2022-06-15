package controller.member;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Member_Dao;
import dto.Account;

/**
 * Servlet implementation class login
 */
@WebServlet("/member/login")
public class login extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public login() {super();}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String loginid = request.getParameter("loginid");
		String password = request.getParameter("password");
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(password.getBytes());
			String hex = String.format("%0128x", new BigInteger(1, md.digest()));
	
			Account account =  Member_Dao.m_dao.account_Login(loginid, hex);
			
			if(account != null) {
				HttpSession session = request.getSession();
				session.setAttribute("Login", account );
				response.getWriter().print(1);
			}
			else {
				response.getWriter().print(2);
			}
		}
		catch (Exception e) {
			System.out.println("비밀번호 복호화 오류 : " + e);
		}
	}
}
