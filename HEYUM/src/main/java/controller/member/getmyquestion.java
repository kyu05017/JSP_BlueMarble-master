package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dao.Member_Dao;

/**
 * Servlet implementation class getmyquestion
 */
@WebServlet("/member/getmyquestion")
public class getmyquestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getmyquestion() {super();}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		JSONArray array = Member_Dao.m_dao.getmyquestion(no);
		
		response.setContentType("application/json");	
		response.getWriter().print(array);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
