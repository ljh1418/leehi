package lee.jun.ho.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.gensol.docuextracter.sdt.api.ExtractApi;
import kr.co.gensol.docuextracter.sdt.api.vo.XInputExtractVo;
import kr.co.gensol.docuextracter.sdt.api.vo.XOutputExtractVo;
import kr.co.gensol.docuextracter.sdt.api.vo.XTocInfoVo;
import lee.jun.ho.board.service.BoardService;
import lee.jun.ho.board.vo.BoardVo;
import lee.jun.ho.file.service.FileService;
import lee.jun.ho.member.vo.MemberVo;
import lee.jun.ho.utils.Criteria;
import lee.jun.ho.utils.PageMaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	FileService fileService;	
	
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
	
	//게시판 리스트
	@GetMapping("/board/boardList.do")
	public ModelAndView boardList(Criteria cri) throws Exception{
		log.info("boardList cri ::: " + cri);
		ModelAndView mav = new ModelAndView();
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardService.listCount());
		
		List<BoardVo> boardList = boardService.boardList(cri);
		
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("boardList", boardList);
		mav.setViewName("/board/boardList");
		
		return mav;
	}
	
	//게시판 상세보기 페이지 or 게시글 작성 페이지 이동
	@GetMapping("/board/boardWrite.do")
	public ModelAndView boardWrite(MemberVo memberVo, BoardVo boardVo, HttpSession session) throws Exception{
		log.info("boardWrite 들어옴 >>> : " + boardVo);
		ModelAndView mav = new ModelAndView();
		
		
		MemberVo memberInfo = (MemberVo) session.getAttribute("memberInfo");
		log.info("boardWrite memberInfo ::: " + memberInfo);
		
		String boardNum  = boardVo.getBoardNum();
		log.info("boardNum ::: " + boardNum);
		
			if(boardVo.getBoardNum() != null) {
				log.info("게시글 수정!!!");
				//조회수
				boardService.boardViewHit(boardNum,session);
				
				//단일 파일 일때
				//Map<String, Object> fileData = boardService.fileView(boardNum);
				//log.info("fileData ::: " + fileData);
				
				List<Map<String, Object>> fileData = boardService.fileData(boardNum);
				
				//뷰에 전달할 게시글 데이터
		        mav.addObject("boardView", boardService.boardView(boardVo));
		        //뷰에 전달한 파일 데이터
		        mav.addObject("fileView", fileData);
				mav.addObject("actionType","/board/boardUpdate.do");
				mav.setViewName("/board/boardView");
			}else {
				if(memberInfo != null) {
					log.info("신규 글작성!!!");
					mav.addObject("actionType","/board/boardInsert.do");
					mav.setViewName("/board/boardWriter");	
				}else {
					mav.setViewName("login/loginPage");
		        	mav.addObject("message","writeLogin");
				}
			}
		return mav;
	}
	
	//게시글 작성 로직 20221215
	@PostMapping("/board/boardInsert.do")
	public ModelAndView boardInsert(BoardVo boardVo, ModelAndView mav,MultipartFile[] file) throws Exception{
		log.info("boardInsert boardVo ::: " + boardVo);
		log.info("boardInsert file ::: " + file);
		log.info("boardInsert file length ::: " + file.length);
		
		for(int i=0; i<file.length; i++) {
			log.info("================== file start ==================");
			log.info("파일 이름: "+file[i].getName());
			log.info("파일 실제 이름: "+file[i].getOriginalFilename());
			log.info("파일 크기: "+file[i].getSize());
			log.info("content type: "+file[i].getContentType());
			log.info("================== file   END ==================");
        }
		
		boardService.boardInsert(boardVo,file);
		mav.setViewName("redirect:/board/boardList.do");
		return mav;
	}
	
	
	//formData의 key 값을 article_file 로 주었기 때문에 @RequestParam("article_file") 작성해주기 작성하지 않으면 데이터 받지못함
	@ResponseBody
	@PostMapping("/board/boardInsert2.do")
	public String boardInsert2(BoardVo boardVo,ModelAndView mav,@RequestParam("article_file") List<MultipartFile> uploadFile, HttpServletRequest request) throws Exception {

		log.info("======================================");
		log.info("boardInsert2");
		log.info("======================================");
		log.info("boardVo ::: " + boardVo);
		log.info("uploadFile ::: " + uploadFile);

		String strResult = "{\"result\":\"OK\"}";
		String result = "success";
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		log.info("contextRoot ::: " + contextRoot);
		String fileRoot;
		//파일 있으면 들어옴
		boardService.boardInsert2(boardVo, uploadFile);

//			try {
//			if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
//				/* 데이터타입 담을변수명 : 변수 */
//				for(MultipartFile file : multipartFile) {
//					System.out.println("file ::: " + file);
//					
//					fileRoot = contextRoot + "resources/upload/";
//					System.out.println("fileRoot : " + fileRoot);
//					
//					String originalFileName = file.getOriginalFilename();	//오리지날 파일명
//					String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
//					String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
//					File targetFile = new File(fileRoot + savedFileName);
//				}
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return result;
	}
	
	//게시글 수정 로직
	@ResponseBody
	@PostMapping("/board/boardUpdate.do")
	public ModelAndView boardUpdate(BoardVo boardVo, ModelAndView mav, MultipartFile[] file, @RequestParam Map<String,Object> map) throws Exception{
		log.info("boardUpdate boardVo ::: " + boardVo);
		log.info("boardUpdate file ::: " + file);
		log.info("boardUpdate map ::: " + map);
		
		
		//새로운 파일 있으면
		boardService.updateBoard(boardVo,file);
		
		//boardService.updateBoard(map,file);
		mav.setViewName("redirect:/board/boardList.do");
		//setViewName 지정하지 않으면 맵핑 url 그대로 jsp 이동
		return mav;
	}
	
	//게시글 삭제
	@GetMapping("/board/boardDelete.do")
	public String boardDelete(HttpServletRequest request,String param) throws Exception{
		
		//데이터값 하나 보낼때
		log.info("HttpServletRequest boardNum ::: " + request.getParameter("boardNum"));
		
		log.info("HttpServletRequest param ::: " + param);
		
		String boardNum = request.getParameter("boardNum");
		
		boardService.boardDelete(boardNum);
		
		return "redirect:/board/boardList.do";
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
