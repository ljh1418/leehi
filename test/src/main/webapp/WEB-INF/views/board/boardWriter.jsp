<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$("#btnInsert").click(function(){
		console.log("123");
		
		var boardTitle = $("#boardTitle").val();
		console.log('boardTitle : ',boardTitle);
		
		var boardContent = $("#boardContent").val();
		console.log('boardContent : ',boardContent);
		
		//null체크
		if(boardTitle == ""){
		    alert("제목을 입력해주세요.");
		    document.form.boardTitle.focus();
		    return false;
		}
		if(boardContent == ""){
		    alert("내용을 입력해주세요.");
		    document.form.boardContent.focus();
		    return false;
		}
		
		$("a[name='file-delete']").on("click", function(e) {
            e.preventDefault();
            deleteFile($(this));
        });
		
		
		document.form.action="/board/boardInsert.do";
		document.form.submit();
		
	})
});

function addFile() {
    var str = "<div class='file-group'><input type='file' name='file'><a href='#this' name='file-delete'>삭제</a></div>";
    $("#file-list").append(str);
    $("a[name='file-delete']").on("click", function(e) {
        e.preventDefault();
        deleteFile($(this));
    });
}

function deleteFile(obj) {
    obj.parent().remove();
}

</script>
</head>
<body>
	<h3>게시글작성</h3>
    <form name="form" action="/board/boardInsert.do" method="post" enctype="multipart/form-data">
	    <div>
	        제목 : <input type="text" name="boardTitle" id="boardTitle" placeholder="제목을 작성해주세요.">
		</div>
	    <div>
	    	작성자 : <input name="boardWriter" id="boardWriter" size="80" value="${sessionScope.memberInfo.memberId}" readonly>
		</div>
		<div>
	        내용 : <textarea name="boardContent" id="boardContent" rows="4" cols="80"></textarea>
		</div>
		
		<!-- 파일첨부 -->
	<div class="form-group" id="file-list">
        <a href="#this" onclick="addFile()">파일추가</a>
        <div class="file-group">
            <input type="file" name="file"><a href='#this' name='file-delete'>삭제</a>
        </div>
    </div>
		
	    <button type="button" id="btnInsert">등록하기</button>
	    <button type="submit" id="btnInsert">submit등록하기</button>
	    <button type="button">목록으로</button>
    </form>
</body>
</html>