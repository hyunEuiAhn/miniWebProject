<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="button" name="id" value="사용하기"onclick="checkIdClose()">
${id }사용 가능
</body>
<script type="text/javascript">
function checkIdClose(){
	opener.writeForm.id.value='${id}';
	opener.writeForm.idcheck.value='${id}';
	window.close();
	opener.writeForm.pwd.focus();
}
</script>
</html>