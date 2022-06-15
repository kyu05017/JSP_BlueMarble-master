

let email_ok = false;
let id_ok = false;
let nick_ok = false;
let pw_ok = false;
let name_ok = false;
let phone_ok = false;

$(function(){ // 자동 실행
	$("#input-email").focus();
	$("#check-id-btn").css("display","none");
	$("#check-id-btn").attr("readonly",true);
	$("#member").attr("readonly",true);
	$("#check-nick-btn").css("display","none");
	$("#checkmail-send").css("display","none");
	$("#check-number-btn").css("display","none");
	$("#signup2").css("display","none");
	$("#input-email").on('input',function(){ // 이메일 입력칸 입력 감지
	
		if($("#input-email").val()==''){    // 입력칸에 아무것도 안 써져있을 시 
			$("#checkmail-send").attr("readonly",true);  // 비활성화
			$("#checkmail-send").css("background","#e9ecef"); // 배경 색 기본
			$("#checkmail-send").css("color","#aaaaaa"); // 글씨 색 기본
			$("#checkmail-info").attr("value","");
			$("#check-number-btn").attr("readonly",true); // 인증번호 확인 버튼 비활성화
			$("#check-number-btn").css("background","#e9ecef"); // 배경 색 기본
			$("#check-number-btn").css("color","#aaaaaa"); // 글씨 색 기본
			$("#check-number").attr("readonly",true);
		}
		else{ // 입력 감지 시
			$("#checkmail-send2").css("display","none");
			$("#checkmail-send").css("display","block");
			$("#checkmail-send").attr("readonly",false) // 버튼 활성화
			$("#checkmail-send").css("background","#ff5000"); // 배경 색 오렌지색
			$("#checkmail-send").css("color","#ffffff"); // 글씨 색 
		}
	})
})

function certification_mail(){ // 인증메일 발송 버튼 누를 시
	
	let memail = $("#input-email").val();	
	var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	if( regEmail.test(memail) === true){
		$.ajax({
			url : "emailcheck",
			data : { "email" : memail } , 
			success : function( result ){
				if( result == 1 ){
					$("#checkmail-value").attr("value","중복된 이메일 주소 입니다."); // 유효성 검사로 띄움   
					$("#checkmail-value").css("color","red"); // 유효성 검사로 띄움 (글씨색 빨간색)
					$("#checkmail-send").css("display","none");
					$("#checkmail-send2").css("display","block");
				}else{
					$.ajax({
						url : "emailauthentification",
						data : { "email" : memail } , 
						success : function( result ){
							swal("인증메일이 발송 되었습니다","\n인증 메일이 오지 않을 경우,\n\n스팸함 또는 스팸설정을 확인해 주세요","info"); // 유효성 검사 통과 할 시에만 이 알람 메세지 띄워줌
							$("#checkmail-send").attr("value","인증메일 재발송");
							$("#checkmail-value").attr("value","");
							
							$("#checkmail-send3").css("display","none");
							$("#check-number-btn").css("display","block");
							$("#check-number").attr("readonly",false);
							$("#check-number-btn").attr("readonly",false); // 인증번호 확인 버튼 활성화  
							$("#check-number-btn").css("background","#ff5000"); // 배경 색 오렌지색
							$("#check-number-btn").css("color","#ffffff"); // 글씨 색 흰색
							
						}
					});
				}
			}
		});
	}
	else {
		$("#input-email").val("");
		$("#checkmail-send").css("display","none");
		$("#checkmail-send2").css("display","block");
		$("#checkmail-value").attr("value","유효하지 않은 이메일 주소 입니다."); // 유효성 검사로 띄움   
		$("#checkmail-value").css("color","red"); // 유효성 검사로 띄움 (글씨색 빨간색)
		return;
	}
}
function certification_number(){ // 인증번호 발송 버튼 누를 시
	let memail = $("#input-email").val();
	let authentification = $("#check-number").val();	
	$.ajax({
		url : "authentification",
		data : { "email" : memail, "check" : authentification } , 
		success : function( result ){
			if(result == '1') {
				$("#check-number").attr("readonly",true);
				$("#check-number-btn").attr("readonly",true);
				$("#checkmail-send").attr("readonly",true); 
				$("#input-email").attr("readonly",true); 
				$("#checkmail-value").attr("value","");
				$("#checknumber-value").attr("value","");
				$("#member").attr("readonly",false);
				$("#checkmail-send").css("display","none");
				$("#checkmail-send2").css("display","block");
				$("#check-number-btn").css("display","none");
				$("#checkmail-send3").css("display","block");
				swal("확인되었습니다."," 아래 확인 버튼을 누르시고 진행 하세요","success");
				email_ok = true;
				if(email_ok == true && id_ok == true && pw_ok == true && name_ok == true && phone_ok == true && nick_ok == true){
					$("#signup2").css("display","block");
					$("#signup1").css("display","none");
					$("#signup2").css("background","#ff5000");
					$("#signup2").css("color","#ffffff");
					return;
				}
				else{
					$("#signup2").css("display","none");
					$("#signup1").css("display","block");
					return;
				}
				
			}
			else {
				$("#checknumber-value").attr("value","올바르지 않은 인증번호 입니다.");
				$("#checknumber-value").css("color","red");
				return;
			}
		}
	});
}


$(function(){ //  아이디

	let idj = /^[a-zA-Z0-9]{5,15}$/;
	$("#member").on('input',function(){ 
		let member_id = $("#member").val();
		if($("#member").val()==''){    // 입력칸에 아무것도 안 써져있을 시 
			$("#check-id-btn").attr("readonly",true);
		}
		else{ // 입력 감지 시
			if(idj.test(member_id) == true){
				$("#checkid-value").attr("value","");
				$("#check-id-btn").attr("readonly",false)
				$("#check-id-btn").css("display","block");
				$("#check-id-btn2").css("display","none");
				$("#check-id-btn").css("background","#ff5000");
				$("#check-id-btn").css("color","#ffffff");
				
				return;
			}
			else{
				
				$("#checkid-value").attr("value","유효하지 않은 아이디 입니다."); // 유효성 검사로 띄움   
				$("#checkid-value").css("color","red"); // 유효성 검사로 띄움 (글씨색 빨간색)
				$("#check-id-btn2").css("display","block");
				$("#check-id-btn").css("display","none");
				return;
			}
		}
	})
});

$(function(){ //  닉네임

	let idj = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g;
	$("#member_nick").on('input',function(){ 
		let member_nick = $("#member_nick").val();
		if($("#member_nick").val()==''){    // 입력칸에 아무것도 안 써져있을 시 
			$("#check-id-btn").attr("readonly",true);
		}
		else{ // 입력 감지 시
			if(idj.test(member_nick) == false){
				$("#checknick-value").attr("value","");
				$("#check-nick-btn").attr("readonly",false)
				$("#check-nick-btn").css("display","block");
				$("#check-nick-btn2").css("display","none");
				$("#check-nick-btn").css("background","#ff5000");
				$("#check-nick-btn").css("color","#ffffff");
				
				return;
			}
			else{
				
				$("#checknick-value").attr("value","유효하지 않은 닉네임 입니다."); // 유효성 검사로 띄움   
				$("#checknick-value").css("color","red"); // 유효성 검사로 띄움 (글씨색 빨간색)
				$("#check-nick-btn2").css("display","block");
				$("#check-nick-btn").css("display","none");
				return;
			}
		}
	})
});
function certification_nick(){
	let member_nick = $("#member_nick").val();
	$.ajax({
		url : 'nickduplication',
		data : { "nick" : member_nick} , 
		success : function( result ){
			
			if(result == '2'){
				$("#checknick-value").attr("value","중복된 닉네임 입니다."); // 유효성 검사로 띄움   
				$("#checknick-value").css("color","red"); // 유효성 검사로 띄움 (글씨색 빨간색)
				return;
			}
			else {
				swal("확인되었습니다."," 아래 확인 버튼을 누르시고 진행 하세요","success");
				$("#check-nick-btn2").css("display","block");
				$("#check-nick-btn").css("display","none");
				$("#member_nick").attr("readonly",true);
				nick_ok = true;
				if(email_ok == true && id_ok == true && pw_ok == true && name_ok == true && phone_ok == true && nick_ok == true){
					$("#signup2").css("display","block");
					$("#signup1").css("display","none");
					$("#signup2").css("background","#ff5000");
					$("#signup2").css("color","#ffffff");
					return;
				}
				else{
					$("#signup2").css("display","none");
					$("#signup1").css("display","block");
					return;
				}
			}
		}
	});
	
}

function certification_id(){
	let id = $("#member").val();
	$.ajax({
		url : 'idcheck',
		data : { "id" : id} , 
		success : function( result ){
			if(result == '1'){
				$("#checkid-value").attr("value","중복된 아이디 입니다."); // 유효성 검사로 띄움   
				$("#checkid-value").css("color","red"); // 유효성 검사로 띄움 (글씨색 빨간색)
				return;
			}
			else {
				swal("확인되었습니다."," 아래 확인 버튼을 누르시고 진행 하세요","success");
				$("#check-id-btn2").css("display","block");
				$("#check-id-btn").css("display","none");
				$("#member").attr("readonly",true);
				id_ok = true;
				if(email_ok == true && id_ok == true && pw_ok == true && name_ok == true && phone_ok == true && nick_ok == true){
					$("#signup2").css("display","block");
					$("#signup1").css("display","none");
					$("#signup2").css("background","#ff5000");
					$("#signup2").css("color","#ffffff");
					return;
				}
				else{
					$("#signup2").css("display","none");
					$("#signup1").css("display","block");
					return;
				}
			}
		}
	});
}
$("#recheckpassword").attr("readonly",true); 
$(function(){ 

	$("#mpassword").on('input',function(){
		let pw = $("#mpassword").val();
		let pwj = /^[a-zA-Z0-9]{5,15}$/; // 정규표현식
		
		if(pw.length >= 15 ){
			swal("오류","15자리 까지 가능합니다.","error");
			$("#recheckpassword").attr("readonly",true); 
			$("#mpassword").val("");
			return;
		}	
		if(pw.length >= 12 && pwj.test(pw)===true){
			$("#pw_worry").css("display","none");
			$("#pw_danger").css("display","none");
			$("#pw_week").css("display","none");
			$("#recheckpassword").attr("readonly",false); 
			
			$("#pw_ok").css("display","inline");
			
			return;
		}	
		if(pw.length >= 8 && pwj.test(pw)===true){
			$("#pw_ok").css("display","none");
			$("#pw_danger").css("display","none");
			$("#pw_week").css("display","none");
			$("#recheckpassword").attr("readonly",false); 
			
			$("#pw_worry").css("display","inline");
			return;
		}	
		if(pw.length >= 5 && pwj.test(pw)===true){
			$("#pw_danger").css("display","none");
			$("#pw_week").css("display","inline");
			$("#recheckpassword").attr("readonly",false); 
			return;
		}
		if(pw.length < 5){
			$("#pw_week").css("display","none");
			$("#pw_worry").css("display","none");
			$("#pw_ok").css("display","none");
		
			$("#pw_danger").css("display","inline");
			
			$("#recheckpassword").attr("readonly",true); 
			return;
		}
	})
})
$(function(){ 

	$("#recheckpassword").on('input',function(){
		let pw = $("#mpassword").val();
		let pw2 = $("#recheckpassword").val();
		
		if( pw == pw2  ){
			$("#checkpassword-value").attr("value","");
			$("#recheckpassword-value").attr("value","비밀번호가 동일합니다."); // 유효성 검사로 띄움   
			$("#recheckpassword-value").css("color","black");

			pw_ok = true;
			if(email_ok == true && id_ok == true && pw_ok == true && name_ok == true && phone_ok == true && nick_ok == true){
				$("#signup2").css("display","block");
				$("#signup1").css("display","none");
				$("#signup2").css("background","#ff5000");
				$("#signup2").css("color","#ffffff");

			}
			else{
				$("#signup2").css("display","none");
				$("#signup1").css("display","block");

			}
		}
		else {
			pw_ok = false;
			$("#recheckpassword-value").attr("value","비밀번호가 동일하지 않습니다."); // 유효성 검사로 띄움   
			$("#recheckpassword-value").css("color","red"); // 유효성 검사로 띄움 (글씨색 빨간색)
			if(email_ok == true && id_ok == true && pw_ok == true && name_ok == true && phone_ok == true && nick_ok == true){
				$("#signup2").css("display","block");
				$("#signup1").css("display","none");
				$("#signup2").css("background","#ff5000");
				$("#signup2").css("color","#ffffff");

			}
			else{
				$("#signup2").css("display","none");
				$("#signup1").css("display","block");

			}
		}
	});
})



$(function(){ 
	let namej = /^[가-힣]{2,10}$/;
	
	$("#name").on('input',function(){
		let name = $("#name").val();
		
		if(namej.test(name) == true){
			$("#name_check").attr("value",""); // 유효성 검사로 띄움  
			name_ok = true;
			if(email_ok == true && id_ok == true && pw_ok == true && name_ok == true && phone_ok == true && nick_ok == true){
				$("#signup2").css("display","block");
				$("#signup1").css("display","none");
				$("#signup2").css("background","#ff5000");
				$("#signup2").css("color","#ffffff");
			}
			else{
				$("#signup2").css("display","none");
				$("#signup1").css("display","block");

			}
		}
		else {
			name_ok = false;
			$("#name_check").attr("value","올바른 형식의 이름이 아닙니다."); // 유효성 검사로 띄움   
			$("#name_check").css("color","red"); // 유효성 검사로 띄움 (글씨색 빨간색)
			if(email_ok == true && id_ok == true && pw_ok == true && name_ok == true && phone_ok == true && nick_ok == true){
				$("#signup2").css("display","block");
				$("#signup1").css("display","none");
				$("#signup2").css("background","#ff5000");
				$("#signup2").css("color","#ffffff");
			}
			else{
				$("#signup2").css("display","none");
				$("#signup1").css("display","block");

			}
		}
	});
})

$(function(){ 
	
	$("#phone").on('input',function(){
		let phone = $("#phone").val();
		$("#phone").val(phoneNumber(phone+"<br>"));
      	
		
			$("#phone_check").attr("value",""); // 유효성 검사로 띄움 
			
			phone_ok = true;
			if(email_ok == true && id_ok == true && pw_ok == true && name_ok == true && phone_ok == true && nick_ok == true){
				$("#signup2").css("display","block");
				$("#signup1").css("display","none");
				$("#signup2").css("background","#ff5000");
				$("#signup2").css("color","#ffffff");
			}
			else{
				$("#signup2").css("display","none");
				$("#signup1").css("display","block");

			}		
	});
})

function phoneNumber(value) {
  if (!value) {
    return "";
  }

  value = value.replace(/[^0-9]/g, "");

  let result = [];
  let restNumber = "";

  // 지역번호와 나머지 번호로 나누기
  if (value.startsWith("02")) {
    // 서울 02 지역번호
    result.push(value.substr(0, 2));
    restNumber = value.substring(2);
  } else if (value.startsWith("1")) {
    // 지역 번호가 없는 경우
    // 1xxx-yyyy
    restNumber = value;
  } else {
    // 나머지 3자리 지역번호
    // 0xx-yyyy-zzzz
    result.push(value.substr(0, 3));
    restNumber = value.substring(3);
  }

  if (restNumber.length === 7) {
    // 7자리만 남았을 때는 xxx-yyyy
    result.push(restNumber.substring(0, 3));
    result.push(restNumber.substring(3));
  } else {
    result.push(restNumber.substring(0, 4));
    result.push(restNumber.substring(4));
  }

  return result.filter((val) => val).join("-");
}
	
function sign(){ 
	swal("가입 실패","미 입력한 내용을 입력해 주세요.","error");
}
function signup(){
	let email = $("#input-email").val();	
	let id = $("#member").val();
	let pw = $("#mpassword").val();
	let name = $("#name").val();
	let phone = $("#phone").val();
	let nick = $("#member_nick").val();
	
	if(name == "" || phone == ""){
		swal("가입 실패","미 입력한 내용을 입력해 주세요.","error");	
		return;
	}
	if(phone.length <= 12){
		swal("가입 실패","미 입력한 내용을 입력해 주세요.","error");	
		return;
	}
	else {
		$.ajax({
			type: 'post',
			url : '/HEYUM/member/signup',
			data : {"email" : email, "id" : id , "pw" : pw , "name": name , "phone" : phone ,"nick" : nick},
			success:function(result){
				if(result == '1'){
					swal({
				        title: "메세지",
				        text: "가입을 축하드립니다.",
				        icon: "success",
				        buttons: '확인'
				    }).then((value) => {
				        if (value) {
							location.href="/HEYUM/main.jsp";
				        }
				    });
					return;
				}
			}
		});
	}

}


