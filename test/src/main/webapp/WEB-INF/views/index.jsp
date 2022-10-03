<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>home.jsp</h1>
<h5>
	<sec:authorize access="isAnonymous()">
		<h5>
			<a href='<c:url value="/secu/loginPage"/>'>LOGIN</a> 로그인 해주세요.
		</h5>
    </sec:authorize>
</h5>

<a href='<c:url value="/secu/user"/>'>user</a>
<a href='<c:url value="/secu/admin"/>'>admin</a>

</body>
</html>