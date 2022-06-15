<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link href="/HEYUM/css/signup.css" rel="stylesheet">
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
		
		}
	</style>
</head>
<body>


	<%@include file="../header.jsp" %>
	
	<div class="container">
		<div class="col-md-12">
	
		</div>
		<br>
		<form onsubmit="return false"><!-- onsubmit="return false" : 예외처리 ok 시 true 변경, 전송 -->
			<div class="row">
				<div class="col-md-6 offset-3">
				
					<h5>이메일</h5>
					
						<div class="row">
							<div class="col-md-9">
								 <div class="input_group">
				
								  <input type="text" maxlength="30" placeholder=" "id="input-email" >
								
								  <label>이메일</label>
								
								</div>
							</div>
							<div class="col-md-3 my-3">
								<input type="button" class="form-control " id="checkmail-send" value="인증메일 발송" onclick="certification_mail()" readonly>
								<input type="button" class="form-control " id="checkmail-send2" value="인증메일 발송" readonly>
						 	</div>
					 	</div>
					 	<div > 
					 		<input type="text" class="form-control check-object" id="checkmail-value" value="" disabled>
					 	</div>
						<h5>인증 번호</h5>
						<div class=" row">
						
							<div class="col-md-9">
							
								<div class="input_group">
				
								  <input type="text" maxlength="15" placeholder=" "id="check-number" readonly >
								
								  <label>인증번호</label>
								
								</div>
							</div>
							
							<div class="col-md-3 my-3"> 
								<input type="button" class="form-control " value="인증번호 확인" id="check-number-btn" readonly onclick="certification_number()">
								<input type="button" class="form-control " id="checkmail-send3" value="인증번호 확인" readonly>
						 	</div>
						 	<div>
							 	<input type="text" class="form-control check-object" id="checknumber-value"  value="" disabled >
						 	</div>
			
						<h5>아이디</h5> 
							<div class="col-md-9">

								<div class="input_group">
				
								  <input type="text" maxlength="15" placeholder=" "id="member"readonly >
								  <label>아이디</label>
								</div>
							</div>
							<div class="col-md-3 my-3"> 
								<input type="button" class="form-control " value="중복확인" id="check-id-btn" readonly onclick="certification_id()">
								<input type="button" class="form-control " value="중복확인" id="check-id-btn2" readonly>
						 	</div>
							<div>
								<input type="text" class="form-control check-object" id="checkid-value"  value="" disabled >
							</div>
						<h5>닉네임</h5> 
							<div class="col-md-9">

								<div class="input_group">
				
								  <input type="text" maxlength="15" placeholder=" "id="member_nick" >
								  <label>닉네임</label>
								</div>
							</div>
							<div class="col-md-3 my-3"> 
								<input type="button" class="form-control " value="중복확인" id="check-nick-btn" readonly onclick="certification_nick()">
								<input type="button" class="form-control " value="중복확인" id="check-nick-btn2" readonly>
						 	</div>
							<div>
								<input type="text" class="form-control check-object" id="checknick-value"  value="" disabled >
							</div>
															
						<h5>비밀번호</h5>
						<div class="col-md-9">
							<div class="input_group">
							
							  <input type="password" maxlength="15" placeholder=" " required id="mpassword">
							
							  <label>비밀번호</label>
							
							</div>
						</div>
						<div class="col-md-3 my-4"> 
							<span id="pw_danger" style="color: red; font-weight: bold;">위험함</span>
  							<span id="pw_week" style="color: orange; font-weight: bold; display: none">취약함</span>
  							<span id="pw_worry" style="color: orange; font-weight: bold; display: none">보통</span>
  							<span id="pw_ok" style="color: green; font-weight: bold; display: none">안전함</span>
						</div>
						<div>
						<input type="text" class="form-control check-object" id="checkpassword-value"  value="" disabled style="color: white;">
						</div>
						
						<h5>비밀번호확인</h5> 
						<div class="col-md-9">
							<div class="input_group">
							
							  <input type="password" maxlength="15" placeholder=" " required id="recheckpassword">
							
							  <label>비밀번호 확인</label>
							
							</div>
						</div>
						<div class="col-md-3 my-3"> 
						
						</div>
						<div>
							<input type="text" class="form-control check-object" id="recheckpassword-value"  value=""  disabled>
						</div>
						<br>
						
						<h5>이름</h5> 
						<div>						
							<div class="input_group">
								  <input type="text" maxlength="15" placeholder=" "id="name"  >
								  <label>이름</label>
							</div>
						</div>
						<div>
							<input type="text" class="form-control check-object" id="name_check"  value=""  disabled>
						</div>
						<br>
						
						<h5>핸드폰 번호</h5> 
						<div>
							<div class="input_group">
								  <input type="text" maxlength="13" placeholder=" "id="phone" >
								  <label>핸드폰 번호</label>
							</div>
						</div>
						<div>
							<input type="text" class="form-control check-object" id="phone_check"  value=""  disabled>
						</div>
						<br>
					
					<div>
					<button onclick="sign()" type="button" class="form-control" id="signup1"> 회원 가입</button>
					<button onclick="signup()" type="button" class="form-control" id="signup2"> 회원 가입</button>
					</div>
				</div>
			</div>
		</div> 
		</form>
	</div>
	
	<br><br><br>
	
	
	<%@include file="../footer.jsp" %>
	<script type="text/javascript" src="/HEYUM/js/signup.js"></script>

</body>
</html>