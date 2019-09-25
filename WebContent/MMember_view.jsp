<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>아이디</td>
			<td>${MMember_view.mId}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${MMember_view.mName}</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${MMember_view.mEmail}</td>
		</tr>
		<tr>
			<td>가입 날짜</td>
			<td>${MMember_view.mRdate}</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>${MMember_view.mAddress}</td>
		</tr>
		<tr>
			<td>정지 여부</td>
			<td></td>
		</tr>
		<tr>
			<td>게시글 갯수</td>
			<td></td>
		</tr>
		<tr>
			<td>댓글 갯수</td>
			<td></td>
		</tr>
	</table>
	
	<a href="mManager.do">관리 페이지 보기</a> &nbsp;&nbsp;
</body>
</html>