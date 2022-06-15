/**
 * 
 */
 
 $(function(){
	let ac_no = $("#my_numw2").val();
	
	let html = '<tr><th>번호	</th><th>제목	</th><th>조회</th><th>등록일</th><th>추천</th></tr>';
	$.ajax({
		url : 'getmyboard',
		data : {"no" : ac_no},
		success:function(result){
			if(result.length == 0){
				html += '<tr ><td></td><td></td><td> 아직 작성된 글이 없습니다.</td><td></td><td></td></tr>'
			}
			for(let i = 0; i < result.length; i++){
				html += '<tr ><td>'+result[i]['bno']+'</td><td>'+result[i]['btitle']+
				'</td><td>'+result[i]['bview']+
				'</td><td>'+result[i]['bdate']+'</td><td>'+result[i]['boffer']+'</td></tr>'
			}
			$("#myboardtable").html(html);
		}
		
	});
})