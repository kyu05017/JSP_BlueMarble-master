<%@page import="dto.Account"%>
<%@page import="dao.Member_Dao"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dao.PlayerDao"%>
<%@page import="dto.Player"%>
<%@page import="dao.GameboardDao"%>
<%@page import="dto.Gameboard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- 사용자정의 css -->
	<!-- 부트스트랩 CSS cdn -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- 폰트어썸[ 아이콘 ]  -->
	<link href="/HEYUM/css/gameboard.css" rel="stylesheet">
	<link href="/HEYUM/css/gameboard2.css" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
</head>
<body class="bg">
	<%
	//int groom_no = 1;
	int groom_no = Integer.parseInt(request.getParameter("groom_no"));
	DecimalFormat decimalFormat = new DecimalFormat("#,###원");
	Account account = (Account)session.getAttribute("Login");
	%>
	<input type="text" id="ac_no" hidden="" value=<%=account.getAc_no()%>>
	
<div class="row"></div>

<div class="container d-flex justify-content-center">
<div id="gametable">
</div>
</div>


	

<div id="playerinfo1" class="playerinfo"></div>
<div id="playerinfo2" class="playerinfo"></div>
<div id="playerinfo3" class="playerinfo"></div>
<div id="playerinfo4" class="playerinfo"></div>


<div id="dicetable2"><img height="70px" id="dice1" alt="" src="../img/game/white.png"><img height="70px" id="dice2" alt="" src="../img/game/white.png"></div>
<div id="dicetable" class="diceplace"></div>


<div style="border: 1px solid black; overflow-y:scroll; height : 200px; " id="tablescroll" >
		<span id="gamemsg"></span>
</div>
<div style="border: 1px solid black; overflow-y:scroll; height : 200px; " id="tablescroll2" >
		<span id="gamemsg2"></span>
</div>

<div class="row mt-2 g-0" style="width: 400px;" id="chbox">
		<div class="col-md-2">
			<div class="btn-group dropup" >
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton12" data-bs-toggle="dropdown" aria-expanded="false">
					 &#128541 
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton12" style="width: 480px">
					<li>
						<img src="/HEYUM/img/imoji/kk1.gif" width="90px" onclick="gifemo(1,'<%=account.getAc_nickname()%>')">
						<img src="/HEYUM/img/imoji/kk2.gif" width="90px" onclick="gifemo(2,'<%=account.getAc_nickname()%>')">
						<img src="/HEYUM/img/imoji/kk3.gif" width="90px" onclick="gifemo(3,'<%=account.getAc_nickname()%>')">
						<img src="/HEYUM/img/imoji/kk4.gif" width="90px" onclick="gifemo(4,'<%=account.getAc_nickname()%>')">
						<img src="/HEYUM/img/imoji/kk5.gif" width="90px" onclick="gifemo(5,'<%=account.getAc_nickname()%>')">
					</li>
					<li>
						<img src="/HEYUM/img/imoji/kk6.gif" width="90px" onclick="gifemo(6,'<%=account.getAc_nickname()%>')">
						<img src="/HEYUM/img/imoji/kk7.gif" width="90px" onclick="gifemo(7,'<%=account.getAc_nickname()%>')">
						<img src="/HEYUM/img/imoji/kk8.gif" width="90px" onclick="gifemo(8,'<%=account.getAc_nickname()%>')"> 
						<img src="/HEYUM/img/imoji/kk9.gif" width="90px" onclick="gifemo(9,'<%=account.getAc_nickname()%>')">
						<img src="/HEYUM/img/imoji/kk10.gif" width="90px" onclick="gifemo(10,'<%=account.getAc_nickname()%>')">
					</li>
					<li>
						<img src="/HEYUM/img/imoji/kk11.gif" width="90px" onclick="gifemo(11,'<%=account.getAc_nickname()%>')">
						<img src="/HEYUM/img/imoji/kk12.gif" width="90px" onclick="gifemo(12,'<%=account.getAc_nickname()%>')">
						<img src="/HEYUM/img/imoji/kk13.gif" width="90px" onclick="gifemo(13,'<%=account.getAc_nickname()%>')">
						<img src="/HEYUM/img/imoji/kk14.gif" width="90px" onclick="gifemo(14,'<%=account.getAc_nickname()%>')">
						<img src="/HEYUM/img/imoji/kk15.gif" width="90px" onclick="gifemo(15,'<%=account.getAc_nickname()%>')">
					</li>
				</ul>
			</div>
		</div>
		<div class="col-md-8">
			<input type="text" class="form-control" id="msginput22">
		</div>
		<div class="col-md-2">
			<input type="text" value="<%=account.getAc_nickname()%>" hidden="" id="mymymynick">
			<button class="form-control" type="button" onclick="sendmsg22('<%=account.getAc_nickname()%>')">전송</button>
		</div>
	</div>
	
<div id="logotable" class="logotable"><img width="100%" alt="" src="../img/game/charlogo.png"> </div>




	
	<!-- NotEnoughMoneyModal -->
	<div class="modal" tabindex="-1" id="nemmodal" data-bs-backdrop="static" data-bs-keyboard="false">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">돈이 부족합니다.</h5>
	      </div>
	      <div class="modal-body"> <!-- 모달 바디 --> 
		        <div id="moremoney"></div>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-primary"  onclick="pay()">지불</button>
	     	<button type="button" class="btn btn-primary" onclick="sellcity()">도시판매</button>
	     	<button type="button" class="btn btn-secondary"  data-bs-dismiss="modal" onclick="bankruptcy()">게임포기</button>
	        <button type="button" hidden="" id="modalclosebtn4"  data-bs-dismiss="modal"></button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<!-- FeeModal -->
	<div class="modal" tabindex="-1" id="feemodal" data-bs-backdrop="static" data-bs-keyboard="false">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">통행료 지불</h5>	<!-- 모달 헤더 -->
	      </div>
	      <div class="modal-body">
	      	<span id="city_name"></span>
	        <p>지불할 통행료 :<span id="fee"></span> 원</p>	<!-- 모달 바디 -->
	        <input type="hidden" id="modalinputfee">
	      </div>
	      <div class="modal-footer">	<!-- 모달 푸터 -->
	      	<button type="button" class="btn btn-primary" onclick="fee()">지불</button>
	        <button type="button" id="giveup" class="btn btn-secondary" data-bs-dismiss="modal" onclick="bankruptcy()">게임포기</button>
	        <button type="button" hidden="" id="modalclosebtn" data-bs-dismiss="modal"></button>
	        
	      </div>
	    </div>
	  </div>
	</div>


	<!-- BuyModal -->
	<div class="modal" tabindex="-1" id="buymodal" data-bs-backdrop="static" data-bs-keyboard="false">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">도시 구매</h5>
	      </div>
	      <div class="modal-body"> <!-- 모달 바디 --> 
		        <input type="hidden" id="modalinputgn">
		        <div id="modal_contents"></div>
	      </div>
	      <div class="modal-footer">
	     	<button type="button" class="btn btn-primary" onclick="buy()">구매</button>
	     	<button type="button" class="btn btn-secondary" onclick="cancelbtn()" >취소</button>
	        <button type="button" hidden="" id="modalclosebtn2" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	
	
	<!-- SellModal -->
	<div class="modal" tabindex="-1" id="sellmodal" data-bs-backdrop="static" data-bs-keyboard="false">
	  <div class="modal-dialog modal-lg modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">도시 판매</h5>
	      </div>
	      <div class="modal-body"> <!-- 모달 바디 --> 
		        <input type="hidden" id="modalinputgn">
		        <div id="mycityinfo"></div>
	      </div>
	      <div class="modal-footer">
	     	<button type="button" class="btn btn-primary" onclick="sell()">선택된 도시판매</button>
	        <button type="button" id="modalclosebtn3" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
	      </div>
	    </div>
	  </div>
	</div>	
	
	<!-- GoldkeyModal -->
	<div class="modal" tabindex="-1" id="goldkeymodal" data-bs-backdrop="static" data-bs-keyboard="false">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel"><span id="gk_name"></span></h5>
	      </div>
	      <div class="modal-body"> <!-- 모달 바디 --> 
		        <span id="gk_content"></span>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-primary" onclick="goldkeyok()">확인</button>
	        <button type="button" hidden="" id="modalclosebtn5" class="btn btn-secondary" data-bs-dismiss="modal">확인</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- itmeModal -->
	<div class="modal" tabindex="-1" id="itemmodal" data-bs-backdrop="static" data-bs-keyboard="false">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">무인도 탈출용 무전기</h5>
	      </div>
	      <div class="modal-body"> <!-- 모달 바디 --> 
		        무인도 탈출용 무전기를 사용해서 탈출 하시겠습니까?
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-primary" onclick="useitem()">사용</button>
	        <button type="button" id="modalclosebtn6" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	


	<!-- swal cdn -->
	<script type="text/javascript" src = "/HEYUM/js/sweetalert.js"></script>
	<!-- 부트스트랩 JavaScript cdn -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- jQuery 최신 -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="/HEYUM/js/gameboard.js" type="text/javascript"></script>
	<script src="/HEYUM/js/gameboard2.js" type="text/javascript"></script>
	
	
</body>
</html>