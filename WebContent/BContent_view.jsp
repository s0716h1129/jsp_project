<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글내용</title>
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
				<a href="bModify_view.do?bId=${bContent_view.bId}">수정</a> &nbsp;&nbsp;
				<a href="bList.do">목록보기</a> &nbsp;&nbsp;
				<a href="bDelete.do?bId=${bContent_view.bId}">삭제</a> &nbsp;&nbsp;
				<a href="bReply_view.do?bId=${bContent_view.bId}">답변</a> &nbsp;&nbsp;
		</tr>
	</table>
</body>
</html>