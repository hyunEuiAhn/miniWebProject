<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.dao.MemberDAO,
    							 java.net.URLEncoder,
    							 member.bean.MemberDTO,
    							 java.util.Map"%>
    
<%	//data
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	//database
	MemberDAO memberDAO = MemberDAO.getInstance();
	Map<String, String> map = memberDAO.login(id, pwd);
	
	//MemberDTO memberDTO = memberDAO.getDTO(id);
	//String email = memberDTO.getEmail1()+"@"+memberDTO.getEmail2();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<form name = login method="post" action="modifyForm.jsp?id=<%=id%>">
<input type="hidden" name="id" value="<%=id %>">

<%if(map!=null){%> 
<%--
<input type="button" value="회원정보수정" onclick="javascript : modify()">
<input type="button" value="회원정보수정" onclick="location.href='modifyForm.jsp?id=<%=id%>'">

<input type="submit" value="회원정보수정" >
--%>
<%
//response.sendRedirect("loginOk.jsp?name="+URLEncoder.encode(name,"UTF-8"));
//유니코드 문법으로 변환해서 넣어주지 않으면 데이터를 못 받음


//쿠키
/* Cookie cookie = new Cookie("memname",name);//생성
cookie.setMaxAge(30*60);	//초단위 -3초 후에 없어진다.
response.addCookie(cookie);	//클라이언트에게 보내기

Cookie cookie2 = new Cookie("memId",id);
cookie2.setMaxAge(30*60);	//30분동안 살아있다.
response.addCookie(cookie2); */

//세션

//HttpSession session = request.getSession(); //ssesion이라는 내장객체가 이미 존재

session.setAttribute("memName", map.get("name")); //기본 1800초 30분을 갖고 있다
//session.setMaxInacriveInterval(30*60);	//초단위
session.setAttribute("memId",id);
session.setAttribute("memEmail",map.get("email1")+"@"+map.get("email2"));


response.sendRedirect("loginOk.jsp");
%>



<%}else{%>
<%
response.sendRedirect("loginFail.jsp");
 %>

<% }%>
</form>
</body>


<script type="text/javascript">
function modify(){
	 var id = "<%=id%>";
	 window.open("modifyForm.jsp?id="+id,"","width=500 height=500 location=yes left=300px top=100px");
}
 </script>
</html>