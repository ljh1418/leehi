package lee.jun.ho.ajax;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AjaxController {
	
	//@ResponseBody는 뷰 페이지를 응답하지 않고 return 값을 그대로 반환하겠다
	
	
	
	
	
	@PostMapping("/ajaxTest.do")
	public @ResponseBody Object ajaxTest() {
		log.info("ajaxTest 들어옴!!!");
		return "ajax/ajaxTest";
	}


	
	//List = interface
	//ArrayList = class
	@ResponseBody
	@PostMapping("/ajaxTestList.do")
	public String test(HttpServletRequest request, TestBody test) throws Exception{
		
		log.info("test >>> : " + test);
		test.getItems().forEach(d ->{
			log.info("name : " + d.getName());
			log.info("name : " + d.getAge());
		});
		
		
		return "bye";
	}
//	public Map<String,Object> ajaxTestList(@RequestParam String data){
//		log.info("ajaxTestList 들어옴 " + data);
//		Map<String,Object> result = new HashMap<>();
//		result.put("success", 1);
//		return result;
//	}
	
	
	//단일객체데이터
	//@ResponseBody 가 없으면 에이작스 통신 자체가 불가능
	@ResponseBody
	@PostMapping("/ajaxTestList2.do")
	public String ajaxTestList2(String year, String month, String day){
		log.info("year + month + day : " + year + month + day);
		return year + month + day;
		
	}
	
}
