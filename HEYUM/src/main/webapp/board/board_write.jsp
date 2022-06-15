<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/HEYUM/css/board_write.css" rel="stylesheet">
</head>
<body>

	<%@include file="../header.jsp" %>
	
	<%
		request.setCharacterEncoding("UTF-8");
		int category = Integer.parseInt(request.getParameter("category"));
	
	
	%>
	
	<div class="container">
		<br><br>
		<h3> 글 쓰기 </h3>
		<br>
			

		<%
			if(category == 1){
		%>
			<div class="col-md-12 ">
				<div class="row">
						<div class="col-md-2"> 
							<select class="form-select" id="select_notice" style="text-align:center">
								<option value="[공지] "> 공지 </option> <!--  key = 필드명 -->
								<option value="[이벤트] "> 이벤트 </option>
								<option value="[패치 및 업데이트] "> 패치 및 업데이트 </option>
								<option value="[점검] "> 점검 </option>
							</select>	
						
						</div>
						<div class="col-md-10"> 
							<input type="text" maxlength="50" class="form-control" placeholder=" 제 목 " name="btitle" id="btitle">
						</div>
						
				</div>
			</div>
			<%} else{%>
				<div class="col-md-12 ">
				<div class="row">
						<div class="col-md-12"> 
							<input type="text" maxlength="50" class="form-control" placeholder=" 제 목 " name="btitle" id="btitle">
						</div>
						<!-- <div class="col-md-2"> 
							<input type="password" class="form-control" placeholder=" 비밀번호 ">
						</div>  -->
				</div>
			</div>
			<%} %>
			<br>
			
			<br>
		 
			
			<input type="text" class="form-control board_write_notice" value="※ 음란물, 차별, 비하, 혐오 및 초상권, 저작권 침해 게시물은 민, 형사상의 책임을 질 수 있습니다." disabled >
					
			
						
		<br>
		
		
		<div class="editor-menu">
						<button id="btn-bold" class="btn btn-default type=">
					        <b> 진하게 </b>
					    </button>
					    <button id="btn-italic" class="btn btn-default type=">
					        <i> 이탤릭 </i>
					    </button>
					    <button id="btn-underline" class="btn btn-default type=">
					        <u> 밑줄!</u>
					    </button>
					    <button id="btn-strike" class="btn btn-default type=" >
					        <s> 취소선</s>
					    </button>
					    <button id="btn-ordered-list" class="btn btn-default type=">
					        순서목록O
					    </button>
					    <button id="btn-unordered-list"  class="btn btn-default type=">
					        순서목록X
					    </button>
					    <button id="btn-image" class="btn btn-default type=">
					        사진
				    	   </button>
				        <input id="img-selector" type="file" accept="image/*"/>
				        <button id="btn-youtube" class="btn btn-default type="button" onclick="youtube()">
					        유튜브
				    	   </button>
				        
				        
				        
				        
					</div>
		<br>
		<div id="editor" name="bcontent"  contenteditable="true" spellcheck="false"> </div>
				
		<br><br>
		
			<div class="row">
				<div class="col-md-4">&nbsp; 첨부파일 :&nbsp;<span> <input type="file" name="bfile" id="bfile" > </span>
				</div>
				
				<div class="col-md-1 offset-6">
				<% if(category==1){	%>
				<input type="submit" class="form-control" value="등록" onclick="submit_write_admin(<%=category%>)">
				<%}else{ %>
				<input type="submit" class="form-control" value="등록" onclick="submit_write(<%=category%>)">
				<%} %>
				</div>
				<div class="col-md-1">
				<a href="board_list.jsp?key=&keyword=&category=<%=category%>"><input type="button" class="form-control" value="취소" onclick="back()"></a>
				</div>
			</div>
			첨부파일은 10Mb 까지만 가능합니다.
	
	</div>
		
		
		
		
		
		<!-- 사용자 정의 js -->
	<script src="/HEYUM/js/board_write.js" type="text/javascript"></script>
		
		
	
	
	
	



	<br><br><br><br><br>
	<%@include file="../footer.jsp" %>
	


</body>
</html>