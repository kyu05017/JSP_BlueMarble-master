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
 * Servlet implementation class replywrite
 */
@WebServlet("/board/replywrite")
public class replywrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public replywrite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int bno =Integer.parseInt( request.getParameter("bno"));
		Account account = (Account)request.getSession().getAttribute("Login");
		String mid = account.getAc_id();
		String rcontent = request.getParameter("rcontent");
		String rcontent1 =  xssFilter(rcontent);
			// 회원은 세션
			//HttpSession
		
		//String logined_account = ((String)request.getSession().getAttribute("Login")).;
		
		int mno = Member_Dao.m_dao.getmno(mid); 
//				MemberDao.getmemberDao().getmno(mid);
//		// 객체화 [ 댓글 번호, 댓글 작성일, rindex, mid 제외 ]
		FreeBoard_reply reply = new FreeBoard_reply(0, rcontent1, null, 0, bno, mno, null);
//		// DB처리
		boolean result = Board_Dao.boardDao.replywrite(reply); 
				
		if(result) {
			response.getWriter().print(1);
		}else {
			response.getWriter().print(2);
		}
	}

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
