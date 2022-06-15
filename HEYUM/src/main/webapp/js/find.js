/**
 * 
 */

 
   
 function emailfind(){
	
	let email = $("#email").val();
	
	var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	
	if( regEmail.test(email) === true){
		$.ajax({
			url : 'findid',
			type : "post",
			data : {'type' : 'email', 'email' : email},
			success:function(re){
				
				if(re == '2'){
					swal("실패","존재하지 않는 회원입니다.","error");	
					return;
				}
				else {
					swal({
				        title: "메세지",
				        text: "회원님의 아이디는 " +re+ " 입니다. 확인을 누르시면 로그인 페이지로 이동합니다.",
				        icon: "success",
				        buttons: '확인'
				    	}).then((value) => {
				        if (value) {
				          location.href = '/HEYUM/member/login.jsp';
				        }
				    });
					return;
				}
				
			}
		});
	}
	else {
		swal("실패","이메일 형식이 잘못되었습니다.","error");	
		return;
	}
}
function phonefind(){
	
	let phone = $("#phone").val();
	
	$.ajax({
			url : 'findid',
			type : "post",
			data : {'type' : 'phone', 'phone' : phone},
			success:function(re){
				
				if(re == '2'){
					swal("실패","존재하지 않는 회원입니다.","error");	
					return;
				}
				else {
					swal({
				        title: "메세지",
				        text: "회원님의 아이디는 " +re+ " 입니다. 확인을 누르시면 로그인 페이지로 이동합니다.",
				        icon: "success",
				        buttons: '확인'
				    	}).then((value) => {
				        if (value) {
				          location.href = '/HEYUM/member/login.jsp';
				        }
				    });
					return;
				}
				
			}
		});
}

function emailfind2(){
	let email = $("#email2").val();
	
	var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	if( regEmail.test(email) === true){
		$.ajax({
			url : 'findpw',
			type : "post",
			data : {'type' : 'email', 'email' : email},
			success:function(re){
				if(re == "1"){
					swal("인증 성공","회원님의 이메일로 임시 비밀번호를 발급했습니다.","success");
					return;
				}
				else {
					swal("실패","존재하지 않는 회원입니다.","error");	
					return;
				}
			}	
		});
	}
	else {
		swal("실패","이메일 형식이 잘못되었습니다.","error");	
		return;  
	}
}

function phonefind2(){
	
	let phone2 = $("#phone2").val();
	
	$.ajax({
		url : 'findpw',
		type : "post",
		data : {'type' : 'phone', 'phone' : phone2},
		success:function(re){
			if(re == "1"){
				swal("인증 성공","회원님의 이메일로 임시 비밀번호를 발급했습니다.","success");
				return;
			}
			else {
				swal("실패","존재하지 않는 회원입니다.","error");	
				return;
			}
			
		}	
	});
	
}

$(function(){ 
	
	$("#phone").on('input',function(){
		let phone = $("#phone").val();
		$("#phone").val(phoneNumber(phone+"<br>"));
      		
	});
	
	$("#phone2").on('input',function(){
		let phone2 = $("#phone2").val();
		$("#phone2").val(phoneNumber(phone2+"<br>"));
      		
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
	