package controller.board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.Board_Dao;
import dto.FreeBoard;

/**
 * Servlet implementation class board_delete
 */
@WebServlet("/board/board_delete")
public class board_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public board_delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt( request.getParameter("bno"));
		// * DB 삭제 전에 파일명 불러오기
		FreeBoard board = Board_Dao.boardDao.getboard(bno);
		String bfile = board.getBfile(); 
				
		
		// 
			boolean result =Board_Dao.boardDao.delete(bno);  
		
		
		response.setCharacterEncoding("UTF-8");// 응답 인코딩 타입 = 한글
		response.setContentType("text/html; charset=UTF-8"); // 응답 파일타입 = HTML
		PrintWriter out =  response.getWriter(); // HTML 내보내기 메소드 사용
		
		
		if(result) {
			
			// * 삭제 성공 시 해당 파일도 서버에서 지우기
			
			String uploadpath =request.getSession().getServletContext().getRealPath("/board/upload/"+bfile);
			File file = new File(uploadpath); // 해당 파일 객체화
			file.delete(); //파일 삭제하기 ( file 클래스 내 제공되는 delete()) 메소드 = 파일삭제 시 사용)
			
			response.getWriter().print(1);
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
