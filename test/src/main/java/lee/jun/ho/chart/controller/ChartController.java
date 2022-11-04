package lee.jun.ho.chart.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lee.jun.ho.chart.vo.ChartVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChartController {
	
	@GetMapping("chartTestMap.do")
	//@RequestParam 붙여야지 url 파라미터값 가져올수있음
	public String chartTestMap(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, ModelMap model) throws Exception {
		log.info("paramMap : " + paramMap);
		return "chart/chartTest";
	}
	
	@GetMapping("chartTestVo.do")
	public String chartTestVo(ChartVo chartVo, HttpServletRequest request, ModelMap model) throws Exception {
		log.info("chartVo : " + chartVo);
		
		return "chart/chartTest";
	}
	
	@GetMapping("management.do")
	public String management() {
		return "chart/management";
	}
		
	@GetMapping("technology.do")
	public String technology() {
		return "chart/technology";
	}
	
	
	
}


