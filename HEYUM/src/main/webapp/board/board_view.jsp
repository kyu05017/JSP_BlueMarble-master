<%@page import="dto.FreeBoard_reply"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.Member_Dao"%>
<%@page import="dto.FreeBoard"%>
<%@page import="dao.Board_Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/HEYUM/css/board_view.css" rel="stylesheet">
</head>
<body>
	
	<%@include file ="../header.jsp" %>
	
	<%
		request.setCharacterEncoding("UTF-8");
		int category = Integer.parseInt(request.getParameter("category").trim());
		int pagenum = Integer.parseInt(request.getParameter("pagenum").trim());
	%>
		
		<div class="container">
		

		<%
		//	String mid2 = account.getAc_id();
		  int admin_type;
			//String admin_type="";
			String mid;
			int mno;
			int bno = Integer.parseInt(request.getParameter("bno").trim());		// 게시물번호
			
			if( session.getAttribute("Login") != null ){
				 mid = account.getAc_id();
				 mno = account.getAc_no();
				  admin_type = account.getAc_type(); // 
				 // admin_type = account.getAc_id();
				if( session.getAttribute(mid+bno+category ) == null  ){
					// 조회수 증가처리 
					Board_Dao.boardDao.increview(bno);
					// 조회수 중복방지[ 세션 생성 ]
					session.setAttribute( mid+bno+category , true );// 세션명 : 아이디 + 게시물번호  // 세션의 값 : true
					session.setMaxInactiveInterval( 60*60*24 ); // 24시간
					
				}
			}else{
				 mid = null;
				if( session.getAttribute(mid+bno+category ) == null  ){
					admin_type = 5;
					// 조회수 증가처리 
					Board_Dao.boardDao.increview(bno);
					// 조회수 중복방지[ 세션 생성 ]
						
					session.setAttribute( mid+bno+category , true );// 세션명 : 아이디 + 게시물번호  // 세션의 값 : true
					session.setMaxInactiveInterval( 60*60*24 ); // 24시간
				}
			}
		
		FreeBoard board =  Board_Dao.boardDao.getboard(bno);// 게시물번호로 게시물 dto 가져오기
		%>
		<br><br>
		<h2 class="boardview_title"><%=board.getBtitle()%></h2>
		<br>
		<table class="table"> <!-- table : 부스트랩 테이블 클래스 -->
			<tr> 
				<td width="20%">번호 : <%=board.getBno()%></td> <!-- td 가로길이 : width속성 -->
				<td width="20%">작성자 : <%=board.getMid() %></td> 
				<td width="20%">작성일 : <%=board.getBdate() %></td>  
				<td width="20%">조회수 : <%=board.getBview() %></td> 
				<td width="20%" id="postoffernum">추천수 : <%=board.getBoffer() %></td>
			</tr>
			<tr> 
				<td colspan="6"> <!-- colspan : 열 병합 // rowspan : 행 병합  -->
					<div class="boardview_content">	
						<%=board.getBcontent() %>
						
						
									<br><br><br><br><br>
						<div class="row">
						<div class="col-md-2 offset-5">
			<%			if( session.getAttribute("Login") != null ){	
							mno = account.getAc_no();
							if(session.getAttribute(mid+bno+mno+category)== null){
				%>			
							<button class="form-control "  onclick="offerbtn(<%=board.getBno()%>,<%=mno%>,<%=category%>,'<%=mid%>',<%=pagenum%>)"> ! 개추 ! </button>
							<% //session.setAttribute( mid+bno+mno+category , true );// 세션명 : 아이디 + 게시물번호  // 세션의 값 : true
							//   session.setMaxInactiveInterval( 60*60*24 ); %> 
						   <%}
							else{%>
							<button class="form-control "  onclick="offerbtn(<%=board.getBno()%>,<%=mno%>,<%=category%>,'<%=mid%>')"> !개추! </button>
						 	<!-- <input type="button" class="form-control " value=" !개추! " > <span> 해당글을 이미  추천 하셨습니다 <br> 추천은 하루에 한 번 가능합니다.</span> -->				
							<%} %>			
											
							<%
				 				}else{
				 			%>
				 				<div class="col-md-12 write_thing">
				 				<input type="button" class="form-control " value=" ! 개추 ! " onclick="nonlogin()"> <span class="write_hover">로그인 후 추천이 가능합니다</span>
				 				</div> 
							<%
							}
							%>
						</div>
					</div>	
						
					</div>
					<br><br>				
				</td> 
			</tr>
			
			<% if( board.getBfile() == null ){ // 첨부파일이 없을경우 %> 
				<tr> <td colspan="6"> 첨부파일 :  -  </td> </tr> <!-- 첨부파일 다운로드 -->
			<%	}else{ // 첨부파일이 있을경우 %>
				<tr> <td colspan="64"> 첨부파일 : <a href="board_filedown?bfile=<%=board.getBfile()%>">
					<%=board.getBfile() %></a> </td> </tr> <!-- 첨부파일 다운로드 -->
			<% } %>
			
		</table>
		<br>
		 <!----------------------------------- 게시물 삭제/수정/목록 버튼 구역 ------------------------------------------------->		
		<div class="row">
		<%
			if( session.getAttribute("Login") != null ){
				admin_type = account.getAc_type();
			}else{
				admin_type= 10;
			}
			
     	if(board.getMno() == Member_Dao.m_dao.getmno(mid) || admin_type == 0 ){ // MemberDao servelet 수정되면 이걸로 사용 
     	//admin_type.equals("admin")){ 	// 아이디로 회원번호 가져와서 비교
     		
		%>
			<div class="col-md-2">
			<!-- 	<a href="board_delete?bno=<%=board.getBno()%>"> --> 
				<button class="form-control" onclick="deletepost(<%=board.getBno()%>,<%=category%>,<%=pagenum%>)">삭제</button> 
			<!-- 	</a> <!--작성자와 로그인된 id가 동일하면 보이는 버튼  -->
			</div>	
			<div class="col-md-2">
				
				<a href="board_update.jsp?bno=<%=board.getBno()%>&category=<%=category%>&pagenum=<%=pagenum%>">
				 <button class="form-control" >수정</button> </a> <!--작성자와 로그인된 id가 동일하면 보이는 버튼  -->	
				<br>
				
				
			</div>
			
		<%} %>
			<div class="col-md-2">
				<a href="board_list.jsp?key=&keyword=&category=<%=category%>&pagenum=<%=pagenum%>"> <button class="form-control">목록</button> </a> <!-- 무조건 보이는 버튼 -->
			</div>
		</div>
		
		
		<br><br>
 <!----------------------------------- 댓글 구역 쓰기 -------------------------------------------------------------------->		
		<h4 class = "boardview_title">댓글</h4>	
		<br>
		
		 
		<% if( mid != null ){  //로그인이 되어있으면 %>
		<div class="row"> <!-- row : 가로배치 -->
			<div class="col-md-10">
				<textarea id="rcontent" class="form-control" rows=3 placeholder="댓글을 작성해 주세요" style="resize:none;"></textarea>
			</div>
			<div class="col-md-2">	<!-- p : padding   /   m : margin -->
				<button class="form-control py-4 my-1" onclick="replywrite(<%=bno%>)">등록</button>
			</div>
		</div>
		<%} else{ // 로그인이 안되어 있으면  %>
			<h5 class="text-center"> *로그인후 댓글쓰기가 가능합니다. </h5>
		<%} %>
		<% %>
<!----------------------------------- 댓글 출력 구역 -------------------------------------------------------------------->			
		<table id="replytable" class="table" > <!-- 댓글 작성 성공시 해당 태그 새로고침 => js( jquery ) -->
			<%  ArrayList<FreeBoard_reply> replylist = Board_Dao.boardDao.replylist(bno);
				for( FreeBoard_reply reply : replylist ){  %>
			<tr>
				<td class="replywriter" width="15%">
					<%=reply.getMid() %> <br> 
					<span class="replydate"> <%= reply.getRdate() %> </span>
				</td>
				
				<td width="80%" colspan="2">
					<%=reply.getRcontent() %> <br> 
				<% if( mid !=null && mid.equals( reply.getMid() ) ){ // 본인 작성한 댓글이면 %>
					<button class="btn replybtn" onclick="replyjsupdate(<%=reply.getRno()%>,<%=reply.getBno() %>,'<%=reply.getRcontent()%>')"> 수정 </button>
					<button class="btn replybtn" onclick="replydelete(<%=reply.getRno()%>)"> 삭제 </button>
				<%} %>
				<% if( mid !=null) {%>
					<button class="btn replybtn" 
					onclick="rereplyview(<%=reply.getRno()%> , <%=reply.getBno()%> )"> 
					댓글 
					<%} %>
					</button>
				</td>
			</tr>
			
			<tr> <!-- 대댓글 입력창 -->
				<td> </td>
				<td colspan="2" id=<%=reply.getRno() %> > </td>   <!-- 해당 태그의 id값을 변수로 설정 = 댓글번호 ( 댓글 한개당 1개씩 ) -->
			</tr>
			
			<!-- 대댓글 출력창  -->
			<%ArrayList<FreeBoard_reply> rereplylist = Board_Dao.boardDao.rereplylist(bno, reply.getRno());
				for( FreeBoard_reply rereply : rereplylist ){%>
				<tr>
					<td></td>
					<td width="10%" class="replywriter">
						<%=rereply.getMid() %> <br> 
						<span class="replydate"> <%=rereply.getRdate() %> </span>
					</td>
					
					<td width="80%">
						<%=rereply.getRcontent() %> <br> 
					<% if( mid != null && mid.equals( rereply.getMid() ) ){ %>
						<button class="btn replybtn" onclick="rereplyjsupdate(<%=rereply.getRno()%>,<%=rereply.getBno() %>,<%=rereply.getRindex()%>,'<%=rereply.getRcontent()%>')"> 수정 </button>
						<button class="btn replybtn" onclick="replydelete(<%=rereply.getRno()%>)"> 삭제 </button>
					<%} %>
					</td>
				</tr>
			
			<tr> <!-- 대댓글 수정창 -->
				<td> </td>
				<td colspan="2" id=<%=rereply.getRno() %> > </td>   <!-- 해당 태그의 id값을 변수로 설정 = 댓글번호 ( 댓글 한개당 1개씩 ) -->
			</tr>
				
				
			<%  }  } %>
		</table>
		
	</div>
		
	<br><br>
	
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script> 
	<script src="/HEYUM/js/board_view.js" type="text/javascript"></script>
	
	
	<%@include file ="../footer.jsp" %>
	
</body>
</html>