package lee.jun.ho.login.service.Impl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import lee.jun.ho.login.dao.LoginDao;
import lee.jun.ho.login.service.LoginService;
import lee.jun.ho.member.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService{

	@Inject
    LoginDao loginDao;

	@Override
	public MemberVo login(MemberVo memberVo, HttpSession session) {
		
		MemberVo memberInfo = loginDao.login(memberVo,session);
		log.info("memberInfo ::: " + memberInfo);
		
		if(memberInfo != null) {
			session.setAttribute("memberInfo", memberInfo);
		}
		
		return memberInfo;
		//return loginDao.login(memberVo,session);
	}
	
	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}
	

}
