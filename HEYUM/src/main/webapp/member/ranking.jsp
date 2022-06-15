<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	img{
		-moz-border-radius: 20px;
		-khtml-border-radius: 20px;
		-webkit-border-radius: 20px;
	}
	#mainrank{
		 position: relative;
		 bottom: 240px;
	}
	#rank22{
		position: relative;
		left : 290px;
	}
	
	#rank33{
		position: relative;
		right : 290px;
	}
	#rank11{
		position: relative;
		bottom : 30px;
	}
</style>
</head>
<body>
<%@include file="../header.jsp" %>
	<div class="container">
		<div class="text-center my-3">
			<img alt="" src="/HEYUM/img/main/rank.png">
			<div id="mainrank">
			<div class="row">
				<div class="col-md-4" id="rank22">
					<img alt="" src="/HEYUM/img/profile/default.jpg" id="pro2" style="width: 70px " class="rounded-circle">
					<h5 id="ranki2"> 아이디2 </h5>
					<h6 id="ranks2"> 점수2 </h6>
				</div>
				<div class="col-md-4" id="rank11">
					<img alt="" src="/HEYUM/img/profile/default.jpg" id="pro1" style="width: 100px " class="rounded-circle">
					<h3 id="ranki1" style="font-weight: bold "> 아이디1 </h3>
					<h5 id="ranks1"> 점수1 </h5>
				</div>
				<div class="col-md-4" id="rank33">
					<img alt="" src="/HEYUM/img/profile/default.jpg" id="pro3" style="width: 70px " class="rounded-circle">
					<h5 id="ranki3"> 아이디2 </h5>
					<h6 id="ranks3"> 점수2 </h6>
				</div>
			</div>
			</div>
		</div>
		<h3> 전체 순위 </h3>
		<br>
		<div class=" text-center">
			<table class="table my" id="ranktable">
			</table>
		</div>
	</div>
<%@include file="../footer.jsp" %>
<script type="text/javascript" src="/HEYUM/js/ranking.js"></script>
</body>
</html>