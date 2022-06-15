/**
 * 
 */
let m_num = $("#my_numw").val();
let account_data;
 $( function(){
	$.ajax({
		type : 'post',
		url : '/HEYUM/member/getaccount',
		data : {"ac_no" : m_num},
		success:function(result){	
			account_data = result;
		
			$("#my_name").html(account_data["ac_name"]);
			$("#my_name").css("background","white");
			$("#my_name").css("font-weight","bold");
			$("#my_name").css("font-size","22px");
			$("#my_email").html(account_data["ac_email"]);
			$("#my_email").css("background","white");
			$("#my_email").css("color","gray ");
			$("#my_nick").html(account_data["ac_nickname"]);
			
			$("#my_email2").html(account_data["ac_email"]);
			$("#my_phone").html(account_data["ac_phone"]);
			$("#now_pro").attr( "src" ,"/HEYUM/img/profile/"+account_data["ac_profileimg"]);
			$("#now_pro2").attr( "src" ,"/HEYUM/img/profile/"+account_data["ac_profileimg"]);
		}
	});
});


function nick_change(){
	let change_nick = $("#my_nick").html();
	$("#nick_change_box").css("display","block");
	$("#now_nickname").val(change_nick);
	$("#btn_change").css("display","none");
	$("#btn_cancle").css("display","inline");
}
function nick_change2(){

	$("#nick_change_box").css("display","none");
	$("#btn_change").css("display","inline");
	$("#btn_cancle").css("display","none");
}
function nick_comf(){
	let idj = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g;
	let change_nick = $("#now_nickname").val();
	if(account_data["ac_nickname"] == change_nick){
		swal("변경 실패","현재 닉네임과 동일합니다.","error");
		return;
	}
	if(change_nick == ""){
		swal("변경 실패","사용할 닉네임을 입력해주세요.","error");
		return;
	}
	if( change_nick.includes(" ")){
		swal("변경 실패","닉네임에 공백이 포함될수 없습니다..","error");
		return;
	}
	if(change_nick.length >= 10){
		swal("변경 실패","10글자 이하만 가능합니다.","error");
		return;
	}
	if(idj.test(change_nick) == true){
		swal("변경 실패","특수문자는 포함할 수 없습니다.","error");
		return;
	}
	
	$.ajax({
		url : 'nickduplication',
		data : {"nick": change_nick},
		success:function(re){	
			if(re == '1'){
				$.ajax({
					url : 'infochange',
					data : {"type" : "nick", "no" : m_num, "nick" : change_nick},
					success:function(re){	
						if(re=="2"){
							swal("변경 실패","관리자 문의 요망","error");
							return;
						}
						else {
							swal("변경 성공","닉네임이 변경되었습니다.","success");
							$("#my_nick").html(re);
							$("#now_nickname").val(re);
							$("#nick_change_box").css("display","none");
							$("#btn_change").css("display","inline");
							$("#btn_cancle").css("display","none");
							location.reload();
							return;
						}
					}
				});
			}
			else {
				swal("변경 실패","중복된 닉네임 입니다.","error");
				return;
			}
		}
	});
	
	
}



function email_change(){
	let my_email = $("#my_email2").html();
	$("#email_change_box").css("display","block");
	$("#now_email").val(my_email);
	$("#btn_change2").css("display","none");
	$("#btn_cancle2").css("display","inline");
}
function email_change2(){
	$("#email_change_box").css("display","none");
	$("#email_change_check_box").css("display","none");
	
	$("#btn_change2").css("display","inline");
	$("#btn_cancle2").css("display","none");
}
function send(){
	let regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	let now_email = $("#now_email").val();

	if(account_data["ac_email"] == now_email){
		swal("변경 실패","현재 이메일과 동일합니다.","error");
		return;
	}
	if(now_email == ""){
		swal("변경 실패","사용할 이메일을 입력해주세요.","error");
		return;
	}
	if(now_email.includes(" ")){
		swal("변경 실패","이메일에 공백이 포함될수 없습니다..","error");
		return;
	}
	if(regEmail.test(now_email) === false){
		swal("변경 실패","올바르지 않은 이메일 형식입니다.","error");
		return;
	}
	
	if( regEmail.test(now_email) === true){
		$.ajax({
			url : "emailcheck",
			data : { "email" : now_email } , 
			success : function( result ){
				if( result == 1 ){
					swal("변경 실패","중복된 이메일 입니다.","error");
					return;
				}
				else {
					$.ajax({
						url : "emailauthentification",
						data : { "email" : now_email } , 
						success : function( ){
							swal("인증메일이 발송 되었습니다","\n인증 메일이 오지 않을 경우,\n\n스팸함 또는 스팸설정을 확인해 주세요","info");
							$("#email_change_check_box").css("display","block");
						}
					});
				}
			}
		});
	}
}

function e_check(){
	let echeck = $("#now_check").val();
	let now_email = $("#now_email").val();
	
	$.ajax({
		url : "authentification",
		data : { "email" : now_email, "check" : echeck } , 
		success : function( result ){
			if(result == '1') {
				$.ajax({
					url : 'infochange',
					data : {"type" : "email", "no" : m_num, "email" : now_email},
					success:function(re){		
						if(re == '2'){
							swal("변경 실패","관리자 문의 요망","error");
							return;
						}
						else {
							swal("변경 성공","이메일이 변경되었습니다.","success");
							$("#my_email").html(re);
							$("#my_email2").html(re);
							$("#now_email").val(re);
							$("#email_change_box").css("display","none");
							$("#email_change_check_box").css("display","none");
							$("#now_check").val("");
						}
					}
				});
				return;
			}
			else {
				swal("변경 실패","잘못된 인증번호 입니다.","error");
				return;
			}
		}
	});
}


function phone_change(){
	let change = $("#my_phone").html();
	$("#phone_change_box").css("display","block");
	$("#now_phone").val(change);
	$("#btn_change3").css("display","none");
	$("#btn_cancle3").css("display","inline");
}
function phone_change2(){

	$("#phone_change_box").css("display","none");
	$("#btn_change3").css("display","inline");
	$("#btn_cancle3").css("display","none");
}

$(function(){ 
	
	$("#now_phone").on('input',function(){
		let phone = $("#now_phone").val();
		$("#now_phone").val(phoneNumber(phone+"<br>"));
      		
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
	


function phone_comf() {
	let phone = $("#now_phone").val();
	
	$.ajax({
		url : 'phoneduplication',
		data : {"phone" : phone},
		success : function( result ){
			if(result == '1'){
				$.ajax({
					url : 'infochange',
					data : {"type" : "phone", "no" : m_num, "phone" : phone},
					success:function(re){
						if(re=="2"){
							swal("변경 실패","관리자 문의 요망","error");
							return;
						}
						else {
							swal("변경 성공","전화번호가 변경되었습니다.","success");
							$("#my_phone").html(re);
							$("#now_phone").val(re);
							$("#phone_change_box").css("display","none");
							$("#btn_change3").css("display","inline");
							$("#btn_cancle3").css("display","none");
							return;
						}
					}
				});
			}
			else {
				swal("변경 실패","중복된 번호 입니다.","error");
				return;
			}
		}	
	});
}


function pw_change(){
	
	$("#btn_change4").css("display","none");
	$("#btn_cancle4").css("display","inline");
	$("#pw_check_box").css("display","block");

	
}
function pw_change2(){
	
	$("#now_pw").val("");
	$("#new_pw").val("");
	$("#new_pw_check").val("");
	
	
	$("#btn_change4").css("display","inline");
	$("#btn_cancle4").css("display","none");
	$("#pw_check_box").css("display","none");
	$("#pw_new_box").css("display","none");
}

function pw_check_comf(){
	let now_pw = $("#now_pw").val();
	$.ajax({
		url : 'pwcheck',
		type : 'POST' , 
		data : {"no": m_num, "pw" : now_pw},
		success:function(re){	
			if(re == '1'){
				$("#pw_check_box").css("display","none");
				$("#pw_new_box").css("display","block");
				last_pw = now_pw;
				return;
			}
			else {
				swal("오류","비밀번호가 다릅니다.","error");
				return;
			}
			
		}	
	});
}
$("#new_pw_check").attr("readonly",true); 
$("#new_pw").on('input',function(){
	let pw = $("#new_pw").val();
	let pwj = /^[a-zA-Z0-9]{5,15}$/; // 정규표현식
	if(pw == last_pw){
		swal("오류","이전 비밀번호랑 동일합니다.","error");
		$("#new_pw_check").attr("readonly",true); 
		return;
	}
	if(pw.length >= 15 ){
		swal("오류","15자리 까지 가능합니다.","error");
		$("#new_pw_check").attr("readonly",true); 
		$("#new_pw").val("");
		return;
	}	
	if(pw.length >= 12 && pwj.test(pw)===true){
		$("#pw_worry").css("display","none");
		$("#pw_danger").css("display","none");
		$("#pw_week").css("display","none");
		$("#new_pw_check").attr("readonly",false); 
		
		$("#pw_ok").css("display","inline");
		
		return;
	}	
	if(pw.length >= 8 && pwj.test(pw)===true){
		$("#pw_ok").css("display","none");
		$("#pw_danger").css("display","none");
		$("#pw_week").css("display","none");
		$("#new_pw_check").attr("readonly",false); 
		
		$("#pw_worry").css("display","inline");
		return;
	}	
	if(pw.length >= 5 && pwj.test(pw)===true){
		$("#pw_danger").css("display","none");
		$("#pw_week").css("display","inline");
		$("#new_pw_check").attr("readonly",false); 
		return;
	}
	if(pw.length < 5){
		$("#pw_week").css("display","none");
		$("#pw_worry").css("display","none");
		$("#pw_ok").css("display","none");
	
		$("#pw_danger").css("display","inline");
		
		$("#new_pw_check").attr("readonly",true); 
		return;
	}
})
$("#new_pw_check").on('input',function(){
	let pw = $("#new_pw").val();
	let pw_check = $("#new_pw_check").val();


	if(pw == pw_check){

		$("#check_false").css("display","none");
	
		$("#check_true").css("display","inline");
		
		return;
		
	}
	else {
		$("#check_false").css("display","inline");
	
		$("#check_true").css("display","none");
		
		return;
	}
})


function pw_new_comf(){
	swal("오류","비밀번호가 다릅니다.","error");
}

function pw_new_comf2(){
	let pw = $("#new_pw").val();
	$.ajax({
		url : 'pwupdate',
		type : 'POST' , 
		data : {"no": m_num, "pw" : pw},
		success : function( re ){
			if(re == '1'){
				$.ajax({
					url : '/HEYUM/member/logout',
					success : function (){
						location.href='/HEYUM/main.jsp';
					} 
				})
				return;
			}
			else {
				swal("오류","관리자에게 문의 하세요.","error");
				return;	
			}
		}
	});
}

function add(no){
	var form = $("#addprofile")[0];
	var formData = new FormData( form ); 
	$.ajax({
		url : "profileadd",
		type : 'POST' , 
		data : formData,
		contentType : false , 
		processData : false ,
		success : function( re ){
			if( re == 1){
				form.reset(); 
				location.reload();
			}else{
				swal("변경 실패","관리자에게 문의 하세요.","error");
			}
		}
	});
}

$("#pro_img").change( function( e ) {
	$("#profile_btn").trigger("click");
	let reader = new FileReader();
	reader.readAsDataURL( e.target.files[0] ); 
	reader.onload = function( e ){	
		$("#preview").attr( "src" , e.target.result );
	}
});

function pro_cancle(){
	$("#pro_img").val("");
}

function out_check(){
	let out_pw = $("#out_pws").val();
	
	$.ajax({
		url : 'pwcheck',
		type : 'POST' , 
		data : {"no": m_num, "pw" : out_pw},
		success:function(re){	
			if(re == '1'){
				$("#outout_check").css("display","none");
				$("#member_out").css("display","block");
				return;
			}
			else {
				swal("오류","비밀번호가 다릅니다.","error");
				return;
			}
			
		}	
	});
	
}

function out() {
	
	swal({
        title: "메세지",
        text: "정말 탈퇴 하시겠습니까?",
        icon: "warning",
        buttons : {
			cancle : {
				text : '탈퇴',
				value : true,
				className : 'btn btn-outline-primary' // 클래스 이름을 줄 수도 있다.
			},
			confirm : {
				text : '취소',
				value : false,
				className : 'btn btn-outline-primary'
			}
		}
    }).then((value) => {
        if (value) {
			$.ajax({
				url : 'memberout',
				type : 'POST' , 
				data : {"no": m_num},
				success:function(re){	
					if(re == '1'){
						swal({
					        title: "메세지",
					        text: "탈퇴 되었습니다. 메인 페이지로 이동합니다.",
					        icon: "success",
					        buttons: '확인'
					    }).then((value) => {
					        if (value) {
					          $.ajax({
									url : '/HEYUM/member/logout',
									success : function (){
										 location.href='/HEYUM/main.jsp';
									} 
								})
					        }
					    });
						return;
					}
					else {
						swal("오류","탈퇴에 실패 했습니다. 관리자에게 문의 하세요","error");
						return;
					}
				}	
			});
        }
    });   
}

function getch(){
	let piechart;
	$.ajax({
		url : 'getresult',
		data : {"no" : m_num},
		success:function(re){	
			
			if(re[0]['value'] == 0 && re[1]['value'] == 0){
				swal("","게임 전적이 없습니다. 게임을 먼저 진행해 주세요","info");
				$("#mymymyinfobtn").click();
				return;
			}

			piechart = re;
			am5.ready(function() {
			
			// Create root element
			// https://www.amcharts.com/docs/v5/getting-started/#Root_element
			var root = am5.Root.new("chartdiv2");
			
			
			// Set themes
			// https://www.amcharts.com/docs/v5/concepts/themes/
			root.setThemes([
			  am5themes_Animated.new(root)
			]);
			
			
			// Create chart
			// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
			var chart = root.container.children.push(am5percent.PieChart.new(root, {
			  layout: root.verticalLayout,
			  innerRadius: am5.percent(50)
			}));
			
			
			// Create series
			// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
			var series = chart.series.push(am5percent.PieSeries.new(root, {
			  valueField: "value",
			  categoryField: "category",
			  alignLabels: false
			}));
			
			series.labels.template.setAll({
			  textType: "circular",
			  centerX: 0,
			  centerY: 0
			});
			
			
			// Set data
			// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Setting_data
			series.data.setAll(piechart);
			
			
			// Create legend
			// https://www.amcharts.com/docs/v5/charts/percent-charts/legend-percent-series/
			var legend = chart.children.push(am5.Legend.new(root, {
			  centerX: am5.percent(50),
			  x: am5.percent(50),
			  marginTop: 15,
			  marginBottom: 15,
			}));
			
			legend.data.setAll(series.dataItems);
			
			
			// Play initial series animation
			// https://www.amcharts.com/docs/v5/concepts/animations/#Animation_of_series
			series.appear(1000, 100);
			
			}); // end am5.ready()
		}
	});
	
}

function deletepro() {
	
	$.ajax({
		url : 'deletepro',
		data : {"no" : m_num},
		success : function( result ){
			
			if(result == '1'){
				location.reload();
			}
			
		}
	});	
}