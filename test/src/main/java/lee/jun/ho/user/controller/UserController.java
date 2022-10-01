package lee.jun.ho.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {

	
	@GetMapping("/loginPage")
	public String loginPage() {
		
		log.info("로그인 페이지!!!");

		
		return "login";
	}
	
	@GetMapping("/users/login")
	public String login(String error, String logout, Model model) {
		
		log.info("로그인 페이지로 이동~");
		log.info("err :" + error );
		log.info("logout : " + logout );
		if(error != null) { 
			model.addAttribute("error","로그인 실패"); 
		} 
		if(logout != null) { 
			model.addAttribute("logout","로그아웃"); 
		}
		
		return "users/login";
	}
	
	
	
	@PostMapping("/login-processing")
	public String loginProcessing(String error) {
		
		System.out.println("loginProcessing호출...");
		System.out.println("error >>> : " + error);
		
		
		return "home";
	}
	
	@GetMapping("/securityLogin")
	public String securityLogin(String error, String logout, Model model) {
		
		System.out.println("securityLogin 들어옴 !!!");
		System.out.println("error >>> : " + error);
		System.out.println("logout >>> : " + logout);
		System.out.println("model >>> : " + model);
		
		return "/securityLogin";
	}
	
	@RequestMapping(value="/secu/loginPage")
	public String page() throws Exception{
		System.out.println("page() 들어옴 !!!");
		return "secu/loginPage";
	}
	

	
	@RequestMapping(value="/secu/admin")
	public String secuAdmin() throws Exception{
		System.out.println("secuAdmin() 들어옴 !!!");
		return "secu/admin";
	}
	
	@RequestMapping(value="/secu/user")
	public String secuUser() throws Exception{
		System.out.println("secuUser() 들어옴 !!!");
		return "secu/user";
	}
	
	

	
	
}
