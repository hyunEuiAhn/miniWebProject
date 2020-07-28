<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

//String name = (String)request.getParameter("name");

String name=null;
String id=null;

//쿠키
/* Cookie [] ar = request.getCookies();	//특정 쿠키를 얻지 못 하고 모든 쿠키를 가져온다.
if(ar!=null){//null이면 퀴키가 다 사라졌다는 것을 의미
	for(int i=0; i<ar.length; i++){
		System.out.println("쿠키명="+ar[i].getName());
		System.out.println("쿠키값="+ar[i].getValue()); 
		
		if(ar[i].getName().equals("memname")) name = ar[i].getValue();
		if(ar[i].getName().equals("memId")) id = ar[i].getValue();
		
	}//for
	
} */


//세션
name = (String)session.getAttribute("memName");
id = (String)session.getAttribute("memId");


%>
<br>

로그인 성공
결과 = ${sessionScope.memName }
	<input type="button" value="회원정보수정" 
	onclick="location.href='/miniproject/member/modifyForm.do'">
	<input type="button" value="logout" 
	onclick="location.href='/miniproject/member/logout.do'">
