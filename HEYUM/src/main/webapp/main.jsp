<%@page import="dto.Bcategory"%>
<%@page import="dao.Board_Dao"%>
<%@page import="dto.FreeBoard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
	#start:hover {
	  -webkit-transform: scale(0.8);
	  -moz-transform: scale(0.8);
	  -ms-transform: scale(0.8);
	  -o-transform: scale(0.8);
	  transform: scale(0.8);
	}
	</style>
<title>마블해윰</title>
</head>
<body>

	<%@include file="header.jsp" %>
	
	<% 
	int category_notice = 1;
	int category_tip =  2;
	int category_qna = 3;
	int category_free = 4;
	int offernum= 5;
	int hitnum=10;
	//Bcategory bcategory = new Bcategory();

	// 메인 페이지 view 용 게시판 호출
	// 1. 모든 게시물  호출 
		// 메인게시판 4개 최신글
 	 	ArrayList<FreeBoard> main_view_notice= Board_Dao.boardDao.getmainviewlist(offernum, category_notice); 
 	 	ArrayList<FreeBoard> main_view_tip= Board_Dao.boardDao.getmainviewlist(offernum, category_tip);
 		ArrayList<FreeBoard> main_view_qna= Board_Dao.boardDao.getmainviewlist(offernum, category_qna);
 		ArrayList<FreeBoard> main_view_free= Board_Dao.boardDao.getmainviewlist(offernum, category_free);
		
 		// 우측 메뉴 글 추천글 모음(최대10개)
 		ArrayList<FreeBoard> right_view_tip = Board_Dao.boardDao.getofferlist(hitnum,category_tip);
	 	ArrayList<FreeBoard> right_view_qna= Board_Dao.boardDao.getofferlist(hitnum,category_qna);
	 	ArrayList<FreeBoard> right_view_free = Board_Dao.boardDao.getofferlist(hitnum,category_free);
 		
	%>
	
	<div class="container my-3">
		<div class="row">
			<div class="col-md-9">
				<div id="mainslide" class="carousel slide" data-bs-ride="carousel" data-bs-interval="5000">	
					<!-- 슬라이드 하단 위치 버튼 -->
					<div class="carousel-indicators">
						<button type="button" data-bs-target="#mainslide" data-bs-slide-to="0" class="active"></button>
						<button type="button" data-bs-target="#mainslide" data-bs-slide-to="1"></button>
						<button type="button" data-bs-target="#mainslide" data-bs-slide-to="2"></button>
					</div>
					<div class="carousel-inner">	<!-- 이미지 목록 -->
						<div class="carousel-item active">	<!-- 이미지 -->
							<a href="/HEYUM/board/board_view.jsp?key=&keyword=&category=1&bno=165&pagenum=1"><img alt="" src="img/main/bannertest.gif" width="100%"></a>
						</div>
						<div class="carousel-item">	<!-- 이미지 -->
							<a href="/HEYUM/board/board_view.jsp?key=&keyword=&category=1&bno=166&pagenum=1"><img alt="" src="img/main/bannertest2.gif" width="100%"></a>
						</div>
						<div class="carousel-item">	<!-- 이미지 -->
							<a href="/HEYUM/board/board_view.jsp?key=&keyword=&category=1&bno=169&pagenum=1"><img alt="" src="img/main/bannertest3.gif" width="100%"></a>
						</div>
					</div>
					<!-- 왼쪽 이동 버튼 -->
					<button class="carousel-control-prev" type="button" data-bs-target="#mainslide" data-bs-slide="prev">
						<span class="carousel-control-prev-icon"></span>
					</button>
					<!-- 오른쪽 이동 버튼 -->
					<button class="carousel-control-next" type="button" data-bs-target="#mainslide" data-bs-slide="next">
						<span class="carousel-control-next-icon"></span>
					</button>			
				</div>
			</div>
			<div class="col-md-3 ">
				<div class="row">
					<div class="col-md-12">
						<% if(session.getAttribute("Login") != null) {%>
						<br><br>
						<a href="/HEYUM/game/lobby.jsp"> <img id="start" alt="" src="/HEYUM/img/main/gamestart.png" width="100%"> </a>
						<%} else { %>
						<br><br>
						<img id="start" alt="" src="/HEYUM/img/main/gamestart.png" width="100%" onclick="nonlogin()">
						<%} %>
						
					</div>
					<div class="col-md-12 text-center" style="font-weight: bold;">
						<% if(session.getAttribute("Login") != null) {%>
						<%=account.getAc_nickname()%>님 환영 합니다
						<%} else { %>
						환영합니다. <br>
						로그인을 해 주세요
						<%} %>
					</div>
				</div>
			</div>
		</div>
		
		
				
		<div class="row">
		<div class="col-md-9">
		<div class="row ">
			<div class="col-md-5 center_board">	<!-- 게시판1 -->
				<h1 class="text-center"><a href="/HEYUM/board/board_list.jsp?key=&keyword=&category=1"> 공 지 사 항 </a> </h1>
				<table class="table">
				<%for(FreeBoard notice_view : main_view_notice){ %>
						<tr><td>
						<%if(notice_view.getBtitle().length() > 18){ %>
						<h3><a href="/HEYUM/board/board_view.jsp?bno=<%=notice_view.getBno()%>&category=<%=notice_view.getBcateory()%>&pagenum=1"> <%=notice_view.getBtitle().substring(0, 18) %> .....</a></h3>
						<%}else{ %>
						<h3><a href="/HEYUM/board/board_view.jsp?bno=<%=notice_view.getBno()%>&category=<%=notice_view.getBcateory()%>&pagenum=1"> <%=notice_view.getBtitle() %></a></h3>
						<%} %>
						</td></tr>
				<%} %>
				</table>
			</div>
			<div class="col-md-5 offset-1 center_board">	<!-- 게시판2 -->
				<h1 class="text-center"> <a href="/HEYUM/board/board_list.jsp?key=&keyword=&category=2"> 팁과 노하우 </a> </h1>
				<table class="table">
				<%for(FreeBoard tip_view : main_view_tip){ %>
						<tr><td>
						<%if(tip_view.getBtitle().length() > 18){ %>
						<h3><a href="/HEYUM/board/board_view.jsp?bno=<%=tip_view.getBno()%>&category=<%=tip_view.getBcateory()%>&pagenum=1"> <%=tip_view.getBtitle().substring(0, 18) %> .....</a></h3>
						<%}else{ %>
						<h3><a href="/HEYUM/board/board_view.jsp?bno=<%=tip_view.getBno()%>&category=<%=tip_view.getBcateory()%>&pagenum=1"> <%=tip_view.getBtitle() %></a></h3>
						<%} %>
						</td></tr>
				<%} %>
				</table>
			</div>
			</div>
			<div class="row">
			<div class="col-md-5  center_board">	<!-- 게시판3 -->
				<h1 class="text-center"> <a href="/HEYUM/board/board_list.jsp?key=&keyword=&category=3"> 질문과 답변 </a> </h1>
				<table class="table">
					<%for(FreeBoard qna_view : main_view_qna){ %>
						<tr><td>
						<%if(qna_view.getBtitle().length() > 18){ %>
						<h3><a href="/HEYUM/board/board_view.jsp?bno=<%=qna_view.getBno()%>&category=<%=qna_view.getBcateory()%>&pagenum=1"> <%=qna_view.getBtitle().substring(0, 18) %> .....</a></h3>
						<%}else{ %>
						<h3><a href="/HEYUM/board/board_view.jsp?bno=<%=qna_view.getBno()%>&category=<%=qna_view.getBcateory()%>&pagenum=1"> <%=qna_view.getBtitle() %></a></h3>
						<%} %>
						</td></tr>
					<%} %>
				</table>
			</div>
			<div class="col-md-5 offset-1 center_board">	<!-- 게시판4 -->
				<h1 class="text-center"> <a href="/HEYUM/board/board_list.jsp?key=&keyword=&category=4"> 자유 게시판 </a> </h1>
				<table class="table">
					<%for(FreeBoard free_view : main_view_free){ %>
						<tr><td>
						<%if(free_view.getBtitle().length() > 18){ %>
						<h3><a href="/HEYUM/board/board_view.jsp?bno=<%=free_view.getBno()%>&category=<%=free_view.getBcateory()%>&pagenum=1"> <%=free_view.getBtitle().substring(0, 18) %> .....</a></h3>
						<%}else{ %>
						<h3><a href="/HEYUM/board/board_view.jsp?bno=<%=free_view.getBno()%>&category=<%=free_view.getBcateory()%>&pagenum=1"> <%=free_view.getBtitle() %></a></h3>
						<%} %>
						</td></tr>
					<%} %>
				</table>
			</div>
		</div>
		</div>
		
		
		<div class="col-md-3  right_board">	<!-- 우측 메뉴 바 -->
				<div class="right_first">
				<div>
					
					<h4 class="text-center"> 해윰마블 일정 모음 </h4>
				</div>
				<div class="right-info">
					<br>
					<ul style="font-size : 25px;  ">
						<li> 06-07 : 클로즈베타 시작  </li>
						<li> 06-08 : 중간 점검 </li>
						<li> 06-09 : 최종 마무리 </li>
						<li> 06-10 : 오ㅡ픈 !</li>
					</ul>
					
				</div>
				</div>
				
				<div class="right_second">
				<div>
				     <h4 class="text-center"> 최근 핫한 게시글</h4>
				     <br>
				     <div class="row col-md-12">
				     	<div class="col-md-3 offset-1 text-center choise_box" id="tip_box"  style="background-color: #696969;">
				     		팁
				     	</div>
				     	
				     	<div class="col-md-3 offset-1 text-center choise_box" id="qna_box">
				     		Q&A
				     	</div>
				     	
				     	<div class="col-md-3 offset-1 text-center choise_box" id="free_box">
				     		자유
				     	</div>
				     
				     </div>
				     <br>
				     
				     <%for(FreeBoard tip_list : right_view_tip){	if(tip_list.getBoffer()!=0){	%>
					  	
						
						<%}} %>
					
					<%for(FreeBoard qna_list : right_view_tip){	if(qna_list.getBoffer()!=0){	%>
					  	
						
						<%}} %>
					
					<%for(FreeBoard free_list : right_view_tip){	if(free_list.getBoffer()!=0){	%>
					  	
						
						<%}} %>
					
					
												
				     <div>
				     	<table class="view_list">
				     		<tr>
				     			<td>
				     				<!-- 커서가 팁 에 올라갈 때 -->
				     			<ol id="tip_view"  > <%for(FreeBoard tip_list : right_view_tip){	if(tip_list.getBoffer()!=0){ 	%>
					  				<li><a href="/HEYUM/board/board_view.jsp?bno=<%=tip_list.getBno()%>&category=<%=tip_list.getBcateory()%>&pagenum=1"> 
					  				<%if(tip_list.getBtitle().length()>10){ %> <%=tip_list.getBtitle().substring(0, 10)%>...<%}else{%>
					  				<%=tip_list.getBtitle()%> <%} %></a></li>
								<% }}   %>
				     		
				     			</ol>
				     			
				     			
				     			
					     			<!-- 커서가 QNA 에 올라갈 때 -->
					     		<ol id="qna_view" hidden="true"> <%for(FreeBoard qna_list : right_view_qna){	if(qna_list.getBoffer()!=0){	%>
						  			<li><a href="/HEYUM/board/board_view.jsp?bno=<%=qna_list.getBno()%>&category=<%=qna_list.getBcateory()%>&pagenum=1"> 
						  			<%if(qna_list.getBtitle().length()>10){ %> <%=qna_list.getBtitle().substring(0, 10)%>...<%}else{%>
					  				<%=qna_list.getBtitle()%> <%} %></a> </li>
								<% }}   %>
					     		
					     		</ol>
					     		<!-- 커서가 자유 에 올라갈 때 -->
					     		<ol id="free_view" hidden="true"> <%for(FreeBoard free_list : right_view_free){	if(free_list.getBoffer()!=0){	%>
						  			<li><a href="/HEYUM/board/board_view.jsp?bno=<%=free_list.getBno()%>&category=<%=free_list.getBcateory()%>&pagenum=1"> 
						  			<%if(free_list.getBtitle().length()>10){ %> <%=free_list.getBtitle().substring(0, 10)%>...<%}else{%>
					  				<%=free_list.getBtitle()%> <%} %></a> </li>
								<% }}   %>
					     		</ol>
					     			
				     			
				     			</td>
				     		</tr>
				     	</table>
				     		
				     		
				     		
				     </div>
				
				</div>
				
				</div>
				
		</div>
		
		
		</div>
	</div>
	
	<%@include file="footer.jsp" %>


	<script type="text/javascript" src="js/main.js"></script>

</body>
</html>