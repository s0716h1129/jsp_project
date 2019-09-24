<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	if(session.getAttribute("ValidMem") == null) {
%>
	<jsp:forward page="MLogin.do"></jsp:forward>
<%
	}
	String bName = (String)session.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인</title>
</head>
<body>
	<h1><%= bName %>님 안녕하세요.</h1><br>
	
	<input type="button" onclick="javascript:window.location='bList.do'" value="게시판으로 이동" />&nbsp;&nbsp;
	    <input type="button" onclick="javascript:window.location='bWrite_view.do'" value="글 작성" />&nbsp;&nbsp;
	<input type="button" value="회원 탈퇴" onclick="javascript:window.location='mDelete.do'">&nbsp;&nbsp;
	<input type="button" value="로그아웃" onclick="javascript:window.location='mLogout.do'">
</body>
</html>