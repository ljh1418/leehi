<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="UTF-8"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
// 		$("#boardInsert").on("click",function(){
// 			$.ajax({
// 				url : 'boardInsertPage.do',
// 				type : 'post',
// 			   	success : function(data){
// 	           		console.log("data : ",data);
// 	           		console.log('에이작스 통신 성공!!!');
// 	           		location.href="/board/test.do";
// 	           	},
// 	           	error : function(request,status,error){
// 	           		console.log("실패...");
// 	           		console.log("error : ",error)
// 	           	}
// 			})
// 		});
		
		$("#boardInsert").on("click",function(){
			console.log("글작성 버튼 누름!!!");
			location.href="/board/boardWriterPage.do";
		})
			
		
	});
	
	
</script>


</head>
<body>
	<h1>게시판 목록</h1>
	<button type="button" id="boardInsert">글작성</button>
	
	<table border="1" width="600px">
	    <tr>
	        <th>번호</th>
	        <th>제목</th>
	        <th>이름</th>
	        <th>작성일</th>
	        <th>조회수</th>
	    </tr>
	    <c:forEach var="boardList" items="${boardList}">
		    <tr>
		        <td>${boardList.boardNum}</td>
		        <td>
		        	<a href="/board/boardWrite.do?boardNum=${boardList.boardNum}">${boardList.boardTitle}</a>
		        </td>
		        <td>${boardList.boardWriter}</td>
		        <td>
		            <!-- 원하는 날짜형식으로 출력하기 위해 fmt태그 사용 -->
		            <fmt:formatDate value="${boardList.boardRegdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
		        </td>
		        <td>${boardList.boardViewcnt}</td>
		    </tr>    
	    </c:forEach>
	</table>
	
</body>
</html>