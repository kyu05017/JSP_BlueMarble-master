<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<%@include file="../header.jsp" %>
<input hidden="true" id="loginid" value="<%=account.getAc_id() %>">
<input hidden="true" id="loginacno" value="<%=account.getAc_no() %>">
<div class="container">
	<div class="row my-5">
	
		<div id="gamelist" class="gamelist col-md-7 offset-md-1">
			<div class="row my-3">
				<div id="gameroombox0" class="gameroom col-md-4 offset-md-1">
					<div id="gameroom0"></div>
					<div id="readylist0"></div>
				</div>
				<div id="gameroombox1" class="gameroom col-md-4 offset-md-1">
					<div id="gameroom1"></div>
					<div id="readylist1"></div>
				</div>
			</div>
			<div class="row my-3">
				<div id="gameroombox2" class="gameroom col-md-4 offset-md-1">
					<div id="gameroom2"></div>
					<div id="readylist2"></div>
				</div>
				<div id="gameroombox3" class="gameroom col-md-4 offset-md-1">
					<div id="gameroom3"></div>
					<div id="readylist3"></div>
				</div>
			</div>
			<div class="row">
			<nav aria-label="Page navigation example">
			  <ul class="pagination justify-content-center">
			    <li class="page-item"><button style="background-color:gray" id="pagebtn1" class="btn btn-white border-dark" type="button" onclick="page(1)">1</button></li>
			    <li class="page-item"><button id="pagebtn2" class="btn btn-white border-dark" type="button" onclick="page(2)">2</button></li>
			    <li class="page-item"><button id="pagebtn3" class="btn btn-white border-dark" type="button" onclick="page(3)">3</button></li>
			    <li class="page-item"><button id="pagebtn4" class="btn btn-white border-dark" type="button" onclick="page(4)">4</button></li>
			    <li class="page-item"><button id="pagebtn5" class="btn btn-white border-dark" type="button" onclick="page(5)">5</button></li>
			  </ul>
			</nav>
			</div>
		
		</div>
		
		<div class="userlist col-md-2 offset-md-1" style="overflow:auto;">
			<table id="waitingmembers" class="table" >
				<thead>
				<tr>
					<th>접속중인 유저목록</th>
				</tr>
				</thead>
				<tr>
				<td>
				<button class="btn"></button>
				</td>
				</tr>		
			</table>
		

		</div>
	</div>
	<div>	
		<div class="row mt-5">
		<div id="chattingbox" class="chattingbox col-md-10 offset-md-1 p-3">
			건전한 대화를 해주시기 바랍니다.<br>
		</div>
		</div>
		<div class="row mt-4 g-0">
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
			<input type="text" class="form-control" id="msginput" maxlength="50">
		</div>
		<div class="col-md-1">
			<button class="form-control" type="button" onclick="sendmsg()">전송</button>
		</div>
	</div>
	</div>
</div>

<script src="/HEYUM/js/lobby.js" type="text/javascript" ></script>
<%@include file="../footer.jsp" %>
</body>
</html>