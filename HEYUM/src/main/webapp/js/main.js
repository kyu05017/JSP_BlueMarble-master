/**
 * 
 */
 
 $( function() {

});
function nonlogin(){
	
    swal({
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

$("#tip_box").hover(function(){
	$(this).css("background-color","#696969");
	$("#qna_box").css("background-color","#ffffff");
	$("#free_box").css("background-color","#ffffff");
	$("#tip_view").removeAttr("hidden","false");
	$("#qna_view").attr("hidden","true");
	$("#free_view").attr("hidden","true");
	},function(){
	//	$(this).css("background-color","#ffffff");
	}
);

$("#qna_box").hover(function(){
	$(this).css("background-color","#696969");
	$("#tip_box").css("background-color","#ffffff");
	$("#free_box").css("background-color","#ffffff");
	$("#qna_view").removeAttr("hidden","false");
	$("#tip_view").attr("hidden","true");
	$("#free_view").attr("hidden","true");
	},function(){
	//	$(this).css("background-color","#ffffff");
	}
);
$("#free_box").hover(function(){
	$(this).css("background-color","#696969");
	$("#tip_box").css("background-color","#ffffff");
	$("#qna_box").css("background-color","#ffffff");
	$("#free_view").removeAttr("hidden","false");
	$("#tip_view").attr("hidden","true");
	$("#qna_view").attr("hidden","true");
	},function(){
	//	$(this).css("background-color","#ffffff");
	}
);