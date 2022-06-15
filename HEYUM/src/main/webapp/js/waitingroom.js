let loginid = $("#loginid").val();
function Request(valuename){
	let rtnval = "";
	let nowAddress = unescape(location.href);
	let parameters = (nowAddress.slice(nowAddress.indexOf("?")+1,nowAddress.length)).split("&");
	for(let i = 0 ; i < parameters.length ; i++){
		let varName = parameters[i].split("=")[0];
		if(varName.toUpperCase() == valuename.toUpperCase()){
			rtnval = parameters[i].split("=")[1];
			break;
		}
	}
	return rtnval;
}
let roomno = Request("groom_no");
let webSocket = new WebSocket('ws://localhost:8080/HEYUM/waitingroom/'+loginid +'/' + roomno);
let inroomno = roomno ;
let bangjang = 0 ;
let ready = 0 ;
let myready = 0 ;
let receivedmsg = "";
let readyuser = [];
let movecheck = 0 ;
$("#msginput").keyup(function(e){
	if(e.keyCode == 13){
		sendmsg();
	}  
});


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
/*    webSocket.onclose = function(event) {
		onClose(event);
	};*/

	window.onbeforeunload = function() {
		if(movecheck==1){
			location.href="/HEYUM/game/gameboard.jsp?groom_no="+roomno;
		}
		else{
			roomout(roomno);
			
	    }
	};
//////////////////////////////// 온 메시지 //////////////////////////////////
    function onMessage(event) {
		receivedmsg = JSON.parse(event.data);
		if(receivedmsg[0]["roomno"]==roomno){
//////////////////////////////// 유저 온오프시 //////////////////////////////////
			if(receivedmsg[0]["type"]=="inuserlist"){ // 유저 접속시
				showmember(receivedmsg[0]["inuserlist"]);
			}
//////////////////////////////// 정상 나가기 했을시 (레디숫자 반영해야함) //////////////////////////////////
			if(receivedmsg[0]["type"]=="roomout"){ // 유저 접속시
				ready -= receivedmsg[0]["readycount"];
				
				
				for(let i = 0 ; i <readyuser.length ; i++){
					if(readyuser[i]==receivedmsg[0]["loginid"]){
						readyuser.splice(i,1);
						break;
					}
				}
				
				showmember(receivedmsg[0]["inuserlist"]);

			}
//////////////////////////////// 방장 나가기 했을시 (레디숫자 반영해야함) //////////////////////////////////
			if(receivedmsg[0]["type"]=="bjroomout"){ // 유저 접속시
				ready -= receivedmsg[0]["readycount"];
				if(receivedmsg[0]["inuserlist"][0]["loginid"]==loginid){
					bangjang=1;
				}
				for(let i = 0 ; i <readyuser.length ; i++){
					if(readyuser[i]==receivedmsg[0]["loginid"]){
						readyuser.splice(i,1);
						break;
					}
				}
				showmember(receivedmsg[0]["inuserlist"]);	

			}
//////////////////////////////// 채팅 //////////////////////////////////
			if(receivedmsg[0]["type"]=="chat"){ 
				$("#wchattingbox").append(receivedmsg[0]["nick"] + "　:　" + receivedmsg[0]["content"] + '<br>');
				$('#wchattingbox').scrollTop($('#wchattingbox')[0].scrollHeight);
			}
////////////////////////////////// 레디 버튼 /////////////////////////////////////////////
			if(receivedmsg[0]["type"]=="readybtn"){
				ready += 1;
				readyuser.push(receivedmsg[0]["loginid"])
				showmember(receivedmsg[0]["inuserlist"]);
			}
////////////////////////////////// 레디 취소 버튼 /////////////////////////////////////////////
			if(receivedmsg[0]["type"]=="readycancelbtn"){
				ready -= 1;
				for(let i = 0 ; i <readyuser.length ; i++){
					if(readyuser[i]==receivedmsg[0]["loginid"]){
						readyuser.splice(i,1);
						break;
					}
				}
				showmember(receivedmsg[0]["inuserlist"]);
			}
////////////////////////////////// 게임시작 /////////////////////////////////////////////
			if(receivedmsg[0]["type"]=="gamestart"){
				movecheck = 1;
				location.href="/HEYUM/game/gameboard.jsp?groom_no="+roomno;
			}
//////////////////////////////// 유저 온오프시 //////////////////////////////////
			if(receivedmsg[0]["type"]=="xout"){ // 유저 접속시
				showmember(receivedmsg[0]["inuserlist"]);
			}
//////////////////////////////// 움직이는 이모티콘 //////////////////////////////////			
			if(receivedmsg[0]["type"]=="emo"){
				let html = receivedmsg[0]["nick"] +'　:　'+ '<img src="/HEYUM/img/imoji/kk'+receivedmsg[0]['content']+'.gif" width="90px">' +  '<br>';
				$("#wchattingbox").append(html);
				$('#wchattingbox').scrollTop($('#wchattingbox')[0].scrollHeight);	
			}
		}
    }
//////////////////////////////// 온 메시지 end //////////////////////////////////

	function emoji(num){
		
		let now_msg = $("#msginput").val();
		let emojis = { 
						1 : " &#128512 ",
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
		let emoji = {"type" : "emo", "content" : num ,'roomno' : roomno};
		webSocket.send(JSON.stringify(emoji));
		$("#msginput").focus();
	}

////////////////////////소켓으로부터 메시지 받고나서 실행되는 메소드들 //////////////////
	function showmember(result){ // 접속 유저 표시하기
		let html = "";
		for(let i = 0 ; i < result.length ; i++){ // 접속한 유저만큼 표시
			if(result[i]["loginid"]==loginid){ // 자기자신일경우
				if(bangjang==1){ // 방장일경우
					html +=
						'<div class="userbox col-md-3 text-center">'+
						'<img alt="" src="/HEYUM/img/profile/'+result[i]["profile"]+'" width="100%" height="220px" class="rounded-circle my-3">'+
						'닉네임 : ' + result[i]["nickname"]+' <br>'+
						'전적 : '+result[i]["win"]+'승 '+result[i]["lose"]+'패 <br>';
					if(ready>=2 && result.length==ready){ // 유저들 레디 수가 방안 유저수가 동의할경우 게임시작버튼 활성화
					html +=	'<button class="form-control" onclick="gamestart('+roomno+')">게임시작</button>' ;
					}
					else{ // 레디수 모자랄경우 게임시작버튼 비활성화
					html +=	'<button class="form-control" disabled="disabled">게임시작</button>' ;
					}	// 나가기버튼
					html+=	'<button class="form-control" onclick="roomout('+roomno+')">나가기</button>'+
							'</div>';
				}
				else{ // 방장이 아닐경우
						html +=
								'<div class="userbox col-md-3 text-center">'+
								'<img alt="" src="/HEYUM/img/profile/'+result[i]["profile"]+'" width="100%" height="220px" class="rounded-circle my-3">'+
								'닉네임 : ' + result[i]["nickname"]+' <br>'+
								'전적 : '+result[i]["win"]+'승 '+result[i]["lose"]+'패 <br>';
						if(myready==0){	// 내가 레디를 안했을경우 준비버튼으로 표시		
								html += '<button class="form-control" onclick="readybtn('+roomno+')">준비</button>';
						}
						else if(myready==1){ // 내가 레디를 했을경우 레디 취소버튼으로 표시
								html += '<button class="form-control" onclick="readycancelbtn('+roomno+')">취소</button>';
						} // 나가기버튼
						html +=	'<button class="form-control" onclick="roomout('+roomno+')">나가기</button>'+
								'</div>';
				}
			}
			else{ // 자기자신이 아닐경우
				html +=
						'<div class="userbox col-md-3 text-center">'+
						'<img alt="" src="/HEYUM/img/profile/'+result[i]["profile"]+'" width="100%" height="220px" class="rounded-circle my-3">'+
						'닉네임 : ' + result[i]["nickname"]+' <br>'+
						'전적 : '+result[i]["win"]+'승 '+result[i]["lose"]+'패 <br>';
					if(readyuser.includes(result[i]["loginid"])){ // 레디한 유저들 배열에 포함되어있으면 레디함 표시
						html += '레디함' ;
					}
				html +=	'</div>';
			}
		}
		$("#inuserlist").html(html);
	}
		
//////////////////////// 웹소켓에 보내는 메소드들 //////////////////////////////
function roomout(roomno){
	$.ajax({
		url : '/HEYUM/lobby/roomout',
		data : {"roomno" : roomno},
		success : function(result){
			if(result == 1){
				$.ajax({
					url : '/HEYUM/lobby/inuserlist',
					data : {"roomno" : roomno},
					success : function(result2){
						let msg ="";
						if(bangjang==1){
							msg = {"type" : "bjroomout", "inuserlist" : result2, "roomno" : roomno, "readycount" : myready};
						}
						else{
							msg = {"type" : "roomout", "inuserlist" : result2, "roomno" : roomno, "readycount" : myready};
						}
						webSocket.send(JSON.stringify(msg));
						location.href="lobby.jsp";
					}
				})
			}
		}
	})	
}

function readybtn(i){
	myready = 1 ;
	$.ajax({
		url : '/HEYUM/lobby/inuserlist',
		data : {'roomno' : roomno},
		success: function(result){
			let msg = {'type' : 'readybtn' , 'inuserlist' : result, 'roomno' : i, 'loginid' : loginid};
			webSocket.send(JSON.stringify(msg));
		}
	});
}

function readycancelbtn(i){
	myready = 0 ;
	$.ajax({
		url : '/HEYUM/lobby/inuserlist',
		data : {'roomno' : roomno},
		success: function(result){
			let msg = {"type" : "readycancelbtn", "inuserlist" : result , "roomno" : i, "loginid" : loginid};
			webSocket.send(JSON.stringify(msg));
		}
	});	
}

function gamestart(i){				
	$.ajax({
		url : '/HEYUM/lobby/gamestart',
		data : {"roomno" : i },
		success : function(result){
			if(result==1){
				let msg = {"type" : "gamestart" , "roomno" : i};
				webSocket.send(JSON.stringify(msg));
			}
			else{
				alert("DB 오류");
			}
		}
	});
}

function sendmsg(){ // 채팅 메시지 전송
	if($("#msginput").val()==""){
		return;
	}
	let msg = {"type" : "chat", "content" : $("#msginput").val(), 'roomno' : roomno}
	webSocket.send(JSON.stringify(msg));
	$("#msginput").val("");
	$("#msginput").focus();
}

/////////////////////// 안쓰는거(아직 쓸줄 모름) ///////////////////////////
	function onOpen(event) {
		$.ajax({
			url : '/HEYUM/lobby/inuserlist',
			data : {'roomno' : roomno},
			success: function(result){
				if(result.length==1){
					bangjang = 1;
					ready += 1;
				}
				let msg = {'type' : 'inuserlist' , 'inuserlist' : result, 'roomno' : roomno};
				webSocket.send(JSON.stringify(msg));
			}
		});	     
    }
    
	function onClose(event) {

    }
     
    function onError(event) {

    }
