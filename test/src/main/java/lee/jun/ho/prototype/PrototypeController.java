package lee.jun.ho.prototype;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PrototypeController {

	@GetMapping("prototype.do")
	public String prototype() {
		log.info("prototype 들어옴!!!");
		return "prototype/prototypeTest";
	}
	
	@PostMapping("viewerUrl.do")
//	@RequestMapping("viewerUrl.do")
	public String viewerUrl(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
			log.info("paramMap : " + paramMap);

			String file = (String) paramMap.get("fileId") + ".html";
			
			String path = (String) paramMap.get("uploadPath");
			
			String filePath = "/leejunho" + path + "/file/" + file; 
			log.info("filePath :" + filePath);
			
			model.addAttribute("result", filePath);
			
			//에이작스 성공 통신 받는곳에 
			
			return "prototype/view";
			
		}
	}
	
