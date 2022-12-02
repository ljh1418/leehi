<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>

	$(document).ready(function(){
		
		//로그인 로직
	    $("#btnLogin").click(function(){
	        var userId = $("#userId").val();
	        var userPw = $("#userPw").val();
	        if(userId == ""){
	            alert("아이디를 입력하세요.");
	            $("#userId").focus(); // 입력포커스 이동
	            return; // 함수 종료
	        }
	        if(userPw == ""){
	            alert("아이디를 입력하세요.");
	            $("#userPw").focus();
	            return;
	        }
	        // 폼 내부의 데이터를 전송할 주소
	        document.form.action="/login/login.do"
	        // 제출
	        document.form.submit();
	    });
		
		//회원가입 페이지 이동
		$("#btnMemeberWriter").click(function(){
			location.href="/memberinfo/memberInsertPage.do";	
		});
	    
	});

</script>
</head>
<body>
	<h1>로그인</h1>
    <form name="form" method="post">
        <table border="1" width="400px">
            <tr>
                <td>아이디</td>
                <td><input name="memberId" id="userId"></td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" name="memberPw" id="userPw"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="button" id="btnLogin">로그인</button>
                    <button type="button" id="btnMemeberWriter">회원가입</button>
                <c:if test="${message == 'error'}">
                    <div style="color: red">
						아이디 또는 비밀번호가 일치하지 않습니다.
                    </div>
                </c:if>
                <c:if test="${message == 'logout'}">
                    <div style="color: red">
						로그아웃되었습니다.
                    </div>
                </c:if>
                </td>
            </tr>
        </table>
    </form>

</body>
</html>