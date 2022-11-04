<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='resources/chart/chart.js'></script>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>

	<h1>chart.jsp</h1>
	
    <h3>bar</h3>
    <div style="width: 900px; height: 900px;">
        <canvas id="bar-chart"></canvas>
    </div>
    
    <h3>pie</h3>
    <div style="width: 900px; height: 900px;">
        <canvas id="pie-chart"></canvas>
    </div>

</body>
<script>

	/**************************************************************************************
	 * api 참고site ->  https://www.chartjs.org/docs/latest/samples/bar/border-radius.html
	 **************************************************************************************/
	var chart = new Chart($("#bar-chart"), {
	    //type: 'bar',
	    data: {
	      labels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],      
	      datasets: [
	        {
	            type: 'bar',
	          label: "관리표준",
	          backgroundColor: ["#3e95cd"],          
	          data: [247,526,74,78,43,33,40,100,200,300,200,50]
	
	        }
	        ,{
	            type: 'bar',
	           label: "기술표준",
	           backgroundColor: ["#c45850"],
	           data: [100,200,300,400,500,400,300,250,350,400,100,150],
	        }
	        
	      ],
	      
	    },
	    options: {
	          onClick : function(event, point){
	              
	              if(point.length > "0"){
	                  
	                  console.log("event", event);
	                  console.log("point", point);
	                  
	                  //추후에 에이작스로
	                  if(point[0].datasetIndex == '0'){
	                	  
	                $.ajax({
	                	url : 'management.do',
	                	type : 'post',
	                	dataType : 'json',
	                	success : function(data){
	                		console.log('에이작스 통신 성공!!!');
	                	}
	                })
	                    window.open("http://localhost:8080/management.do");
	                  }else{
	                    window.open("http://localhost:8080/technology.do");
	                  }
	              }
	          }
	    }
	});
	  
	 /* pie-example */
	new Chart(document.getElementById("pie-chart"), {
	    type: 'pie',
	    data: {
	      labels: ["규정", "세칙", "작업표준", "QC공정도", "규격",'검사표준','시험표준'],
	      datasets: [
	        {
	          label: "Population (millions)",
	          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850","#FFE400","#A6A6A6"],
	          data: [248,567,734,784,433,112,119]
	        }
	      ]
	    },
	    options: {
	        plugins : {
	            legend : {
	                position : 'right'
	            }
	        , title : {
	                display: true,
	                text: 'test tile'
	            }
	        }
	    }
	});
 
</script>


</html>