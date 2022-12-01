package lee.jun.ho.member.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lee.jun.ho.member.service.MemberService;
import lee.jun.ho.member.vo.MemberVo;

@Controller
public class MemberController {
	
	 // MemberService 객체를 스프링에서 생성하여 주입시킴
    @Inject
    MemberService memberService;
    
    // 01 회원 목록
    @GetMapping("memberinfo/memberList.do")
    public String memberList(Model model) throws Exception{
        List<MemberVo> memberList = memberService.memberList();
        model.addAttribute("memberList", memberList);
        return "memberinfo/memberList";
    }
    
    //회원등록
    
}
