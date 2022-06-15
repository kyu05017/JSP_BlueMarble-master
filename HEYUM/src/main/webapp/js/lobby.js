////////////////////////////////////////////////////////
let loginid = $("#loginid").val();
let webSocket = new WebSocket('ws://localhost:8080/HEYUM/lobby/'+loginid);
let inroomno = 0 ;
let bangjang = 0 ;
let receivedmsg = "";
let mypage = 1 ;
$("#msginput").keyup(function(e){
	if(e.keyCode == 13){
		sendmsg();
	}  
});

	

function page(i){
	mypage = i ;
	for(let j= 1 ; j <= 5 ; j++){
		if(j!=i){
			$("#pagebtn"+j).css("background-color" , "white");
		}
		else if(j==i){
			$("#pagebtn"+j).css("background-color" , "gray");
		}
	}
	$.ajax({
		url : '/HEYUM/lobby/getgamelist',
		success: function(result2){
			showgamelist(result2);
		}
	});
}

/////////////////////////////////////////////////////
    webSocket.onerror = function(event) {
      onError(event)
    };
    webSocket.onopen = function(event) {
      onOpen(event)
    };
    webSocket.onmessage = function(event) {
      onMessage(event)
    };
    webSocket.onclose = function(message) {

	};

//////////////////////////////// 온 메시지 //////////////////////////////////
    function onMessage(event) {
		receivedmsg = JSON.parse(event.data);
		
//////////////////////////////// 유저 온오프시 //////////////////////////////////
		if(receivedmsg[0]["type"]=="userlist"){ // 유저 접속시
			
			showmember();
		}
//////////////////////////////// 채팅 //////////////////////////////////
		if(receivedmsg[0]["type"]=="chat"){ 
			$("#chattingbox").append(receivedmsg[0]["nick"] + "　:　" + receivedmsg[0]["content"] + '<br>');
			$('#chattingbox').scrollTop($('#chattingbox')[0].scrollHeight);			
		}
//////////////////////////////// 방의 변화가 있을 시 ////////////////////////////////////
		if(receivedmsg[0]["type"]=="roomcheck"){
			showgamelist(receivedmsg[0]["roomlist"]);
		}
//////////////////////////////// 움직이는 이모티콘 //////////////////////////////////		
		if(receivedmsg[0]["type"]=="emo"){
			let html = receivedmsg[0]["nick"] +'　:　'+ '<img src="/HEYUM/img/imoji/kk'+receivedmsg[0]['content']+'.gif" width="90px">' +  '<br>';
			$("#chattingbox").append(html);
			$('#chattingbox').scrollTop($('#chattingbox')[0].scrollHeight);	
		}
//////////////////////////////// 온 메시지 end //////////////////////////////////
	}

/*
function sendmsg(){ // 채팅 메시지 전송
	if($("#msginput").val()==""){
		return;
	}
	let msg = {"type" : "chat", "content" : $("#msginput").val()}
	webSocket.send(JSON.stringify(msg));
	$("#msginput").val("");
}

*/
//////////////////////////////// 기본 이모티콘 시스템 //////////////////////////////
	function emoji(num){
		
		let now_msg = $("#msginput").val();
		let emojis = {  1 : " &#128512 ",
						2 : " &#128513 ",
						3 : " &#128525 ",
						4 : " &#128526 ",
						5 : " &#128541 ",
						6 : " &#128545 ",
						7 : " &#128557 ",
						8 : " &#128151 ",
						9 : " &#128561 ",
						10: " &#129326 ",
						11: " &#9757 ",
						12: " &#128074 ",
						13: " &#129321 ",
						14: " &#129324 ",
						15: " &#128149 "};
		
		for(let i = 0; i <= num; i++){
			if(num == i){
				now_msg += emojis[num];
				$("#msginput").val(now_msg);
				$("#msginput").focus();
				return;
			}
		}	
	}
	function gifemo(num){
		let emoji = {"type" : "emo", "content" : num };
		webSocket.send(JSON.stringify(emoji));
		$("#msginput").focus();
	}
	
//////////////////////////////////////////////////////////////////////////////

////////////////////////소켓으로부터 메시지 받고나서 실행되는 메소드들 //////////////////
	function showmember(){ // 접속 유저 표시하기	
		let html =  '<thead>'+
		'				<tr>'+
		'					<th>접속중인 유저목록 ( '+receivedmsg.length+' ) </th>'+
		'				</tr>'+
		'				</thead>';
		for(let i = 0 ; i < receivedmsg.length ; i++){
			html +=  '<tr>'+
			'				<td>'+
			'				<button class="btn" onclick="infofo(\''+receivedmsg[i]["loginid"]+'\')"> <img alt="" src="/HEYUM/img/profile/'+receivedmsg[i]["profile"]+'" style="width: 30px " class="rounded-circle">     '+receivedmsg[i]["nick"]+'</button>'+
			'				</td>'+
			'				</tr>';
		}
		$("#waitingmembers").html(html);
	}
	function infofo(id){
	
		$.ajax({
			url : '/HEYUM/game/getother',
			data : {"id" : id },
			success : function(result){
				swal(result[0]['nick'] + "님의 전적 ","승 : "+result[0]['win']+" 패 : "+result[0]['lose']);
				return;

			}
		});
		
	
	}	
	function showgamelist(result){ // 방 목록 보여주기	
		for(let i = 0 ; i <= 3 ; i++){
			if(result.length > (mypage-1)*4+i){
				if(result[(mypage-1)*4+i]['인원수']!=0){
					let html = '<span id="roomno"> 방번호 : '+result[(mypage-1)*4+i]['방번호']+' </span><br>'+
							'<span> 방이름 : '+result[(mypage-1)*4+i]['방제목']+' </span><br>'+
							'<span> 인원수 : '+result[(mypage-1)*4+i]['인원수']+' / 4 </span><br>';
					if(result[(mypage-1)*4+i]['인원수']<4){
						if(result[(mypage-1)*4+i]['게임중']==1){
							html += '게임중';
						}
						else{
							if(inroomno==0){
								html +=	'<button type="button" id="roombtn" onclick="enterroom('+result[(mypage-1)*4+i]['방번호']+','+((mypage-1)*4+i)+')">입장</button>';
							}
						}

					}
					$("#gameroom"+i).html(html);
				}
				else{
					let html ='방이 없습니다<br>';
					if(inroomno==0){
						html += '<button type="button" id="roombtn" onclick="makeroom('+((mypage-1)*4+i)+')">방만들기</button>';
					}	
					$("#gameroom"+i).html(html);
				}
			}
			else{
				let html ='방이 없습니다<br>';
				if(inroomno==0){
					html += '<button type="button" id="roombtn" onclick="makeroom('+((mypage-1)*4+i)+')">방만들기</button>';
				}	
				$("#gameroom"+i).html(html);
			}	
		}
	}


//////////////////////// 웹소켓에 보내는 메소드들 //////////////////////////////
function makeroom(i){
let roomname = prompt('방이름을 입력해주세요', '');
	if(roomname==null){
		return ;
	}
	else if(roomname==''){
		alert('방이름을 입력해주세요');
	}
	else{
		bangjang = 1;
		$.ajax({
			url : '/HEYUM/lobby/gotoroom',
			data : {"type" : "make" , "roomname" : roomname},
			success : function(result){
				inroomno = result;
				$.ajax({
					url : '/HEYUM/lobby/getgamelist',
					success: function(result2){
						let msg = {"type" : "roomcheck", "roomlist" : result2};
						webSocket.send(JSON.stringify(msg));
						webSocket.close();
						location.href="waitingroom.jsp?groom_no="+inroomno;
					}
				});
			}
		})
	}
}

function enterroom(roomno){
	inroomno = roomno ;
	$.ajax({
		url : '/HEYUM/lobby/gotoroom',
		data : {"type" : "enter" , "roomno" : roomno},
		success : function(result){
			if(result != 0){
				$.ajax({
					url : '/HEYUM/lobby/getgamelist',
					success: function(result2){
						let msg = {"type" : "roomcheck", "roomlist" : result2};
						webSocket.send(JSON.stringify(msg));
						webSocket.close();
						location.href="waitingroom.jsp?groom_no="+roomno;						
					}
				});
			}
		}
	})
}

function sendmsg(){ // 채팅 메시지 전송
	if($("#msginput").val()==""){
		return;
	}
	let msg = {"type" : "chat", "content" : $("#msginput").val()}
	webSocket.send(JSON.stringify(msg));
	$("#msginput").val("");
	$("#msginput").focus();
}

/////////////////////// 안쓰는거(아직 쓸줄 모름) ///////////////////////////
	function onOpen(event) {
		$.ajax({
			url : '/HEYUM/lobby/getgamelist',
			success: function(result2){
				let msg = {"type" : "roomcheck", "roomlist" : result2}
				webSocket.send(JSON.stringify(msg));
			}
		});
    }
    
    function onError(event) {
      alert("접속 오류");
    }

    
