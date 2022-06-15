<%@page import="org.json.JSONObject"%>
<%@page import="java.text.ParseException"%>
<%@page import="dao.Board_Dao"%>
<%@page import="dto.FreeBoard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="/HEYUM/css/board_list.css" rel="stylesheet">
	
</head>
<body>

	<%@include file="../header.jsp" %>
	
<!-- //////////////////////// 검색 처리 //////////////////////// -->	
	
	<%
			request.setCharacterEncoding("UTF-8");
			String key = request.getParameter("key");
			String keyword = request.getParameter("keyword");
										
			int category = Integer.parseInt(request.getParameter("category"));
							
							
							// 검색이 있을 경우
				if(key!=null && keyword !=null){
					session.setAttribute("key", key); // 세션설정 [세션명,세션데이터]	
					session.setAttribute("keyword", keyword);
					session.setAttribute("category", category);
				}else{ // 검색이 없을 경우
					key = (String)session.getAttribute("key"); // 세션 호출 [세션명 -> 세션데이터]
					keyword = (String)session.getAttribute("keyword"); // 세션 호출 [세션명 -> 세션데이터]
					session.setAttribute("category", category); 
				}
			%>
	
<%
	 //1. 게시물 전체 갯수 ( 최종 페이지 구하기)
	 	 	 	
	 	 	 	int totalrow = Board_Dao.boardDao.gettotalrow(key, keyword,category);
				
	 	 	 	int offernum = 5;
	 	 	 
	 	 	 	//2. 현재 페이지 번호
	 	 	 	int currentpage = 1;
	 	 	 		// *요청받은 페이지 번호 ()<- 페이지 입력 )
	 	 	 		String pagenum = request.getParameter("pagenum");
	 	 	 		if(pagenum==null){ // 만약에 페이지 요청이 없으면 1페이지
	 	 	 	currentpage = 1 ;
	 	 	 		}else{
	 	 	 	currentpage = Integer.parseInt(pagenum); // 요청된 페이지번호 -> 정수형 변환 => 현재페이지 설정
	 	 	 		}
	 	 	 	
	 	 	 	
	 	 	 	//3. 페이지당 게시물 표시할 갯수
	 	 	 	int listsize= 10; // 추후에 사용자에게 혹은 관리자에게 입력 받기 
	 	 	 	
	 	 	 	// 4. 페이지당 게시물의 시작번호
	 	 	 	int startrow = (currentpage-1)*listsize;
	 	 	 		// 1v페이지 = > 0 // 2페이지 -> 5 // 3페이지 -> 10~~~
	 	 	 	
	 	 	 	//5.  마지막 페이지 번호
	 	 	 	int lastpage; //
	 	 	 	if(totalrow % listsize==0){ // 나머지가 없으면 [전체 게시물 /화면표시게시물 수 나누기 했을 때 나머지가 0이면]
	 	 	 	lastpage = totalrow/listsize;
	 	 	 	}else{
	 	 	 		lastpage = totalrow/listsize+1; // 나머지가 있으면 나머지 게시물을 표시 할 페이지 +1
	 	 	 	}
	 	 	 	
	 	 	 	// 6-1 : 페이징 표시 갯수
	 	 	 	int btnsize = 5; // 추후 관리자 입력
	 	 	 	
	 	 	 	// 6-2. 페이징 버튼의 시작 번호
	 	 	 	int startbtn = ( (currentpage-1) / btnsize ) * btnsize +1;
	 	 	 	// 예) currentpage가 1 : -> 1-1->0/5->0*5=>0 +1 => 1
	 	 	 		//  currentpage 2 : -> 1  ---------> 1
	 	 	 		//  currentpage 5 : -> 5  ---------> 1
	 	 	 		//  currentpage 6 : 6-1 -> 5/5 -> 1 * 5 -> 5+1 -> 6
	 	 	 		//  currentpage 10 : 10-1 -> 9/5 -> 1*5 -> 5+1 -> 6
	 	 	 		//  currentpage 12 : 12-1 ->11/5 -> 2*5 -> 10+1 -> 11
	 	 	 	// 7. 페이징 버튼의 끝번호
	 	 	 	int endbtn = startbtn+(btnsize-1);
	 	 	 	if(endbtn>lastpage ) endbtn=lastpage; 
	 	 	 	// 만약 페이징 버튼이 마지막 페이지 번호 보다 커지면 페이징 버튼의 마지막 페이지 번호 설정
	 	 	 	
	 	 	 	
	 	 	 	
	 	 	 	// 1. 모든 게시물  호출 
	 	 	 	ArrayList<FreeBoard> boardlist = 
	 	 	 		Board_Dao.boardDao.getboardlist(startrow, listsize, key, keyword,category);
	 	 	 
	 	 	 	ArrayList<FreeBoard> offerpostlist =  // 추천 수 많은 글들(5개만)
	 	 	 			Board_Dao.boardDao.getofferlist(offernum,category);
	 	 	 	ArrayList<FreeBoard> viewpostlist =  // 조회 수 많은 글들(5개만)
	 	 	 			Board_Dao.boardDao.getviewlist(offernum,category);
			
	 	 	 	
	 %>	 
	

	<div class="container"> <!-- 메인 컨테이너 -->
	
		<div class="col-md-12 title">
			<div class="class-md-6">
				<div class="class-md-1">
					<h3 class="title_name text-center"> <% if(category==1){ %>공 지 사 항 <%}
					else if(category==2){%>팁과 노하우<%}
					else if(category==3){%>질문과 답변<%}
					else {%> 자유 게시판 <%} %></h3>
				</div>
			</div>
		</div>
		<br>
		
		<div class="col-md-12 subtitle"> <!--  서브 타이틀 -->
			<div> 
				<br>
				<div class="row">
					<div class="col-md-6">
						<h2> 최다 추천 글</h2>
						<ol class="toptitle">
							
							<%for(FreeBoard olist : offerpostlist){	if(olist.getBoffer()!=0){	
								int replycount_olist = Board_Dao.boardDao.getReplyCount(olist.getBno());
							%>
						  	<li> <a href="board_view.jsp?bno=<%=olist.getBno()%>&category=<%=category%>&pagenum=<%=currentpage%>">&nbsp;&nbsp; 
						   <% if(olist.getBtitle().length() >40){%>
						   <%=olist.getBtitle().substring(0, 40)%>...
						   <%}else{%>
						   <%=olist.getBtitle()%>
						   <%} %>
						   <span style="color:blue;">
						   <%if(replycount_olist!=0){ %>
						   [<%=replycount_olist%>]
						   <%} %>
						   </span></a></li>
							<%}} %>
						</ol>
					</div>
					<div class="col-md-6">
						<h2> 최다 조회 글</h2>
						<ol class="toptitle">			
							<%for(FreeBoard vlist : viewpostlist){ if(vlist.getBview()!=0){		
								int replycount_vlist = Board_Dao.boardDao.getReplyCount(vlist.getBno());
							%>
						  	<li> <a href="board_view.jsp?bno=<%=vlist.getBno()%>&category=<%=category%>&pagenum=<%=currentpage%>">&nbsp;&nbsp;  
						    <%	if(vlist.getBtitle().length() >40){%>
						    <%=vlist.getBtitle().substring(0, 40)%>...
						    <%}else{%>
						   	<%=vlist.getBtitle()%>
						   	<%} %>
						   	<span style="color :blue;">
						   	<%if(replycount_vlist!=0){ %>
						   	[<%=replycount_vlist%>]
						   	<%} %>
						   	</span></a></li>
							<%}}%>
						</ol>
					</div>
				</div>
			</div>			
		</div>
		<br><br>
		
		
		
		<div class="main_board"> <!-- 게시판 메인  -->
			
			<div class="row choise_button">
			
			</div>
			
			<br>
			
			<div>
			
			<!--   전체 | 공지 | 점검 | 패치 | 일반 | 기타  : 소분류 - 미구현  -->
			
			</div>
			<br>
			<div>
				<table class="table table-hover text-center"> <!-- 테이블 -->
					<tr>
						<th width="5%"> 번호 </th> 
						<th width="50%"> 제목 </th> 
						<th width="15%"> 글쓴이 </th> 
						<th width="10%"> 조회 </th> 
						<th width="10%"> 등록일 </th> 
						<th width="10%"> 추천 </th> 
					</tr>
					
					<%
						for(FreeBoard board : boardlist){
					    int replycount = Board_Dao.boardDao.getReplyCount(board.getBno());
									
					%>
					
					<tr class="board_list">
						<td> <%=board.getBno() %> </td> <!-- 게시물 번호 -->
						<td class="loard_list_title" onclick="location.href='board_view.jsp?bno=<%=board.getBno()%>&category=<%=category %>&pagenum=<%=currentpage%>'" style="text-align: left; cursor:pointer;"> <!-- 게시물 제목 -->
							
						<%	if(board.getBtitle().length() >40){%>	<%=board.getBtitle().substring(0, 40)%>...<%}else{%>
						   	<%=board.getBtitle()%> <%} %> 	<span style="color :blue;"><%if(replycount!=0){ %> [<%=replycount%>]<%} %></span> 
					
						</td> 
						<td onclick="location.href='board_view.jsp?bno=<%=board.getBno()%>&category=<%=category %>&pagenum=<%=currentpage%>'" style=" cursor:pointer; text-align: center;"> 
							<%=board.getMid() %>  
						</td> <!-- 게시물 작성자 -->
						<td onclick="location.href='board_view.jsp?bno=<%=board.getBno()%>&category=<%=category %>&pagenum=<%=currentpage%>'" style= "cursor:pointer; text-align: center;">
							<%=board.getBview() %> 
						</td>  <!-- 게시물 조회수 -->
						<td onclick="location.href='board_view.jsp?bno=<%=board.getBno()%>&category=<%=category %>&pagenum=<%=currentpage%>'" style=" cursor:pointer; text-align: center;">
							<%=board.getBdate() %> 
						</td> <!-- 게시물 등록일 -->
						<td onclick="location.href='board_view.jsp?bno=<%=board.getBno()%>&category=<%=category %>&pagenum=<%=currentpage%>'" style= "cursor:pointer; text-align: center;"> 
							<%=board.getBoffer() %> 
						</td>  <!-- 게시물 추천수 -->
					</tr>
					<%
						}
					%>
					
					
					
			</table>
			</div>
		</div>

	
	  <!--------------------------  페이지 입력 ---------------------- -->
	  <div class="col-md-1 offset-9"> 
	  
	  </div>
	  <br> 
	  <div class= "row">
	  <!-- 부트스트랩 버전 -->
	 <div class= "col-md-4 offset-4 d-flex justify-content-center ">
	 								<!-- d-flex justify-content-center: 박스권 내에서 센터로 이동 -->
	 	<ul class="pagination">
	 	<!-- 이전 버튼> -->
	 	<%if(currentpage==1) {%>
	 		<li class="page-item disabled" >
	 			<a class="page-link pagenum" href="board_list.jsp?category=<%=category%>"> 
	 			이전</a> 
 			</li>
		<%} else{ %>
				<li class="page-item">
	 			<a class="page-link pagenum" href="board_list.jsp?category=<%=category%>&pagenum=<%=currentpage-1%>"> 
	 			이전</a> 
				</li>
		<%} %>
 			<% for (int i = startbtn; i<=endbtn; i++){ %>
		 		<li class="page-item"><a class="page-link pagenum" href="board_list.jsp?category=<%=category%>&pagenum=<%=i%>"> 
		 			<%=i %></a> 
	 			</li>
	 		<%} %>
	 		<!-- 다음 버튼> -->
	 	<% if(currentpage == lastpage && currentpage>=endbtn) {%>	
	 		<li class="page-item disabled"><a class="page-link pagenum" href="board_list.jsp?category=<%=category%>"> 다음</a> </li>
	 		<%}else{ %>
	 		<li class="page-item"><a class="page-link pagenum" href="board_list.jsp?category=<%=category%>&pagenum=<%=currentpage+1%>"> 다음</a> </li>
	 		<%} 
	 		%>
		</ul>
		</div>
		<!-- ---- -->
		<div class="col-md-1 offset-3 write_thing">
			<%
			
			if(category == 1){
				if(session.getAttribute("Login")!= null){
					
					Account account2 = (Account)session.getAttribute("Login");
				
					int acountType = account2.getAc_type();
					
			
					if(acountType == 0){%>
						<a href="board_write.jsp?category=<%=category%>"> <button class="form-control" > 글쓰기 </button></a>
					<%}
					
				}
			}else{
				if( session.getAttribute("Login") != null ){
					Account account2 = (Account)session.getAttribute("Login");
			
					
			%>
				<a href="board_write.jsp?category=<%=category%>"> <button class="form-control" > 글쓰기 </button></a>
				<%
 				}else{
 			%>	<input type="button" class="form-control " value=" 글쓰기 " onclick="nonlogin()"> <span class="write_hover">로그인 후 작성이 가능합니다</span>
 				
			<%
				}
			%>	
			<%}%>
		
			</div>
		
		
		
		
	 </div>
	
	
	
	
	<!-- ---------------------------검색 입력 구역 -->

		
		<form action="board_list.jsp?category=<%=category%>&pagenum=<%=currentpage%>&key=<%=key%>&keyword=<%=keyword%>" class="col-md-4 offset-4 d-flex justify-content-center">
			<div class="col-md-3 " > <!-- 키워드 선택 -->
				<select class="form-select" name="key">
					<option value="btitle"> 제목 </option> <!--  key = 필드명 -->
					<option value="bcontent"> 내용 </option>
					<option value="ac_id"> 작성자 </option>
				</select>
			</div>
			
			<div class="col-md-5"> <!-- 검색 =키워드 입력창 -->
				<input type="text" class="form-control" name="keyword"> <!-- 키워드 명 -->
			</div>
			<div class="col-md-2"> <!-- 검색 버튼 -->
				<input type="submit" class="form-control" value="검색">
			</div>
		<input type="text" hidden="true" class="form-control" name="category" value="<%=category%>"> 
		<input hidden="true" type="text" class="form-control" name="pagenum" value="<%=currentpage%>">
		</form> 
	
	

	


	</div>  <!-- main container </div> -->
		
	<br><br><br><br><br>
	
	<script type="text/javascript" src="/HEYUM/js/board_list.js"></script>
	
	<%@include file="../footer.jsp" %>
	
</body>
</html>