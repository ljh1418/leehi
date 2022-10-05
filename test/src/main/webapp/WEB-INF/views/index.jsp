<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
 <%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String name = "";
	if(auth.getPrincipal() != null) {
		name = auth.getName();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>home.jsp</h1>

<div style="border:1px solid ;">
	
		<sec:authorize access="isAnonymous()">
			<h5>
				<a href='<c:url value="/secu/loginPage"/>'>LOGIN</a> 로그인 해주세요.
			</h5>
	    </sec:authorize>
		
		<!-- isAuthenticated() 인증된 이용자 일때 로그아웃 버튼을 보여줌 -->
		<sec:authorize access="isAuthenticated()">
          	<h5><%=name %>님, 반갑습니다.</h5>
	        <form action="/logout" method="POST">
	                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	                <button type="submit">LOGOUT</button>
	        </form>
          </sec:authorize>
</div>


<div class="container text-center col-2">
	<a href='<c:url value="/page"/>' role="button">GUEST</a>
	<a href='<c:url value="/user/page"/>' role="button">USER</a>
	<a href='<c:url value="/member/page"/>' role="button">MEMBER</a>
	<a href='<c:url value="/admin/page"/>' role="button">ADMIN</a>
</div>

</body>
</html>