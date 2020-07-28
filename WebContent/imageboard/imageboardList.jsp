<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<style>
#paging{
	color:black;
	rext-deciration:none;
	cursor:pointer;
}
#currentPaging{
	color:red;
	text-decoration:underline;
	cursor:pointer;
}
</style>
<form name="imageboardList" method="" 
	action="/miniproject/imageboard/imageboardDelete.do">
<c:if test="${list!=null }">

<table border="1" cellpading="3" cellspacing="0" grame="hsides" rules="rows">
	<tr>
		<th width="100"><input type="checkbox" id="checkT" onclick="javascript:check()" >번호</th>
		<th width="100">이미지</th>		
		<th width="100">상품명</th>
		<th width="100">단가</th>
		<th width="100">개수</th>
		<th width="100">합계</th>
	</tr>
	<c:forEach var="imageboardDTO" items="${list }">
		<tr>
			<td align="center">
			<input name="checkb" type="checkbox" value="${imageboardDTO.seq }">
			${imageboardDTO.seq }
			</td>
			<td align="center">
			<img src="http://localhost:8080/miniproject/storage/${imageboardDTO.image1}" width="50" height="50">
<%-- 		<img src="../storage/">${imageboardDTO.image1}--%>
			</td>
			<td align="center">${imageboardDTO.imageName }</td>
			<td align="center">
			<fmt:formatNumber pattern="#,###원" value="${imageboardDTO.imagePrice }"/>
			</td>
			<td align="center">${imageboardDTO.imageQty }</td>
			<td align="center">${imageboardDTO.imageQty*imageboardDTO.imagePrice }</td>
		</tr>
	</c:forEach>
</table>
<div style="float:left; width:70px; ">
	<input type="button" value="선택삭제" onclick="choiceDelete()">
	</div>
<div style="float:left; text-align:center; width:500px;">${imageboardPaging.pagingHTML }</div>
</c:if>
</form>
<script type="text/javascript">
function check(){
	if(imageboardList.checkT.checked==true){
		for(i=0; i<=imageboardList.checkb.length; i++){
			imageboardList.checkb[i].checked = true;
		}
	}else{
		for(i=0; i<=imageboardList.checkb.length; i++){
			imageboardList.checkb[i].checked = false;
		}
	}
}
function imageboardPaging(pg){
	location.href="/miniproject/imageboard/imageboardList.do?pg="+pg;
}
function checkAll(){
	alert(document.getElementsByName("checkNum").length);
	alert(document.getElementById("checkNum").length);
	var checkNum = document.getElementsByName("checkNum");
	var size = document.getElementsByName("checkNum").lenth;
	if(document.getElementById("all").checked){
		for(i=0;i<size;i++){
			checkNum[i].checked = true;
		}
	}else{
		for(i=0;i<size; i++){
			checkNum[i].checked= false;
		}
	}
}
function choiceDelete(){
	var size = document.getElementsByName("checkb").length;
	var count=0;
	
	for(i=0;i<imageboardList.checkb.length;i++){
		if(imageboardList.checkb[i].checked){
			count++;
		}
	}
	if(count==0){
		alert("항목을 선택해 주세요");
	}else{
		document.imageboardList.submit();

	}
}
</script>
