

$(function() {
	//<img alt="" src="/HEYUM/img/profile/'+receivedmsg[i]["profile"]+'" style="width: 30px " class="rounded-circle">
	let html = '<tr><th> 등수 </th><th> 프로필 사진 </th><th>아이디</th><th>점수</th></tr>';
	$.ajax({
		url : 'getallmember',
		success : function (result){
			
			$("#ranki1").html(result[0]['id']);
			$("#ranki2").html(result[1]['id']);
			$("#ranki3").html(result[2]['id']);
			
			$("#ranks1").html((result[0]['win'])*1000);
			$("#ranks2").html((result[1]['win'])*1000);
			$("#ranks3").html((result[2]['win'])*1000);
			
			$("#pro1").attr( "src" ,"/HEYUM/img/profile/"+result[0]['profile']);	
			$("#pro2").attr( "src" ,"/HEYUM/img/profile/"+result[1]['profile']);	
			$("#pro3").attr( "src" ,"/HEYUM/img/profile/"+result[2]['profile']);	
			
			for(let i = 0; i < result.length; i++){
				html += '<tr ><td>'+(i+1)+
				'</td><td> <img alt="" src="/HEYUM/img/profile/'+result[i]["profile"]+'" style="width: 50px " class="rounded-circle">'+
				'</td><td>'+result[i]['id']+
				'</td><td>'+((result[i]['win'])*1000)+
				'</td></tr>'
			}
			$("#ranktable").html(html);
		} 
	});
})