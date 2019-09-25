<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>

</head>
<body>
	<h1> 회원 정보 </h1>
	
	<table width="700" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>이메일</td>
			<td>주소</td>
			<td>가입 날짜</td>
			<td> 정지 여부 </td>
		</tr>

		<c:forEach items="${mlist}" var="mdto">		
		<tr>
			<td><a href="mMember_view.do?mId=${mdto.mId}">${mdto.mId}</a></td>
			<td>${mdto.mName}</td>
			<td>${mdto.mEmail}</td>
			<td>${mdto.mAddress}</td>
			<td>${mdto.mRdate}</td>
			<td></td>
		</tr>
		</c:forEach>
	</table>
		<input type="button" value="새로고침" onclick= "javascript:window.location='mManager.do'">
	</form>
	<input type="button" value="메인 화면으로" onclick="javascript:window.location='mMain.do'">

	<input type="button" value="로그아웃" onclick="javascript:window.location='mLogout.do'">
</body>
</html>