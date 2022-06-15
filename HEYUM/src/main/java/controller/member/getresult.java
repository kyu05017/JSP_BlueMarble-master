package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.Member_Dao;

/**
 * Servlet implementation class getresult
 */
@WebServlet("/member/getresult")
public class getresult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getresult() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int no = Integer.parseInt(request.getParameter("no"));
		
		JSONObject object1 = Member_Dao.m_dao.getwin(no);
		JSONObject object2 = Member_Dao.m_dao.getlose(no);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(object1);
		jsonArray.put(object2);
		
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
