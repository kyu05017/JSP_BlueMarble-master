function move(i,sum,mlocation) {
	let origindivx = $("#player"+i+"board"+mlocation).offset().left; // 플레이어 x축이동시 쓰는 기준 좌표 (자기 위치 좌표)
	let origindivy = $("#player"+i+"board"+mlocation).offset().top; // 플레이어 y축이동시  쓰는 기준 좌표 (자기 위치 좌표)
	let div0x = $("#player"+i+"board0").offset().left; // 시작칸 y축이동 좌표
	let div0y = $("#player"+i+"board0").offset().top; // 시작칸 y축이동 좌표
	let div10x = $("#player"+i+"board10").offset().left; // 우주여행 x축이동 좌표
	let div10y = $("#player"+i+"board10").offset().top; // 무인도 y축이동 좌표
	let div20x = $("#player"+i+"board20").offset().left; // 우주여행 x축이동 좌표
	let div20y = $("#player"+i+"board20").offset().top; // 무인도 y축이동 좌표
	let div30x = $("#player"+i+"board30").offset().left; // 사회복지기금 x축이동 좌표
	let div30y = $("#player"+i+"board30").offset().top; // 무인도 y축이동 좌표
	
	
	function zerototen(i){
		$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div10x))+'px, '+(-(origindivy-div0y))+'px)'});
	}
	function tentotwenty(i){
		$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div10x))+'px, '+(-(origindivy-div20y))+'px)'});
	}
	function twentytothirty(i){
		$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div30x))+'px, '+(-(origindivy-div20y))+'px)'});
	}
	function thirtytozero(i){
		$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div30x))+'px, '+(-(origindivy-div0y))+'px)'});
	}
	
	if(Math.floor(mlocation/10) == 0){     // 0~9 번라인일때
	let divx = 0 ;
	let divy = 0 ;
	if((sum+mlocation)>=40){
		divx = $("#player"+i+"board"+(sum+mlocation-40)).offset().left;
		divy = $("#player"+i+"board"+(sum+mlocation-40)).offset().top;
	}
	else if((sum+mlocation)<0){
		divx = $("#player"+i+"board"+(sum+mlocation+40)).offset().left;
		divy = $("#player"+i+"board"+(sum+mlocation+40)).offset().top;			
	}
	else{
		divx = $("#player"+i+"board"+(sum+mlocation)).offset().left;
		divy = $("#player"+i+"board"+(sum+mlocation)).offset().top;
	}
		if((mlocation+sum)>10 && (mlocation+sum)<=20 ){ // 한번 꺾을때
			$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div10x))+'px, 0)'});
			setTimeout(function() {
				$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div10x))+'px, '+(-(origindivy-divy))+'px)'});
				/*setTimeout(function() {*/
					/*mlocation+=sum; 	*/
				/*	showtable();*/
				/*	showplayer();*/
			/*	}, 1100);*/
			}, 1100);
		}
		else if((mlocation+sum)>20){  // 2회이상
			if((mlocation+sum)>30 && (mlocation+sum)<=40){ // 3번꺾을때
				$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div10x))+'px, 0)'});
				setTimeout(function() {
					tentotwenty(i);
					setTimeout(function() {
						twentytothirty(i);
						setTimeout(function() {
							$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div30x))+'px, '+(-(origindivy-divy))+'px)'});
						/*	setTimeout(function() {
								mlocation+=sum;
								if(mlocation >= 40){
									mlocation-=40;
								} 	
								showtable();
								showplayer();		
							}, 1100);		 */			
						}, 1100);							
					}, 1100);
				}, 1100);						
			}
			else if((mlocation+sum)>40 && (mlocation+sum)<=50){ // 4번꺾을때
				$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div10x))+'px, 0)'});
				setTimeout(function() {
					tentotwenty(i);
					setTimeout(function() {
						twentytothirty(i);
					 	setTimeout(function() {
							thirtytozero(i);
								setTimeout(function() {
								$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px, '+(-(origindivy-div0y))+'px)'});
							/*	setTimeout(function() {
									mlocation+=sum;
									if(mlocation >= 40){
										mlocation-=40;
									} 	
									showtable();
									showplayer();		
								}, 1100); */
							}, 1100);						
						}, 1100);							
					}, 1100);
				}, 1100);					
			}
			else if((mlocation+sum)>50 && (mlocation+sum)<=60){ // 5번꺾을때
				$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div10x))+'px, 0)'});
				setTimeout(function() {
					tentotwenty(i);
					setTimeout(function() {
						twentytothirty(i);
						setTimeout(function() {
							thirtytozero(i);
							setTimeout(function() {
								zerototen(i);
								setTimeout(function() {									
									$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div10x))+'px, '+(-(origindivy-divy))+'px)'});
								/*	setTimeout(function() {
										mlocation+=sum;
										if(mlocation >= 40){
											mlocation-=40;
										} 	
										showtable();
										showplayer();		
									}, 1100);  */
								}, 1100);
							}, 1100);						
						}, 1100);							
					}, 1100);
				}, 1100);					
			}
			else{ // 2번 꺾을때
				$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div10x))+'px, 0)'});
				setTimeout(function() {
					tentotwenty(i);
					setTimeout(function() {
						$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px, '+(-(origindivy-div20y))+'px)'});
						/*setTimeout(function() {
							mlocation+=sum;
						 	showtable();
							showplayer();
						}, 1100);		*/					
					}, 1100);
				}, 1100);		
			}
		}
		else if((mlocation+sum)<0){ // 뒤로 꺾을때
			$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div0x))+'px, 0)'});
			setTimeout(function() {
				$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div0x))+'px, '+(-(origindivy-divy))+'px)'});
			/*	setTimeout(function() {
					mlocation+=sum;
					if(mlocation < 0){
						mlocation+=40;
					} 								
					showtable();
					showplayer();						
				}, 1100);			*/					
			}, 1100);			
		}
		else{ // 안꺾을때
			$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px, 0)'});
			/*setTimeout(function() {
				mlocation+=sum;
			 	showtable();
				showplayer();
			}, 1100);*/
		}
	}   // 0~9 번라인 끝
	else if(Math.floor(mlocation/10)  == 1){ // 10~19 번라인
	let divx = 0 ;
	let divy = 0 ;
	if((sum+mlocation)>=40){
		divx = $("#player"+i+"board"+(sum+mlocation-40)).offset().left;
		divy = $("#player"+i+"board"+(sum+mlocation-40)).offset().top;
	}
	else{
		divx = $("#player"+i+"board"+(sum+mlocation)).offset().left;
		divy = $("#player"+i+"board"+(sum+mlocation)).offset().top;
	}
		if((mlocation+sum)>20 && (mlocation+sum)<=30){ // 1번꺾을때
			$("#player"+i).css({'transform' : 'translate( 0 , '+(-(origindivy-div20y))+'px)'});
			setTimeout(function() {
				$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px, '+(-(origindivy-div20y))+'px)'});
			/*	setTimeout(function() {
					mlocation+=sum; 	
					showtable();
					showplayer();
				}, 1100);*/
			}, 1100);
		}
		else if((mlocation+sum)>30 && (mlocation+sum)<=40){ // 2번꺾을때
			$("#player"+i).css({'transform' : 'translate( 0 , '+(-(origindivy-div20y))+'px)'});
			setTimeout(function() {
				twentytothirty(i);
				setTimeout(function() {
					$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div30x))+'px, '+(-(origindivy-divy))+'px)'});
				/*	setTimeout(function() {	
						mlocation+=sum;
						if(mlocation >= 40){
							mlocation-=40;
						} 	
						showtable();
						showplayer();	
					}, 1100);		*/
				}, 1100);					
			}, 1100);		
		}
		else if((mlocation+sum)>40 && (mlocation+sum)<=50){ // 3번꺾을때
			$("#player"+i).css({'transform' : 'translate( 0 , '+(-(origindivy-div20y))+'px)'});
			setTimeout(function() {
				twentytothirty(i);
				setTimeout(function() {
					thirtytozero(i);
					setTimeout(function() {
						$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px, '+(-(origindivy-div0y))+'px)'});
					/*	setTimeout(function() {	
							mlocation+=sum;
							if(mlocation >= 40){
								mlocation-=40;
							} 	
							showtable();
							showplayer();	
						}, 1100);*/
					}, 1100);		
				}, 1100);					
			}, 1100);				
		}
		else if((mlocation+sum)>50 && (mlocation+sum)<=60){ // 4번꺾을때
			$("#player"+i).css({'transform' : 'translate( 0 , '+(-(origindivy-div20y))+'px)'});
			setTimeout(function() {
				twentytothirty(i);
				setTimeout(function() {
					thirtytozero(i);
					setTimeout(function() {
						zerototen(i);
						setTimeout(function() {
							$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div10x))+'px, '+(-(origindivy-divy))+'px)'});
						/*	setTimeout(function() {	
								mlocation+=sum;
								if(mlocation >= 40){
									mlocation-=40;
								} 	
								showtable();
								showplayer();	
							}, 1100);*/
						}, 1100);
					}, 1100);		
				}, 1100);					
			}, 1100);			
		}
		else if((mlocation+sum)>60 && (mlocation+sum)<=70){ // 5번꺾을때
			$("#player"+i).css({'transform' : 'translate( 0 , '+(-(origindivy-div20y))+'px)'});
			setTimeout(function() {
				twentytothirty(i);
				setTimeout(function() {
					thirtytozero(i);
					setTimeout(function() {
						zerototen(i);
						setTimeout(function() {
							tentotwenty(i);
							setTimeout(function() {
								$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px, '+(-(origindivy-div20y))+'px)'});
							/*	setTimeout(function() {	
									mlocation+=sum;
									if(mlocation >= 40){
										mlocation-=40;
									} 	
									showtable();
									showplayer();	
								}, 1100);*/
							}, 1100);
						}, 1100);
					}, 1100);		
				}, 1100);					
			}, 1100);			
		}
		else if((mlocation+sum)<10){ // 뒤로 꺾을때
			$("#player"+i).css({'transform' : 'translate( 0 , '+(-(origindivy-div10y))+'px)'});
			setTimeout(function() {
				$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px , '+(-(origindivy-div10y))+'px)'}); 
			/*		setTimeout(function() {	
					 	mlocation+=sum;
						showtable();
						showplayer();
					}, 1100);*/
			}, 1100);			
		}	
		else{ // 안꺾을때
		 	$("#player"+i).css({'transform' : 'translate(0 , '+(-(origindivy-divy))+'px)'});
		/*	setTimeout(function() { 	
			 	mlocation+=sum;
				showtable();
				showplayer();
			}, 1100);			*/			
		}		
	}  // 10~19 번라인 끝
	else if(Math.floor(mlocation/10) == 2){ // 20~29 번라인
	let divx = 0 ;
	let divy = 0 ;
	if((sum+mlocation)>=40){
		if((sum+mlocation)>80){
			divx = $("#player"+i+"board"+(sum+mlocation-80)).offset().left;
			divy = $("#player"+i+"board"+(sum+mlocation-80)).offset().top;
		}
		else{
			divx = $("#player"+i+"board"+(sum+mlocation-40)).offset().left;
			divy = $("#player"+i+"board"+(sum+mlocation-40)).offset().top;			
		}
	}
	else{
		divx = $("#player"+i+"board"+(sum+mlocation)).offset().left;
		divy = $("#player"+i+"board"+(sum+mlocation)).offset().top;
	}
		if((mlocation+sum)>30 && (mlocation+sum)<=40){ // 1번꺾을때
			$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div30x))+'px, 0)'});
			setTimeout(function() {
				$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div30x))+'px, '+(-(origindivy-divy))+'px)'});
			/*	setTimeout(function() {
					mlocation+=sum; 	
					showtable();
					showplayer();
				}, 1100);*/
			}, 1100);
		}
		else if((mlocation+sum)>40 && (mlocation+sum)<=50){  // 2번꺾을때
			$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div30x))+'px, 0)'});
			setTimeout(function() {
				thirtytozero(i);
				setTimeout(function() {
					$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px, '+(-(origindivy-div0y))+'px)'});
					/*setTimeout(function() {
						if(mlocation >= 40){
							mlocation-=40;
						} 	
						showtable();
						showplayer();	
					}, 1100);*/
				}, 1100);
			}, 1100);
		}
		else if((mlocation+sum)>50 && (mlocation+sum)<=60){  // 3번꺾을때
			$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div30x))+'px, 0)'});
			setTimeout(function() {
				thirtytozero(i);
				setTimeout(function() {
					zerototen(i);
					setTimeout(function() {									
						$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div10x))+'px, '+(-(origindivy-divy))+'px)'});
					/*	setTimeout(function() {
							mlocation+=sum;
							if(mlocation >= 40){
								mlocation-=40;
							} 	
							showtable();
							showplayer();		
						}, 1100);*/
					}, 1100);
				}, 1100);
			}, 1100);
		}
		else if((mlocation+sum)>60 && (mlocation+sum)<=70){  // 4번꺾을때
			$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div30x))+'px, 0)'});
			setTimeout(function() {
				thirtytozero(i);
				setTimeout(function() {
					zerototen(i);
					setTimeout(function() {	
						tentotwenty(i);
						setTimeout(function() {								
							$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px, '+(-(origindivy-div20y))+'px)'});
						/*	setTimeout(function() {
								mlocation+=sum;
								if(mlocation >= 40){
									mlocation-=40;
								} 	
								showtable();
								showplayer();		
							}, 1100);*/
						}, 1100);
					}, 1100);
				}, 1100);
			}, 1100);
		}
		else if((mlocation+sum)>70 && (mlocation+sum)<=80){  // 5번꺾을때
			$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div30x))+'px, 0)'});
			setTimeout(function() {
				thirtytozero(i);
				setTimeout(function() {
					zerototen(i);
					setTimeout(function() {	
						tentotwenty(i);
						setTimeout(function() {
							twentytothirty(i);
							setTimeout(function() {								
								$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div30x))+'px, '+(-(origindivy-divy))+'px)'});
							/*	setTimeout(function() {
									mlocation+=sum;
									if(mlocation >= 40){
										if(mlocation>80){ mlocation-=80;	}
										else{ mlocation-=40; }
									} 	
									showtable();
									showplayer();		
								}, 1100);*/
							}, 1100);
						}, 1100);
					}, 1100);
				}, 1100);
			}, 1100);
		}
		else if((mlocation+sum)<20){ // 뒤로 꺾을때
			$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div20x))+'px, 0)'});
			setTimeout(function() {
				$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div20x))+'px, '+(-(origindivy-divy))+'px)'});
			/*	setTimeout(function() {
					mlocation+=sum; 	
					showtable();
					showplayer();
				}, 1100);*/
			}, 1100);
		}
		else{ // 안꺾을때
			$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px,0)'});
		/*	setTimeout(function() {
				mlocation+=sum;
			 	showtable();
				showplayer();
			}, 1100);*/
		}
	} // 20~29 번라인 끝
	else if(Math.floor(mlocation/10) == 3){ // 30~39 번라인
	let divx = 0 ;
	let divy = 0 ;
	if((sum+mlocation)>=40){
		if((sum+mlocation)>80){
			divx = $("#player"+i+"board"+(sum+mlocation-80)).offset().left;
			divy = $("#player"+i+"board"+(sum+mlocation-80)).offset().top;
		}
		else{
			divx = $("#player"+i+"board"+((sum+mlocation)-40)).offset().left;
			divy = $("#player"+i+"board"+(sum+mlocation-40)).offset().top;		
		}
	}
	else{
		divx = $("#player"+i+"board"+(sum+mlocation)).offset().left;
		divy = $("#player"+i+"board"+(sum+mlocation)).offset().top;
	}
		if((mlocation+sum)>40 && (mlocation+sum)<=50){ // 1번 꺾을때
			$("#player"+i).css({'transform' : 'translate( 0 , '+(-(origindivy-div0y))+'px)'});
			setTimeout(function() {
				$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px, '+(-(origindivy-div0y))+'px)'});
			/*	setTimeout(function() {
					mlocation+=sum;
					if(mlocation >= 40){
						mlocation-=40;
					} 	
					showtable();
					showplayer();
				}, 1100);*/
			}, 1100);
		}
		else if((mlocation+sum)>50 && (mlocation+sum)<=60){ // 2번 꺾을때
			$("#player"+i).css({'transform' : 'translate( 0 , '+(-(origindivy-div0y))+'px)'});
			setTimeout(function() {
				zerototen(i);
				setTimeout(function() {
					$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div10x))+'px, '+(-(origindivy-divy))+'px)'});
				/*	setTimeout(function() {
						mlocation+=sum-40; 	
						showtable();
						showplayer();
					}, 1100);*/
				}, 1100);
			}, 1100);		
		}
		else if((mlocation+sum)>60 && (mlocation+sum)<=70){ // 3번 꺾을때
			$("#player"+i).css({'transform' : 'translate( 0 , '+(-(origindivy-div0y))+'px)'});
			setTimeout(function() {
				zerototen(i);
				setTimeout(function() {
					tentotwenty(i);
					setTimeout(function() {
						$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px, '+(-(origindivy-div20y))+'px)'});
						/*setTimeout(function() {
							mlocation+=sum-40; 	
							showtable();
							showplayer();
						}, 1100);*/
					}, 1100);
				}, 1100);
			}, 1100);		
		}
		else if((mlocation+sum)>70 && (mlocation+sum)<=80){ // 4번 꺾을때
			$("#player"+i).css({'transform' : 'translate( 0 , '+(-(origindivy-div0y))+'px)'});
			setTimeout(function() {
				zerototen(i);
				setTimeout(function() {
					tentotwenty(i);
					setTimeout(function() {
						twentytothirty(i);
						setTimeout(function() {
							$("#player"+i).css({'transform' : 'translate('+(-(origindivx-div30x))+'px, '+(-(origindivy-divy))+'px)'});
						/*	setTimeout(function() {
								if((mlocation+sum)>80){
									mlocation+=sum-80; 
								}
								else{
									mlocation+=sum-40; 
								}			
								showtable();
								showplayer();
							}, 1100);*/
						}, 1100);
					}, 1100);
				}, 1100);
			}, 1100);		
		}
		else if((mlocation+sum)>80 && (mlocation+sum)<=90){ // 5번 꺾을때
			$("#player"+i).css({'transform' : 'translate( 0 , '+(-(origindivy-div0y))+'px)'});
			setTimeout(function() {
				zerototen(i);
				setTimeout(function() {
					tentotwenty(i);
					setTimeout(function() {
						twentytothirty(i);
						setTimeout(function() {
							thirtytozero(i);
							setTimeout(function() {
								$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px, '+(-(origindivy-div0y))+'px)'});
							/*	setTimeout(function() {
									mlocation+=sum-80; 		
									showtable();
									showplayer();
								}, 1100);*/
							}, 1100);
						}, 1100);
					}, 1100);
				}, 1100);
			}, 1100);		
		}
		else if((mlocation+sum)<30){ // 뒤로 꺾을때
			$("#player"+i).css({'transform' : 'translate( 0 , '+(-(origindivy-div30y))+'px)'});
			setTimeout(function() {
				$("#player"+i).css({'transform' : 'translate('+(-(origindivx-divx))+'px, '+(-(origindivy-div30y))+'px)'});
				/*setTimeout(function() {
					mlocation+=sum; 	
					showtable();
					showplayer();
				}, 1100);*/
			}, 1100);
		}	
		else{ // 안꺾을때
		 	$("#player"+i).css({'transform' : 'translate(0 , '+(-(origindivy-divy))+'px)'});
		/*	setTimeout(function() {
				if((mlocation+sum)==40){
					mlocation = 0 ;
				}
				else{
					mlocation+=sum;	
				}
				showtable();
				showplayer();
			}, 1100);	*/					
		}
	} // 30~39 번라인 끝
	

}



function showtable(){
	let html = '';	
html +=
'<div class="row">'+
'<div id="board20" class="ltcorner" onclick="gospacetravel(20)" >'+
'<div class="startbartop text-center">무 인 도</div>'+
'	<div class="row leftspace"><div id="player1board20" class="playerarea"></div><div id="player2board20" class="playerarea"></div></div>'+
'	<div class="row leftspace"><div id="player3board20" class="playerarea"></div><div id="player4board20" class="playerarea"></div></div>'+
'</div>'+
'<div id="board21" class="gamerow" onclick="gospacetravel(21)">'+
'	<div class="pricetagtop"><span id="cityfee21"></span></div>'+
'	<div class="row"><div id="player1board21" class="playerarea"></div><div id="player2board21" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board21" class="playerarea"></div><div id="player4board21" class="playerarea"></div></div>'+
'	<div class="citynamebuenos">부에노스<br>아이레스<br><br></div>'+
'	<div class="colorbarorange"><div class="row"><div id="villaimg21" class="villaimg"></div><div id="buildingimg21" class="buildingimg"></div><div id="hotelimg21" class="hotelimg"></div></div></div>'+
'	</div>'+
'<div id="board22" class="gamerowtopgoldkey" onclick="gospacetravel(22)">'+
'	<div class="pricetagtop"></div>'+
'	<div class="row"><div id="player1board22" class="playerarea"></div><div id="player2board22" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board22" class="playerarea"></div><div id="player4board22" class="playerarea"></div></div>'+
'	<div class="goldkeytop">황금열쇠</div>'+
'	</div>'+
'<div id="board23" class="gamerow" onclick="gospacetravel(23)" >'+
'	<div class="pricetagtop"><span id="cityfee23"></span></div>'+
'	<div class="row"><div id="player1board23" class="playerarea"></div><div id="player2board23" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board23" class="playerarea"></div><div id="player4board23" class="playerarea"></div></div>'+
'	<div class="cityname">상파울루</div>'+
'	<div class="colorbarorange"><div class="row"><div id="villaimg23" class="villaimg"></div><div id="buildingimg23" class="buildingimg"></div><div id="hotelimg23" class="hotelimg"></div></div></div>'+
'	</div>'+
'<div id="board24" class="gamerow" onclick="gospacetravel(24)" >'+
'	<div class="pricetagtop"><span id="cityfee24"></span></div>'+
'	<div class="row"><div id="player1board24" class="playerarea"></div><div id="player2board24" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board24" class="playerarea"></div><div id="player4board24" class="playerarea"></div></div>'+
'	<div class="cityname">시드니</div>'+
'	<div class="colorbarorange"><div class="row"><div id="villaimg24" class="villaimg"></div><div id="buildingimg24" class="buildingimg"></div><div id="hotelimg24" class="hotelimg"></div></div></div>'+
'	</div>'+
'<div id="board25" class="gamerow" onclick="gospacetravel(25)" >'+
'	<div class="pricetagtop"><span id="cityfee25"></span></div>'+
'	<div class="row"><div id="player1board25" class="playerarea"></div><div id="player2board25" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board25" class="playerarea"></div><div id="player4board25" class="playerarea"></div></div>'+
'	<div class="busan">부산</div>'+
'	</div>'+
'<div id="board26" class="gamerow" onclick="gospacetravel(26)" >'+
'	<div class="pricetagtop"><span id="cityfee26"></span></div>'+
'	<div class="row"><div id="player1board26" class="playerarea"></div><div id="player2board26" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board26" class="playerarea"></div><div id="player4board26" class="playerarea"></div></div>'+
'	<div class="cityname">하와이</div>'+
'	<div class="colorbarorange"><div class="row"><div id="villaimg26" class="villaimg"></div><div id="buildingimg26" class="buildingimg"></div><div id="hotelimg26" class="hotelimg"></div></div></div>'+
'	</div>'+
'<div id="board27" class="gamerow" onclick="gospacetravel(27)" >'+
'	<div class="pricetagtop"><span id="cityfee27"></span></div>'+
'	<div class="row"><div id="player1board27" class="playerarea"></div><div id="player2board27" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board27" class="playerarea"></div><div id="player4board27" class="playerarea"></div></div>'+
'	<div class="cityname">리스본</div>'+
'	<div class="colorbarorange"><div class="row"><div id="villaimg27" class="villaimg"></div><div id="buildingimg27" class="buildingimg"></div><div id="hotelimg27" class="hotelimg"></div></div></div>'+
'	</div>'+
'<div id="board28" class="gamerowtopgoldkey" onclick="gospacetravel(28)" >'+
'	<div class="pricetagtop"></div>'+
'	<div class="row"><div id="player1board28" class="playerarea"></div><div id="player2board28" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board28" class="playerarea"></div><div id="player4board28" class="playerarea"></div></div>'+
'	<div class="goldkeytop">황금열쇠</div>'+
'	</div>'+
'<div id="board29" class="gamerow" onclick="gospacetravel(29)" >'+
'	<div class="pricetagtop"><span id="cityfee29"></span></div>'+
'	<div class="row"><div id="player1board29" class="playerarea"></div><div id="player2board29" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board29" class="playerarea"></div><div id="player4board29" class="playerarea"></div></div>'+
'	<div class="cityname">마드리드</div>'+
'	<div class="colorbarorange"><div class="row"><div id="villaimg29" class="villaimg"></div><div id="buildingimg29" class="buildingimg"></div><div id="hotelimg29" class="hotelimg"></div></div></div>'+
'	</div>'+
'<div id="board30" class="rtcorner" onclick="gospacetravel(30)" >'+
'<div class="startbartop text-center">사회복지기금</div>'+
'	<div class="row rightspace"><div id="player1board30" class="playerarea"></div><div id="player2board30" class="playerarea"></div></div>'+
'	<div class="row rightspace"><div id="player3board30" class="playerarea"></div><div id="player4board30" class="playerarea"></div></div>'+
'	</div></div>'+
//////////////////////////////////////////////////////////////////////////////////////////
'<div class="row sero">'+
'<div id="board19" class="gamecolleft" onclick="gospacetravel(19)" >'+
'	<div class="colorbargreen"><div class="row"><div id="villaimg19" class="villaimg"></div><div id="buildingimg19" class="buildingimg"></div><div id="hotelimg19" class="hotelimg"></div></div></div>'+
'	<div class="cityname">오타와</div>'+
'	<div class="row"><div id="player2board19" class="playerarea leftplayer"></div><div id="player4board19" class="playerarea leftplayer"></div></div>'+
'	<div class="row"><div id="player1board19" class="playerarea leftplayer"></div><div id="player3board19" class="playerarea leftplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee19"></span></div>'+
'	</div>'+
'<div class="centerbox"></div>'+
'<div id="board31" class="gamecolright" onclick="gospacetravel(31)" >'+
'	<div class="colorbarblue"><div class="row"><div id="villaimg31" class="villaimg"></div><div id="buildingimg31" class="buildingimg"></div><div id="hotelimg31" class="hotelimg"></div></div></div>'+
'	<div class="cityname">도쿄</div>'+
'	<div class="row"><div id="player3board31" class="playerarea rightplayer"></div><div id="player1board31" class="playerarea rightplayer"></div></div>'+
'	<div class="row"><div id="player4board31" class="playerarea rightplayer"></div><div id="player2board31" class="playerarea rightplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee31"></span></div>'+
'	</div>'+
'	</div>'+
//////////////////////////////////////////////////////////////////////////////////////////
'<div class="row sero">'+
'<div id="board18" class="gamecolleft" onclick="gospacetravel(18)" >'+
'	<div class="colorbargreen"><div class="row"><div id="villaimg18" class="villaimg"></div><div id="buildingimg18" class="buildingimg"></div><div id="hotelimg18" class="hotelimg"></div></div></div>'+
'	<div class="cityname">베를린</div>'+
'	<div class="row"><div id="player2board18" class="playerarea leftplayer"></div><div id="player4board18" class="playerarea leftplayer"></div></div>'+
'	<div class="row"><div id="player1board18" class="playerarea leftplayer"></div><div id="player3board18" class="playerarea leftplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee18"></span></div>'+
'	</div>'+
'<div class="centerbox"></div>'+
'<div id="board32" class="gamecolright" onclick="gospacetravel(32)" >'+
'	<div class="columbia">컬럼비아호</div>'+
'	<div class="row"><div id="player3board32" class="playerarea rightplayer"></div><div id="player1board32" class="playerarea rightplayer"></div></div>'+
'	<div class="row"><div id="player4board32" class="playerarea rightplayer"></div><div id="player2board32" class="playerarea rightplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee32"></span></div>'+
'	</div>'+
'	</div>'+
//////////////////////////////////////////////////////////////////////////////////////////
'<div class="row sero">'+
'<div id="board17" class="gamecolleftgoldkey" onclick="gospacetravel(17)" >'+
'	<div class="goldkey">황금열쇠</div>'+
'	<div class="row"><div id="player2board17" class="playerarea leftplayer"></div><div id="player4board17" class="playerarea leftplayer"></div></div>'+
'	<div class="row"><div id="player1board17" class="playerarea leftplayer"></div><div id="player3board17" class="playerarea leftplayer"></div></div>'+
'	</div>'+
'<div class="centerbox"></div>'+
'<div id="board33" class="gamecolright" onclick="gospacetravel(33)" >'+
'	<div class="colorbarblue"><div class="row"><div id="villaimg33" class="villaimg"></div><div id="buildingimg33" class="buildingimg"></div><div id="hotelimg33" class="hotelimg"></div></div></div>'+
'	<div class="cityname">파리</div>'+
'	<div class="row"><div id="player3board33" class="playerarea rightplayer"></div><div id="player1board33" class="playerarea rightplayer"></div></div>'+
'	<div class="row"><div id="player4board33" class="playerarea rightplayer"></div><div id="player2board33" class="playerarea rightplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee33"></span></div>'+
'	</div>'+
'	</div>'+
//////////////////////////////////////////////////////////////////////////////////////////
'<div class="row sero">'+
'<div id="board16" class="gamecolleft" onclick="gospacetravel(16)" >'+
'	<div class="colorbargreen"><div class="row"><div id="villaimg16" class="villaimg"></div><div id="buildingimg16" class="buildingimg"></div><div id="hotelimg16" class="hotelimg"></div></div></div>'+
'	<div class="cityname">베른</div>'+
'	<div class="row"><div id="player2board16" class="playerarea leftplayer"></div><div id="player4board16" class="playerarea leftplayer"></div></div>'+
'	<div class="row"><div id="player1board16" class="playerarea leftplayer"></div><div id="player3board16" class="playerarea leftplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee16"></span></div>'+
'	</div>'+
'<div class="centerbox"></div>'+
'<div id="board34" class="gamecolright" onclick="gospacetravel(34)" >'+
'	<div class="colorbarblue"><div class="row"><div id="villaimg34" class="villaimg"></div><div id="buildingimg34" class="buildingimg"></div><div id="hotelimg34" class="hotelimg"></div></div></div>'+
'	<div class="cityname">로마</div>'+
'	<div class="row"><div id="player3board34" class="playerarea rightplayer"></div><div id="player1board34" class="playerarea rightplayer"></div></div>'+
'	<div class="row"><div id="player4board34" class="playerarea rightplayer"></div><div id="player2board34" class="playerarea rightplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee34"></span></div>'+
'	</div>'+
'	</div>'+
//////////////////////////////////////////////////////////////////////////////////////////
'<div class="row sero">'+
'<div id="board15" class="gamecolleft" onclick="gospacetravel(15)" >'+
'	<div class="plane">콩코드<br>여객기</div>'+
'	<div class="row"><div id="player2board15" class="playerarea leftplayer"></div><div id="player4board15" class="playerarea leftplayer"></div></div>'+
'	<div class="row"><div id="player1board15" class="playerarea leftplayer"></div><div id="player3board15" class="playerarea leftplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee15"></span></div>'+
'	</div>'+
'<div class="centerbox"></div>'+
'<div id="board35" class="gamecolrightgoldkey" onclick="gospacetravel(35)" >'+
'	<div class="goldkey">황금열쇠</div>'+
'	<div class="row"><div id="player3board35" class="playerarea rightplayer"></div><div id="player1board35" class="playerarea rightplayer"></div></div>'+
'	<div class="row"><div id="player4board35" class="playerarea rightplayer"></div><div id="player2board35" class="playerarea rightplayer"></div></div>'+
'	</div>'+
'	</div>'+
//////////////////////////////////////////////////////////////////////////////////////////
'<div class="row sero">'+
'<div id="board14" class="gamecolleft" onclick="gospacetravel(14)" >'+
'	<div class="colorbargreen"><div class="row"><div id="villaimg14" class="villaimg"></div><div id="buildingimg14" class="buildingimg"></div><div id="hotelimg14" class="hotelimg"></div></div></div>'+
'	<div class="cityname">스톡홀름</div>'+
'	<div class="row"><div id="player2board14" class="playerarea leftplayer"></div><div id="player4board14" class="playerarea leftplayer"></div></div>'+
'	<div class="row"><div id="player1board14" class="playerarea leftplayer"></div><div id="player3board14" class="playerarea leftplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee14"></span></div>'+
'	</div>'+
'<div class="centerbox"></div>'+
'<div id="board36" class="gamecolright" onclick="gospacetravel(36)" >'+
'	<div class="colorbarblue"><div class="row"><div id="villaimg36" class="villaimg"></div><div id="buildingimg36" class="buildingimg"></div><div id="hotelimg36" class="hotelimg"></div></div></div>'+
'	<div class="cityname">런던</div>'+
'	<div class="row"><div id="player3board36" class="playerarea rightplayer"></div><div id="player1board36" class="playerarea rightplayer"></div></div>'+
'	<div class="row"><div id="player4board36" class="playerarea rightplayer"></div><div id="player2board36" class="playerarea rightplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee36"></span></div>'+
'	</div>'+
'	</div>'+
////////////////////////////////////////////////////////////////////////////////////////
'<div class="row sero">'+
'<div id="board13" class="gamecolleft" onclick="gospacetravel(13)" >'+
'	<div class="colorbargreen"><div class="row"><div id="villaimg13" class="villaimg"></div><div id="buildingimg13" class="buildingimg"></div><div id="hotelimg13" class="hotelimg"></div></div></div>'+
'	<div class="cityname">코펜하겐</div>'+
'	<div class="row"><div id="player2board13" class="playerarea leftplayer"></div><div id="player4board13" class="playerarea leftplayer"></div></div>'+
'	<div class="row"><div id="player1board13" class="playerarea leftplayer"></div><div id="player3board13" class="playerarea leftplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee13"></span></div>'+
'	</div>'+
'<div class="centerbox"></div>'+
'<div id="board37" class="gamecolright" onclick="gospacetravel(37)" >'+
'	<div class="colorbarblue"><div class="row"><div id="villaimg37" class="villaimg"></div><div id="buildingimg37" class="buildingimg"></div><div id="hotelimg37" class="hotelimg"></div></div></div>'+
'	<div class="cityname">뉴욕</div>'+
'	<div class="row"><div id="player3board37" class="playerarea rightplayer"></div><div id="player1board37" class="playerarea rightplayer"></div></div>'+
'	<div class="row"><div id="player4board37" class="playerarea rightplayer"></div><div id="player2board37" class="playerarea rightplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee37"></span></div>'+
'	</div>'+
'	</div>'+
///////////////////////////////////////////////////////////////////////////////////////////
'<div class="row sero">'+
'<div id="board12" class="gamecolleftgoldkey" onclick="gospacetravel(12)" >'+
'	<div class="goldkey">황금열쇠</div>'+
'	<div class="row"><div id="player2board12" class="playerarea leftplayer"></div><div id="player4board12" class="playerarea leftplayer"></div></div>'+
'	<div class="row"><div id="player1board12" class="playerarea leftplayer"></div><div id="player3board12" class="playerarea leftplayer"></div></div>'+
'	</div>'+
'<div class="centerbox"></div>'+
'<div id="board38" class="gamecolright" onclick="gospacetravel(38)" >'+
'	<div class="socialfund">사회<br>복지<br>기금</div>'+
'	<div class="row"><div id="player3board38" class="playerarea rightplayer"></div><div id="player1board38" class="playerarea rightplayer"></div></div>'+
'	<div class="row"><div id="player4board38" class="playerarea rightplayer"></div><div id="player2board38" class="playerarea rightplayer"></div></div>'+
'	</div>'+
'	</div>'+
////////////////////////////////////////////////////////////////////////////////////////
'<div class="row sero">'+
'<div id="board11" class="gamecolleft" onclick="gospacetravel(11)" >'+
'	<div class="colorbargreen"><div class="row"><div id="villaimg11" class="villaimg"></div><div id="buildingimg11" class="buildingimg"></div><div id="hotelimg11" class="hotelimg"></div></div></div>'+
'	<div class="cityname">아테네</div>'+
'	<div class="row"><div id="player2board11" class="playerarea leftplayer"></div><div id="player4board11" class="playerarea leftplayer"></div></div>'+
'	<div class="row"><div id="player1board11" class="playerarea leftplayer"></div><div id="player3board11" class="playerarea leftplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee11"></span></div>'+
'	</div>'+
'<div class="centerbox"></div>'+
'<div id="board39" class="gamecolright" onclick="gospacetravel(39)" >'+
'	<div class="seoul">서울</div>'+
'	<div class="row"><div id="player3board39" class="playerarea rightplayer"></div><div id="player1board39" class="playerarea rightplayer"></div></div>'+
'	<div class="row"><div id="player4board39" class="playerarea rightplayer"></div><div id="player2board39" class="playerarea rightplayer"></div></div>'+
'	<div class="pricetag"><span id="cityfee39"></span></div>'+
'	</div>'+
'	</div>'+
/////////////////////////////////////////////////////////////
'<div class="row">'+
'<div id="board10" class="lbcorner" >'+
'<div class="startbar text-center">우 주 여 행</div>'+
'	<div class="row leftspace"><div id="player1board10" class="playerarea"></div><div id="player2board10" class="playerarea"></div></div>'+
'	<div class="row leftspace"><div id="player3board10" class="playerarea"></div><div id="player4board10" class="playerarea"></div></div>'+
'</div>'+
'<div id="board9" class="gamerow" onclick="gospacetravel(9)" >'+
'	<div class="colorbarred"><div class="row"><div id="villaimg9" class="villaimg"></div><div id="buildingimg9" class="buildingimg"></div><div id="hotelimg9" class="hotelimg"></div></div></div>'+
'	<div class="cityname">이스탄불</div>'+
'	<div class="row"><div id="player1board9" class="playerarea"></div><div id="player2board9" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board9" class="playerarea"></div><div id="player4board9" class="playerarea"></div></div>'+
'	<div class="pricetag"><span id="cityfee9"></span></div>'+
'	</div>'+
'<div id="board8" class="gamerow" onclick="gospacetravel(8)" >'+
'	<div class="colorbarred"><div class="row"><div id="villaimg8" class="villaimg"></div><div id="buildingimg8" class="buildingimg"></div><div id="hotelimg8" class="hotelimg"></div></div></div>'+
'	<div class="cityname">카이로</div>'+
'	<div class="row"><div id="player1board8" class="playerarea"></div><div id="player2board8" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board8" class="playerarea"></div><div id="player4board8" class="playerarea"></div></div>'+
'	<div class="pricetag"><span id="cityfee8"></span></div>'+
'	</div>'+
'<div id="board7" class="gamerowgoldkey" onclick="gospacetravel(7)" >'+
'	<div class="goldkey">황금열쇠</div>'+
'	<div class="row"><div id="player1board7" class="playerarea"></div><div id="player2board7" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board7" class="playerarea"></div><div id="player4board7" class="playerarea"></div></div>'+
'	</div>'+
'<div id="board6" class="gamerow" onclick="gospacetravel(6)" >'+
'	<div class="colorbarred"><div class="row"><div id="villaimg6" class="villaimg"></div><div id="buildingimg6" class="buildingimg"></div><div id="hotelimg6" class="hotelimg"></div></div></div>'+
'	<div class="cityname">싱가포르</div>'+
'	<div class="row"><div id="player1board6" class="playerarea"></div><div id="player2board6" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board6" class="playerarea"></div><div id="player4board6" class="playerarea"></div></div>'+
'	<div class="pricetag"><span id="cityfee6"></span></div>'+
'	</div>'+
'<div id="board5" class="gamerow" onclick="gospacetravel(5)" >'+
'	<div class="jeju">제주도</div>'+
'	<div class="row"><div id="player1board5" class="playerarea"></div><div id="player2board5" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board5" class="playerarea"></div><div id="player4board5" class="playerarea"></div></div>'+
'	<div class="pricetag"><span id="cityfee5"></span></div>'+
'	</div>'+
'<div id="board4" class="gamerow" onclick="gospacetravel(4)" >'+
'	<div class="colorbarred"><div class="row"><div id="villaimg4" class="villaimg"></div><div id="buildingimg4" class="buildingimg"></div><div id="hotelimg4" class="hotelimg"></div></div></div>'+
'	<div class="cityname">마닐라</div>'+
'	<div class="row"><div id="player1board4" class="playerarea"></div><div id="player2board4" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board4" class="playerarea"></div><div id="player4board4" class="playerarea"></div></div>'+
'	<div class="pricetag"><span id="cityfee4"></span></div>'+
'	</div>'+
'<div id="board3" class="gamerow" onclick="gospacetravel(3)" >'+
'	<div class="colorbarred"><div class="row"><div id="villaimg3" class="villaimg"></div><div id="buildingimg3" class="buildingimg"></div><div id="hotelimg3" class="hotelimg"></div></div></div>'+
'	<div class="cityname">베이징</div>'+
'	<div class="row"><div id="player1board3" class="playerarea"></div><div id="player2board3" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board3" class="playerarea"></div><div id="player4board3" class="playerarea"></div></div>'+
'	<div class="pricetag"><span id="cityfee3"></span></div>'+
'	</div>'+
'<div id="board2" class="gamerowgoldkey" onclick="gospacetravel(2)" >'+
'	<div class="goldkey">황금열쇠</div>'+
'	<div class="row"><div id="player1board2" class="playerarea"></div><div id="player2board2" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board2" class="playerarea"></div><div id="player4board2" class="playerarea"></div></div>'+
'	</div>'+
'<div id="board1" class="gamerow" onclick="gospacetravel(1)" >'+
'	<div class="colorbarred"><div class="row"><div id="villaimg1" class="villaimg"></div><div id="buildingimg1" class="buildingimg"></div><div id="hotelimg1" class="hotelimg"></div></div></div>'+
'	<div class="cityname">타이베이</div>'+
'	<div class="row"><div id="player1board1" class="playerarea"></div><div id="player2board1" class="playerarea"></div></div>'+
'	<div class="row"><div id="player3board1" class="playerarea"></div><div id="player4board1" class="playerarea"></div></div>'+
'	<div class="pricetag"><span id="cityfee1"></span></div>'+
'	</div>'+
'<div id="board0" class="rbcorner text-center" onclick="gospacetravel(0)" >'+
'<div class="startbar"> 출 발 </div>'+
'	<div class="row rightspace"><div id="player1board0" class="playerarea"></div><div id="player2board0" class="playerarea"></div></div>'+
'	<div class="row rightspace"><div id="player3board0" class="playerarea"></div><div id="player4board0" class="playerarea"></div></div>'+
'	</div></div>';
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	$("#gametable").html(html);
	/*showdicetable();*/
	cityfeeinfo();
}

