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
	$(document).ready(function(){
		
		$("#btnUpdete").click(function(){
			alert("게시글을 수정 하시겠습니까?");
			
			var title = $("#boardTitle").val();
			console.log('title : ',title);
			
		    var content = $("#boardContent").val();
		    console.log('content : ',content);
		    
		    
			document.form.action="/board/boardUpdate.do";
			document.form.submit();
					
		});
		
	});
</script>
</head>
<body>
	<h1>게시글 상세보기</h1>
	
	<form name="form" action="${actionType}" method="POST" enctype="multipart/form-data">
		
		게시글번호 : <input name="boardNum" id="boardNum" readonly value="${boardView.boardNum}">
    <div>
       	작성자 : <input name="boardWriter" id="boardWriter" readonly value="${boardView.boardWriter}" placeholder="이름을 입력해주세요">
    </div>
    <div>
       	제목 : <input name="boardTitle" id="boardTitle" value="${boardView.boardTitle}">
    </div>
    <div>
    	내용
    	<textarea name="boardContent" rows="4" cols="80">${boardView.boardContent}</textarea>
    </div>
    <div>        
        작성일자 : ${boardView.boardRegdate}
    </div>
    <div>
        조회수 : ${boardView.boardViewcnt}
    </div>
	<button type="button" id="btnUpdete">수정</button>
	<button type="button" id="btnDelete">삭제</button>
    
<%--     
	<div>x	
        제목
        <c:choose>
	        <c:when test="${sessionScope.userInfo.userId == boardView.boardWriter}">
	        	<input name="boardTitle" id="boardTitle"  size="80" value="${boardView.boardTitle}" placeholder="제목을 입력해주세요">
	        </c:when>
	        <c:otherwise>
	        	<input name="boardTitle" id="boardTitle"  size="80" readonly value="${boardView.boardTitle}" placeholder="제목을 입력해주세요">
	        </c:otherwise>
        </c:choose>
    </div>
    <div>
        내용
        <c:choose>
	        <c:when test="${sessionScope.userInfo.userId == boardView.boardWriter}">
	        	<textarea name="boardContent" id="boardContent" rows="4" cols="80" placeholder="내용을 입력해주세요">${boardView.boardContent}</textarea>
	        </c:when>
	        <c:otherwise>
	        	<textarea name="boardContent" id="boardContent"  readonly rows="4" cols="80" placeholder="내용을 입력해주세요">${boardView.boardContent}</textarea>	        
        	</c:otherwise>
        </c:choose>
        
    </div>
 --%>
				
				

		
	</form>
	
</body>
</html>