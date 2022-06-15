<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<style type="text/css">

		body{
		
		  font-family: sans-serif;
		
		}
		
		
		
		.input_group{
		
		  position: relative;
		
		  padding-top: 13px;
		
		}
		
		
		
		.input_group input{
		
		  border: 1px solid lightgrey;
		
		  border-radius: 5px;
		
		  outline: none;
		
		  min-width: 400px;
		
		  padding: 15px 20px;
		
		  font-size: 16px;
		
		  transition: all .1s linear;
		
		  -webkit-transition: all .1s linear;
		
		  -moz-transition: all .1s linear;
		
		  -webkit-appearance:none;
		
		}
		
		
		
		.input_group input:focus{
		
		  border: 2px solid #3951b2;
		
		}
		
		
		
		.input_group input::placeholder{
		
		  color:transparent;
		
		}
		
		
		
		.input_group label{
		
		  pointer-events: none;
		
		  position: absolute;
		
		  top: calc(50% - 8px);
		
		  left: 15px;
		
		  transition: all .1s linear;
		
		  -webkit-transition: all .1s linear;
		
		  -moz-transition: all .1s linear;
		
		  background-color: white;
		
		  padding: 5px;
		
		  box-sizing: border-box;
		
		}
		
		
		
		.input_group input:required:invalid + label{
		
		  color: red;
		
		}
		
		.input_group input:focus:required:invalid{
		
		  border: 2px solid red;
		
		}
		
		.input_group input:required:invalid + label:before{
		
		  content: '*';
		
		}
		
		.input_group input:focus + label,
		
		.input_group input:not(:placeholder-shown) + label{
		
		  font-size: 13px;
		
		  top: 0;
		
		  color: #3951b2;
		
		.login_btn{}
		
		
		}
		
		.login_btn{
			margin-left : 10px;
		}
	</style>
</head>
<body>
	<%@include file="../header.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				
			</div>
			<div class="col-md-6">
				
			</div>

			<br>
			
			<div class="col-md-4 offset-4">
				<div class="text-center my-5">
					<img alt="" src="/HEYUM/img/main/logo.png"> 
				</div>
				<div class="input_group">
				
				  <input type="text" maxlength="15" placeholder=" " name="mid" id="loginid">
				
				  <label>아이디</label>
				
				</div>
				<div class="input_group">
				
				  <input type="password" maxlength="20" placeholder=" " required name="mpassword" id="password" >
				
				  <label>비밀번호</label>
				
				</div>
				<div class="my-2 login_btn" >
					<div class="row">
						<div class="col-md-4 offset-2">
							<button type="button" class="form-control my-3 text-center" style="height:50px; width: 231.19px; background-color: #56FAB4; color:gray; font-weight: bold" onclick="login()" >로그인</button>
						</div>
					</div>
				</div>
				<div class="my-2 login_btn">
					<div class="row">
						<div class="col-md-4 offset-2">
							<div id="naverIdLogin"></div>
							<div id="naver_id_login"></div>
						</div>
					</div>
				</div>
				<div class="my-5 d-flex offset-2 login_btn"> 
					<span> <a href="/HEYUM/member/signupagree.jsp"> <button class="btn"> 회원가입 </button> </a> </span>
					<span> <a href="/HEYUM/member/find.jsp"><button class="btn"> 아이디 / 비밀번호 찾기 </button> </a> </span>
				</div>
			</div>

		</div>
	</div>

	
	<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId( {
	    clientId: "_JlvGjsbYtMnwNDpCsCk",
	    callbackUrl: "http://192.168.17.131:8080/HEYUM/member/collback.jsp",
	    isPopup: false, /* 팝업을 통한 연동처리 여부 */
	    loginButton: {color: "green", type: 4, height: 50} /* 로그인 버튼의 타입을 지정 */
	} ); /* 설정정보를 초기화하고 연동을 준비 */
	naverLogin.init();
  </script>
  <script src="/HEYUM/js/login.js"></script>
  <%@include file="../footer.jsp" %>
</body>
</html>