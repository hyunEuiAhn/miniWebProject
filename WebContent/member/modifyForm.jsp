<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.bean.MemberDTO, member.dao.MemberDAO"%>
    

<form name ="modifyForm" method="post" action="/miniproject/member/modify.do">

${param.id }
<table border="1" cellpadding="5" cellspacing = "0">
  <tr>
   <td align ="center" width="80">* 이름</td>
   <td><input type = "text" size ="7" name = "name" placeholder="이름입력" value="${memberDTO.name }">
   </td>
  </tr>
  
  <tr>
   <td align ="center">* 아이디</td>
   <td><input type ="text" size ="10" name ="id" readonly="readonly" value="${memberDTO.id }">
   </td>
  </tr>
  
  <tr>
   <td align ="center">* 비밀번호</td>
   <td><input type ="password" size ="13" name ="pwd">
   </td>
  </tr>
  
  <tr>
  <td align ="center">성별</td>
   <td>	
   	   <input type = "radio" name = "gender" value ="0">남자
       <input type = "radio" name = "gender" value ="1">여자
   </td>
  </tr>
   
  <%--
  <tr>
   <td align ="center">성별</td>
   <%if(gender.equals("0")){%>
   <td><input type = "radio" name = "gender" value =0 checked>남자
       <input type = "radio" name = "gender" value =1>여자
   </td>
   <%}else{ %>
   <td><input type = "radio" name = "gender" value =0 >남자
       <input type = "radio" name = "gender" value =1 checked>여자
   </td>
   <%} %>
  </tr>
  --%>
  
  <tr>
   <td align="center">이메일</td>
   <td><input type = "text" size ="12" name="email1" value="${memberDTO.email1}">@
   		<input type = "text" list = "email2"  id=myEmail2 name="email2">
   		<datalist id = "email2">
   		<!-- select name ="email3" style="width : 100px;"-->
   		<!-- option value="">직접입력</option-->
   		<option value="naver.com">naver.com</option>
   		<option value="google.com">google.com</option>
   		<option value="daum.net">daum.net</option>
   		<!--/select-->
   		</datalist>
   </td>
  </tr>
  <tr>
   <td align="center">핸드폰</td>
   <td><select name = tel1 id=mytel1>
        <option value = "010" >010</option>
        <option value = "011">011</option>
        <option value = "017">017</option>
       </select> - 

   	   <input type ="text" size="5" name = tel2 value="${memberDTO.tel2 }"> - 
   	   <input type ="text" size="5" name = tel3 value="${memberDTO.tel3}"> 
   </td>
  </tr>
  
  <%-- 
   <tr>
   <td align="center">핸드폰</td>
   <%if(tel1.equals("010")){%>
   <td><select name = tel1>
        <option value = "010" selected="selected">010</option>
        <option value = "011">011</option>
        <option value = "017">017</option>
       </select> - 
   <%}else if(tel1.equals("011")){ %>
   <td><select name = tel1>
        <option value = "010">010</option>
        <option value = "011"selected="selected">011</option>
        <option value = "017">017</option>
       </select> - 
   <%}else{%>
   <td><select name = tel1>
        <option value = "010">010</option>
        <option value = "011">011</option>
        <option value = "017" selected="selected">017</option>
       </select> - 
   <%} %>

   	   <input type ="text" size="5" name = tel2 value="<%=tel2%>"> - 
   	   <input type ="text" size="5" name = tel3 value="<%=tel3%>">
   </td>
  </tr>
  --%>
  
  
   <tr>
   <td align="center">주소</td>
   <td><input type="text" size = "7" name ="zipcode" id="daum_zipcode" readonly>
   	   <input type="button" value="우편번호찾기" onclick = "checkPost()">
   <br>
   	   <input type="text" size = "50" name="addr1" id="daum_addr1" placeholder="기본주소" readonly><br>
   	   <input type="text" size = "50" name="addr2" id="daum_addr2" placeholder="상세주소">
   </td>
  </tr>
  
  <tr>
   <td colspan ="2" align="center">
    <input type=button value = "수정완료" onclick='check()'>
   </td>
  </tr>
</table>
</form>
</body>
 <script src="../js/member.js"></script>
<script type="text/javascript" >
window.onload=function(){
	document.modifyForm.gender[0].checked=true; //script에서 배열 타입으로 넘긴다(라디오버튼)
	//document.modifyForm.email2.value = '${email2}'; 
	document.getElementById("myEmail2").value = '${memberDTO.email2}';	//<>모든테그의 최상위 element
	//document.modifyForm.tel1.value='${tel1}';
	document.getElementById("mytel1").value='${memberDTO.tel1}';
}

</script>
</html>