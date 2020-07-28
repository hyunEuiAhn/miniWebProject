<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소검색</title>
<link rel="stylesheet" href="../css/member.css"></link>
</head>
<body>
<form name = "checkPost" method="get"  action="/miniproject/member/checkPost.do">
<table border="1" cellpadding="5" cellspacing="0" width="100%">
 <tr>
	<td align="center">시도</td>
	<td><select name="sido">
		<option value=>시도선택</option>
		<option value="서울">서울</option>
		<option value="인천">인천</option>
		<option value="대전">대전</option>
		<option value="대구">대구</option>
		<option value="울산">울산</option>
		<option value="세종">세종</option>
		<option value="광주">광주</option>
		<option value="경기">경기</option>
		<option value="강원">강원</option>
		<option value="전남">전남</option>
		<option value="전북">전북</option>
		<option value="경남">경남</option>
		<option value="경북">경북</option>
		<option value="충남">충남</option>
		<option value="충북">충북</option>
		<option value="부산">부산</option>
		<option value="제주">제주</option>
	</select>
	</td>
	<td>시군구</td>
	<td><input type="text" width="10" name="sigungu"></td>

 </tr>
 	<td align="center">도로명</td>
 	<td colspan="3">
 	<input type="text" name = "roadname">
 	<input type="submit" value="검색">
 	</td>
 <tr>
	<td align="center">우편번호</td>
	<td colspan="3">주소</td>

 </tr>

</table>
 <table border="3" cellpadding="5" cellspacing="0" width="100%">

 <c:if test="${not empty requestScope.list }">
	<c:forEach var="zipcodeDTO" items="${list}">
	<c:set var="address">
		${zipcodeDTO.sido 
		} ${zipcodeDTO.sigungu
		} ${zipcodeDTO.yubmyumdong
		} ${zipcodeDTO.ri 
		} ${zipcodeDTO.roadname 
		} ${zipcodeDTO.buildingname}
	</c:set>
	<tr>
		<td align = "center">${zipcodeDTO.zipcode }</td>
		<td colspan="3">
	<a href="#" class='addressA' 
	onclick="checkPostClose('${zipcodeDTO.zipcode }','${address }')">
	${address}
 </a>
 </td>
 </tr>
 	</c:forEach>
 	</c:if>
 </table>
 

</form>
</body>
 <script src="../js/member.js">

</script>
</html>