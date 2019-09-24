<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="bModify.do" method="post">
			<input type="hidden" name="bId" value="${BContent_view.bId}">
			<tr>
				<td>번호</td>
				<td>${BContent_view.bId}</td>
			</tr>
			<tr>
				<td>게시판</td>
				<td>${BContent_view.bType}</</td>
			</tr>
			<tr>
				<td>히트</td>
				<td>${BContent_view.bHit}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="bName" value="${BContent_view.bName}"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle" value="${BContent_view.bTitle}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" name="bContent">${BContent_view.bContent}</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정">&nbsp;&nbsp;
				<a href = "bContent_view.do?bId=${BContent_view.bId}">취소</a>&nbsp;&nbsp;
				<a href="bList.do">목록보기</a>&nbsp;&nbsp;
				</td>
			</tr>
		</form>
	</table>
</body>
</html>