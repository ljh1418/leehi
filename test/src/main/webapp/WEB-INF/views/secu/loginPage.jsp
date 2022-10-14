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

<h1>loginPage</h1>
	
	<form action="/login" method="post">
		
		<input type="text" name="loginId" placeholder="example">
		<input type="password" name="loginPwd" placeholder="password">
		
		
		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
			<font color="red">
				<p>Your login attempt was not successful due to <br/>
					${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
				<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
			</font>
		</c:if>
		
		<input name="${_csrf.paraeterName}"	 type="hidden" value="${_csrf.token}"/>
		<button type="submit">login</button>
		
		<c:if test="${not empty ERRORMSG}">
					<font color="red">
				  		<p>Your login attempt was not successful due to <br/>
				  		${ERRORMSG }</p>
					</font>
		</c:if>
		
	</form>
	
	

</body>
</html>

