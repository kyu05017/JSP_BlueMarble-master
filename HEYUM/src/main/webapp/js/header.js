/**
 * 
 */
 
function logout(){
	
	$.ajax({
		url : '/HEYUM/member/logout',
		success : function (){
			 location.href='/HEYUM/main.jsp';
		} 
	})
}

$(function(){
	
	let mno = $("#my_num").val();
	
	if(mno != null){
		$.ajax({
			type : 'post',
			url : '/HEYUM/member/getaccount',
			data : {"ac_no" : mno},
			success:function(result){
				$("#mymypro").attr( "src" ,"/HEYUM/img/profile/"+result["ac_profileimg"]);	
				$("#headernick").html(result["ac_nickname"]+"님");
			}
		});
	}
});