<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2><img src="../image/images.jpeg" width="50" height="50"
onclick="location.href='../main/index.do'" style="cursor: pointer;"> MVC를 이용한 미니 프로젝트</h2>
<br>


<c:if test="${sessionScope.memName!=null}">
<a href="../board/boardWriteForm.do">글쓰기</a>&nbsp;
<a href="../imageboard/imageboardWriteForm.do">이미지 등록</a>&nbsp;

</c:if>
<a href="../board/boardList.do?pg=1">목록</a>&nbsp;
<a href="../imageboard/imageboardList.do?pg=1">이미지 목록</a>&nbsp;
