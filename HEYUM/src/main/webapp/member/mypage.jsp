<%@page import="dto.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<%@include file="../header.jsp"%>	
	<div style="display: none"><input id="my_num" hidden="" readonly="readonly" value="<%=account.getAc_no()%>"></div>
	<br>
	<br>
	<br>
	<div class="container" >
		<div class="row">
			<div class="col-md-3"  >
				<div class="row">
				
					<div class="col-md-4"></div>
					<div class="col-md-8"></div>
					
					<!--  -->
					
					<div class="col-md-3"></div>
					<div class="col-md-6 text-center" id="my_profile">

						<img id="now_pro" class="rounded-circle" width="80%" data-bs-toggle="modal" data-bs-target="#exampleModal2">

						<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						        <img id="now_pro2" width="80%">
						      </div>
						      <div class="modal-footer">
						      </div>
						    </div>
						  </div>
						</div>
						<br>
						<span id="my_name"> 이름 </span>
						<span id="my_email"> 이메일 </span>
					</div>
					<div class="col-md-3"></div> 
					
					<!--  -->
					<div class="col-md-12 my-3">
						<h4 class="text-center"> </h4>
						<ul class="list-group text-center my-3 sdmenu">
							 <li  class="list-group-item" onclick="pagechange('/HEYUM/member/my_info')">  내 정보 </li>
							 <li  class="list-group-item" onclick="pagechange('/HEYUM/member/myboard')">  내가쓴글 </li>
							 <li  class="list-group-item" onclick="pagechange('/HEYUM/member/myquestion')">  내 문의 </li>
						</ul>
					</div>
					
				</div>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-8" id="mainbox" >
				
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
	
	<script src="/HEYUM/js/mypage.js"></script>
</body>
</html>