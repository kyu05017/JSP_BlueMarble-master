	const editor = document.getElementById('editor');
    const btnBold = document.getElementById('btn-bold');
    const btnItalic = document.getElementById('btn-italic');
    const btnUnderline = document.getElementById('btn-underline');
    const btnStrike = document.getElementById('btn-strike');
    const btnOrderedList = document.getElementById('btn-ordered-list');
    const btnUnorderedList = document.getElementById('btn-unordered-list');
	
	 const btnImage = document.getElementById('btn-image');
    const imageSelector = document.getElementById('img-selector');
    
    btnImage.addEventListener('click', function () {
        imageSelector.click();
    });

    imageSelector.addEventListener('change', function (e) {
        const files = e.target.files;
        if (!!files) {
            insertImageDate(files[0]);
        }
    });
    
    function insertImageDate(file) {
        const reader = new FileReader();
        reader.addEventListener('load', function (e) {
            focusEditor();
            document.execCommand('insertImage', false, `${reader.result}`);
        });
        reader.readAsDataURL(file);
    }
    
	
    btnBold.addEventListener('click', function () {
        setStyle('bold');
    });

    btnItalic.addEventListener('click', function () {
        setStyle('italic');
    });

    btnUnderline.addEventListener('click', function () {
        setStyle('underline');
    });

    btnStrike.addEventListener('click', function () {
        setStyle('strikeThrough')
    });

    btnOrderedList.addEventListener('click', function () {
        setStyle('insertOrderedList');
    });

    btnUnorderedList.addEventListener('click', function () {
        setStyle('insertUnorderedList');
    });

    function setStyle(style) {
        document.execCommand(style);
        focusEditor();
    }

    // 버튼 클릭 시 에디터가 포커스를 잃기 때문에 다시 에디터에 포커스를 해줌
    function focusEditor() {
        editor.focus({preventScroll: true});
    }




function youtube(){
	
	swal({
		icon : 'warning',
		title : '미 구현입니다.' ,
		text : '추후 업데이트 예정입니다 기다려주세요 ㅠ ' });
	
}






 function submit_write_admin(category){ /// 글쓰기 (공지사항용)
 	
	let bcontent =document.getElementById('editor').innerText; // 텍스트 내용 읽어오기
	
	let btitle= $("#btitle").val();
	let head_data = document.getElementById('select_notice');
	final_head_data = head_data.options[head_data.selectedIndex].value;
	var maxUploadSize =10;
	if($("#bfile").val()!=0){
		var fileSize = $('#bfile')[0].files[0].size;	
		var fileSizeKb = fileSize /1024;
		var fileSizeMb = fileSizeKb/1024;
		if(fileSizeMb>maxUploadSize){
		swal("경고","업로드 파일은 10Mb 까지로 제한합니다.","error");
		return;
		}
	}
	
	if(!btitle.size>50){ 
		swal("작성 실패","제목은 50자를 초과 할 수 없습니다.","error");
		return;
	}	
	if(bcontent=="" || btitle==""){
		swal("작성 실패","제목 또는 내용이 비어 있습니다.","error");
		return;
	}
		
	
	
	
		let formData = new FormData();
		
		
		formData.append("bcontent",document.getElementById("editor").innerHTML);
		formData.append("btitle",final_head_data+btitle); 
		formData.append("bcategory",category);
		formData.append("bfile", $('#bfile')[0].files);
		
		fileInput = document.querySelector("#bfile");
		formData.append("bfile",fileInput.files[0]);
		
		
	
		$.ajax({
			url : "board_write",
			type: "POST",
			enctype:'multipart/form-data',
			data : formData, 	
			contentType  : false,
			processData  : false,
			cache : false,
			success : function( re ){
				if(re == "1"){
					
					location.replace("/HEYUM/board/board_list.jsp?key=&keword=&category="+category);
				}
				if(re == "2"){
					swal("작성 실패","관리자에게 문의 하세요","error");
				}
			}
		});
	
}


function submit_write(category){ /// 글쓰기 (공지사항 외 게시판용 글쓰기)
	//let bcontent_text =document.getElementById('editor').innerText
	let bcontent_html =document.getElementById('editor').innerHTML; // div박스 전체 읽어오기
	let btitle= $("#btitle").val();
	var maxUploadSize =10;
	if($("#bfile").val()!=0){
	
		var fileSize = $('#bfile')[0].files[0].size;	
		var fileSizeKb = fileSize /1024;
		var fileSizeMb = fileSizeKb/1024;
		if(fileSizeMb>maxUploadSize){
		swal("경고","업로드 파일은 10Mb 까지로 제한합니다.","error");
		return;
	}
	}
	
	
	
	if(!btitle.size>50){
		swal("작성 실패","제목은 50자를 초과 할 수 없습니다.","error");
		return;
	}	
	if(bcontent_html.length=="0"|| bcontent_html.length=="1" || btitle=="" ){
		swal("작성 실패","제목 또는 내용이 비어 있습니다.","error");
		return;
	}
	
		
		let formData = new FormData();
		
		formData.append("bcontent",document.getElementById("editor").innerHTML);
		formData.append("btitle",btitle); 
		formData.append("bcategory",category);
		formData.append("bfile", $('#bfile')[0].files);
		
		fileInput = document.querySelector("#bfile");
		formData.append("bfile",fileInput.files[0]);
	
		$.ajax({
			url : "board_write",
			type: "POST",
			enctype:'multipart/form-data',
			data : formData, 	
			contentType  : false,
			processData  : false,
			cache : false,
			success : function( re ){
				if(re == "1"){
					location.replace("/HEYUM/board/board_list.jsp?key=&keword=&category="+category);
				}
				if(re == "2"){
					swal("작성 실패","관리자에게 문의 하세요","error");
				}
			}
		});
		
	
}



 ////
 function submit_update(category){ /// 글수정
	let bcontent =document.getElementById("editor").innerHTML
	let btitle= $("#btitle").val();
	if(bcontent=="" || btitle==""){
		swal("작성 실패","제목 또는 내용이 비어 있습니다.","error");
		return;
	}
	
	let formData = new FormData();
	
	formData.append("bcontent",document.getElementById("editor").innerHTML);
	formData.append("bno",bno);
	formData.append("btitle",btitle); 
	formData.append("bcategory",category);
	formData.append("bfile", $('#bfile')[0].files);
	
	fileInput = document.querySelector("#bfile");
	formData.append("bfile",fileInput.files[0]);
	
	$.ajax({
		url : "board_update",
		type: "POST",
		enctype:'multipart/form-data',
		data : formData, 	
		contentType  : false,
		processData  : false,
		cache : false,
		success : function( re ){
			if(re == "1"){
				location.replace("/HEYUM/board/board_list.jsp?key=&keword=&category="+category);
			}
			if(re == "2"){
				swal("작성 실패","관리자에게 문의 하세요","error");
			}
		}
	});
}

    
 /// 
    

function filedelete( bno ){
	
	$.ajax({
		url : "file_delete", /*서블릿통신 */ 
		data : { "bno" : bno }, /* 통신 데이터 : { 변수명 : 값 , 변수명2 : 값  } */
		success : function( result ){
			if( result == "1" ){ 
				swal("첨부 파일 삭제 완료","success");
				location.reload(); // 현재 페이지 새로고침
			}
			else { alert("첨부파일삭제실패[관리자에게문의]")}
		}
		
	});
}
/*
//////////
//Sass
let btn_count =0;
function btnbold(){
	btn_count=btn_count+1;
	let less_count = btn_count%2;
	if(less_count == 1){
		$("#btn-bold").css("background","#e9ecef");
	}
	if(less_count == 0){
		$("#btn-bold").css("background","#ffffff");
	}
}
	
let btnitalic_count=0;
function btnitalic(){
	btnitalic_count=btnitalic_count+1;
	let italic_count = btnitalic_count%2;
	if(italic_count == 1){
		$("#btn-italic").css("background","#e9ecef");
	}
	if(italic_count == 0){
		$("#btn-italic").css("background","#ffffff");
	}
}
	
let btnunderline_count=0;
function btnunderline(){
	btnunderline_count=btnunderline_count+1
	let under_count = btnunderline_count%2;
	
	if(under_count == 1){
		$("#btn-underline").css("background","#e9ecef");
	}
	if(under_count == 0){
		$("#btn-underline").css("background","#ffffff");
	}
}		

let btnstrike_count=0;
function btnstrike(){
	btnstrike_count=btnstrike_count+1
	let strike_count = btnstrike_count%2;
	
	if(strike_count == 1){
		$("#btn-strike").css("background","#e9ecef");
	}
	if(strike_count == 0){
		$("#btn-strike").css("background","#ffffff");
	}
}	
*/
function back(){
	
	
		location.replace("/HEYUM/board/board_view.jsp?&category="+category+"&pagenum="+pagenum);
	
	
	
	
					
}
