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
   <h2>회원 목록</h2>
    <table border="1" width="700px">
        <tr>
        	<th>회원번호</th>
            <th>아이디</th>
            <th>이름</th>
            <th>이메일</th>
            <th>회원가입일자</th>
        </tr>
        <c:forEach var="memberList" items="${memberList}">
        <tr>
            <td>
            
            	${memberList.memberNum}
            </td>
            <td>
            	<a href="/memberinfo/memberWriter.do?memberNum=${memberList.memberNum}">${memberList.memberId}</a>
            </td>
            <td>
            	${memberList.memberName}
            </td>
            <td>${memberList.memberName}</td>
            <td>${memberList.memberEmail}</td>
            <td>${memberList.memberRegdate}</td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>