package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.Member_Dao;
import dto.Account;

/**
 * Servlet implementation class get_account
 */
@WebServlet("/member/getaccount")
public class get_account extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public get_account() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int ac_no = Integer.parseInt(request.getParameter("ac_no"));		
		JSONObject object = Member_Dao.m_dao.get_account(ac_no);		
		response.setContentType("application/json");	
		response.getWriter().print(object);	
		doGet(request, response);
	}

}
