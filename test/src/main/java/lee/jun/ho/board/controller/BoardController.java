package lee.jun.ho.board.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.gensol.docuextracter.sdt.api.ExtractApi;
import kr.co.gensol.docuextracter.sdt.api.vo.XInputExtractVo;
import kr.co.gensol.docuextracter.sdt.api.vo.XOutputExtractVo;
import kr.co.gensol.docuextracter.sdt.api.vo.XTocInfoVo;
import lee.jun.ho.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("insert.do")
	public String txInsert() throws Exception{
		
		try{
			log.info("트랜잭션 테스트 @@@@@@@@@@@@@@@@@@@@@@");
			boardService.txInsert();
		}catch(Exception e) {
			e.printStackTrace();
  		}
		
		
		return "home";
		
	}
	
	@GetMapping("test.do")
	public void test() throws Exception{
		
		try{
			ExtractApi				api					= new ExtractApi();
			
			XInputExtractVo			inputVo				= new XInputExtractVo();
			System.out.println("XInputExtractVo inputVo >>> : " + inputVo);
			
			//로그유형 셋팅 (true:작업로그 출력, false:작업로그 출력 안함)(default=false)
			inputVo.setDebugMode(true);
			
			
			//작업TEMP 및 파일반환 경로 셋팅
			inputVo.setTempDirPath("/data/docuextract/temp/");
			
			//목차추출 및 HTML가공 대상 경로 셋팅
			inputVo.setHtmlDirPath("/data/docuextract/sample/20220727/synap/20220727.docx.files/");
			
			System.out.println("inputVo >>> : " + inputVo.toString());
			
			//목차추출 및 HTML가공 요청
			XOutputExtractVo		outputVo			= api.getTocList(inputVo);
			System.out.println("outputVo >>> : " + outputVo);
			
			//요청결과 확인
			if(outputVo.getResultCode()==0){
				//목차 결과 확인
				ArrayList<XTocInfoVo>	tocList				= outputVo.getTocInfoList();
				
				for(int i=0 ; i<tocList.size() ; i++){
					
					XTocInfoVo				tocInfo				= tocList.get(i);
					
					System.out.println("TOC LEVEL=======["+tocInfo.getTocLevel()+"]");
					System.out.println("TOC PATTERN=====["+tocInfo.getTocPattern()+"]");
					System.out.println("TOC ITEM CD=====["+tocInfo.getTocItemCd()+"]");
					System.out.println("TOC SNO=========["+tocInfo.getTocSNO()+"]");
					System.out.println("TOC UPPER PATH==["+tocInfo.getTocUpperPath()+"]");
					System.out.println("TOC ANO=========["+tocInfo.getTocANO()+"]");
					System.out.println("TOC NO==========["+tocInfo.getTocNoStr()+"]");
					System.out.println("TOC TITLE=======["+tocInfo.getTocTitle()+"]\n");
					
				}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	
	
	
}
