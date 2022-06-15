package controller.game;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.Member_Dao;
import dto.Account;

/**
 * Servlet implementation class getother
 */
@WebServlet("/game/getother")
public class getother extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getother() {super();}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String id = request.getParameter("id");
			
			int no = Member_Dao.m_dao.getmno(id);
			
			Account account = Member_Dao.m_dao.getother(no);
			
			JSONArray array = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("no", account.getAc_no());
	
			jsonObject.put("id", account.getAc_id());
	
			jsonObject.put("nick", account.getAc_nickname());
			
			jsonObject.put("win", account.getWin());
	
			jsonObject.put("lose", account.getLose());
			
			array.put(jsonObject);
			
			response.setContentType("application/json");
			response.getWriter().print(array);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
