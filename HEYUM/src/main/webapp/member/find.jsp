<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
	<div class="row">
		<div class="col-md-6 my-4">
		
			<h3> 아이디 찾기 </h3>
			
			<div class="accordion my-5" id="accordionExample">
			  <div class="accordion-item">
			    <h2 class="accordion-header" id="headingOne">
			      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne" style="font-weight: bold">
			        이메일로 찾기
			      </button>
			    </h2>
			    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
			      <div class="accordion-body">
				      <div class="input_group">
						
					  <input type="text" placeholder=" " name="email" id="email">
					
					  <label>이메일</label>
					  
					  <button class="btn mx-4" onclick="emailfind()"> 확인 </button>
					  
					  
					</div>
			      </div>
			    </div>
			  </div>
			  <div class="accordion-item">
			    <h2 class="accordion-header" id="headingTwo">
			      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo"  style="font-weight: bold">
			        전화번호로 찾기
			      </button>
			    </h2>
			    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
			      <div class="accordion-body">
			      		<div class="input_group">
						
					  <input type="text" placeholder=" " name="phone" id="phone">
					
					  <label>핸드폰 번호</label>
					   <button class="btn mx-4" onclick="phonefind()"> 확인 </button>
					   
					   
					   
					</div>
			       </div>
			    </div>
			  </div>
			</div>
			<div>
				
			</div>
		</div>
		
		
		<div class="col-md-6 my-4">
			<h3> 비밀번호 찾기 </h3>
			
			<div class="accordion my-5" id="accordionExample">
			  <div class="accordion-item">
			    <h2 class="accordion-header" id="headingOne">
			      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne" style="font-weight: bold">
			        이메일로 찾기
			      </button>
			    </h2>
			    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
			      <div class="accordion-body">
				      <div class="input_group">
						
					  <input type="text" placeholder=" " name="email2" id="email2">
					
					  <label>이메일</label>
					  
					  <button class="btn mx-4" onclick="emailfind2()"> 확인 </button>
					  
					  
					</div>
			      </div>
			    </div>
			  </div>
			  <div class="accordion-item">
			    <h2 class="accordion-header" id="headingTwo">
			      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo"  style="font-weight: bold">
			        전화번호로 찾기
			      </button>
			    </h2>
			    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
			      <div class="accordion-body">
			      		<div class="input_group">
						
					  <input type="text" placeholder=" " name="phone2" id="phone2">
					
					  <label>핸드폰 번호</label>
					   <button class="btn mx-4" onclick="phonefind2()"> 확인 </button>
					   
					   
					   
					</div>
			       </div>
			    </div>
			  </div>
			</div>
			
		</div>
	</div>
</div>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script src="/HEYUM/js/find.js"></script>
<%@include file="../footer.jsp" %>
</body>
</html>