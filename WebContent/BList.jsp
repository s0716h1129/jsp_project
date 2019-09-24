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
	<h1> 게시판 </h1>
		<form action="bListSerch.do">
		<select name=bType_ser>
			<option value="t1"> 공지사항 </option>
			<option value="t2"> 자유 게시판 </option>
			<option value="t3"> 자료실 </option>
		</select>
		<select name=bSer_t>
			<option value="sname"> 작성자 </option>
			<option value="stitle"> 제목 </option>
		</select>
		<input type="text" name="serch" size = "30" />
		<input type="submit" value="검색">
		<input type="button" value="새로고침" onclick="javascript:window.location='bList.do'">
		</form>
		
	
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>번호</td>
			<td>게시판</td>
			<td>작성자</td>
			<td>제목</td>
			<td>날짜</td>
			<td>히트</td>
		</tr>

		<c:forEach items="${list}" var="dto">		
		<tr>
			<td>${dto.bId}</td>
			<td>${dto.bType}</td>
			<td>${dto.bName}</td>
			<td>
				<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
				<a href="bContent_view.do?bId=${dto.bId}">${dto.bTitle}</a>
			</td>
			<td>${dto.bDate}</td>
			<td>${dto.bHit}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6"> <a href="bWrite_view.do">글작성</a></td>
		</tr>
	</table>
	<br /><input type="button" value="로그아웃" onclick="javascript:window.location='mLogout.do'">
</body>
</html>