$(function(){
	$("#loginid").focus();
})

$("#password").keyup(function(e){
	if(e.keyCode == 13){
		login();
	}  

});

function login(){ // 로그인페이지에서 사용할 로그인 함수 (세션에 로그인 아이디를 담아주는 역할)
	let loginid = $("#loginid").val();
	let password = $("#password").val();
	$.ajax({
		url:'/HEYUM/member/login',
		data : {"loginid" : loginid, "password" : password},
		type : "post",
		success:function(result){
			if(result==1){
				location.href="/HEYUM/main.jsp"
				return;
			}
			else{
				swal("로그인 실패","존재하지 않거나, 비밀번호가 잘못되었습니다.","error");	
				return;
			}	
		}
	})
} // 로그인 함수 끝
