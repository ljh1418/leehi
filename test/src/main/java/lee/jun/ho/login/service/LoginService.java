package lee.jun.ho.login.service;

import javax.servlet.http.HttpSession;

import lee.jun.ho.member.vo.MemberVo;

public interface LoginService {

	//boolean login(MemberVo memberVo, HttpSession session) throws Exception;

	public MemberVo login(MemberVo memberVo, HttpSession session);
	
	public void logout(HttpSession session);

}
