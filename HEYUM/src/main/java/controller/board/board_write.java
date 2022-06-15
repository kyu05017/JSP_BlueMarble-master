package controller.board;

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
 * Servlet implementation class boardwrite
 */
@WebServlet("/board/board_write")
public class board_write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public board_write() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//서버주소
		//String uploadpath = "C:/Users/504/eclipse-workspace/SSS/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/test220518/board/upload";
		String uploadpath = request.getSession().getServletContext().getRealPath("/board/upload") ;
		//System.out.println("서버의 경로 찾기 : " + 
			//	request.getSession().getServletContext().getRealPath("/board/upload"));
		
		//첨부파일
		MultipartRequest multipartRequest = new MultipartRequest(
				request,
				uploadpath,
				1024*1024*10,
				"UTF-8",
				new DefaultFileRenamePolicy()
				);
		// 데이터 요청

		request.setCharacterEncoding("UTF-8");
		String btitle = multipartRequest.getParameter("btitle");
		
		String bcontent = multipartRequest.getParameter("bcontent");

		int bcategory =Integer.parseInt(multipartRequest.getParameter("bcategory")); 

		String bfile = multipartRequest.getFilesystemName("bfile");
		//String btitle1 = xssFilter(btitle);
		
	//	String bcontent2 = bcontent.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\S)*(/)?","");
		//String bcontent1 = ContentxssFilter(bcontent);
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("Login");
		
		String mid = account.getAc_id();
		
		
		// 추가 및 수정 
		 int mno = Member_Dao.m_dao.getmno(mid);
		 //System.out.println("bcontent :"+bcontent1);
		//객체 
		 //FreeBoard board = new FreeBoard(0, btitle, bcontent, mno, bfile, 0, null, 0, bcategory, null);
		FreeBoard board = new FreeBoard(0, btitle, bcontent, mno, bfile, 0, null, 0, bcategory, null);
		
		
		// db processing
		boolean result = Board_Dao.boardDao.write(board);

		// result
		if(result) {
			response.getWriter().print(1);}
			//response.sendRedirect("/HEYUM/board/board_list.jsp");}
		else {
			response.getWriter().print(2);}
			//response.sendRedirect("/HEYUM/board/board_write.jsp");}
				
	}
	

	
	 public static String xssFilter(String str) {
	      String result = "";
	      
	      result = str;
	      result = result.replaceAll("[<]", "&lt;");
	      result = result.replaceAll("[>]", "&gt;");

	      return result;
	   }
}











