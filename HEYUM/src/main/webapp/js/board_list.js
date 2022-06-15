

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