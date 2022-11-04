<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>

 <%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String name = "";
	if(auth.getPrincipal() != null) {
		name = auth.getName();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script src='resources/js/prototype.js'></script> -->
<script type="text/javascript" src="<c:url value='resources/js/prototype.js'/>"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<script type="text/javascript">

	var i = 0;
	//ajax 통신 json 으로 받기
	//JSON.stringify : json객체를 string객체로
	//JSON.parse : string객체를 json객체로
	
	var param = [{"fruit1":"apple","fruit2":"banana"}];
	console.log("param : ",param);
	
	var stringify = JSON.stringify(param);
	console.log("stringify : ",stringify);
	
	var obj = {
			"year" : "2022",
			"month" : "10",
			"day" : 24
	}
	console.log("obj : ",obj);
	
	var getMemberInfo = [
	   { memberNo: 1, name: "홍길동1", phone: "010-1111-1111", birth: "2020-01-01" },
	   { memberNo: 2, name: "홍길동2", phone: "010-2222-2222", birth: "2020-02-02" },
	   { memberNo: 3, name: "홍길동3", phone: "010-3333-3333", birth: "2020-03-03" },
	   { memberNo: 4, name: "홍길동4", phone: "010-4444-4444", birth: "2020-04-04" },
	   { memberNo: 5, name: "홍길동5", phone: "010-5555-5555", birth: "2020-05-05" },
	];
	
	var items = [
		{"name":"dog1" , "age": 2},
		{"name":"dog4" , "age": 4}
	];
	console.log("items : ",items);
	
	console.log("getMemberInfo : ",getMemberInfo);
	console.log("getMemberInfo JSON.stringify : ",JSON.stringify(getMemberInfo));
	 
	
	/* 
	var parse = JSON.parse(param);
	console.log("parse : ",parse); 
	*/
	
	
	$(document).ready(function(){
		
		//
		$("#ajaxBtn").on("click",function(){
			//배열객체
		    $.ajax({
            	url : 'ajaxTestList.do',
            	type : 'post',
            	data : JSON.stringify(getMemberInfo),
            	success : function(data){
            		console.log("data1 : ",data);
            		console.log('에이작스 통신 성공!!!');
            	},
            	error : function(request,status,error){
            		console.log("실패...");
            		console.log("error : ",error)
            	}
            })
			
		})
		
		//
		$("#ajaxBtn2").on("click",function(){
		//단일객체
	    $.ajax({
           	url : 'ajaxTestList2.do',
           	type : 'post',
           	data : obj,
           	success : function(data){
           		console.log("data2 : ",data);
           		console.log('에이작스 통신 성공!!!');
           		
           		addHtml(data);
           	},
           	error : function(request,status,error){
           		console.log("실패...");
           		console.log("error : ",error)
           	}
           })
		})
		
		//에이작스 차트페이지이동
// 		$("#chartId").on("click",function(){
// 		    $.ajax({
//             	url : 'chartTest.do',
//             	type : 'get',
//             	success : function(data){
//             		console.log("차트페이지이동 data : ",data);
//             		//var url = "/chartTest.do";
//             		//location.replace(url);
//             		//location.href="chartTest.do";
//             	},
//             	error : function(request,status,error){
//             		console.log("실패...");
//             		console.log("error : ",error)
//             	}
//             })
// 		})
		
	});
	
	function addHtml(data){
		
		var trHtml = "";
		trHtml += "<tr id='test'>"
		trHtml += "<td><button type='button' id='delBtn"+(++i)+"'>삭제</button></td>"
		//trHtml += "<td><span class=\"data_sort\">"+(++i)+"</span></td>";
		trHtml += "<td>"+data+"</td>"
		trHtml += "</tr>"
		
		$("#ajaxAppend").append(trHtml);
	}
	
	
</script>
<body>
<h1>home.jsp</h1>
<button type="button" onclick="location.href='testPage.do'">testPage</button>
<div style="border:1px solid ;">
	
		<sec:authorize access="isAnonymous()">
			<h5>
				<a href='<c:url value="/secu/loginPage"/>'>LOGIN</a> 로그인 해주세요.
			</h5>
	    </sec:authorize>
		
		<!-- isAuthenticated() 인증된 이용자 일때 로그아웃 버튼을 보여줌 -->
		<sec:authorize access="isAuthenticated()">
          	<h5><%=name %>님, 반갑습니다.</h5>
	        <form action="/logout" method="POST">
	                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	                <button type="submit">LOGOUT</button>
	        </form>
          </sec:authorize>
</div>


<div class="container text-center col-2">
	<a href='<c:url value="/page"/>' role="button">GUEST</a>
	<a href='<c:url value="/user/page"/>' role="button">USER</a>
	<a href='<c:url value="/member/page"/>' role="button">MEMBER</a>
	<a href='<c:url value="/admin/page"/>' role="button">ADMIN</a>
</div>


<h3>에이작스 버튼</h3>
<button type="button" id="ajaxBtn">ajax</button>
<button type="button" id="ajaxBtn2">ajax2</button>
<button type="button" id="chartIdMap" onClick="location.href='/chartTestMap.do?test1=leeMap&test2=junhoMap'">chartMap</button>
<button type="button" id="chartIdVo" onClick="location.href='/chartTestVo.do?test1=leeVo&test2=junhVo'">chartVo</button>
<!--  -->
<table id="ajaxTable">
	
</table>
<div id="ajaxAppend"><span id=sapnId>추가목록이 없습니다.</span></div>

</body>
</html>