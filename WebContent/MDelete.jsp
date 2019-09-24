<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery.js"></script>
<title>회원 탈퇴</title>

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
	
	function submit_ajax(){
		var queryString = $("#my-form").serialize();
		$.ajax({
			url: '/JspProject/MDeleteProcess',
			type: 'POST',
			data: queryString,
			dataType: 'text',
			success: function(json) {
				var result = JSON.parse(json);
				if (result.code == "success") {
					alert(result.desc)
					window.location.replace("mMain.do");
				}
			}
		});
	}
</script>
</head>
<body>
	<form method="post" id="my-form">
		아이디: <input type="text" name="id" id="id"><br>
		비밀번호: <input type="password" name="pw" id="pw"><br><br>
		<input type="button" value="회원 탈퇴" onclick="form_check()">&nbsp;&nbsp;
		<input type="button" value="메인 화면으로" onclick="javascript:window.location='mMain.do'">
	</form>
</body>
</html>