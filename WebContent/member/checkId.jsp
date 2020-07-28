<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "member.dao.MemberDAO"%>
<%
String id = request.getParameter("id"); 
MemberDAO memberDAO = MemberDAO.getInstance();
boolean exist = memberDAO.isExistId(id);
  
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID확인</title>
</head>
<body>
<form method="get" action="/miniproject/member/checkId.do">

<%if(exist){ %>
	<%=id %> 사용 가능 <br>
	<input type="button" value="사용하기" onclick="checkIdClose()">
<%}else{ %>
	<%=id %> 사용 불가능 <br>
	아이디 : <input type = "text" width="10" name="id">
			<input type="submit" value="중복체크" >
	
<%} %>
</form>
</body>
<script type="text/javascript">
function checkIdClose(){
	opener.writeForm.id.value="<%=id%>";
	opener.writeForm.idcheck.value="<%=id%>";
	window.close();
	opener.writeForm.pwd.focus();
}
</script>
</html>