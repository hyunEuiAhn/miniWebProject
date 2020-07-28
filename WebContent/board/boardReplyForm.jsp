<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form name = "boardReplyForm" method="post" action="boardReply.do?">
<h3>답글쓰기</h3>
<input type="hidden" name="pg" value="${pg }">
<input type="hidden" name="pseq" value="${pseq}">


<table border="1" cellpadding="5" cellspacing="0">
	<tr>
		<td>제목</td>
		<td>
		<input type="text" name="subject" size="50">
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
		<textarea name="content" cols="70" rows="30"></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="button" name="write" value="답글쓰기" onclick="checkBoardReply()">		
		<input type="reset" name="reset" value="다시작성">
		</td>
	</tr>
</table>
</form>
<script type="text/javascript">
function checkBoardReply(){
	if(document.boardReplyForm.subject.value==""){
		alert("제목을 입력하세요");
		document.boardReplyForm.subject.focus();
		}
	else if(document.boardReplyForm.content.value==""){
		alert("내용을 입력하세요");
		document.boardReplyForm.content.focus();
		}
	else
	document.boardReplyForm.submit();
}
</script>