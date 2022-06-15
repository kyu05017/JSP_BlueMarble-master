<%@page import="dto.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<%
	Account account = null;
	if(session.getAttribute("Login") != null){
		account = (Account)session.getAttribute("Login");
		
	} %>
	<div class="container">
		<div style="display: none"><input id="my_numw2" hidden="" readonly="readonly" value="<%=account.getAc_no()%>"></div>
		<div class="row">
			<h4> 내가 쓴글 </h4>
			<div>
				<table class="table table-hover my-3" id="myboardtable">
				</table>
			</div>
		</div>
	</div>
	<script src="/HEYUM/js/myboard.js"></script>
</body>
</html>