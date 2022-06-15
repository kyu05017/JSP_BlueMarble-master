<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/HEYUM/css/waitingroom.css" rel="stylesheet">
</head>
<body>
<%@include file="../header.jsp" %>
<input hidden="true" id="loginid" value="<%=account.getAc_id() %>">
<input hidden="true" id="loginacno" value="<%=account.getAc_no() %>">

<div class="container">

	<div id="inuserlist" class="inuserlist row my-4">
	
	</div>

	<div>	
		<div class="row mt-5">
		<div id="wchattingbox" class="chattingbox col-md-10 offset-md-1 p-3">
			건전한 대화를 해주시기 바랍니다.<br>
		</div>
		</div>
		<div class="row mt-2 g-0">
		<div class="col-md-1 offset-1">
			<div class="btn-group dropup">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
					이모티콘
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
					<li>
					<button class="btn" onclick="emoji(1)">&#128512</button>
					<button class="btn" onclick="emoji(2)">&#128513</button>
					<button class="btn" onclick="emoji(3)">&#128525</button>
					</li>
					<li>
					<button class="btn" onclick="emoji(4)">&#128526</button>
					<button class="btn" onclick="emoji(5)">&#128541</button>
					<button class="btn" onclick="emoji(6)">&#128545</button>
					</li>
					<li>
					<button class="btn" onclick="emoji(7)">&#128557</button>
					<button class="btn" onclick="emoji(8)">&#128151</button>
					<button class="btn" onclick="emoji(9)">&#128561</button>
					</li>
					<li>
					<button class="btn" onclick="emoji(10)">&#129326</button>
					<button class="btn" onclick="emoji(11)">&#9757</button>
					<button class="btn" onclick="emoji(12)">&#128074</button>
					</li>
					<li>
					<button class="btn" onclick="emoji(13)">&#129321</button>
					<button class="btn" onclick="emoji(14)">&#129324</button>
					<button class="btn" onclick="emoji(15)">&#128149</button>
					</li>
				</ul>
			</div>
		</div>
		<div class="col-md-1">
			<div class="btn-group dropup" >
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton12" data-bs-toggle="dropdown" aria-expanded="false">
					이모티콘
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton12" style="width: 480px">
					<li>
						<img src="/HEYUM/img/imoji/kk1.gif" width="90px" onclick="gifemo(1)">
						<img src="/HEYUM/img/imoji/kk2.gif" width="90px" onclick="gifemo(2)">
						<img src="/HEYUM/img/imoji/kk3.gif" width="90px" onclick="gifemo(3)">
						<img src="/HEYUM/img/imoji/kk4.gif" width="90px" onclick="gifemo(4)">
						<img src="/HEYUM/img/imoji/kk5.gif" width="90px" onclick="gifemo(5)">
					</li>
					<li>
						<img src="/HEYUM/img/imoji/kk6.gif" width="90px" onclick="gifemo(6)">
						<img src="/HEYUM/img/imoji/kk7.gif" width="90px" onclick="gifemo(7)">
						<img src="/HEYUM/img/imoji/kk8.gif" width="90px" onclick="gifemo(8)"> 
						<img src="/HEYUM/img/imoji/kk9.gif" width="90px" onclick="gifemo(9)">
						<img src="/HEYUM/img/imoji/kk10.gif" width="90px" onclick="gifemo(10)">
					</li>
					<li>
						<img src="/HEYUM/img/imoji/kk11.gif" width="90px" onclick="gifemo(11)">
						<img src="/HEYUM/img/imoji/kk12.gif" width="90px" onclick="gifemo(12)">
						<img src="/HEYUM/img/imoji/kk13.gif" width="90px" onclick="gifemo(13)">
						<img src="/HEYUM/img/imoji/kk14.gif" width="90px" onclick="gifemo(14)">
						<img src="/HEYUM/img/imoji/kk15.gif" width="90px" onclick="gifemo(15)">
					</li>
				</ul>
			</div>
		</div>
		<div class="col-md-7">
			<input type="text" class="form-control" id="msginput">
		</div>
		<div class="col-md-1">
			<button class="form-control" type="button" onclick="sendmsg()">전송</button>
		</div>
	</div>
	</div>
</div>
<script src="/HEYUM/js/waitingroom.js" type="text/javascript" ></script>
<%@include file="../footer.jsp" %>
</body>
</html>