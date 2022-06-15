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
 * Servlet implementation class pwcheck
 */
@WebServlet("/member/pwcheck")
public class pwcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public pwcheck() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			
			String pw = request.getParameter("pw");
			
			
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(pw.getBytes());
			String hex = String.format("%0128x", new BigInteger(1, md.digest()));
			
			boolean result = Member_Dao.m_dao.pw_check(no, hex);
			
			if(result) {
				response.getWriter().print(1);
			}
			else {
				response.getWriter().print(2);
			}
			
			doGet(request, response);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

}
