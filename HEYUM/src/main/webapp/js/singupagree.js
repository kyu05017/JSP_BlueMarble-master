/**
 * 
 */
 
 function next(){
 
 	if($("#Check1").prop("checked")== true && $("#Check2").prop("checked") == true){
		location.href="/HEYUM/member/signup.jsp";
	}
	else {
		swal("경고","필수 동의에 체크 하여야 회원가입이 가능합니다.","error");	
	}
 
}

$("#allChk").change(function(){

	if($("#allChk").is(":checked")){
		
		$("#Check1").prop("checked", true);
	 	$("#Check2").prop("checked", true);
	 	
	}else{
		$("#Check1").prop("checked", false);
	 	$("#Check2").prop("checked", false);	
	}
});