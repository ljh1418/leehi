<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<style>
		ul {
			list-style: none;
			width : 30%;
			display: inline-block;
		}
		
		li {
			float: left;
			margin-left : 5px;
		}
</style>
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
			location.href="/board/boardWrite.do";
		})
			
		
	});
	
	
</script>


</head>
<body>
	<div>
		<a href="/main.do">
			<h1>MAIN</h1>
		</a>
	</div>
	
	<h2>게시판 목록</h2>
	<button type="button" id="boardInsert">글작성</button>
	
	<table border="1" width="1000px">
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
	<%-- pageMaker : ${pageMaker} --%>
	<ul>
		<c:if test="${pageMaker.prev}">
			<li><a href="/board/boardList.do?page=${pageMaker.startPage - 1}">이전</a></li>
		</c:if> 
		 
		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
			<li><a href="/board/boardList.do?page=${idx}">${idx}</a></li>
		</c:forEach>
		   
		<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			<li><a href="/board/boardList.do?page=${pageMaker.endPage + 1}">다음</a></li>
		</c:if> 
	</ul>
	
</body>
</html>