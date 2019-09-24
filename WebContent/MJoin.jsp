<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Connect-Type" content= "text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery.js"></script>
<title>회원 가입</title>

<script>
	function form_check() {
		if($('#id').val().length == 0) {
			alert("아이디는 필수사항입니다.");
			$('#id').focus();
			return;
		}
		
		if($('#id').val().length < 4) {
			alert("아이디는 4글자 이상이어야 합니다.");
			$('#id').focus();
			return;
		}
		
		if($('#pw').val().length == 0) {
			alert("비밀번호는 필수사항입니다.");
			$('#pw').focus();
			return;
		}
		
		if($('#pw').val() != $('#pw_check').val()) {
			alert("비밀번호가 일치하지 않습니다.");
			$('#pw').focus();
			return;
		}
		
		if($('#name').val().length == 0) {
			alert("이름은 필수사항입니다.");
			$('#name').focus();
			return;
		}
		
		if($('#eMail').val().length == 0) {
			alert("이메일은 필수사항입니다.");
			$('#eMail').focus();
			return;
		}
		submit_ajax();
	}
	
	function submit_ajax() {
		var queryString = $("#my-form").serialize();
		$.ajax({
			url: '/JspProject/MJoinProcess',
			type: 'POST',
			data: queryString,
			dataType: 'text',
			success: function(json) {
				var result = JSON.parse(json);
				if (result.code == "success") {
					alert(result.desc)
					window.location.replace("mLogin.do");
				} else {
					alert(result.desc);
				}
			}
		});
	}
	
</script>
</head>
<body>
	<form id="my-form">
		아이디 : <input type="text" name="id" id="id" size="20"><br>
		비밀번호 : <input type="password" name="pw" id="pw" size="20"><br>
		비밀번호 확인 : <input type="password" name="pw_check" id="pw_check" size="20"><br>
		이름 : <input type="text" name="name" id="name" size="20"><br>
		메일 : <input type="text" name="eMail" id="eMail" size="20"><br>
		<input type="button" value="회원가입" onclick = "form_check()">
		<input type="button" value="로그인" onclick = "javascript:window.location='mLogin.do'">
	</form>
</body>
</html>