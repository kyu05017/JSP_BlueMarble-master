package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board_Dao;

/**
 * Servlet implementation class board_offer
 */
@WebServlet("/board/board_offer")
public class board_offer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public board_offer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");	// request 사용시 = 한글 인코딩 
		int bno = Integer.parseInt( request.getParameter("bno") ) ; // 게시물번호 요청 
		String mid = request.getParameter("mid");
		int mno = Integer.parseInt(request.getParameter("mno"));
		int category = Integer.parseInt(request.getParameter("category"));
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(mid+bno+mno+category)== null) {
			boolean result =Board_Dao.boardDao.increoffer(bno);
			if(result) { 
				session.setAttribute( mid+bno+mno+category , true );
				session.setMaxInactiveInterval( 60*60*24 );
				response.getWriter().print(1);
			
			}
		}else {
			response.getWriter().print(2);
		
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
