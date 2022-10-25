<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
    setBtn();
    
    $("li[tree_lvl='2'] > [type=button].plus").click();
    
    //lawDetailPopCall();
    
    $("#printId").click(function(){
    	
    	let printArea = $("#divId").clone()    // 프린트 할 특정 영역 복사
    	console.log("printArea : ",printArea);
    	
    	let innerHtml = printArea[0].innerHTML
    	console.log("innerHtml : ",innerHtml);
    	
        let popupWindow = window.open("", "_blank", "width=700,height=800")
        console.log("popupWindow : ",popupWindow);
        
        popupWindow.document.write("<!DOCTYPE html>"+
                "<html>"+
                  "<head>"+
                  "</head>"+
                  "<body>"+innerHtml+"</body>"+
                "</html>");
                
                popupWindow.document.close();
                popupWindow.focus();
                
                /** 1초 지연 */
                setTimeout(() => {
                	popupWindow.print()         // 팝업의 프린트 도구 시작
                	popupWindow.close()         // 프린트 도구 닫혔을 경우 팝업 닫기
                }, 100)
    	
    });
    
	function beforePrint() {
		 console.log("beforePrint 들어옴!!!");
		 
	     initBodyHtml = document.body.innerHTML;
	     console.log("initBodyHtml : ",initBodyHtml);
	     
	     document.body.innerHTML = document.getElementById('divId').innerHTML;
	}
	 
	function afterPrint() {
		console.log("afterPrint 들어옴!!!");
	    document.body.innerHTML = initBodyHtml;
	}
	 
	function printBtn() {
		window.print();
	}
	 
	 window.onbeforeprint = beforePrint;
	 window.onafterprint = afterPrint;
    
    
    function setBtn(){
    	
    	var test = $('#divTest');
        //var treePlus = '\<button type=\"button\" class=\"treeToggle plus\"\>+\<\/button\>';
        var treePlus = '<button type="button" id="submit" value="Submit" class="treeToggle plus">+</button>';
        var treeMinus = '\<button type=\"button\" class=\"treeToggle minus\"\>-\<\/button\>';
        
        /* $("#liTest").append(treePlus); */
        $("div > ul > li").append(treePlus);
        
        $('#divTest .treeToggle').click(function(){
            t = $(this);
            console.log(" t : ",this);
            
            /* console.log(t.parent('li').toggleClass('tree_open'));  이거 들어가면  li부모에 class 못 넣음...*/
            //toggleClass() , hasClass() , removeClass() , addClass()
            t.parent('li').toggleClass('tree_open');
            if(t.parent('li').hasClass('tree_open')){
            	console.log("1");
            	t.text('-').removeClass('plus').addClass('minus');
            }else{
            	console.log("2");
            	t.text('+').removeClass('minus').addClass('plus');
            }
        });
        
        
        
    }
    
});

</script>
<body>
	<h3>testPage.jsp</h3>
	<div id="divTest">
        <ul>
            <li tree_lvl='2'>
            </li>
        </ul>
    </div>
    
    <div id="divId">
        <h3>여기만 프린트...</h3>
    </div>
    
    <input type="button" value="인쇄하기" id="print" onclick="window.print()"/>
    
    <button type="button" id="printId">인쇄버튼</button>

</body>
</html>