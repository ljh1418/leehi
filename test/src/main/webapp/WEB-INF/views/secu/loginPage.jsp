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
		
		<input type="text" name="loginId" placeholder="example" value="member">
		<input type="password" name="loginPwd" placeholder="password" value="member">
		
		
		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
			<font color="red">
				<p>Your login attempt was not successful due to <br/>
					${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
				<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
			</font>
		</c:if>
		
		
		<!-- not empty = 객체 값이 비어있지 않을 경우 -->
		<!--  security-context 에서 설정한 value 값으로 jstl 표현 -->
		<c:if test="${not empty ERRORMSG}">
					<font color="red">
				  		<p>Your login attempt was not successful due to <br/>
				  		${ERRORMSG }</p>
					</font>
		</c:if>
		
		<h3>username : ${username }</h3>
		<h3>loginId : ${loginId }</h3>
		
		
		
		<input name="${_csrf.paraeterName}"	 type="hidden" value="${_csrf.token}"/>
		<button type="submit">login</button>
		
		<!-- name을 _spring_security_remember_me 지정하지 않으면 SPRING_SECURITY_REMEMBER_ME_COOKIE 생성못함 -->
		<input type="checkbox"  id = "remember_me" name ="_spring_security_remember_me">로그인 유지<br>
		
		
	</form>
	
	

</body>
</html>

