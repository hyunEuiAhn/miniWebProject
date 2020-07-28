<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="" method="get" action="/miniproject/member/checkId.do">
${requestScope.id }사용 불가<br>
	아이디 : <input type = "text" width="10" name="id">
			<input type="submit" value="중복체크" >
</form>
</body>
</html>