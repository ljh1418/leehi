<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>게시글작성</h3>
    <form action="/board/boardWriter.do" method="post">
	        <!-- <input type="text" name="boardNum" value="0"> -->
	    <div>
			<label>제목</label>
	        <%-- <input type="text" name="boardTitle" placeholder="제목을 작성해주세요."> --%>
	        <input type="text" name="boardTitle" placeholder="제목을 작성해주세요.">
		</div>
	    <div>
	        <label>작성자</label>
	        <%-- <input type="text" name="boardWriter" placeholder="이름을 적어주세요."> --%>
	        <input type="text" name="boardWriter" placeholder="이름을 적어주세요.">
		</div>
		<div>
	        <label>내용</label>
	        <textarea name="boardContent" rows="10"></textarea>
		</div>
	    <button type="submit">등록하기</button>
	    <button type="button">목록으로</button>
    </form>
	
</body>
</html>