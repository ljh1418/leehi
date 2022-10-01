<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"      uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt"     uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script src='resources/chart/chart.js'></script>
<script src='resources/chart/chartjs-plugin-datalabels.js'></script>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	Chart.register(ChartDataLabels);
	Chart.defaults.set('plugins.datalabels', { color: 'white' });
</script>
 -->
 




<!-- 둘다가능 -->
<%-- <%@include file="../sample/test.jsp" %> --%>
<c:import url="/jsp/test.jsp" charEncoding="UTF-8" />


<!-- <canvas id="myChart" width="400" height="400"></canvas> -->
<!-- <script>
//해당 부분은 JS파일을 따로 만들어서 사용해도 된다.
//차트를 그럴 영역을 dom요소로 가져온다.
var chartArea = document.getElementById('myChart').getContext('2d');
//차트를 생성한다. 
var myChart = new Chart(chartArea, {
 // ①차트의 종류(String)
 //type: 'bar',
 type: 'doughnut',
 // ②차트의 데이터(Object)
 data: {
     // ③x축에 들어갈 이름들(Array)
     labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
     // ④실제 차트에 표시할 데이터들(Array), dataset객체들을 담고 있다.
     datasets: [{
         // ⑤dataset의 이름(String)
         label: '# of Votes',
         // ⑥dataset값(Array)
         data: [12, 19, 3, 5, 2, 3],
         // ⑦dataset의 배경색(rgba값을 String으로 표현)
         backgroundColor: 'rgba(255, 99, 132, 0.2)',
         // ⑧dataset의 선 색(rgba값을 String으로 표현)
         borderColor: 'rgba(255, 99, 132, 1)',
         // ⑨dataset의 선 두께(Number)
         borderWidth: 1
     }]
 },
 // ⑩차트의 설정(Object)
 options: {
     // ⑪축에 관한 설정(Object)
     scales: {
         // ⑫y축에 대한 설정(Object)
         y: {
             // ⑬시작을 0부터 하게끔 설정(최소값이 0보다 크더라도)(boolean)
             beginAtZero: true
         }
     }
 }
});
</script> -->



</head>
<body>

<h1>chart.jsp</h1>

		
		<!-- <div style="width: 900px; height: 900px;">
			<canvas id="bar-chart"></canvas>
		</div> -->
		
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


/* new Chart(document.getElementById("bar-chart"), {
    type: 'line',
    
    data: {
      labels: [1500,1600,1700,1750,1800,1850,1900,1950,1999,2050],
      datasets: [{ 
          data: [86,114,106,106,107,111,133,221,783,2478],
          borderColor: "#3e95cd",
          fill: false
        }, { 
          data: [282,350,411,502,635,809,947,1402,3700,5267],
          borderColor: "#8e5ea2",
          fill: false
        }, { 
          data: [168,170,178,190,203,276,408,547,675,734],
          borderColor: "#3cba9f",
          fill: false
        }, { 
          data: [40,20,10,16,24,38,74,167,508,784],
          borderColor: "#e8c3b9",
          fill: false
        }, { 
          data: [6,3,2,2,7,26,82,172,312,433],
          borderColor: "#c45850",
          fill: false
        }
      ]
    },
    options: {
    	plugins : {
    		title :{
    			display : false,
    			text : 'test title'
    		},
    		legend : {
    			display : false
    		}
    	}
      
    }
  }); */



/* bar example */
/* new Chart(document.getElementById("bar-chart"), { */
// 	var chart = new Chart($("#bar-chart"), {
//     type: 'bar',
//     data: {
//       labels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],      
//       datasets: [
//         {
//           label: "관리표준",
//           backgroundColor: ["#3e95cd"],          
//           data: [247,526,74,78,43,33,40,100,200,300,200,50]

//         }
// 		,{
//            label: "기술표준",
//            backgroundColor: ["#c45850"],
//            data: [100,200,300,400,500,400,300,250,350,400,100,150],
// 		}
        
//       ],
//     },
//     options: {
//     	  onClick : function(event, point){
//     		  console.log("event", event);
//     		  console.log("point", point);
//     	  }
//     }
// });

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
        		  
        		  if(point[0].datasetIndex == '0'){
					window.open("http://localhost:8080/management");
        		  }else{
        		  	window.open("http://localhost:8080/technology");
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