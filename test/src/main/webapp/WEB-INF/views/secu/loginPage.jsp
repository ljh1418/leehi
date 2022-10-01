<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>loginPage</h1>

<form action="/login" method="post">
	<input type="text" name="loginId" placeholder="example">
	<input type="password" name="loginPwd" placeholder="password">
	<input name="${_csrf.paraeterName}"	 type="hidden" value="${_csrf.token}"/>
	<button type="submit">GO</button>
</form>


</body>
</html>


jsp