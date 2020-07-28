 function checkWrite(){

 	if(document.writeForm.name.value=="")alert("이름을 입력하세요")
 	else if(document.writeForm.id.value==""){
 		alert("아이디를 입력하세요")
 		document.writeForm.id.focus()
 	}
 	else if(document.writeForm.pwd.value=="")alert("비밀본호를 입력하세요")
 	else if (document.writeForm.pwd.value!=document.writeForm.repwd.value)alert("비밀번호가 맞지 않습니다.")
 	else if(document.writeForm.id.value!=document.writeForm.idcheck.value)alert("아이디 중복체크 해주세요");
 	
 	else document.writeForm.submit()
 }
 function checkPost(){
     window.open("/miniproject/member/checkPost.do","","width=400 height=400 left=350 top=200 scrollbars=yes");
 }
 
 function checkId(){
	 var sid = document.writeForm.id.value;
	 if(sid==""){
		 alert("아이디를 입력하세요");
	 }else 
		 window.open("/miniproject/member/checkId.do?id="+sid,"","width=300 height=100 location=yes left=300px top=100px");
 }
 function check(){
	 	if(document.modifyForm.name.value=="")alert("이름을 입력하세요")
	 	else if(document.modifyForm.id.value==""){
	 		alert("아이디를 입력하세요")
	 		document.modifyForm.id.focus()
	 	}
	 	else if(document.modifyForm.pwd.value=="")alert("비밀본호를 입력하세요")
	 	else document.modifyForm.submit()
	 }
 
 function checkPostClose(zipcode, address){
 	//opener.writeForm.zipcode.value=zipcode;
 	//opener.writeForm.addr1.value=address;
 	//window.close();
 	//opener.writeForm.addr2.focus();
	 
//	opener.document.forms[0].zipcode.value=zipcode;
//	opener.document.forms[0].addr1.value=address;
//	window.close();
//	opener.document.forms[0].addr2.focus();
	 
	 opener.document.getElementById('daum_zipcode').value = zipcode;
	 opener.document.getElementById('daum_addr1').value = address;
	 window.close();
	 opner.document.getElementById('daum_addr2').focus();
 }

 /**
 * 
 */