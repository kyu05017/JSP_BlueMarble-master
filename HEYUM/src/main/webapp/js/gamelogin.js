function login(){ // 로그인페이지에서 사용할 로그인 함수 (세션에 로그인 아이디를 담아주는 역할)
	let loginid = $("#loginid").val();
	let ac_no = $("#ac_no").val();
	$.ajax({
		url:'login',
		data : {"loginid" : loginid, "ac_no":ac_no},
		success:function(result){
			alert("로그인 성공"+result);
			location.href="/HEYUM/game/gameboard.jsp?groom_no=1"
			
		}
	})
} // 로그인 함수 끝