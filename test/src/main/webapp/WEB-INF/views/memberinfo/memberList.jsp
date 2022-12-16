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

	$("#btnMainPage").click(function(){
		location.href = ""
	});

</script>
</head>
<body>

	<div>
		<a href="/main.do">
			<h1>MAIN</h1>
		</a>
	</div>
   
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
            <td>
            	${memberList.memberEmail}
			</td>
            <td>
            	<fmt:formatDate value="${memberList.memberRegdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>