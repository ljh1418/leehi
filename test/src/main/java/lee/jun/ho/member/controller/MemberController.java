package lee.jun.ho.member.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lee.jun.ho.board.vo.BoardVo;
import lee.jun.ho.member.service.MemberService;
import lee.jun.ho.member.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	 // MemberService 객체를 스프링에서 생성하여 주입시킴
    @Inject
    MemberService memberService;
    
    //회원목록 로직
    @GetMapping("/memberinfo/memberList.do")
    public String memberList(Model model) throws Exception{
        List<MemberVo> memberList = memberService.memberList();
        model.addAttribute("memberList", memberList);
        return "memberinfo/memberList";
    }
    
	//회원등록 페이지 이동
	@GetMapping("/memberinfo/memberInsertPage.do")
	public String userWrite() throws Exception{
		return "memberinfo/memberWriter";
	}
	
	//회원등록 인서트 및 업데이트
	//return을 ModelAndView로 받기 위해서는 데이터 타입도 ModelAndView 
	@GetMapping("/memberinfo/memberWriter.do")
	public ModelAndView memberWriter(MemberVo memberVo) throws Exception{
		
		log.info("memberInsert ::: " + memberVo);
		ModelAndView mav = new ModelAndView();
		
		String memberNum  = memberVo.getMemberNum();
		log.info("memberNum ::: " + memberNum);
		
		if(memberNum != null) {
			log.info("회원수정");
			
			//memberService.memberUpdate(memberVo);
			// 뷰에 전달할 데이터
			MemberVo memberView = memberService.memberView(memberVo);
			log.info("memberView ::: " + memberView);
			
			mav.addObject("memberView", memberView);
			mav.addObject("actionType","/memberinfo/memberUpdate.do");
			mav.setViewName("/memberinfo/memberView");
			
		}else {
			memberService.memberWriterInsert(memberVo);
		}
		return mav; 
	}
	
	//회원수정 로직
	@PostMapping("/memberinfo/memberUpdate.do")
	public String memberUpdate(MemberVo memberVo, Model model) throws Exception{
		log.info("memberUpdate ::: " + memberVo);
		memberService.memberUpdate(memberVo);
		
		return "memberinfo/memberList";
			
	}
	
	@PostMapping("/memberinfo/update.do")
	public ModelAndView boardUpdate(MemberVo memberVo) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		memberService.memberUpdate(memberVo);
		mav.setViewName("redirect:/member/memberList.do");
		
		return mav;
		
		
		
	}
	
	
    
}
