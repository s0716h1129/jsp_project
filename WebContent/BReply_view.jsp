<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%
String bName = (String)session.getAttribute("name");
String bId = (String)session.getAttribute("id");
%>
</head>
<body>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="bReply.do" method="post">
			<input type="hidden" name="bId" value="${reply_view.bId}">
			<input type="hidden" name="bGroup" value="${reply_view.bGroup}">
			<input type="hidden" name="bStep" value="${reply_view.bStep}">
			<input type="hidden" name="bIndent" value="${reply_view.bIndent}">
			<tr>
				<td>번호</td>
				<td>${reply_view.bId}</td>
			</tr>
			<tr>
				<td>게시판</td>
				<td>
					<select name=bType>
						<option value="t1" <c:if test="${reply_view.bType eq 't1'}">selected="slected" </c:if>>공지사항 </option>
						<option value="t2" <c:if test="${reply_view.bType eq 't2'}">selected="slected"</c:if>> 자유 게시판 </option>
						<option value="t3"<c:if test="${reply_view.bType eq 't3'}">selected="slected" </c:if>> 자료실 </option>
					</select>
				</td>
			</tr>
			<tr>
				<td>히트</td>
				<td>${reply_view.bHit}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td> <%= bName %> (<%= bId %>)</td>
			</tr>
			<tr>
				<td>제목</td>
				<td> <input type="text" name="bTitle" value="${reply_view.bTitle}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td> <textarea rows="10" name="bContent">${reply_view.bContent}</textarea>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="답변"> <a href = "bList.do">목록</a>
			</tr>
		</form>
	</table>
</body>
</html>