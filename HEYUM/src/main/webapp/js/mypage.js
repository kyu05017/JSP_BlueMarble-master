/**
 * 
 */
let m_num = $("#my_num").val();
let account_data;
let page = '';


 $( function(){
	$("#mainbox").load( "/HEYUM/member/my_info.jsp");

});

function pagechange( page ){
	$("#mainbox").load( page+".jsp");
	// $ : jquery 문법 
	// 특정태그호출 : $("#태그id명")
	//  태그.load( 페이지경로 ) : 해당 태그에 페이지 열기 
}


