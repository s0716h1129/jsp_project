<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글작성</title>
<%
String bName = (String)session.getAttribute("name");
String bId = (String)session.getAttribute("id");
%>
</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="bWrite.do" method="post">
			<tr>
				<td> 이름 </td>
				<td> <%= bName %> (<%= bId %>)</td>
			</tr>
			<tr>
				<td> 게시판 </td>
				<td>
					<select name=bType_sel>
						<option value="t1"> 공지사항 </option>
						<option value="t2" selected=selected> 자유 게시판 </option>
						<option value="t3"> 자료실 </option>
					</select>
				</td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="bTitle" size="50"></td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> <textarea name="bContent" rows="10"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="입력">&nbsp;&nbsp;
					<a href="bList.do">목록보기</a>
				</td>
			</tr>
		</form>
	</table>
</body>
</html>