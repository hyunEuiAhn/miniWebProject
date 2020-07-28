<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check==true }">
수정성공!!
</c:if>
<c:if test="${check==false }">
수정 실패
</c:if>
<script type="text/javascript">
window.onload=function(){
	if(${check==true}){
		alert("회원정보수정성공")
	}else{
		alert("회원정보수정 실패")
	}
	location.href="http://localhost:8080/miniproject/main/index.do";
}
</script>
