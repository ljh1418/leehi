<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		
		
		var url = "${result}";
		console.log("url : ",url);
		
		$("#docViewer").attr("src",url);
		
	});
	
	
	
</script>
</head>
<body>
<iframe name="docViewer" id="docViewer" width="100%" style="border:none;  height: 100%;"></iframe>
<h3>view페이지...</h3>

</body>
</html>