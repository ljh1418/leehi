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
	

	
	
	//스크립트로 값 넘겨주기
	$("#btnUpdete").click(function(){
		alert("게시글을 수정 하시겠습니까?");
		
		var title = $("#boardTitle").val();
		console.log('title : ',title);
		
	    var content = $("#boardContent").val();
	    console.log('content : ',content);
	    
	    
		document.form.action="/memberinfo/memberUpdate.do";
		document.form.submit();
				
	});
	
	$("#btnLoginPage").click(function(){
		location.href="/login/loginPage.do";
	})

});



</script>
</head>
<body>
	<h1>회원수정</h1>
	<form name="form" action="${actionType}" method="POST" enctype="multipart/form-data">
	<table border="1" width="800px">
		<tr>
			<td>아이디</td>
			<td>
				<input type="text" name="memberId" id="memberId" value="${memberView.memberId}">
				<!-- <button type="button" id="idCheck">중복체크</button> -->
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="memberPw" id="memberPw" value="${memberView.memberPw}"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input name="memberName" id="memberName" value="${memberView.memberName}"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input name="memberEmail" id="memberEmail" value="${memberView.memberEmail}"></td>
		</tr>
		<button type="button" id="btnUpdete">수정</button>
		<button type="button" id="btnLoginPage">회원목록 페이지 돌아가기</button>
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
	
	</form>
</body>
</html>