<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form name = "boardWriteForm" method="post" action="boardWrite.do">
<h3>글쓰기</h3>

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
		<input type="button" name="write" value="글쓰기" onclick="checkBoardWrite()">		
		<input type="reset" name="reset" value="다시작성">
		</td>
	</tr>
</table>
</form>
<script type="text/javascript">
function checkBoardWrite(){
	if(document.boardWriteForm.subject.value==""){
		alert("제목을 입력하세요");
		document.boardWriteForm.subject.focus();
		}
	else if(document.boardWriteForm.content.value==""){
		alert("내용을 입력하세요");
		document.boardWriteForm.content.focus();
		}
	else
	document.boardWriteForm.submit();
}
</script>