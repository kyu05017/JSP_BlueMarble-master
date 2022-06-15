package controller.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class board_filedown
 */
@WebServlet("/board/board_filedown")
public class board_filedown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public board_filedown() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bfile = request.getParameter("bfile");
		
		// 2.서버에서 해당 파일 찾기 [ 서버 내 경로 찾기 ]
		String uploadpath = request.getSession().getServletContext().getRealPath("/board/upload/"+bfile);
		// 프로젝트 경로 찾기
		// String uploadpath = C:/Users/504/eclipse-workspace/SSS/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/jspweb/board/upload/"+bfile;
		
		// 3. 파일 객체화
		File file = new File(uploadpath);
		
		// 4. 다운로드 형식 [ 브라우저 마다 다름 ]
		response.setHeader("Content-Disposition", 
				"attachment;filename="+URLEncoder.encode(bfile,"UTF-8")+";");
								// 다운로드 형식 HTML	// 다운로드 화면에서 표시할 파일 명
		// 5. 바이트 형식으로 내보내기 ( 통신 ) ;
			//1. 입력 스트림 [서버가 pc에 있는 파일을 스트림 가져오기 단계 ]
			BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
				// 1. 바이트 배열  선언
				byte[] bytes = new byte[(int)file.length()]; // 파일길이(크기=용량) 만큼 배열 선언
				// 2. 바이트 읽어 오기
				fin.read(bytes);
			//2 . 출력 스트림 [ 서버가 pc내 파일을 내보내기 단계 ]
				// response : (서블릿)웹 응답 객체
			BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());
			fout.write(bytes);
			// 3. 스트림 닫기 ( 기록 제거 )
				fin.close();
				fout.close();
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
