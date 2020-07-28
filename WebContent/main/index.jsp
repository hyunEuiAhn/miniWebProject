<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" cellpadding="3" cellspacing="0" width="100%">
	<tr>
		<td colspan="2" align="center">
			<jsp:include page="../template/top.jsp"/>
		</td>
	</tr>
	<tr>
		<td width="200px" height="300" valign="top">
			<jsp:include page="../template/left.jsp"/>
		</td>
		<td>
			<jsp:include page="${display }"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<jsp:include page="../template/bottom.jsp"/>
		</td>
	</tr>
</table>
</body>
</html>