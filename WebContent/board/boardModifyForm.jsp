<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<form name="boardUpdate" action="boardModify.do">
<table>
	<tr>
		<th>제목</th>
		<td><input type="text" name="subject" value="${subject}"></td>
	</tr>
	
	<tr>
		<th>내용</th>
		<td><textarea rows="" cols="" name="content">${content }</textarea></td>
	</tr>
	
	<tr>
		<td align="center" colspan="2">
			<input type="submit" value="글수정" >
			<input type="reset" value="다시작성">
			<input name = "seq" type="hidden" value=${seq }>
		</td>
	</tr>
</table>
</form>