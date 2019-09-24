<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("ValidMem") != null) { %>
	<jsp:forward page="main.jsp"></jsp:forward>
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Connect-Type" content= "text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery.js"></script>
<meta name="google-signin-client_id" content="781640802978-v04itbunfg6e0m1r9rllhsrad417r77n.apps.googleusercontent.com">
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="naveridlogin_js_sdk_2.0.0.js"></script>
<title>로그인</title>

<script>
function form_check() {
	if($('#id').val().length == 0) {
		alert("아이디를 입력해주세요.");
		$('#id').focus();
		return;
	}
	
	if($('#pw').val().length == 0) {
		alert("비밀번호는 필수사항입니다.");
		$('#pw').focus();
		return;
	}
	
	submit_ajax();
}

function submit_ajax() {
	var queryString = $("#my-form").serialize();
	$.ajax({
		url: '/JspProject/MLoginProcess',
		type: 'POST',
		data: queryString,
		dataType: 'text',
		success: function(json) {
			var result = JSON.parse(json);
			if (result.code == "success") {
				alert(result.desc)
				window.location.replace("mMain.do");
			} else {
				alert(result.desc);
			}
		}
	});
}
</script>

<script>
	// 구글 로그인
    function onSignIn(googleUser) {
    	var profile = googleUser.getBasicProfile();
		$('#my-signin').css('display', 'none');
		$('#my-form').css('display', 'none');
		$('#fblogin').css('display', 'none');
		 $('#klogin').css('display', 'none');
    	$('#glogout').css('display', 'block');
    	$('#gupic').attr('src', profile.getImageUrl());
    	$('#guname').html('[ ' +profile.getName() + ' ]');
    }
    function onFailure(error) {
    }
	function signOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	    	$('#my-signin').css('display', 'block');
	    	$('#my-form').css('display', 'block');
	    	$('#fblogin').css('display', 'block');
	    	$('#klogin').css('display', 'block');
	    	$('#glogout').css('display', 'none');
	    	$('#gupic').attr('src', '');
	    	$('#guname').html('');
	    });
	}
    function renderButton() {
        gapi.signin2.render('my-signin', {
	        'scope': 'profile email',
	        'width': 240,
	        'height': 50,
	        'longtitle': true,
	        'theme': 'dark',
	        'onsuccess': onSignIn,
	        'onfailure': onFailure
        });

    }
    $(document).ready(function() {
    	
    });
</script>

<script>
	// 페이스북 로그인
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '632640690592518',
      cookie     : true,
      xfbml      : true,
      version    : 'v4.0'
    });

    FB.getLoginStatus(function(response) {
    	console.log(response);
      statusChangeCallback(response);
    });
  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  function statusChangeCallback(response) {
    if (response.status === 'connected') {
      getINFO();
    } else {
      $('#fblogin').css('display', 'block');
      $('#my-signin').css('display', 'block');
  	  $('#my-form').css('display', 'block');
  	  $('#klogin').css('display', 'block');
      $('#fblogout').css('display', 'none');
      $('#fbupic').attr('src', '');
      $('#fbuname').html('');
    }
  }
	  
  function fbLogin () {
    FB.login(function(response){
      statusChangeCallback(response);
    }, {scope: 'public_profile, email'});
  }

  function fbLogout () {
    FB.logout(function(response) {
      statusChangeCallback(response);
    });
  }

  function getINFO() {
    FB.api('/me?fields=id,name,picture.width(100).height(100).as(picture_small)', function(response) {
      console.log(response);
      $('#fblogin').css('display', 'none');
      $('#my-signin').css('display', 'none');
	  $('#my-form').css('display', 'none');
	  $('#klogin').css('display', 'none');
      $('#fblogout').css('display', 'block');
      $('#fbupic').attr('src', response.picture_small.data.url );
      $('#fbuname').html('[ ' + response.name + ' ]');
    });
  }

</script>


<script type='text/javascript'>
	//카카오
    Kakao.init('7fb8614182d4f537376e07f4294097c7');
    function loginWithKakao() {
      // 로그인 창을 띄웁니다.
      Kakao.Auth.login({
        success: function(authObj) {
          //alert(JSON.stringify(authObj));
          signIn(authObj);
        },
        fail: function(err) {
          alert(JSON.stringify(err));
        }
      });
    };

    function signIn(authObj) {
        //console.log(authObj);
        Kakao.API.request({
            url: '/v2/user/me',
            success: function(res) {
                //console.log(res);
                console.log(res.id);
                $('#klogin').css('display', 'none');
                $('#my-signin').css('display', 'none');
        		$('#my-form').css('display', 'none');
        		$('#fblogin').css('display', 'none');
               	$('#klogout').css('display', 'block');
                $('#kupic').attr('src', res.properties.thumbnail_image );
               	$('#kuname').html('[ ' + res.properties.nickname + ' ]');
             }
         })
	}

    function ksignOut() {
	    Kakao.Auth.logout(function () {
	    	$('#klogin').css('display', 'block');
	    	$('#my-signin').css('display', 'block');
	    	$('#my-form').css('display', 'block');
	    	$('#fblogin').css('display', 'block');
	    	$('#klogout').css('display', 'none');
	    	$('#kupic').attr('src', '');
	    	$('#kuname').html('');
	    });
	}
    
    
</script>

</head>
<body>
	<form action="LoginProcess" method="post" id="my-form">
		아이디: <input type="text" name="id" id="id"
						value="<% if (session.getAttribute("id") != null)
									  out.println(session.getAttribute("id"));
									  %>"><br>
		비밀번호: <input type="password" name="pw" id="pw"><br><p>
		<input type="button" value="로그인" onclick="form_check()">&nbsp;&nbsp;
		<input type="button" value="회원가입" onclick="javascript:window.location='MJoin.do'">
	</form>
	
	<!-- 구글 로그인 -->
	<div id="my-signin"> <br> </div>
    <div id="glogout" style="display: none;">
	    <img id="gupic" src=""><br>
	    <span id="guname"></span><br><br>
	    <input type="button" onclick="javascript:window.location='bList.do'" value="게시판으로 이동" />&nbsp;&nbsp;
	    <input type="button" onclick="javascript:window.location='bWrite_view.do'" value="글 작성" />&nbsp;&nbsp;
	    <input type="button" onclick="signOut();" value="로그아웃" /> &nbsp;&nbsp;
    </div>
    
    <!-- 페이스북 로그인 -->
    
    <div id="fblogin" style="display: block;">
    	<input type="button" onclick="fbLogin();" value=" 페이스북으로 로그인" /><br>
	</div>

	<div id="fblogout" style="display: none;">
	    <img id="fbupic" src=""><br>
	    <span id="fbuname"></span><br> <br>
	    <input type="button" onclick="javascript:window.location='bList.do'" value="게시판으로 이동" />&nbsp;&nbsp;
	    <input type="button" onclick="javascript:window.location='bWrite_view.do'" value="글 작성" />&nbsp;&nbsp;
	    <input type="button" onclick="fbLogout();" value="로그아웃" /><br>
	</div>
	
	<!-- 카카오 로그인 -->
	<div id="klogin" style="display: block">
    	<a id="custom-login-btn" href="javascript:loginWithKakao()">
   		<img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/>
    	</a>
	</div>

	<div id="klogout" style="display: none;">
    	<img id="kupic" src=""><br>
   		<span id="kuname"></span><br><br>
   		<input type="button" onclick="javascript:window.location='bList.do'" value="게시판으로 이동" />&nbsp;&nbsp;
	    <input type="button" onclick="javascript:window.location='bWrite_view.do'" value="글 작성" />&nbsp;&nbsp;
   		<input type="button" class="btn btn-success" onclick="ksignOut();" value="로그아웃" />&nbsp;&nbsp;
	</div>


    <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
</body>
</html>