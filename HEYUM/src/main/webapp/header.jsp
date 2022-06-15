<%@page import="dao.Board_Dao"%>
<%@page import="dto.FreeBoard"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Bcategory"%>
<%@page import="dto.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마블해윰</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- 사용자 정의 css -->
	<link href="/HEYUM/css/main.css" rel="stylesheet">
	
</head>
<body>
	<% 
		Account account = null;
		String loginid;
		if(session.getAttribute("Login") != null){
			account = (Account)session.getAttribute("Login");
			
		}
		
	%>
	 
<div class="container-fluid">
	<div class="container header">
		<div class="row firstrow">
			<div class="col-md-4  text-center">
				<a href="/HEYUM/main.jsp"> <img alt="" src="/HEYUM/img/game/charlogo.png"> </a>

			</div>

			<div class="col-md-4 offset-4 my-4 d-flex justify-content-end">
				
				<ul class="nav">
					<% if(account == null) {%>
					
					<li><a class="hearder-topmenu" href="/HEYUM/member/login.jsp"> <button class="btn btntext" > 로그인 </button></a></li>
					<li><a class="hearder-topmenu" href="/HEYUM/member/signupagree.jsp"> <button class="btn btntext" > 회원가입 </button></a></li>

					<%} else if(account !=null) { %>
					<li style="display: none"><div ><input id="my_num" hidden="" readonly="readonly" value="<%=account.getAc_no()%>"></div></li>
					<li><a class="hearder-topmenu" href="/HEYUM/member/mypage.jsp"> <button class="btn" > 
					<img id="mymypro" alt=""  style="width: 50px " class="rounded-circle">
					<span id="headernick"></span>
					</button> </a> <button class="btn my-2" onclick="logout()"> 로그아웃 </button></li>
					<%}%>
					
					
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="container-fluid secondrow">
	<div class="container">
		<div class="row">
			<div class="my-3 text-center">
				<ul id="headerbar" class="nav d-flex justify-content-between">
					<li><a href="/HEYUM/introduce.jsp">마블 해윰 소개!</a></li>
					<li><a href="/HEYUM/board/board_list.jsp?key=&keyword=&category=1">공지 사항</a></li>
					<li><a href="/HEYUM/board/board_list.jsp?key=&keyword=&category=2">팁과 노하우</a></li>
					<li><a href="/HEYUM/board/board_list.jsp?key=&keyword=&category=3">질문과 답변</a></li>
					<li><a href="/HEYUM/board/board_list.jsp?key=&keyword=&category=4">자유 게시판</a></li>
					<li><a href="/HEYUM/member/ranking.jsp"> 명예의 전당 </a></li>
				</ul>
			</div>

		</div>
	</div>
</div>
	
	<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/locales/de_DE.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/geodata/germanyLow.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/fonts/notosans-sc.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/percent.js"></script>
	<!-- 부트스트랩 js cdn -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script type="text/javascript" src = "/HEYUM/js/sweetalert.js"></script>
	<!-- jquery 최신 cdn -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="/HEYUM/js/header.js"></script>
</body>
</html>