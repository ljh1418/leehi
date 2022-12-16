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
		
		var memberNum = $("#memberNum").val();
		console.log('memberNum : ',memberNum);
		
		var memberId = $("#memberId").val();
		console.log('memberId : ',memberId);
		
	    var memberPw = $("#memberPw").val();
	    console.log('memberPw : ',memberPw);
	    
	    alert('1');
	    
	    
		document.form.action="/memberinfo/memberUpdate.do";
		document.form.submit();
				
	});
	
	
	$("#btnAjax").on("click",function(){
		
		//배열객체
		
		var memberNum = $("#memberNum").val();
		console.log('memberNum : ',memberNum);
		
		var memberId = $("#memberId").val();
		console.log('memberId : ',memberId);
		
	    var memberPw = $("#memberPw").val();
	    console.log('memberPw : ',memberPw);
	    
	    var memberName = $("#memberName").val();
	    console.log('memberName : ',memberName);
	    
	    var memberEmail = $("#memberEmail").val();
	    console.log('memberEmail : ',memberEmail);
	    
	    $.ajax({
        	url : '/memberinfo/memberAjaxUpdate.do',
        	type : 'post',
        	data : {
        				memberNum : memberNum ,
        				memberId : memberId , 
						memberPw : memberPw , 
						memberName : memberName , 
						memberEmail : memberEmail
					},
        	success : function(data){
        		console.log("data1 : ",data);
        		location.href="/memberinfo/memberList.do";
        	},
        	error : function(request,status,error){
        		console.log("실패...");
        		console.log("error : ",error)
        	}
        })
		
	})
	
	$("#btnMemberDelete").click(function(){
		confirm("회원을 삭제 하시겠습니까?");
		
		var memberNum = $("#memberNum").val();
		console.log("memberNum : ",memberNum);
		
		location.href = "/memberinfo/memberDelete.do?memberNum=" + memberNum
		
	});
	
	$("#btnLoginPage").click(function(){
		location.href="/memberinfo/memberList.do";
	})

});



</script>
</head>
<body>
	<h1>회원수정</h1>
	<form name="form" action="${actionType}" method="GET" enctype="multipart/form-data">
		<table border="1" width="800px">
			<!-- 회원번호 -->
			<input type="hidden" name="memberNum" id="memberNum" value="${memberView.memberNum}" />
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="memberId" id="memberId" readonly value="${memberView.memberId}">
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
				<td><input name="memberEmail" id="memberEmail" value="${memberView.memberEmail}" placeholder="ex)test@gmail.com"></td>
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
	</form>
	<!-- <button type="button" id="btnUpdete">수정</button> -->
	
	<c:if test="${sessionScope.memberInfo.memberId == memberView.memberId}">
		<button type="button" id="btnAjax">회원수정(Ajax)</button>
		<button type="button" id="btnMemberDelete">회원삭제</button>
		<button type="button" id="btnLoginPage">회원목록 페이지 돌아가기</button>
	</c:if>
</body>
</html>