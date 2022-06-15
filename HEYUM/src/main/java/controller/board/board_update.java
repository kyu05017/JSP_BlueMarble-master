package controller.board;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Board_Dao;
import dao.Member_Dao;
import dto.Account;
import dto.FreeBoard;

/**
 * Servlet implementation class board_update
 */
@WebServlet("/board/board_update")
public class board_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public board_update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
			
		String uploadpath = request.getSession().getServletContext().getRealPath("/board/upload");
		MultipartRequest multi = new MultipartRequest( 
				request,
				uploadpath ,
				1024*1024*10 ,
				"UTF-8" ,
				new DefaultFileRenamePolicy()
				);
		
		int bno =Integer.parseInt( multi.getParameter("bno"));
		
		
		String btitle = multi.getParameter("btitle");
				
		String bcontent = multi.getParameter("bcontent");
				
		String bfile = multi.getFilesystemName("bfile");
				
		int bcategory =Integer.parseInt(multi.getParameter("bcategory")); 
		

			//기존파일명
			FreeBoard temp =Board_Dao.boardDao.getboard(bno);
			String oldfile = temp.getBfile();
			if( bfile == null ) {// 새로운 첨부파일 없다 . 
				bfile = oldfile;
			}else { // 새로운 첨부파일 있다. 
				// * 기존파일은 서버내에서 삭제처리
				String upload = request.getSession().getServletContext().getRealPath("/board/upload/"+oldfile);
				File file = new File(upload);
				file.delete();
			}
			
			
			FreeBoard board = new FreeBoard(bno, btitle, bcontent, 0, bfile, 0, null, 0, bcategory, null);
		
		boolean result = Board_Dao.boardDao.update(board);
		// result
				if(result) {
					response.getWriter().print(1);}
				
				else {
					response.getWriter().print(2);}
					
					
	}

}
