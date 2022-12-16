package lee.jun.ho.member.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lee.jun.ho.board.service.BoardService;
import lee.jun.ho.member.service.MemberService;
import lee.jun.ho.member.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Slf4j
@Controller
public class MemberController {

	// MemberService 객체를 스프링에서 생성하여 주입시킴
	@Inject
	MemberService memberService;

	// 회원목록 로직
	@GetMapping("/memberinfo/memberList.do")
	public String memberList(Model model) throws Exception {
		List<MemberVo> memberList = memberService.memberList();
		model.addAttribute("memberList", memberList);
		return "memberinfo/memberList";
	}

	// 회원등록 로직
	@PostMapping("/memberinfo/memberInsert.do")
	public ModelAndView memberInsert(MemberVo memberVo) {
		log.info("memberInsert memberVo ::: " + memberVo);
		ModelAndView mav = new ModelAndView();
		memberService.memberInsert(memberVo);
		mav.setViewName("redirect:/memberinfo/memberList.do");
		return mav;
	}

	// 회원 인서트 및 업데이트 페이지 및 로직
	// return을 ModelAndView로 받기 위해서는 데이터 타입도 ModelAndView
	// @PostMapping("/memberinfo/memberWriter.do")
	@GetMapping("/memberinfo/memberWriter.do")
	public ModelAndView memberWriter(MemberVo memberVo, HttpSession session) throws Exception {

		log.info("memberInsert ::: " + memberVo);
		ModelAndView mav = new ModelAndView();

		String memberNum = memberVo.getMemberNum();
		log.info("memberNum ::: " + memberNum);

		if (memberNum != null) {
			log.info("회원수정");

			// 뷰에 전달할 데이터
			MemberVo memberView = memberService.memberView(memberVo);
			log.info("memberView ::: " + memberView);

			mav.addObject("memberView", memberView);
			mav.addObject("actionType", "/memberinfo/memberUpdate.do");
			mav.setViewName("/memberinfo/memberView");
		} else {
			mav.addObject("actionType", "/memberinfo/memberInsert.do");
			mav.setViewName("/memberinfo/memberWriter");
		}
		return mav;
	}

	// 회원수정 로직
	@PostMapping("/memberinfo/memberUpdate.do")
	public String memberUpdate(MemberVo memberVo, Model model) throws Exception {
		log.info("memberUpdate ::: " + memberVo);
		memberService.memberUpdate(memberVo);

		return "memberinfo/memberList";

	}

	@ResponseBody
	@PostMapping("/memberinfo/memberAjaxUpdate.do")
	public String memberAjaxUpdate(MemberVo memberVo) throws Exception {

		log.info("memberAjaxUpdate memberVo 1111 ::: " + memberVo);

		memberService.memberUpdate(memberVo);

		log.info("memberAjaxUpdate memberVo ::: " + memberVo);

		return "succes";

	}

	// 회원삭제
	@GetMapping("/memberinfo/memberDelete.do")
	public String boardDelete(HttpServletRequest request) throws Exception {

		// 데이터값 하나 보낼때
		log.info("HttpServletRequest memberNum ::: " + request.getParameter("memberNum"));

		String memberNum = request.getParameter("memberNum");
		memberService.memberDelete(memberNum);

		return "redirect:/memberinfo/memberList.do";
	}

}
