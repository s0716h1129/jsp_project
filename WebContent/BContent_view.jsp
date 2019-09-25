<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글내용</title>

<%
String bName = (String)session.getAttribute("name");
String bId = (String)session.getAttribute("id");
pageContext.setAttribute("bName", bName);
%>
</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>번호</td>
			<td>${BContent_view.bId}</td>
		</tr>
		<tr>
			<td>게시판</td>
			<td>${BContent_view.bType}</td>
		</tr>
		<tr>
			<td>히트</td>
			<td>${BContent_view.bHit}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${BContent_view.bName}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${BContent_view.bTitle}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${BContent_view.bContent}</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:if test="${BContent_view.bName eq bName}"><a href="bModify_view.do?bId=${BContent_view.bId}">수정</a> &nbsp;&nbsp;</c:if>				
				<a href="bList.do">목록보기</a> &nbsp;&nbsp;
				<a href="bDelete.do?bId=${BContent_view.bId}">삭제</a> &nbsp;&nbsp;
				<a href="bReply_view.do?bId=${BContent_view.bId}">답변</a> &nbsp;&nbsp;
		</tr>
	</table>
	
	<br><hr>
	<form action="bComment.do?bId=${BContent_view.bId}" method="post">
		<input type="hidden" name="boardId" value="${BContent_view.bId}">
		<input type="text" name="bComment" size="50">
		<input type="submit" value="댓글달기">
	</form>
	
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<c:forEach items="${list}" var="cdto">
			<tr>
				<td> ${BComment_view.cName} </td>
				<td> ${BComment_view.cComment}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>