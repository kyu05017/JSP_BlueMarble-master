<%@page import="dto.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
#chartdiv2 {
  width: 100%;
  height: 500px;
}
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
	<%
	Account account = null;
	if(session.getAttribute("Login") != null){
		account = (Account)session.getAttribute("Login");
		
	} %>
	<div class="container my-1 py-3">
		<div style="display: none"><input id="my_numw" hidden="" readonly="readonly" value="<%=account.getAc_no()%>"></div>
		<div class="accordion"  id="accordionExample">
		  <div class="accordion-item">
		    <h2 class="accordion-header" id="headingOne">
		      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" id="mymymyinfobtn" aria-expanded="true" aria-controls="collapseOne" style="font-weight: bold">
		        내 정보
		      </button>
		    </h2>
		    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
		      <div class="accordion-body">
		      		<div class="row py-4">
		      			<div class="col-md-6">
		      				닉네임 : 
		      			</div>
		      			<div class="col-md-6">
		      				<span id="my_nick">  </span>
		      				<span id="btn_change"> <button class="btn" onclick="nick_change()">수정</button> </span>
		      				<span id="btn_cancle" style="display: none"> <button class="btn" onclick="nick_change2()">취소</button> </span>
		      				
		      				<div id="nick_change_box" style="display: none" class="my-2">
		      					<div class="row">
		      						<div class="col-md-8">

		      						<div class="input_group">
				
									  <input type="text" placeholder=" "id="now_nickname">
									
									  <label>닉네임</label>
									
									</div>
		      						</div>
		      						<div class="col-md-4 my-3">
		      						<span><button class="btn" onclick="nick_comf()">완료</button> </span>
		      						</div>
		      					</div>
		      				</div>
		      			</div>
		      			
		      			<br><br>

		      			<div class="col-md-6">
		      				이메일 : 
		      			</div>
		      			<div class="col-md-6">
		      				<span id="my_email2">  </span>
		      				<span id="btn_change2"> <button class="btn" onclick="email_change()">수정</button> </span>
		      				<span id="btn_cancle2" style="display: none"> <button class="btn" onclick="email_change2()">취소</button> </span>
		      				
		      				<div id="email_change_box" style="display: none" class="my-2">
		      					<div class="row">
		      						<div class="col-md-8">
		      						<div class="input_group">
				
									  <input type="text" placeholder=" "id="now_email">
									
									  <label>이메일</label>
									
									</div>
		      						</div>
		      						<div class="col-md-4 my-3">
		      						<span><button class="btn" onclick="send()">인증번호</button> </span>
		      						</div>
		      					</div>
		      				</div>
		      				
		      				<div id="email_change_check_box" style="display: none" class="my-2">
		      					<div class="row">
		      						<div class="col-md-8">
		      						<div class="input_group">
				
									  <input type="text" placeholder=" "id="now_check">
									
									  <label>인증번호</label>
									
									</div>
		      						</div>
		      						<div class="col-md-4 my-3">
		      						<span><button class="btn" onclick="e_check()">확인</button> </span>
		      						</div>
		      					</div>
		      				</div>
		      			</div>
		      			
		      			<br><br>
		      			
		      			<div class="col-md-6">
		      				핸드폰 번호 :  
		      			</div>
		      			<div class="col-md-6">
		      				<span id="my_phone">  </span>
		      				<span id="btn_change3"> <button class="btn" onclick="phone_change()">수정</button> </span>
		      				<span id="btn_cancle3" style="display: none"> <button class="btn" onclick="phone_change2()">취소</button> </span>
		      				
		      				<div id="phone_change_box" style="display: none" class="my-2">
		      					<div class="row">
		      						<div class="col-md-8">
		      						<div class="input_group">
				
									  <input type="text" placeholder=" " id="now_phone">
									
									  <label>핸드폰 번호</label>
									
									</div>
		      						</div>
		      						<div class="col-md-4 my-3">
		      						<span><button class="btn" onclick="phone_comf()">완료</button> </span>
		      						</div>
		      					</div>
		      				</div>
		      			</div>
		      			
		      			<br><br>
		      			
		      			<div class="col-md-6">
		      				비밀번호 변경 : 
		      			</div>
		      			<div class="col-md-6">
		      				<span id="btn_change4"> <button class="btn" onclick="pw_change()">변경</button> </span> 
		      				<span id="btn_cancle4" style="display: none"> <button class="btn" onclick="pw_change2()">취소</button> </span>
		      				
		      				<div id="pw_check_box" style="display: none" class="my-2">
		      					<form>
		      					<div class="row">
		      						<div class="col-md-8">
		      						<div class="input_group">
				
									  <input type="password" placeholder=" " required  id="now_pw"  >
									
									  <label>비밀번호</label>
									
									</div>
		      						</div>
		      						<div class="col-md-4 my-3">
		      						<span> <button class="btn" onclick="pw_check_comf()" type="button">확인</button> </span>
		      						</div>
		      					</div>
		      					</form>
		      				</div>
		      				
		      				<div id="pw_new_box" style="display: none" class="my-2">
		      					<form>
		      					<div class="row">
		      						<div class="col-md-8">
		      						<div class="input_group">
				
									  <input type="password" placeholder=" " required  id="new_pw" >
									
									  <label>새 비밀번호</label>
									
									</div>
		      						</div>
		      						<div class="col-md-4">
		      							<span id="pw_danger" style="color: red; font-weight: bold;">위험함</span>
		      							<span id="pw_week" style="color: orange; font-weight: bold; display: none">취약함</span>
		      							<span id="pw_worry" style="color: orange; font-weight: bold; display: none">보통</span>
		      							<span id="pw_ok" style="color: green; font-weight: bold; display: none">안전함</span>
		      						</div>
		      						<div class="col-md-8 my-3">
		      						<div class="input_group">
				
									  <input type="password" placeholder=" " required  id="new_pw_check"  >
									
									  <label>비밀번호 확인</label>
									
									</div>
		      						</div>
		      						<div class="col-md-4 my-3">
		      						<span id="check_false" style="font-weight: bold;"> <button class="btn" onclick="pw_new_comf()" type="button">확인</button> </span>
		      						<span id="check_true" style="display: none">  <button class="btn" onclick="pw_new_comf2()" type="button">확인</button> </span>
		      						</div>
		      					</div>
		      					</form>
		      				</div>
		      				
		      			</div>
		      			
		      			<br><br>
		      			
		      			<div class="col-md-6">
		      				프로필사진 : 
		      			</div>
		      			<div class="col-md-6">
		      				<form id="addprofile">
		      				<span><input type="file" class="form-control" id="pro_img" name="pro_img" accept="image/*"> </span>
		      				<span><input type="button" class="form-control" value="프로필 사진 삭제" onclick="deletepro()"> </span>
		      				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" id="profile_btn" style="display: none" >
							 프로필사진오픈
							</button>
							<!-- Modal -->
							<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLabel"></h5>
							        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" id="close"></button>
							      </div>
							      <div class="modal-body">
							       <img id="preview" width="100%">
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-primary" onclick="add(<%=account.getAc_no()%>)">저장</button>
							        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="pro_cancle()">취소</button>
							      </div>
							    </div>
							  </div>
							</div>
							</form>
		      			</div>
		      		</div>
		      </div>
		    </div>
		  </div>
		  <div class="accordion-item">
		    <h2 class="accordion-header" id="headingTwo">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" id="mymymyre" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" style="font-weight: bold" onclick="getch()">
		        내 전적
		      </button>
		    </h2>
		    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
		      <div class="accordion-body">
		      		<div class="row">
		      			<div class="col-md-4 offset-4">
		      				<div id="chartdiv2"></div>
		      			</div>
		      		</div>
		       </div>
		    </div>
		  </div>
		  <div class="accordion-item">
		    <h2 class="accordion-header" id="headingThree">
		      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree" style="font-weight: bold">
		      	회원 탈퇴
		      </button>
		    </h2>
		    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
		      <div class="accordion-body">
		      		<div class="row my-5">
		      			<div class="col-md-6">
		      				<span> 비밀번호 확인 </span>
		      			</div>
		      			<div class="col-md-6">
		      				<div class="row" id="outout_check">
	      						<div class="col-md-8">
	      							<input type="password" placeholder="비밀번호"  class="form-control" id="out_pws">
	      						</div>
	      						<div class="col-md-4">
	      							<span><button class="btn" onclick="out_check()">확인</button> </span>
	      						</div>
	      					</div>
	      					<div class="row" id="member_out" style="display: none ">
	      						<div class="col-md-4 offset-4">
	      							<span><button class="btn" onclick="out()">탈퇴</button> </span>
	      						</div>
	      					</div>
		      			</div>
		      		</div>
		       </div>
		    </div>
		  </div>
		</div>
	</div>
	<script src="/HEYUM/js/my_info.js"></script>
</body>
</html>