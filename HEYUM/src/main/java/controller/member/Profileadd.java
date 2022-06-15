package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Member_Dao;
import dto.Account;

/**
 * Servlet implementation class Profileadd
 */
@WebServlet("/member/profileadd")
public class Profileadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Profileadd() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("Login");

		MultipartRequest multi = new MultipartRequest(
				request,			/*요청 타입 */ 
				request.getSession().getServletContext().getRealPath("/img/profile") , /* 저장 폴더위치 */
				1024*1024*1024, 	/* 파일 최대용량 = 바이트 기준 */
				"UTF-8" ,			/* 파일 인코딩타입 */
				new DefaultFileRenamePolicy()/* 보안 방식 = */
				/* DefaultFileRenamePolicy : 파일명이 중복이면 파일명 뒤에 숫자 자동 부여 = 식별 */
				);
		String pimg = multi.getFilesystemName("pro_img");
		
		boolean result = Member_Dao.m_dao.profile_change(pimg, account.getAc_no());
		
		if( result ) { 
			response.getWriter().print(1);
		}
		else { 
			response.getWriter().print(2);
		}
	}

}
