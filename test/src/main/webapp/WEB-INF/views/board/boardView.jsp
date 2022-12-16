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
		    
		    //registerAction();
			document.form.action="/board/boardUpdate.do";
			document.form.submit();
		});
		
		$("#btnDelete").click(function(){
			confirm("게시글을 삭제하시겠습니까?");
			
			var boardNum = $("#boardNum").val();
			console.log("boardNum : ",boardNum);
			
			location.href = "/board/boardDelete.do?boardNum=" + boardNum
			
		});
		
		
		//파일삭제
		$("a[name='file-delete']").on("click", function(e) {
	        e.preventDefault();
	        deleteFile($(this));
	    });
		
		
		
		
		/* 
			$("#viewImgDelete").on("click",function(e){ <<< 이걸로하면 하나밖에 안지워짐... 
		*/
/* 		$("img[id='viewImgDelete']").on("click", function(e) {
			console.log("viewImgDelete");
			e.preventDefault();
	        deleteFile($(this));
		}) */
		
	});
	
	
	//파일 부분 삭제 함수
	/* function fileDelete(fileNum){
		console.log("fileDelete fileNum ::: ",fileNum);
	    var num = fileNum.replace(/[^0-9]/g, "");
	    content_files[num].is_delete = true;
		$('#' + fileNum).remove();
		fileCount --;
	    console.log("fileDelete content_files ::: ",content_files);
	} */
	
	
	function addFile() {
        var str = "<div class='file-input'><input type='file' name='file'><a href='#this' name='file-delete'>삭제</a></div>";
        $("#file-list").append(str);
        $("a[name='file-delete']").on("click", function(e) {
            e.preventDefault();
            deleteFile($(this));
        });
    }
 
    function deleteFile(obj) {
        obj.parent().remove();
    }
	
	var fileCount = 0;
	// 해당 숫자를 수정하여 전체 업로드 갯수를 정한다.
	var totalCount = 10;
	// 파일 고유넘버
	var fileNum = 0;
	// 첨부파일 배열
	var content_files = new Array();
	console.log("array content_files ::: ",content_files);
	
	//ajax submit
	function registerAction(){
		
		console.log("content_files >>>> : ",content_files);
		var form = $("form")[0];
		console.log("form ::: ",form);
		
		//이미지를 ajax로 업로드할 때 FormData가 필요!!!
	 	var formData = new FormData(form);
			for (var i=0;  i<content_files.length; i++) {
				// 삭제 안한것만 담아 준다.
				console.log("1111 ::: ",content_files[i]);
				if(!content_files[i].is_delete){
					//컨트롤러에서 article_file 파라미터값으로 받는다!
					 formData.append("article_file", content_files[i]);
				}
			}
	   /*
	   * 파일업로드 multiple ajax처리
	   */
	  
	   //console.log("formData ::: ",formData);
	  
	   //for (var pair of formData.entries()) {
       //   console.log("formData ::: ",pair[0]+ ', ' + pair[1]);
       //}
	  
	  
	   //dataType: 'json' 지정해주지 않으면 json 데이터 인식하지 못해서 데이터 못보냄
	   //컨트롤러에서 받는 데이터타입이 String 이기 때문에 dataType = text
		$.ajax({
	   	      type: "POST",
	   	   	  enctype: "multipart/form-data",
			  dataType: 'text',
	   	      url: "/board/boardUpdate.do",
	       	  data : formData,
	       	  processData: false,
	   	      contentType: false,
	   	      success: function (data) {
	   	    	console.log("data ::: ",data);
				if(data == "success"){
					location.href = "/board/boardList.do";
				}
	   	      },
	   	      error: function (xhr, status, error) {
				console.log("status ::: ",status);	   	    	
				console.log("error ::: ",error);
				alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
   	     		return false;
	   	      }
	   	    });
	   	    return false;
		}
	 
</script>
</head>
<body>
	<h1>게시글 상세보기</h1>
	<!-- enctype="multipart/form-data" 있으면 POST 전송 null -->
	<!-- <form name="form" action="/board/update.do" method="post" enctype="multipart/form-data"> -->
	<!-- 파일을 보내야하기 때문에 반드시 form 태그에 enctype="multipart/form-data"  -->
	<form name="form" action='${actionType}' method="post" enctype="multipart/form-data">
		게시글번호 : <input name="boardNum" id="boardNum" readonly value="${boardView.boardNum}">
		
	    <div>
	       	작성자 : <input name="boardWriter" id="boardWriter" readonly value="${boardView.boardWriter}" placeholder="이름을 입력해주세요">
	    </div>
	    <div>
	       	제목 : <input name="boardTitle" id="boardTitle" value="${boardView.boardTitle}">
	    </div>
	    <div>
	    	내용
	    	<textarea name="boardContent" id="boardContent" rows="4" cols="80">${boardView.boardContent}</textarea>
	    </div>
		<div class="form-group" id="file-list">
	        <a href="#this" onclick="addFile()">파일추가</a>
	        <div class="file-group">
	            <input type="file" name="file"><a href='#this' name='file-delete'>삭제</a>
	        </div>
    	</div>    
	    <div>
		    <ul>
			    <c:forEach items="${fileView}" var="fileView">
	                <div class="file-info">
	                	<li>
							<a href='<c:url value="/board/fileDownload.do?FILE_NUM=${fileView.FILE_NUM }"/>'> ${fileView.FILE_ORG_NAME }</a>
		                    <span style="margin-left:5px;">${fileView.FILE_SIZE}kb</span>
		                    <input type="hidden" name="FILE_NUM=${fileView.FILE_NUM}" value="true">
		                    <input type="hidden" name=fileNum value='${fileView.FILE_NUM}'>
       						<a href='#this' name='file-delete'>삭제</a>
	                	</li>
	                </div>
	        	</c:forEach>
        	</ul>
       	</div>
			<%-- <c:choose>  
				<c:when test="${fileView.FILE_NUM == null}">
					이미지가 없습니다.
				</c:when>
				<c:otherwise>
					<img width="500px"src='/resources/upload/${fileView.FILE_STORED_NAME}' />
				</c:otherwise>
			</c:choose> --%>
			
	    <%-- <div>
	        작성일자 : <input name="boardRegdate" id="boardRegdate" value="${boardView.boardRegdate}">
	    </div> --%>
	    
		<!-- <button type="button" id="btnUpdete">수정</button> -->
	<!-- 	<div>
			<button type="submit" id="btnUpdete">수정(submit)</button>
			<button type="button" id="btnUpdete">수정</button>
			<button type="button" id="btnDelete">삭제</button>
	    </div> 
	    -->
	    	
    	<c:if test="${sessionScope.memberInfo.memberId == boardView.boardWriter}">
			<button type="submit">submit수정</button>
			<button type="button" id="btnUpdete">수정</button>
			<button type="button" id="btnDelete">삭제</button>
		    </div>
	    </c:if>

	
</body>
</html>