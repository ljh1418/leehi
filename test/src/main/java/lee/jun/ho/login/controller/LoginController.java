package lee.jun.ho.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lee.jun.ho.login.service.LoginService;
import lee.jun.ho.member.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	//로그인페이지 이동
	@GetMapping("login/loginPage.do")
	public String loginPage() throws Exception{
		return "login/loginPage";
	}
	
	//로그인 처리
//	@GetMapping("login/login.do")
//    public ModelAndView login(MemberVo memberVo, HttpSession session) throws Exception{
//        boolean result = loginService.login(memberVo, session);
//        log.info("result ::: " + result);
//        
//        ModelAndView mav = new ModelAndView();
//        if (result == true) { // 로그인 성공
//            // main.jsp로 이동
//            mav.setViewName("home");
//            mav.addObject("msg", "success");
//        } else {    // 로그인 실패
//            // login.jsp로 이동
//            mav.setViewName("member/login");
//            mav.addObject("msg", "failure");
//        }
//        return mav;
//    }
	
	//로그인 처리
	@PostMapping("login/login.do")
	public ModelAndView login(MemberVo memberVo, HttpSession session) throws Exception{
		log.info("login ::: " + memberVo);
		ModelAndView  mav = new ModelAndView();
		
		MemberVo memberInfo = loginService.login(memberVo, session);
		log.info("memberInfo : " + memberInfo);
		
		if(memberInfo != null) {
			mav.setViewName("index");
			//mav.addObject("message","success");
		}else {
			mav.setViewName("/login/loginPage");
			//mav.addObject("message","error");
		}
		return mav;
	}
	
	//로그아웃 처리
	@GetMapping(value = "/login/logout.do")
	public ModelAndView logout(HttpSession session) throws Exception{
		
		ModelAndView  mav = new ModelAndView();
		
		loginService.logout(session);
		
		mav.setViewName("login/loginPage");
		mav.addObject("message","logout");
		
		return mav;
		
		
	}
	
	
	
}
