<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form name ="imageBoardWrite" method="post" enctype="multipart/form-data"
action="imageboardWrite.do">
<h3>이미지 등록</h3>
<table border="1" cellpadding="3" cellspasing="0">
	<tr>
		<td>상품코드</td>
		<td><input name="imageId" type="text" value="img_"></td>
	</tr>
	
	<tr>
		<td>상품명</td>
		<td><input name="imageName"type="text" placeholder="상품명 입력"></td>
	</tr>
	
	<tr>
		<td>단가</td>
		<td><input name="imagePrice" type="text" placeholder="단가 입력"></td>
	</tr>
	
	<tr>
		<td>갯수</td>
		<td><input name="imageQty"type="text" placeholder="갯수 입력"></td>
	</tr>
	
	<tr>
		<td>내용</td>
		<td><textarea name="imageContent" rows="35" cols="50" placeholder="내용 입력"></textarea></td>
	</tr>
	
	<tr>
		<td colspan="2"><input type="file" name="image1" size="50"></td>
	</tr>
	
	<tr>
		<td align="center" colspan="2">
			<input type="button" value="이미지 등록" onclick="cjeckImageBoardWrite()">
			<input type="reset" value="다시 작성">
		</td>
	</tr>
	</table>
</form>
<script type="text/javascript">
function cjeckImageBoardWrite(){
	if(document.imageBoardWrite.imageId.value=="")
		alert("상품코드를 입력하세요");
	else if(document.imageBoardWrite.imageName.value=="")
		alert("상품명을 입력하세요");
	else if(document.imageBoardWrite.imagePrice.value=="")
		alert("단가를 입력하세요");
	else if(document.imageBoardWrite.imageQty.value=="")
		alert("개수를 입력하세요");
	else if(document.imageBoardWrite.imageContent.value=="")
		alert("내용을 입력하세요");
	else if(document.imageBoardWrite.image1.value=="")
		alert("ㅁ이미지를 입력하세요");
	else document.imageBoardWrite.submit();
}

</script>