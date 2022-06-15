var webSocket = new WebSocket('ws://localhost:8080/HEYUM/gamesocket');
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
    function onOpen(event) {      
    }   
    function onError(event) {
      alert(event.data);
    }  
    function send() {
        webSocket.send( nicname.value +" : " + inputMessage.value);
    }
/////////////////////////////////////////////////////////////////////////////
let groom_no = Request("groom_no");// 게임방번호

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
/////////////////////////////////////////////////////////////////////////////
let alllocation = []; // 모든 플레이어 위치  ajax로 받아와서 여기에 담아둔다음 send로 뿌린다
let turninfolist = []; // 모든 유저들 턴정보
let player;	// 자기 정보  // p_no p_money p_location p_turn p_uisland p_order groom_no ac_no ac_nickname
let turncheck=0;	// 한바퀴 돌았는지 체크
let uisland = 0;	// 무인도 턴 체크
let socialfund = 0; 	// 사회복지기금
let goldkeylist = [] ; 	// 사용한 황금열쇠 체크
let bankruptcycheck = [];	// 모든 플레이어 파산 체크 
let bankruptcycheck2 = 0;	// 파산 체크
let dicedouble = 0; // 주사위 더블 체크
let gameend = 0; 	// 게임중인지 체크
$(function(){
	let ac_no = $("#ac_no").val();
	$.ajax({
		url : "getplayer",
		data : {"ac_no" : ac_no, "groom_no" : groom_no},
		success : function(result){
			player = result; // p_no p_money p_location p_turn p_uisland groom_no ac_no ac_nickname
			groom_no = player["groom_no"];
			playerinfo();
			if(player["p_turn"]==1){
				showdicetable();
			}
			
		}
	});
	let array = new Array();
	let object = new Object();
	object.bankruptcy = 0;
	object.nickname = "";
	array.push(object);
	bankruptcycheck = array;
	
})

function cityfeeinfo(){
	$.ajax({
		url : "cityfee",
		data : {"groom_no" : groom_no},
		success : function(result){
			let gblocation2 = 0;
			let sumfee = 0;
			for(let i=0; i<result.length; i++){
				if(gblocation2 == result[i]["gb_location"]){
					if(result[i]["bc_no"]==1){
						sumfee += result[i]["gb_emptyfee"];
					}else if(result[i]["bc_no"]==2){
						$("#villaimg"+result[i]["gb_location"]).html('<img width="100%;" src="../img/game/building/villa'+result[i]["p_order"]+'.png">');
						sumfee += result[i]["gb_villafee"];
					}else if(result[i]["bc_no"]==3){
						$("#buildingimg"+result[i]["gb_location"]).html('<img width="100%;" src="../img/game/building/building'+result[i]["p_order"]+'.png">');
						sumfee += result[i]["gb_buildingfee"];
					}else if(result[i]["bc_no"]==4){
						$("#hotelimg"+result[i]["gb_location"]).html('<img width="100%;" src="../img/game/building/hotel'+result[i]["p_order"]+'.png">');
						sumfee += result[i]["gb_hotelfee"];
					}			
				}else{
					if(gblocation2!=0){
						let sumfee1 = Math.floor(parseInt(sumfee) / 10000);
						let sumfee2 = parseInt(sumfee)%10000/1000;
						if(sumfee1==0){
							$("#cityfee"+result[i-1]["gb_location"]).html(sumfee2+"천원");
						}else{
							if(sumfee2==0){
								$("#cityfee"+result[i-1]["gb_location"]).html(sumfee1+"만원");
							}else{
								$("#cityfee"+result[i-1]["gb_location"]).html(sumfee1+"만"+sumfee2+"천원");
							}
						}
						sumfee = 0;
					}
					
					if(result[i]["bc_no"]==1){
						sumfee += result[i]["gb_emptyfee"];
					}else if(result[i]["bc_no"]==2){
						$("#villaimg"+result[i]["gb_location"]).html('<img width="100%;" src="../img/game/building/villa'+result[i]["p_order"]+'.png">');
						sumfee += result[i]["gb_villafee"];
					}else if(result[i]["bc_no"]==3){
						$("#buildingimg"+result[i]["gb_location"]).html('<img width="100%;" src="../img/game/building/building'+result[i]["p_order"]+'.png">');
						sumfee += result[i]["gb_buildingfee"];
					}else if(result[i]["bc_no"]==4){
						$("#hotelimg"+result[i]["gb_location"]).html('<img width="100%;" src="../img/game/building/hotel'+result[i]["p_order"]+'.png">');
						sumfee += result[i]["gb_hotelfee"];
					}

				}
				if(i==result.length-1){
					let sumfee1 = Math.floor(parseInt(sumfee) / 10000);
					let sumfee2 = parseInt(sumfee)%10000/1000;
					if(sumfee1==0){
						$("#cityfee"+result[i]["gb_location"]).html(sumfee2+"천원");
					}else{
						if(sumfee2==0){
							$("#cityfee"+result[i]["gb_location"]).html(sumfee1+"만원");
						}else{
							$("#cityfee"+result[i]["gb_location"]).html(sumfee1+"만"+sumfee2+"천원");
						}
					}
					sumfee = 0;
				}
				
				gblocation2 = result[i]["gb_location"];
			}
			
		}
	})
}

function playerinfo(){
	$.ajax({
		url : "getplayerinfo",
		data : {"groom_no" : groom_no},
		success : function(result){
			for(let i=0; i<result.length; i++){
					let html = "";
					html += '<div class="row">'+
							'<div class="col-md-3 my-3">'+
								'<img class="my-2" alt="" src="../img/profile/'+result[i]["ac_profileimg"]+'">'+
								'<img class="my-2" alt="" src="../img/game/player'+result[i]["p_order"]+'.png">'+
							'</div>'+
							'<div class="col-md-9">'+
								'<h4 id="player_nickname" class="my-2">'+result[i]["ac_nickname"]+'</h4>'+
								'<span id="player_money" class="my-2">'+parseInt(result[i]["p_money"]).toLocaleString('ko-KR')+'원</span>'+
								'<div class="row g-0 my-2">';
					let city = result[i]["mycitylist"];
					let location = 0;
					let red =0;		let green = 0;		let yellow = 0;		let blue = 0;		let black = 0;
					for(let i=0; i<city.length; i++){
						if(location==city[i]["gb_location"]){
							
						}else{
							if(city[i]["gb_location"]==1 || city[i]["gb_location"]== 3 || city[i]["gb_location"]==4 || city[i]["gb_location"]==6 || city[i]["gb_location"]==8 || city[i]["gb_location"]==9	){
								red += 1;
							}
							else if(city[i]["gb_location"]==11 || city[i]["gb_location"]== 13 || city[i]["gb_location"]==14 || city[i]["gb_location"]==16 || city[i]["gb_location"]==18 || city[i]["gb_location"]==19	){
								green += 1;
							}
							else if(city[i]["gb_location"]==21 || city[i]["gb_location"]== 23 || city[i]["gb_location"]==24 || city[i]["gb_location"]==26 || city[i]["gb_location"]==27 || city[i]["gb_location"]==29	){
								yellow += 1; 
							}
							else if(city[i]["gb_location"]==31 || city[i]["gb_location"]== 33 || city[i]["gb_location"]==34 || city[i]["gb_location"]==36 || city[i]["gb_location"]==37){
								blue += 1;
							}
							else if(city[i]["gb_location"]==5 || city[i]["gb_location"]== 15 || city[i]["gb_location"]==25 || city[i]["gb_location"]==32 || city[i]["gb_location"]==39){
								black += 1;
							}
						}
						location = city[i]["gb_location"];
					}
					if(red>0){
						html +=	'<div class="col-md-1 player_city" >'+
									'<div class="player_redcity">　</div>'+
									'<div class="player_cityamount">'+red+'</div>'+
								'</div>';
					}
					if(green>0){
						html += '<div class="col-md-1 player_city">'+
									'<div class="player_greencity">　</div>'+
									'<div class="player_cityamount"  >'+green+'</div>'+
								'</div>';
					}
					if(yellow>0){
						html +=	'<div class="col-md-1 player_city">'+
									'<div class="player_yellowcity" >　</div>'+
									'<div class="player_cityamount" >'+yellow+'</div>'+
								'</div>';
					}
					if(blue>0){
						html += '<div class="col-md-1 player_city" >'+
									'<div class="player_bluecity" >　</div>'+
									'<div class="player_cityamount" >'+blue+'</div>'+
								'</div>';
					}
					if(black>0){
						html += '<div class="col-md-1 player_city" >'+
									'<div class="player_blackcity" >　</div>'+
									'<div class="player_cityamount" >'+black+'</div>'+
								'</div>';
					}
					html += '</div></div><br><br>';	
					if(player["ac_nickname"]==result[i]["ac_nickname"]){
						if(result[i]["p_turn"]==1){
							html += '<div class="col-md-4 offset-8 d-flex justify-content-end my-2"><button class="form-control" onclick="gameout()">나가기</button></div></div>';
						}else{
							html += '<div class="col-md-4 offset-8 d-flex justify-content-end my-2"></div></div>';	
						}
						$("#playerinfo1").html(html);
					}else{
						html += '</div>'
						if(player["p_order"]+1 == result[i]["p_order"] || player["p_order"]+1 == result[i]["p_order"]+4){
							$("#playerinfo2").html(html);	
						}else if(player["p_order"]+2 == result[i]["p_order"] || player["p_order"]+2 == result[i]["p_order"]+4){
							$("#playerinfo3").html(html);
						}else if(player["p_order"]+3 == result[i]["p_order"] || player["p_order"]+3 == result[i]["p_order"]+4  ){
							$("#playerinfo4").html(html);
						}
					}
					
	
			}
		
		}
	})
	
}

let movecheck = 0;
function gameout(){
	movecheck = 1;
	bankruptcy();
}

/*window.onbeforeunload = function () {
	if(movecheck==1){
		bankruptcy();
	}else{
		
	}
	
}
*/


// '<div><img height="70px" id="dice1" alt="" src="../img/game/white.png"><img height="70px" id="dice2" alt="" src="../img/game/white.png"></div>'+
function showdicetable(){
/*	let html = 
		'<div class="row text-center"><button id="dicebtn" type="button" onclick="throwDice()">ROLL!</button></div>'+
		'<input type="text" value="0" id="inputdice">'+
		'<button onclick="turnend()">턴 종료</button></div>';*/
		
		let html = 
		'<button id="dicebtn" onclick="throwDice()">ROLL !!</button>';
		//'<input type="text" value="0" id="inputdice">'
		//'<button onclick="turnend()">턴 종료</button></div>';
	$("#dicetable").html(html);
}

	
function onMessage(event) { // 소켓으로부터 receive
	let result = JSON.parse(event.data);
	if(groom_no==result[0]["groom_no"]){
		let check = true;
		if(result[0]["type"]=="diceaction"){
			document.getElementById("dice1").src = "/HEYUM/img/game/dice"+result[0]["dice1"]+".gif";
			document.getElementById("dice2").src = "/HEYUM/img/game/dice"+result[0]["dice2"]+".gif";
			setTimeout(function() { // 주사위 애니매이션 대기시간 1.3초
				let sum = result[0]["sum"];
				if(sum=="2" || sum=="4" || sum=="5" || sum=="9"){	
					$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 주사위를 굴려 "+result[0]["sum"]+"가 나왔습니다<br>" );
				}else{
					$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 주사위를 굴려 "+result[0]["sum"]+"이 나왔습니다<br>" );	
				}
				if(result[0]["startpoint"]==300000){
					$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 월급 "+parseInt(result[0]["startpoint"]).toLocaleString('ko-KR')+"원을 얻으셨습니다<br>" );
				}
				if(result[0]["uisland"]==0){
					move(result[0]['mover'],result[0]["sum"],result[0]["mover_location"] );
				}
						
			}, 1300);	 // 주사위 애니매이션 대기시간 1.3초		
		}
		if(result[0]["type"]=="dice"){ // 주사위 굴렸을때 
	
			alllocation = result;		
			/*let sum = result[0]["sum"];
			if(sum=="2" || sum=="4" || sum=="5" || sum=="9"){	
				$("#gamemsg").append("[시스템] "+result[0]["player"]+"님이 주사위를 굴려 "+result[0]["sum"]+"가 나왔습니다<br>" );
			}else{
				$("#gamemsg").append("[시스템] "+result[0]["player"]+"님이 주사위를 굴려 "+result[0]["sum"]+"이 나왔습니다<br>" );	
			}
			if(result[0]["startpoint"]==300000){
				$("#gamemsg").append("[시스템] "+result[0]["player"]+"님이 월급 "+parseInt(result[0]["startpoint"]).toLocaleString('ko-KR')+"원을 얻으셨습니다<br>" );
			}*/
		} // 주사위 굴렸을때 end 
		if(result[0]["type"]=="city"){ // 턴정보를 받아 주사위 활성화시킨다
			$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 "+result[0]["cityname"]+"에 도착했습니다<br>" );				
		}
		
		if(result[0]["type"]=="turn"){ // 턴정보를 받아 주사위 활성화시킨다
			
			if(player["ac_nickname"]==result[0]["ac_nickname"]){
				turnstart();
				showdicetable();
			}else{
				let html = "";
				$("#dicetable").html(html);
			}
			let turnmsgcheck = true;
			for(let i=0; i<bankruptcycheck.length; i++){
				if(result[0]["ac_nickname"]==bankruptcycheck[i]["nickname"] && bankruptcycheck[i]["bankruptcy"]==1){
					turnmsgcheck = false;
				}
			}
			if(turnmsgcheck){
				$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님의 턴입니다<br>" );
			}
	
		}
		if(result[0]["type"]=="fee"){
			let currentfee = parseInt(result[0]["fee"]).toLocaleString("ko-KR")+"원";
			$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 "+result[0]["recievenickname"]+"님에게 통행료 "+currentfee+"을 지불했습니다.<br>" );
		}
		if(result[0]["type"]=="buycity"){
			let html = "";
			if(result[0]["location"]==5 || result[0]["location"]==15 || result[0]["location"]==32 ){
				$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 "+result[0]["cityname"]+"를 구매하였습니다.<br>");
			}else if(result[0]["location"]==25 || result[0]["location"]==39){
				$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 "+result[0]["cityname"]+"을 구매하였습니다.<br>");
			}else{
				$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 "+result[0]["cityname"]+" 도시의 " );
				let buildingclass = result[0]["buildingclass"];
				for(let i=0; i<buildingclass.length; i++){
					if(buildingclass[i]==1){html += "빈땅";}
					else if(buildingclass[i]==2){html += "별장";} 
					else if(buildingclass[i]==3){html += "빌딩";}
					else if(buildingclass[i]==4){html += "호텔";}
					if(i!=buildingclass.length-1){
						html += ",";
					}
				}
				$("#gamemsg").append(html+"을 구매하였습니다.<br>");
			}
		}
		if(result[0]["type"]=="sellcity"){
			let html = "";
			html += result[0]["ac_nickname"]+"님이 ";
			for(let i=0; i<result.length; i++){
				html += result[i]["cityname"];
				if(i!=result.length-1){
					html += ", ";
				}else{
					html += " 도시를 "+parseInt(result[0]["sum"]).toLocaleString('ko-KR') +"원에 판매하였습니다.<br>";
				}
			}
			$("#gamemsg").append("[시스템] "+html);
		}
		
		if(result[0]["type"]=="givesocialfund"){
			socialfund += 200000;
			$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 사회복지기금으로 20만원을 기부하셨습니다.<br>");
		}
			
		if(result[0]["type"]=="receivesocialfund"){
			if(socialfund==0){
				$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 사회복지기금 수령처에 도착했지만 사회복지기금이 없어서 받지 못했어요 아쉬워요.<br>")	
			}else{
				$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 사회복지기금 "+parseInt(socialfund).toLocaleString('ko-KR')+"원을 받으셨습니다.<br>");
			}
			socialfund = 0;
		}
		if(result[0]["type"]=="uislandturn"){
			if(result[0]["uisland"]==1){
				$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님은 무인도에 갇히셨습니다. (다음턴에 탈출 가능)<br>");
			}else{
				$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님은 무인도에 갇히셨습니다. (남은턴 : "+(parseInt(result[0]["uisland"])-1)+")<br>");	
			}
			
		}
		if(result[0]["type"]=="goldkeyearn"){
			$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 "+result[0]["gk_name"] +" 카드를 뽑아서 "+parseInt(result[0]["gk_value"]).toLocaleString('ko-KR')+"원을 받으셨습니다.<br>");
		}
		if(result[0]["type"]=="goldkeypay"){
			$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 "+result[0]["gk_name"] +" 카드를 뽑아서 "+parseInt(result[0]["gk_value"]).toLocaleString('ko-KR')+"원을 지불 하셨습니다.<br>");
		}
		if(result[0]["type"]=="goldkeymoveaction"){
			setTimeout(function() {
				move(result[0]['mover'],result[0]["move"],result[0]["p_location"] );
			}, 1300);
		}
		if(result[0]["type"]=="goldkeymove"){		
			if(result[0]["gk_no"]==10){
				$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 "+result[0]["gk_name"] +" 카드를 뽑아서 한바퀴 돌아 제자리로 도착했습니다.<br>");	
			}else{
				$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 "+result[0]["gk_name"] +" 카드를 뽑아서 뒤로 "+(-result[0]["gk_value"])+"칸 이동했습니다.<br>");
			}
			if(result[0]["startpoint"]==300000){
				$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 월급 "+parseInt(result[0]["startpoint"]).toLocaleString('ko-KR')+"원을 얻으셨습니다<br>" );
			}
			alllocation = result[0]["alllocation"];
		}
		if(result[0]["type"]=="goldkeymove2"){
			$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 "+result[0]["gk_name"] +" 카드를 뽑았습니다.<br>");
			if(result[0]["startpoint"]==300000){
				$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 월급 "+parseInt(result[0]["startpoint"]).toLocaleString('ko-KR')+"원을 얻으셨습니다<br>" );
			}
			alllocation = result[0]["alllocation"];
		}
		if(result[0]["type"]=="bankruptcy"){
			$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 파산했습니다.<br>");
			bankruptcycheck = result[0]["bankruptcycheck"];
			let gameset = 0;
			for(let i=0; i<bankruptcycheck.length; i++){
				if(bankruptcycheck[i]["bankruptcy"]==1){
					gameset += 1;
				}
			}
			if(gameset==bankruptcycheck.length-1 && bankruptcycheck.length!=1){
				endgame();
			}else{
				if(result[0]["ac_nickname"]==player["ac_nickname"]){
					turnend();
				}
			}
			
		}
		if(result[0]["type"]=="item"){
			$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 "+result[0]["gk_name"] +"를 획득했습니다.<br>");
		}
		if(result[0]["type"]=="goldkeylist"){
			goldkeylist = result;
		}
		if(result[0]["type"]=="spacetravel"){
			setTimeout(function() {
				move(result[0]['mover'],result[0]["move"],result[0]["p_location"] );
				if(result[0]["startpoint"]==300000){
					$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 월급 "+parseInt(result[0]["startpoint"]).toLocaleString('ko-KR')+"원을 얻으셨습니다<br>" );
				}
				$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 우주여행을 통해 "+result[0]["cityname"]+"에 도착했습니다.<br>");
				alllocation = result[0]["alllocation"];
			}, 1300);

		}
		if(result[0]["type"]=="paymoney"){
			$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 "+result[0]["gk_name"]+"카드를 뽑아서 "+parseInt(result[0]["topay"]).toLocaleString('ko-KR')+"원을 지불하였습니다.<br>");
		}
		if(result[0]["type"]=="endgame"){
			$.ajax({
				url : "groomdelete",
				data : {"groom_no" : groom_no},
				success : function(re){
					if(re==1){
						gameend = 1;
						if(result[0]["ac_nickname"]==player["ac_nickname"]){
							swal({
						        title: "게임종료",
						        text: "축하합니다 게임에서 승리하셨습니다!",
						        icon: "success",
						        buttons: '확인'
						    }).then((value) => {
						        location.href = "/HEYUM/game/lobby.jsp";
						    });
						}else{
							swal({
						        title: "게임종료",
						        text: "게임에서 패배하셨습니다!",
						        icon: "success",
						        buttons: '확인'
						    }).then((value) => {
						        location.href = "/HEYUM/game/lobby.jsp";
						    });
						}
					}else{
						alert("DB 오류");
					}
				}
			})
			
			
		}
		if(result[0]["type"]=="dicedouble"){
			$("#gamemsg").append("[시스템] "+result[0]["ac_nickname"]+"님이 3연속 주사위 더블이 나와서 무인도로 갔습니다.<br>");
			if(player["ac_nickname"]==result[0]["ac_nickname"]){
				setTimeout(function(){
					swal({
				        text: "3연속 더블이 나와 무인도로 이동합니다.",
				        icon: "info",
				        buttons: '확인'
				    }).then((value) => {
							alllocation = result[0]["alllocation"];
							turnend();
				    });	
				},1300)
			}
		}
		
		///  채팅 
		if(result[0]["type"]=="chat2"){
			check=false;
			$("#gamemsg2").append(result[0]["id"] + " : " + result[0]["content"] + '<br>');	
			$("#gamemsg2").css("color","white");
			$("#tablescroll2").scrollTop($("#tablescroll2")[0].scrollHeight);		
		}
		
		if(result[0]["type"]=="emo"){
			check=false;
			let html = result[0]["id"] +' : '+ '<img src="/HEYUM/img/imoji/kk'+result[0]['content']+'.gif" width="90px">' +  '<br>';
			$("#gamemsg2").append(html);
			$("#tablescroll2").scrollTop($("#tablescroll2")[0].scrollHeight);	
		}
		///  채팅 
		if(check){
			cityfeeinfo();
			playerinfo();
			getgameboard(alllocation);
		}	
		$("#tablescroll").scrollTop($("#tablescroll")[0].scrollHeight);

	}

}

function turnstart(){
	gkturnendcheck = 0;
	if(gameend==0){		
		dicedouble = 0;
		if(bankruptcycheck2==1){
			dicethrow = 0;
			turnend();
		}else{
			swal({
		        text: "턴시작",
		        icon: "info",
		        buttons: '확인'
		    }).then((value) => {
		        
					if(uisland>0){
						swal({
					        text: "무인도에 갇혀있습니다 ("+uisland+"턴 남음)",
					        icon: "info",
					        buttons: '확인'
					    }).then((value) => {
					        for(let i=0; i<goldkeylist.length; i++){
								if(goldkeylist[i]["gk_no"]==26 && goldkeylist[i]["p_no"]==player["p_no"]){
									$('#itemmodal').modal('show');
								}
							}
					    });
						
					}
					if(player["p_location"]==10){
						spacetravel();
					}
		        
		    });

			
				
		}
	}
}

function endgame(){
	swal({
        text: "게임종료",
        icon: "info",
        buttons: '확인'
    }).then((value) => {
        
			$.ajax({
				url : "endgame",
				data : {"groom_no" : groom_no, "bankruptcy" : JSON.stringify(bankruptcycheck)},
				success : function(result){
					if(result==1){
						alert("DB 오류");
					}else{
						webSocket.send(result);
					}
					
				}
			});
        
    });

	
	
}

let travelcount = 0;
function spacetravel(){
	swal({
        title: "우주여행",
        text: "가고 싶은 도시를 선택해주세요.",
        icon: "info",
        buttons: '확인'
    }).then((value) => {
        
			travelcount = 1;
        
    });
	
}

function gospacetravel(i){
	if(i<10){
		turncheck = 1;
	}
	if(travelcount==1){
		$.ajax({
			url : "spacetravel",
			data : {"p_no" : player["p_no"], "ac_nickname" : player["ac_nickname"], "mover" : player["p_order"] , "groom_no" : groom_no, "gb_location" : i},
			success : function(result){
				let move = 0 ; 
				if(10>i){
					move = i - 10 + 40;	
				}
				else{
					move = i - 10;
				}	 
				let countmyline = 0 ;
				let moveline = Math.floor((10+move-1)/ 10);
				let timecount = moveline - countmyline ;				
				webSocket.send(JSON.stringify(result));
				setTimeout(function() {
					player["p_location"]=i;
					setTimeout(function(){
						city();
						travelcount = 0;
					},500)	
				},(2500+(1500*timecount) ) );	
			}
		});
	}
}

function ifuisland(){
	if(uisland>0){
		let island = new Array();
		let object = new Object();
		object.uisland = uisland;
		object.groom_no = groom_no;
		object.ac_nickname = player["ac_nickname"];
		object.type = 'uislandturn';
		island.push(object);
		webSocket.send(JSON.stringify(island));
	}
}


let dicethrow=1;

function throwDice(){
/*	$("#dicebtn").attr("disabled", true);*/ /* 자기 차례때 쇼다이스 메소드 실행시키면 이거 사용가능 */
	//let inputdice = parseInt($("#inputdice").val());
	/*let dice1 = 0;
	let dice2 = 0;
	let sum =0;*/

	/*if(inputdice==0){
		dice1 = Math.floor(6 * Math.random()) + 1;
		dice2 = Math.floor(6 * Math.random()) + 1;
		sum = dice1+dice2;
	}else{
		sum = inputdice;
	}*/
	
	let dice1 = Math.floor(6 * Math.random()) + 1;
	let dice2 = Math.floor(6 * Math.random()) + 1;
	let sum = dice1+dice2;
	if(dice1==dice2 && dicedouble==2){
		uisland = 3;
		let diceaction = [{"type" : "diceaction" , "dice1" : dice1 , "dice2" : dice2, "sum" : sum , "groom_no" : groom_no ,"mover" : player['p_order'] , "mover_location" : player['p_location'] , "ac_nickname" : player["ac_nickname"], "uisland" : uisland} ];
		webSocket.send(JSON.stringify(diceaction));
		$.ajax({
			url : "dicedouble",
			data : {"groom_no" : groom_no , "p_no" : player["p_no"], "p_location" : player["p_location"], "ac_nickname" : player["ac_nickname"]},
			success : function(result){
				player["p_location"]=20;
				webSocket.send(result);
			}
		});
	}else{
	let diceaction = [{"type" : "diceaction" , "dice1" : dice1 , "dice2" : dice2, "sum" : sum , "groom_no" : groom_no ,"mover" : player['p_order'] , "mover_location" : player['p_location'] , "ac_nickname" : player["ac_nickname"], "uisland" : uisland } ];
	if(dice1 == dice2){ diceaction = [{"type" : "diceaction" , "dice1" : dice1 , "dice2" : dice2, "sum" : sum , "groom_no" : groom_no ,"mover" : player['p_order'] , "mover_location" : player['p_location'] , "ac_nickname" : player["ac_nickname"], "uisland" : 0 } ];}
	webSocket.send(JSON.stringify(diceaction));
	
	let countmyline = Math.floor(player['p_location'] / 10);
	let moveline = Math.floor((player['p_location']+sum-1)/ 10);
	let timecount = moveline - countmyline ;
	setTimeout(function() {
				if(uisland>0){
					if(dice1 == dice2){ 
						dicethrow = 0;   
					/*	alert("무인도 탈출 성공!");*/
						uisland = 0;
						$.ajax({
							url : "dice",
							data : {"p_no" : player["p_no"] , "sum" : sum, "groom_no" : groom_no , "ac_nickname" : player["ac_nickname"]},
							success : function(result){
								if(result==0){
									alert("db오류");
								}else{
									if((player["p_location"]+sum)>39 ){
										turncheck = 1;
										player.p_location = player["p_location"]+sum-40;
									}else{
										player.p_location = player["p_location"]+sum;	
									}
									
									city();
									webSocket.send(result);
								}
							}
						});
					}else{
						ifuisland();
						uisland -= 1;
						turnend();
					}
				}else{
					if(dicethrow==0){
						swal({
					        text: "이번턴에 더이상 주사위를 던질 수 없습니다.",
					        icon: "warning",
					        buttons: '확인'
					    }).then((value) => {
					        
					    });
					}else{
						if(dice1==dice2){
							dicedouble += 1
							dicethrow = 1;
						}else{
							$("#dicetable").html("");
							dicethrow = 0;
						}							
						$.ajax({
							url : "dice",
							data : {"p_no" : player["p_no"] , "sum" : sum, "groom_no" : groom_no , "ac_nickname" : player["ac_nickname"]},
							success : function(result){
								if(result==0){
									alert("db오류");
								}else{                       
									/*alert(sum+"칸 이동");*/
									if((player["p_location"]+sum)>39 ){
										turncheck = 1;
										player.p_location = player["p_location"]+sum-40;
									}else{
										player.p_location = player["p_location"]+sum;	
									}	
									city();
									webSocket.send(result);
								}
							}
						});	
					}
				}
	},(2500+(1500*timecount)));
	}
}

$(function(){
	$.ajax({
		url : "gameboard",
		data : {"groom_no" : groom_no},
		success : function(result){
			cjl = JSON.parse(result.split("@@")[0]);
			showtable();
			alllocation = JSON.parse(result.split("@@")[1]);
			for(let j = 0  ; j < alllocation.length ; j++){
				let html2 = '<img width="35px" height="35px" id="player'+alllocation[j]['p_order']+'" class="player" alt="" src="/HEYUM/img/game/player'+alllocation[j]['p_order']+'.png">';
				$("#player"+alllocation[j]['p_order']+"board"+alllocation[j]['location']).html(html2);
			}

		}
	});
});

let cjl;

function showplayer1(){
	getgameboard(alllocation);
}

function showplayer(alllocation){
	for(let j = 0  ; j < alllocation.length ; j++){
		let html2 = '<img width="35px" height="35px" id="player'+alllocation[j]['p_order']+'" class="player" alt="" src="/HEYUM/img/game/player'+alllocation[j]['p_order']+'.png">';
		$("#player"+alllocation[j]['p_order']+"board"+alllocation[j]['location']).html(html2);
	}
}

function getgameboard(alllocation){
	// 게임판(도시이름) 출력
	showtable();
	// 플레이어 위치 출력
	showplayer(alllocation);
	
}

function city(){
	
	$.ajax({
		url : "city",
		data : {"location" : player["p_location"] , "p_no" : player["p_no"] , "groom_no" : player["groom_no"], "ac_nickname" : player["ac_nickname"]},
		success : function(re){
			
			let result = JSON.parse(re);
			if(result[0]["resultno"]==1){
				swal({
			        text: "빈땅 : 구매 가능",
			        icon: "info",
			        buttons: '확인'
			    }).then((value) => {
			        
					$("#modalinputgn").val(player["p_location"]);
					getcity(1);
					$('#buymodal').modal('show');
			        
			    });
				
			}else if(result[0]["resultno"]==2){
				swal({
			        text: "내 땅",
			        icon: "info",
			        buttons: '확인'
			    }).then((value) => {
			       
						getcity(2);
			        
			    });
			}else if(result[0]["resultno"]==3){
				swal({
			        text: "황금열쇠",
			        icon: "info",
			        buttons: '확인'
			    }).then((value) => {
			        
						goldkeycheck();
			        
			    });

			}else if(result[0]["resultno"]==4){
				swal({
			        text: "무인도",
			        icon: "info",
			        buttons: '확인'
			    }).then((value) => {
			        
						uisland = 3;
						turnend();
			       
			    });
				
			}else if(result[0]["resultno"]==5){
				swal({
			        text: "우주여행",
			        icon: "info",
			        buttons: '확인'
			    }).then((value) => {
			        
						turnend();
			        
			    });
			}else if(result[0]["resultno"]==6){
				swal({
			        text: "사회복지기금 수령처",
			        icon: "info",
			        buttons: '확인'
			    }).then((value) => {
			       
						receivesocialfund();
			        
			    });
				
			}else if(result[0]["resultno"]==7){
				swal({
			        text: "사회복지기금 접수처",
			        icon: "info",
			        buttons: '확인'
			    }).then((value) => {
			        
						givesocialfund();
			        
			    });
			}else if(result[0]["resultno"]==8){
				swal({
			        text: "출발",
			        icon: "info",
			        buttons: '확인'
			    }).then((value) => {
			       
						if(dicethrow==0){
							turnend();
						}else{
							swal({
						        text: "주사위를 한번 더 던져주세요.",
						        icon: "info",
						        buttons: '확인'
						    }).then((value) => {
						        
						    });
						}
			        
			    });
				
			}else{
				$("#fee").html(parseInt(result[0]["fee"]).toLocaleString('ko-KR'));
				$("#modalinputfee").val(result[0]["fee"]);
				$("#city_name").html(result[0]["cityname"]);
				$('#feemodal').modal('show');
			}
			webSocket.send(JSON.stringify(result));
		}
	});
}

function cancelbtn(){
	$("#modalclosebtn2").click();
	if(dicethrow==0){
		turnend();
	}else{
		swal({
	        text: "주사위를 한번 더 던져주세요.",
	        icon: "info",
	        buttons: '확인'
	    }).then((value) => {
	        
	    });
	}
}

function getcity(type){
	$.ajax({
		url : "getcity",
		data : {"p_location" : player["p_location"] , "type" : type, "groom_no" : groom_no, "turncheck" : turncheck} ,
		success : function(result){
			if(type==2){
				if(result==1){
					swal({
				        title: "내 땅",
				        text: "더 이상 구매가 불가능합니다.",
				        icon: "info",
				        buttons: '확인'
				    }).then((value) => {
				        
							if(dicethrow==0){
								turnend();
							}else{
								swal({
							        text: "주사위를 한번 더 던져주세요.",
							        icon: "info",
							        buttons: '확인'
							    }).then((value) => {
							        
							    });
							}
				        
				    });

				}else{
					$("#modal_contents").html(result);
					$('#buymodal').modal('show');
					getCheckboxValue();
				}
			}else{
				$("#modal_contents").html(result);
				getCheckboxValue();
			}
			
			
		}
	})
}

// 턴 종료 메소드
function turnend(){
	$("#dicetable1").css("display","none");
	$.ajax({
		url : "turnend" ,
		data : {"groom_no" : player["groom_no"],  "p_no" : player["p_no"]} ,
		success : function(result){
			if(result==2){
				alert("오류 - 관리자에게 문의");
			}else if(bankruptcycheck2==1){
				webSocket.send(result);
			}else{
				swal({
				        text: "턴을 종료합니다.",
				        icon: "info",
				        buttons: '확인'
				    }).then((value) => {
				       
							dicethrow = 1;
							webSocket.send(result);
				        
				    });
			}
		}
	});
}

function fee(){
	let fee = $("#modalinputfee").val();
	$.ajax({
		url : "fee" ,
		data : {"fee" : fee, "p_no" : player["p_no"], "gb_location" : player["p_location"], "groom_no" : groom_no, "ac_nickname" : player["ac_nickname"]} ,
		success : function(result){
			if(JSON.parse(result)[0]["resultno"]==1){
				swal({
			        text: "통행료 지불 완료",
			        icon: "success",
			        buttons: '확인' 
			    }).then((value) => {
			        
						$("#modalclosebtn").click();
						webSocket.send(result);
						lackmoney = 0;
						topay = 0;
						if(dicethrow==0){
							turnend();
						}else{
							swal({
						        text: "주사위를 한번 더 던져주세요.",
						        icon: "info",
						        buttons: '확인'
						    }).then((value) => {
						        
						    });
						}
			        
			    });

			}else if(JSON.parse(result)[0]["resultno"]==3){
				alert("DB 오류");
			}else{
				swal({
			        text: "현재 보유자금이 부족합니다.",
			        icon: "warning",
			        buttons: '확인'
			    }).then((value) => {
			        
						paytype = "fee";
						topay = JSON.parse(result)[0]["fee"];
						lackmoney = JSON.parse(result)[0]["lackmoney"];
						nomnoey();
						$("#modalclosebtn").click();
			        
			    });

			}
		}
	});
}


// 파산
function bankruptcy(){
	$.ajax({
		url : "bankruptcy",
		data : {"groom_no" : groom_no , "p_no" : player["p_no"], "ac_nickname" : player["ac_nickname"], "bankruptcycheck" : JSON.stringify(bankruptcycheck)},
		success : function(result){
			if(result==1){
				alert("db오류");
			}else{
				bankruptcycheck =result[0]["bankruptcycheck"];
				bankruptcycheck2 = 1;
				webSocket.send(JSON.stringify(result));
				player["p_location"] = 40;
				swal({
			        text: "파산!!",
			        icon: "error",
			        buttons: '확인'
			    }).then((value) => {
			       
						dicethrow = 0;
						if(movecheck==1){
							location.href = "/HEYUM/game/lobby.jsp";
						}
			        
			    });
				
				
			}
		}	
	});
	
}




function buy(){
	let sumprice = $("#sumprice").val();
	if(buildingclass.length==0){
		swal({
	        text: "구매할 건물 종류를 선택해주세요.",
	        icon: "info",
	        buttons: '확인'
	    }).then((value) => {
	        
	    });
	}else{
		if($("#buildingclass1").is(":checked") || $("#checkedclass1").is(":checked")){
			$.ajax({
				url : "buycity",
				data : {"location" : player["p_location"], "p_no" : player["p_no"], "groom_no" : groom_no, "buildingclass" : JSON.stringify(buildingclass), "sumprice" : sumprice, "ac_nickname" : player["ac_nickname"]  },
				success : function(result){
					if(JSON.parse(result)[0]["resultno"] == 1){
						swal({
					        text: "소유 자금이 부족합니다.",
					        icon: "warning",
					        buttons: '확인'
					    }).then((value) => {
					        
					    });
						
					}else if(JSON.parse(result)[0]["resultno"]==3){
						swal({
					        text: "구매 성공",
					        icon: "success",
					        buttons: '확인'
					    }).then((value) => {
					        
								$("#modalclosebtn2").click();
								webSocket.send(result);
								if(dicethrow==0){
									turnend();
								}else{
									swal({
								        text: "주사위를 한번 더 던져주세요.",
								        icon: "info",
								        buttons: '확인'
								    }).then((value) => {
								        
								    });
								}
					        
					    });
					}else{
						alert("DB오류");
					}
				}
			});
		}else{
			alert("건물을 구매하시려면 빈땅을 무조건 구매하셔야 합니다.");
		}
		
	}
	buildingclass = [];
}

let buildingclass = [];	// 선택한 건물 종류 저장하는 배열

// 도시구매 모달창에서 체크박스 선택시 이벤트
function getCheckboxValue()  {
	buildingclass = [];
	for(let i=1; i<5; i++){
		if($("#buildingclass"+i).is(":checked")){
		$("#buildingclass"+i).prop('checked',true);
		buildingclass.push(i);
		}else{
			$("#buildingclass"+i).prop('checked',false);
			for(let j = 0; j < buildingclass.length; j++) {
			  if(buildingclass[j] == 'i')  {
			    buildingclass.splice(j, 1);
			    j--;
			  }
			}
		}
	}
	$.ajax({
		url : "getprice",
		data : {"buildingclass" : JSON.stringify(buildingclass) , "p_location" : player["p_location"]},
		success : function(result){
			let sumprice = parseInt(result).toLocaleString('ko-KR')+"원";
			$("#result").html("전체가격 : "+sumprice);
			$("#sumprice").val(result);

			
		}
	});
}

//////////////////////////// 모달 위 모달 띄우기 /////////////////////////////

//모달 목록 (Modal List)
var recentModalList = [];

// 무한루프 오류 방지용 
$(document).ready(function () {
    //Modal on Modal : jquery-1.9.1.js:3257 Uncaught RangeError: Maximum call stack size exceeded.
    $.fn.modal.Constructor.prototype.enforceFocus = function () { };

    //모달이 뜰 때 객체를 리스트에 추가 (Add modal to list)
    $('.modal').on('shown.bs.modal', function (e) {
        recentModalList.push(e.target);
    });

    //모달이 닫힐 때 객체를 리스트에서 삭제. (Remove modal from list)
    $('.modal').on('hide.bs.modal', function (e) {
        customModalClosed(e);
    });
});

function customModalClosed(e) {
    //나를 지운다.(Remove me in list)
    for (var i = recentModalList.length - 1; i >= 0; i--) {
        if (recentModalList[i] == e.target) {
            recentModalList.splice(i, 1);
        }
    }
    //이전 모달이 있으면 포커싱.(Focus to before modal)
    if (recentModalList.length > 0) {
        recentModalList[recentModalList.length - 1].focus();
    }
};

////////////////////////////////////////////////////////////////////


let mycitylist = [];	// 내 도시위치 저장용

// 도시판매 클릭시
function sellcity(){
	mycitylist = [];
	$.ajax({
		url :"getmycity",
		data : {"p_no" : player["p_no"], "groom_no" : groom_no},
		success : function(result){
			if(result==0){
				swal({
			        text: "판매 가능한 도시가 없습니다.",
			        icon: "info",
			        buttons: '확인'
			    }).then((value) => {
			       
			    });
			}else{
				$("#mycityinfo").html(result.split("@@")[0]);
				//mycitylist = result.split("@@")[1];
				$('#sellmodal').modal('show');
			}
			
		}
	});
	
}

// 도시판매 모달 - 선택된 도시 판매 클릭시
function sell(){
	let sum = $("#sumsell").val();
	$.ajax({
		url : "sellcity",
		data : {"sum" : sum, "mycitylist" : JSON.stringify(mycitylist), "groom_no" : groom_no, "ac_nickname" : player["ac_nickname"], "p_no" : player["p_no"]},
		success : function(jsonarray){
			webSocket.send(jsonarray);
			$.ajax({
				url :"getmycity",
				data : {"p_no" : player["p_no"], "groom_no" : groom_no},
				success : function(result){
					$("#mycityinfo").html(result.split("@@")[0]);
					$("#modalclosebtn3").click();
					if(sum<lackmoney){
						lackmoney -= sum;
						$("#moremoney").html(parseInt(lackmoney).toLocaleString('ko-KR')+"원이 더 필요합니다.<br>가지고 계신 도시를 팔아서 지불해주세요.");	
					}else{
						$("#moremoney").html(topay.toLocaleString('ko-KR')+"원을 지불해주세요.");
					}
					mycitylist = [];	
				}
			});
			
		}
	})
}

////////////////// 도시 판매모달 - 체크박스 클릭 이벤트 ////////////

// 전체선택 눌렀을때
function selectAll(selectAll)  {
	
	const checkboxes = document.getElementsByName('citycheck');
	 
	checkboxes.forEach((checkbox) => {
		checkbox.checked = selectAll.checked;
	})
	if(selectAll.checked == true){

		$.ajax({
			url :"getmycity",
			data : {"p_no" : player["p_no"], "groom_no" : groom_no},
			success : function(mycity){
				mycitylist = JSON.parse(mycity.split("@@")[1]);
				// 선택한 도시들의 총 판매금액 계산
				$.ajax({
					url : "gettotalsell",
					data : {"mycitylist" : JSON.stringify(mycitylist), "groom_no" : groom_no},
					success : function(result){
						$("#selltext").html("총 판매금액 : ");
						$("#sumsell").val(result);
						$("#sumsellmoney").html(parseInt(result).toLocaleString('ko-KR')+"원");
					}
				});
			}
		});
		
	}else{
		mycitylist = [];
		$("#selltext").html("");
		$("#sumsellmoney").html("");
	}
}

// 개별 선택시
function check(box, cityname){
	if(box.checked == true){
		mycitylist.push({gb_location : box.value, cityname : cityname});

		// 선택한 도시들의 총 판매금액 계산
		$.ajax({
			url : "gettotalsell",
			data : {"mycitylist" : JSON.stringify(mycitylist), "groom_no" : groom_no},
			success : function(result){
				$("#selltext").html("총 판매금액 : ");
				$("#sumsell").val(result);
				$("#sumsellmoney").html(parseInt(result).toLocaleString('ko-KR')+"원");
			}
		});
		
	}
	else{
		for(let i=0; i<mycitylist.length; i++){
			if(mycitylist[i]["gb_location"]==box.value){
				mycitylist.splice(i, 1);
			    i--;
			}
		}

		// 선택한 도시들의 총 판매금액 계산
		$.ajax({
			url : "gettotalsell",
			data : {"mycitylist" : JSON.stringify(mycitylist), "groom_no" : groom_no},
			success : function(result){
				$("#selltext").html("총 판매금액 : ");
				$("#sumsell").val(result);
				$("#sumsellmoney").html(parseInt(result).toLocaleString('ko-KR')+"원");
			}
		});
	}
		
}
///////////////////////////////////////////////////////////

let lackmoney = 0; // 부족한 돈
let topay = 0; // 지불해야할 돈
let paytype = "";	// 지불 종류

// 현재 소유한 돈 부족시 실행되는 메소드
function nomnoey(){
	$("#moremoney").html(parseInt(lackmoney).toLocaleString('ko-KR')+"원이 더 필요합니다.<br>가지고 계신 도시를 팔아서 지불해주세요.");
	mycitylist = [];
	$.ajax({
		url :"getmycity",
		data : {"p_no" : player["p_no"], "groom_no" : groom_no},
		success : function(mycity){
			mycitylist = mycity.split("@@")[1];
			// 선택한 도시들의 총 판매금액 계산
			$.ajax({
				url : "gettotalsell",
				data : {"mycitylist" : mycitylist, "groom_no" :groom_no},
				success : function(result){
					if(parseInt(result)<parseInt(lackmoney)){	// 내가가진 도시들 판매시 얻을 수 있는 돈보다 내야 할 돈이 많을경우 파산처리
						bankruptcy();
						$("#modalclosebtn4").click();	
					}else{	// 아닌 경우 도시 판매 모달 띄우기
						$('#nemmodal').modal('show');
					}
				}
			});
		}
	});
}

// 돈 부족 모달에서 지불 눌렀을때 이벤트
function pay(){
	let ac_no = $("#ac_no").val();
	$.ajax({
		url : "getplayer",
		data : {"ac_no" : ac_no, "groom_no" : groom_no},
		success : function(result){
			player = result;
			if(player["p_money"]<topay){
				lackmoney = topay - player["p_money"];
				$("#moremoney").html(parseInt(lackmoney).toLocaleString('ko-KR')+"원이 더 필요합니다.<br>가지고 계신 도시를 팔아서 지불해주세요.");
				swal({
			        text: "보유한 돈이 부족합니다.",
			        icon: "warning",
			        buttons: '확인'
			    }).then((value) => {
			        
			    });

			}else{
				if(paytype=="socialfund"){
					$("#modalclosebtn4").click();
					givesocialfund();
				}else if(paytype=="fee"){
					$("#modalclosebtn4").click();
					fee();
				}else{
					$("#modalclosebtn4").click();
					goldkey(gk);
				}
			}
		}
	});
	
}


/////////////////////// 이벤트 ///////////////////////////////

// 사회복지기금 접수처
function givesocialfund(){
	$.ajax({
		url : "socialfund",
		data : {"p_no" : player["p_no"], "ac_nickname" : player["ac_nickname"], "groom_no" : groom_no, "type" : 'give', "socialfundmoney" : socialfund},
		success : function(result){
			if(JSON.parse(result)[0]["resultno"]==1){
				paytype = "socialfund";
				topay = 200000;
				swal({
			        text: "보유한 돈이 부족합니다.",
			        icon: "warning",
			        buttons: '확인'
			    }).then((value) => {
			       
						lackmoney = JSON.parse(result)[0]["lackmoney"];
						nomnoey();
			        
			    });
				
			}else if(JSON.parse(result)[0]["resultno"]==2){
				alert("사회복지기금 DB 오류");
			}else{
				lackmoney = 0;
				topay = 0;
				webSocket.send(result);
				if(dicethrow==0){
					turnend();
				}else{
					swal({
				        text: "주사위를 한번 더 던져주세요.",
				        icon: "info",
				        buttons: '확인'
				    }).then((value) => {
				        
				    });
				}
			}
		}
	});
}

// 사회복지기금 수령처
function receivesocialfund(){
	$.ajax({
		url : "socialfund",
		data : {"p_no" : player["p_no"], "ac_nickname" : player["ac_nickname"], "groom_no" : groom_no, "type" : 'receive', "socialfundmoney" : socialfund},
		success : function(result){
			if(result==2){
				alert("사회복지기금 DB 오류");
			}else{
				webSocket.send(result);
				if(dicethrow==0){
					turnend();
				}else{
					swal({
				        text: "주사위를 한번 더 던져주세요.",
				        icon: "info",
				        buttons: '확인'
				    }).then((value) => {
				        
				    });
				}
			}
		}
	});
}


let gk =0; // 황금열쇠 번호 임시저장용

// 황금열쇠 이벤트
function goldkeycheck(){
	let gkcheck = true;			
	if(goldkeylist.length==30){
		let goldkey26 = "";

		for(let i=0; i<goldkeylist.length; i++){
			if(goldkeylist[i]["gk_no"]==26 && goldkeylist[i]["p_no"]!=0){
				goldkey26 = goldkeylist[i];
			}
		}
		goldkeylist = [];
		if(goldkey26!=""){
			goldkeylist.push(goldkey26);
		}
	}
	if(goldkeylist.length==0){
		let gkno = Math.floor(30 * Math.random()) + 1;
		goldkey(gkno);
		return;
	}
	let gkno = 0;
	while(true){
		gkcheck = true;
		gkno = Math.floor(30 * Math.random()) + 1;
		for(let i=0; i<goldkeylist.length; i++){
			if(gkno==goldkeylist[i]["gk_no"]){
				gkcheck = false;
			}
		}
		if(gkcheck){
			break;
		}
	}
	goldkey(gkno);

}

let gkturnendcheck = 0;
function goldkey(gk_no){
	gkturnendcheck = 0;
	$.ajax({
		url : "goldkey",
		data : {"gk_no" : gk_no , "p_no" : player["p_no"], "groom_no" : groom_no, "ac_nickname" : player["ac_nickname"]},
		success : function(result){
			$("#gk_name").html(result[0]["gk_name"]);
			$("#gk_content").html(result[0]["gk_content"]);
			$('#goldkeymodal').modal('show');
			if(result[0]["resultno"]==1){	// 돈 받는 이벤트
				topay = 0;
				lackmoney = 0;
				webSocket.send(JSON.stringify(result));
			}else if(result[0]["resultno"]==2){	// 돈 내는 이벤트(돈 부족시)
				gkturnendcheck = 1;
				gk = gk_no;
				lackmoney = result[0]["lackmoney"];
				topay = result[0]["topay"];
				paytype = "goldkey";
				nomnoey();
			}else if(result[0]["resultno"]==3){	// 돈 내는 이벤트
				topay = 0;
				lackmoney = 0;
				webSocket.send(JSON.stringify(result));
			}else if(result[0]["resultno"]==4){	// 뒤로가시오,세계일주
				if(result[0]["move"]==40){
					turncheck = 1;
				}
				gkturnendcheck = 1;
				let countmyline = Math.floor(player['p_location'] / 10);
				let moveline = Math.floor((player['p_location']+parseInt(result[0]["move"])-1)/ 10);
				let timecount = moveline - countmyline ;
				let goldkeymoveaction = [{"type" : 'goldkeymoveaction', "move" : result[0]["move"], "p_location" : player["p_location"], "mover" : player["p_order"], "groom_no" : groom_no }];
				webSocket.send(JSON.stringify(goldkeymoveaction));
				setTimeout(function() {
				player["p_location"] = result[0]["p_location"];
				setTimeout(function(){
					city();
					webSocket.send(JSON.stringify(result));
				},500)	
				},(2500+(1500*timecount) ) );
			}else if(result[0]["resultno"]==5){	// 특정위치로 이동 이벤트
				if(parseInt(result[0]["p_location"])<player["p_location"] ){
					turncheck = 1;
				}
				gkturnendcheck = 1;
				let countmyline = Math.floor(player['p_location'] / 10);
				let moveline = Math.floor((player['p_location']+parseInt(result[0]["move"])-1)/ 10);
				let timecount = moveline - countmyline ;
				let goldkeymoveaction = [{"type" : 'goldkeymoveaction', "move" : result[0]["move"], "p_location" : player["p_location"], "mover" : player["p_order"], "groom_no" : groom_no }];
				webSocket.send(JSON.stringify(goldkeymoveaction));		
				setTimeout(function() {	
				player["p_location"] = result[0]["p_location"];
				setTimeout(function(){
					city();
					webSocket.send(JSON.stringify(result));
				},500)	
				},(2500+(1500*timecount) ) );					
			}else if(result[0]["resultno"]==6){	// 무인도 탈출용 무전기
				webSocket.send(JSON.stringify(result));
			}else if(result[0]["resultno"]==7){	// 건물 종류에 따라 돈내기
				webSocket.send(JSON.stringify(result));
			}else{
				
			}
			// goldkeylist 에 중복되지 않는값만 저장
			let gkcheck = true;
			for(let i=0; i<goldkeylist.length; i++){
				if(goldkeylist[i]["gk_no"]==result[0]["gk_no"]){
					gkcheck = false;
				}
			}
			if(gkcheck){
				let object1 = new Object();
				object1.gk_no = result[0]["gk_no"];
				object1.ac_nickname = result[0]["ac_nickname"];
				object1.p_no = player["p_no"];
				object1.groom_no = groom_no;
				object1.type = "goldkeylist";
				goldkeylist.push(object1);
				webSocket.send(JSON.stringify(goldkeylist));
			}
		}
	});
	
}

function useitem(){
	uisland = 0;
	swal({
	    text: "탈출 성공!",
	    icon: "success",
	    buttons: '확인'
	}).then((value) => {
	    
			for(let i=0; i<goldkeylist.length; i++){
				if(goldkeylist[i]["gk_no"]==26){
					goldkeylist[i]["p_no"]==0;
					$("#modalclosebtn6").click();
				}
			}
	    
	});

	
}

function goldkeyok(){
	$("#modalclosebtn5").click();
	if(gkturnendcheck == 0){
		if(dicethrow==0){
			turnend();
		}else{
			swal({
		        text: "주사위를 한번 더 던져주세요.",
		        icon: "info",
		        buttons: '확인'
		    }).then((value) => {
		        
		    });
		}
	}
	
}

////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////// 채팅 ////////////////////////////////////////////////////////////

let mymymynick = $("#mymymynick").val();
$("#msginput22").keyup(function(e){
	if(e.keyCode == 13){
		sendmsg22(mymymynick);
	}  
});
function sendmsg22(id){ // 채팅 메시지 전송
	if($("#msginput22").val()==""){
		return;
	}
	let msg = {"type" : "chat2", "content" : $("#msginput22").val(), "id" : id, "groom_no" : groom_no};
	let msg2 = [];
	msg2.push(msg)
	webSocket.send(JSON.stringify(msg2));
	$("#msginput22").val("");
	$("#msginput").focus();
}
function gifemo(num,id){
	let emoji = {"type" : "emo", "content" : num , "id" : id, "groom_no" : groom_no};
	let msg2 = [];
	msg2.push(emoji)
	webSocket.send(JSON.stringify(msg2));
	$("#msginput").focus();
}



