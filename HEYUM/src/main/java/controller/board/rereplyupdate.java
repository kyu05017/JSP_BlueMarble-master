package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.Board_Dao;

/**
 * Servlet implementation class rereplyupdate
 */
@WebServlet("/board/rereplyupdate")
public class rereplyupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rereplyupdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int rno = Integer.parseInt(request.getParameter("rno"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		String content = request.getParameter("content");
		
		String content1 =  xssFilter(content);
		

		boolean result = Board_Dao.boardDao.replyupdate(rno, bno, content1); 
				
		if(result) {response.getWriter().print(1);}
		else  {response.getWriter().print(2);}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	 public static String xssFilter(String str) {
	      String result = "";
	      
	      result = str;
	      result = result.replaceAll("[<]", "&lt;");
	      result = result.replaceAll("[>]", "&gt;");

	      return result;
	   }
}
