<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src='resources/js/prototype.js'></script>
<%-- <script type="text/javascript" src="<c:url value='resources/js/prototype.js'/>"></script> --%>
<!-- 주의할점 : 제이쿼리 CDN이 js 경로 호출하는 스크립트 보다 더 위에 있어야함!!!! -->
<script type="text/javascript">
	
	
	$(document).ready(function(){
		
		
		$("#idClick").on("click",function(){
			//$.LEE_PROTOTYPE.test("jsp에서 온값....");
			$.LEE_PROTOTYPE.call("ifrId",{fileId : "id0001", uploadPath : "/test"});
		});
		
		btnOnClick();
		
	});
	
	function btnOnClick(){
		console.log("btnOnClick 들어옴...");
	}
	
</script>
</head>
<body>
<h3>prototype page!!!</h3>

<button type="button" id="idClick">id버튼</button>
<button type="button" onclick="btnOnClick()">onclick버튼</button>

<div id="ifrId" style="height: 700px;">
</div>

</body>
</html>