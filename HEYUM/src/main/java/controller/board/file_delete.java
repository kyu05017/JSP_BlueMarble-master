package controller.board;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.Board_Dao;

/**
 * Servlet implementation class file_delete
 */
@WebServlet("/board/file_delete")
public class file_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public file_delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 게시물 번호 요청
				int bno= Integer.parseInt(request.getParameter("bno"));
					
					String bfile = Board_Dao.boardDao.getboard(bno).getBfile();
							
							
					
				//2. [DB변경]해당 게시물 번호의 첨푸파일의 필드 null 변경
				boolean result = Board_Dao.boardDao.file_delete(bno); 
						
				
				//3. [실제 파일 삭제]서버 내 첨부파일 삭제
				if(result) {
					
					String uploadpath=request.getSession().getServletContext().getRealPath("/board/upload/"+bfile);
					File file = new File(uploadpath);
					file.delete();
					response.getWriter().print("1");
				}else {
					response.getWriter().print("2");
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
