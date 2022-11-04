<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	
	
	//
	$(document).ready(function(){
		
		var url = "${result}";
		console.log("url11111111111111111111 : ",url);
		
		//파일 넣어주기 서버기동될때 어디로 기동되는지 확인하기
		//C:\DEV\eclipse-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\webapps\leejunho\test\file
		$("#docViewer").attr("src",url);
		
	});
	
</script>
</head>
<body>
<iframe name="docViewer" id="docViewer" width="100%" style="border:none;  height: 100%;"></iframe>
</body>
</html>