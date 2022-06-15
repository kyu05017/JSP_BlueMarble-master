package controller.member;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_Dao;
import dto.Account;

@WebServlet("/member/signup")
public class Signup extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public Signup() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String nick = request.getParameter("nick");
		
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(pw.getBytes());
			String hex = String.format("%0128x", new BigInteger(1, md.digest()));
	
			Account account = new Account(0, id, hex, email, name, nick, "default.jpg", phone, 1, null, null, 0 , 0);
			
			boolean result = Member_Dao.m_dao.signup(account);
			
			if(result) {
				response.getWriter().print(1);
			}
			else {
				response.getWriter().print(2);
			}
			
		}
		catch (Exception e) {
			System.out.println("비밀번호 복호화 오류 : " + e);
		}
		doGet(request, response);
	}

}
