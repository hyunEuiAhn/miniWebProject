<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
#subjectA:link { color:black; text-decoration:none;}
#subjectA:visited{color:black;text-decoration:none;}
#subjectA:hover{color:red; text-decoration:underline;}
#subjectA:active{text-decoration:none;}
#paging{
	color : black;
	text-decoration:none;
}
#currentPaging{
	color:red;
	text-decoration:underline;
}


</style>

<form name = "boardWriteForm" action="">
<table width="500" border="1" cellpadding="3" frame="hsides" rules="rows">
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
<c:if test="${list!=null }">
<c:forEach var="boardDTO" items="${list}">
		<tr>
			<td align="center">
				<c:if test="${boardDTO.pseq==0}">
					${ boardDTO.getSeq()}
				</c:if>
			</td>
			<td align="left">
				<c:forEach var="i" begin="1" end="${boardDTO.lev}" step="1">
					&nbsp;
				</c:forEach>
				<c:if test="${boardDTO.pseq!=0}">
					<img src="../image/reply.gif">
				</c:if>
				<c:if test="${memName==null }">
					<a href="javascript:void(0)" id="subjectA"  style="cursor: pointer;"  onclick="isLogin()">${boardDTO.getSubject() }</a>
				</c:if>
				<c:if test="${memName!=null }">
					<a href="javascript:void(0)" id="subjectA"  style="cursor: pointer;"  onclick="isLogins('${name }',${boardDTO.getSeq()},${memPg})">${boardDTO.getSubject() }</a>
				</c:if>
			</td>
			
			<td align="center">${ boardDTO.getId()}</td>
			<td align="center">${ boardDTO.getLogtime()}</td>
			<td align="center">${ boardDTO.getHit()}</td>
		</tr>
	</c:forEach>
</c:if>
</table>

<br>
<div style="float: left; width:50px;">

<img src="../image/images.jpeg" width="50" height="50" 
onclick="location.href='../main/index.do'" style="cursor: pointer;">
</div>
<div style="float : left; text-align:center; width:650px;">
${ boardPaging.getPagingHTML()}
</div><br>
</form>

</body>
<script type="text/javascript">
function isLogin(){
		alert("먼저 로그인 하세요");
}
function isLogins(name,seq,memPg){
		location.href="boardView.do?seq="+seq;
}
</script>
</html>
