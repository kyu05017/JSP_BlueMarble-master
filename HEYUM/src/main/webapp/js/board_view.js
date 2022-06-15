

function filedelete( bno ){
	
	
	$.ajax({
		url : "file_delete", /*서블릿통신 */ 
		data : { "bno" : bno }, /* 통신 데이터 : { 변수명 : 값 , 변수명2 : 값  } */
		success : function( result ){
			if( result == "1" ){ 
				alert("첨부파일삭제성공 ");
				location.reload(); // 현재 페이지 새로고침
			}
			else { alert("첨부파일삭제실패[관리자에게문의]")}
		}
		
	});
}

function replywrite( bno ){
	
	let rcontent = $("#rcontent").val();
	
	if(rcontent==""){
		
			Swal.fire({
            title: '작성 실패',
            text : '내용이 비어 있습니다.', 
            icon: 'warning',
	 	})
 		return;
	}else{
		
		$.ajax({
			url: "replywrite" ,
			data: { "bno":bno , "rcontent" : rcontent  } ,
			success : function( result ){
				if( result == 1 ){
					
					 $("#rcontent").val(""); // 작성 input 공백으로 초기화 
					 $("#replytable").load( location.href+" #replytable"); // 특정 태그 새로고침
					 
				}
				swal("댓글 작성 오류!","관리자에게 문의하세요","error");
				
			}
		});
	}
}

function rereplyview( rno , bno , mid ){ // 대댓글 입력창 표시 메소드 
	
	$("#"+rno).html(
		'<div class="row">'+
			'<div class="col-md-10">'+
				'<textarea id="rrcontent" class="form-control" rows=3></textarea>'+
			'</div>'+
			'<div class="col-md-2">'+
				'<button class="form-control py-4 my-1" onclick="rereplywrite('+rno+','+bno+')">대댓글 등록</button>'+
			'</div>'+
		'</div>'
	);	
}
function rereplywrite( rno , bno ){ // 대댓글 쓰기 메소드 
	let rrcontent = $("#rrcontent").val();
	if(rrcontent==""){
	 	
	 	Swal.fire({
            title: '작성 실패',
            text : '내용이 비어 있습니다.', 
            icon: 'warning',
	 	})
 		return;
	 	
	 	
	}else{
		$.ajax({
			url : "rereplywrite" , 
			data : { "rno" : rno , "bno" : bno  , "rrcontent" : rrcontent} ,
			success : function( result ){
				if( result == 1 ){
					
					 $("#rrcontent").val(""); // 작성 input 공백으로 초기화 
					 $("#replytable").load( location.href+" #replytable"); // 특정 태그 새로고침
				}
				else{ alert("대댓글작성이 실패했습니다."); }
			}
		});
	}
}


function replydelete( rno ){
	$.ajax({
		url : "replydelete" , 
		data : { "rno" : rno } , 
		success : function( result ){
			if( result == 1 ){
				
				$("#replytable").load( location.href+" #replytable"); // 특정 태그 새로고침
			}
			else{ alert("삭제 실패(관리자에게 문의)"); } 
		}
	});
}

function replyjsupdate( rno,bno,content ){ // 댓글 수정
//let rcontent = $("#rcontent").val();
//function rereplywrite( rno , bno ){ // 대댓글 쓰기 메소드 
	
	$("#"+rno).html(
		'<div class="row">'+
			'<div class="col-md-10">'+
				'<input id="rrcontent" class="form-control" value="'+content+'" rows=3>'+
			'</div>'+
			'<div class="col-md-2">'+
			'<button class="form-control py-4 my-1" onclick="replyupdate('+rno+','+bno+')">수정</button>'+
			'</div>'+
		'</div>'
	 	
	);

}

function rereplyjsupdate( rno,bno,rindex, content ){ // 댓글 수정
//let rcontent = $("#rcontent").val();
//function rereplywrite( rno , bno ){ // 대댓글 쓰기 메소드 
	
	$("#"+rno).html(
		'<div class="row">'+
			'<div class="col-md-8">'+
				'<input id="rrrcontent" class="form-control" value="'+content+'" rows=3>'+
			'</div>'+
			'<div class="col-md-2">'+
			'<button class="form-control py-4 my-1" onclick="rereplyupdate('+rno+','+bno+','+rindex+')">수1정</button>'+
			'</div>'+
		'</div>'
	 	
	);

}






function replyupdate( rno , bno ){ // 대댓글 쓰기 메소드 
	let content = $("#rrcontent").val();
	if(content==""){
		 	Swal.fire({
            title: '작성 실패',
            text : '내용이 비어 있습니다.', 
            icon: 'warning',
	 	})
 		return;
	}else{
		$.ajax({
			url : "../board/replyupdate" , 
			data : { "rno" : rno , "bno" : bno  , "content" : content} ,
			success : function( result ){
				if( result == 1 ){
					 
					 $("#rcontent").val(""); // 작성 input 공백으로 초기화 
					 $("#replytable").load( location.href+" #replytable"); // 특정 태그 새로고침
				}
				else{ alert("수정실패 아빠불러와."); }
			}
		});
	}
}

function rereplyupdate( rno , bno , rindex ){ // 대댓글 수정 메소드 
	let content = $("#rrrcontent").val();
	if(content==""){
		 	Swal.fire({
            title: '작성 실패',
            text : '내용이 비어 있습니다.', 
            icon: 'warning',
	 	})
 		return;
	}else{
		$.ajax({
			
			url: "rereplyupdate",
			data: {"rno":rno , "bno":bno, "rindex":rindex,"content":content},
				
			success : function(result){
				if( result == 1 ){
					
					 $("#rrrcontent").val(""); // 작성 input 공백으로 초기화 
					 $("#replytable").load( location.href+" #replytable"); // 특정 태그 새로고침
				}
				else{ alert("수정실패 아빠불러와."); }
			}
		})
	
	}	
}

function deletepost(bno,category,pagenum){
		
		
        Swal.fire({
            title: '정말 삭제 하시겠습니까?',
            //text: "다시 되돌릴 수 없습니다. 신중하세요.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
				$.ajax({
				url : "board_delete",
				data: {"bno":bno},			
				success : function(result){
					if(result == "1"){
						
						location.replace("/HEYUM/board/board_list.jsp?category="+category+"&pagenum="+pagenum);
						};
					
					
				}
			
			});
            
            };
        });
                      
    };

function offerbtn(bno,mno,category,mid,pagenum){
	
	$.ajax({
		url: "board_offer" ,
		data: { "bno":bno,"mno":mno,"category":category,"mid":mid} ,
		success : function( result ){
			
			if(result == "1"){
				swal.fire({
					icon : 'success',
					title: '추천 하셨습니다.',
					closeOnClickOutside  : false}).then(function(){
					location.replace("/HEYUM/board/board_view.jsp?bno="+bno+"&category="+category+"&pagenum="+pagenum);		
					});
				
				
			}
			
			
			if(result=="2"){ 
				swal.fire({
					icon : 'warning',
					title: '이미 추천 하신 글 입니다.',
					text : '추천은 하루에 한 번만 가능합니다.'});
		
			
		}}
	})  
	
}

		
function nonlogin(){
	
    swal.fire({
        title: "메세지",
        text: "로그인 후 이용할 수 있습니다",
        icon: "error",
        buttons: '확인'
    }).then((value) => {
        if (value) {
          location.href = '/HEYUM/member/login.jsp';
        }
    });

}		




    