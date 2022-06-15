<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<script type="text/javascript">
    var naverLogin = new naver.LoginWithNaverId(
            {
                clientId: "{......}",
                callbackUrl: "http://localhost:8080/HEYUM/member/collback.jsp",
                isPopup: true,
                callbackHandle: true
                /* callback 페이지가 분리되었을 경우에 callback 페이지에서는 callback처리를 해줄수 있도록 설정합니다. */
            }
        );
    naverLogin.init();
    window.addEventListener('load', function () {
        naverLogin.getLoginStatus(function (status) {
            if (status) {
                /* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */

                // 유저 아이디, 이메일 주소, 이름을 Session에 저장하였습니다.
                sessionStorage.setItem("id", naverLogin.user.getEmail());
                sessionStorage.setItem("password",naverLogin.user.getId());
                sessionStorage.setItem("phone",naverLogin.user.getMobile());
                sessionStorage.setItem("name",naverLogin.user.getName());
                // 네이버 로그인과 카카오 로그인을 구분하기 위해 별도의 세션을 저장합니다.
                sessionStorage.setItem("kinds","naver");

				const id = sessionStorage.getItem("id");
                const pw = sessionStorage.getItem("password");
                const phone = sessionStorage.getItem("phone");
                const name = sessionStorage.getItem("name");
                $.ajax({
                	//'id': naverLogin.user.getEmail()+"" , 'pw', naverLogin.user.id+"", 'phone' : naverLogin.user.getMobile()+"",
                	url : "snslogin",
                	data : { 'id' : id ,'pw' : pw,'phone' : phone,'name' : name,'type' : "2" },
                	success :  function(result) {
                		if(result == 1){
                			location.href = "http://localhost:8080/HEYUM/main.jsp";
                		}
                		else {
                			location.href = "http://localhost:8080/HEYUM/member/login.jsp";
                		}
					}
                	
                });
                // 회원가입 혹은 로그인 시 처리하기 위한 페이지 입니다. 예를 들어 DB
                /* 인증이 완료된후 /sample/main.html 페이지로 이동하라는것이다. 본인 페이로 수정해야한다. */
               
                } else {
                console.log("callback 처리에 실패하였습니다.");
            }
        });
    });
</script>
</body>
</html>