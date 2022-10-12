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
      <div>
          <h1>GUEST PAGE</h1><br>
          <h1>누구나 접근가능!!!!</h1>
      </div>
      <br><br>
      <div>
          <h6>GUEST 페이지입니다.<br>감사합니다. :)</h6>
      </div>
      <br><br>
      <div>
        <a href='<c:url value="/"/>'>home</a>
      </div>
</body>
</html>