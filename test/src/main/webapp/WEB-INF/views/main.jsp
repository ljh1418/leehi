<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메인페이지</h1>
	<h2>메뉴</h2>
	
	<c:choose>
		<c:when test="${sessionScope.memberInfo == null}">
		    <a href="/login/loginPage.do">로그인/회원가입</a>
		</c:when>
		<c:otherwise>
			${sessionScope.memberInfo.memberId}님 환영합니다.
		   <a href="/login/logout.do">로그아웃</a>
		</c:otherwise>
	</c:choose>
	
	<a href="/memberinfo/memberList.do">회원목록</a>
	<a href="/board/boardList.do">게시판</a>
	
</body>
</html>