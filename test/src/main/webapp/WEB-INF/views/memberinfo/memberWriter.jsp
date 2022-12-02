<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	

	$("#btnMemberInsert").click(function(){
		
	
		var memberId = $("#memberId").val(); 
		console.log("memberId : ",memberId);
		
		var memberPw = $("#memberPw").val();
		console.log("memberPw : ",memberPw);
		
		var memberNm = $("#memberNm").val();
		console.log("memberNm : ",memberNm);
		
		var memberEmail = $("#memberEmail").val();
		console.log("memberEmail : ",memberEmail);
		
		//폴더의 memberinfo가 붙어서 action 전송
		//var action = "/memberinfo/memberInsert.do";
		//console.log("action : ",action);
		
		var f = document.createElement("form"); // form 엘리멘트 생성 
		f.setAttribute("method","post"); // method 속성을 post로 설정
		f.setAttribute("action","/memberinfo/memberInsert.do"); // submit했을 때 무슨 동작을 할 것인지 설정
		document.body.appendChild(f); // 현재 페이지에 form 엘리멘트 추가 
		
		//memberId
		var i = document.createElement("input"); // input 엘리멘트 생성 
		i.setAttribute("type","hidden"); // type 속성을 hidden으로 설정
		i.setAttribute("name","memberId"); // name 속성을 'm_nickname'으로 설정 
		i.setAttribute("value",memberId); // value 속성을 neilong에 담겨있는 값으로 설정 
		f.appendChild(i); // form 엘리멘트에 input 엘리멘트 추가 
		
		alert("1");
		f.submit(); // form 태그 서브밋 실행
	

		
		
		//$("#formId").submit();
		
		/* var idChkVal = $("#idChk").val();
		console.log('idChkVal : '. idChkVal);
		
		if(idChkVal == "N"){
			alert("중복확인 버튼을 눌러주세요.");
		}else if(idChkVal == "Y"){
			$("#joinForm").submit();
		} */ 
	})
	
	$("#btnLoginPage").click(function(){
		location.href="/login/loginPage.do";
	})

});



</script>
</head>
<body>
	<h1>회원등록</h1>
	<form action="/memberinfo/memberWriter.do" method="post">
	<table border="1" width="800px">
		<tr>
			<td>아이디</td>
			<td>
				<input type="text" name="memberId" id="memberId">
				<!-- <button type="button" id="idCheck">중복체크</button> -->
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="memberPw" id="memberPw"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input name="memberName" id="memberName"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input name="memberEmail" id="memberEmail"></td>
		</tr>
		<!-- <tr>
			<td>우편번호</td>
			<td>
				<input type="text" id="userZipcode" name="userZipcode" size="10">
				<input type="button" onclick="findAddr()" value="주소검색">
				
			</td>
		</tr> -->
		<!-- <tr>
			<td>집주소</td>
			<td><input type="text" name="userAddr1" id="userAddr1" size="50"></td>
		</tr>
		<tr>
			<td>상세주소</td>
			<td><input type="text" name="userAddr2" id="userAddr2" size="50"></td>
		</tr> -->
		<!-- <tr>
			<td>전화번호</td>
			<td><input name="userPhone" placeholder="01012345678"></td>
		</tr> -->
		<!-- 회원중복 체크 했는지 -->
		<!-- <input id="idChk" type="hidden" value="N"> -->
	</table>
	<button type="submit">등록하기</button>
	<button type="button" id="btnLoginPage">로그인페이지로 돌아가기</button>
	</form>
</body>
</html>