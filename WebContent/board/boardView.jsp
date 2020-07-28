<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form name="boardForm" action="boardModifyForm.do?seq=${seq }">
<table border="1" width="300" frame="hsides" rules="rows">
	<tr>
		<th>제목</th>
		<td align="center" colspan="6"><input type="text" name="subject" value="${ boardDTO.getSubject()}" readonly="readonly"></td>
	</tr>
	<tr>
		<th>글번호</th>
		<td>${boardDTO.getSeq()}</td>
		<th>작성자</th>
		<td>${boardDTO.getId()}</td>
		<th>조회수</th>
		<td>${boardDTO.getHit()}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td align="center" colspan="6"><textArea name = "content" readonly="readonly">${ boardDTO.getContent()}</textArea></td>
	</tr>
	<tr>
		<td align="center" colspan="6">
		<input type="button" value="목록" onclick="backList('${pg}')">
		<c:if test="${id==writeId }">
			<input type="submit" value="글수정">
			<input type="button" value="글삭제" onclick="location.href='/miniproject/board/boardDelete.do?seq=${seq}'">
			<input name ="pg" type="hidden" value=${pg }>
			<input name="seq" type="hidden" value=${seq }>
		</c:if>
		<input type="button" value="답글" onclick="replyForm(${pg},${seq})">
		</td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
function backList(pg){
	location.href="boardList.do?pg="+pg;
}
function replyForm(pg,pseq){
	location.href="boardReplyForm.do?pg="+pg+"&pseq="+pseq;
}
</script>
</html>