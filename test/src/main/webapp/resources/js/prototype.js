function prototypePage(){
	console.log("prototype.js 페이지 들어왔습니다.")
}

var LEE_PROTOTYPE = (function(){
	console.log("LEE_PROTOTYPE 입니다.");
	
	var target = this;
    console.log("target : ",target);
    
	//
	function JUNHO(){
		
		console.log("JUNHO() 입니다.");
		this.viewerUrl = "viewerUrl.do";
		console.log("this.viewerUrl : ",this.viewerUrl);
	};
	
	JUNHO.prototype = {
		
		test : function(param){
			console.log(param,"JUNHO.prototype test 함수");
		},
		call : function (targetId, param){
			console.log("targetId : ",targetId);
			console.log("param : ",param);
			
			//변수에 한번 집어 넣어야지 extend 제대로 됨
			var oParam = {ID : targetId};
			console.log("oParam : ",oParam);			

			//extend 객체 합치기
			$.extend(oParam, param, {});
			console.log("extend oParam : ",oParam);
			
			//이상하게 합쳐짐
			//$.extend(targetId, param, {});
			//console.log("extend targetId : ",targetId);
			$.ajax({
					type : 'POST'
				,	url : $.LEE_PROTOTYPE.getUrl()
				,	data : oParam
				,	success : function(data,status,xhr){
					console.log("data : ",data);
					console.log("status : ",status);
					console.log("xhr : ",xhr);
						$("#"+targetId).html(data);
					}
				,	error : function(transport){
					alert("에러");
				}				
					
			});
		},
		
		getUrl : function(){
			return this.viewerUrl;
		}
	};
	
	//리턴이 있어야 prototype 안의 함수를 호출 했을때 값을 넘겨줌
	return JUNHO;

})();

//생성자가 없으면 jsp에서 호출할때 변수를 찾지못함
$.LEE_PROTOTYPE = new LEE_PROTOTYPE();