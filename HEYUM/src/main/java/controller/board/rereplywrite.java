package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.Board_Dao;
import dao.Member_Dao;
import dto.Account;
import dto.FreeBoard_reply;


/**
 * Servlet implementation class rereplywrite
 */
@WebServlet("/board/rereplywrite")
public class rereplywrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rereplywrite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int rindex = Integer.parseInt( request.getParameter("rno") ); // rindex : 어떤 댓글의 대댓글인 식별번호 ( 부모 댓글번호 ) 
		int bno = Integer.parseInt(  request.getParameter("bno") ) ;	
		String rcontent = request.getParameter("rrcontent");
		
		String rcontent1 =  xssFilter(rcontent);
		

		Account account = (Account)request.getSession().getAttribute("Login");
			String mid =  account.getAc_id();
		int mno = Member_Dao.m_dao.getmno(mid);
				
		// 객체화 ( 댓글번호 , 작성일 , mid 제외 )
		FreeBoard_reply reply = new FreeBoard_reply( 0 , rcontent1, null , rindex , bno, mno, null);
		// db처리
		boolean result = Board_Dao.boardDao.replywrite(reply);  
				
		if( result ) { response.getWriter().print(1); }
		else { response.getWriter().print(2); }
		

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
