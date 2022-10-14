package lee.jun.ho.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import lee.jun.ho.user.vo.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;

//AuthenticationProvider 인터페이스 : 화면에서 입력한 로그인 정보와 DB에서 가져온 사용자의 정보를 비교햊

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	
	@Autowired
	private UserDetailsService userDeSer;

	@SuppressWarnings("unchecked")
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		log.info("authenticate 들어옴 >>> : " + authentication);
		
		//화면에서 입력한 아이디 비밀번호 담는다
		String username = (String) authentication.getPrincipal();
		log.info("username : " + username);
		
		String password = (String) authentication.getCredentials();
		log.info("password : " + password);
		
		
		log.debug("AuthenticationProvider :::::: 1");
		
		//화면에서 입력한 아이디로 DB에 있는 사용자의 정보를 UserDetails 형으로 가져와 user에 담는다
		CustomUserDetails user = (CustomUserDetails) userDeSer.loadUserByUsername(username);
		log.debug("AuthenticationProvider loadUserByUsername :::::: 3");
		
		
		//화면에서 입력한 비밀번호와 DB에서 가져온 비밀번호를 비교하는 로직
		if(!matchPassword(password, user.getPassword())) {
			log.debug("matchPassword :::::::: false!");
			throw new BadCredentialsException(username);
		}
		
		//계정 활성화 여부를 확인하는 로직 AuthenticationProvider 인터페이스를 구현하게 되면 계정 잠금 , 계정 활성화 등은 여기에서 확인
		//if(!user.isEnabled()) {
		//	log.debug("isEnabled :::::::: false!");
		//	throw new BadCredentialsException(username);
		//}
		
		//AuthenticationProvider 인터페이스를 구현한 클래스의 Authentication()
		if(!user.isEnabled() || !user.isCredentialsNonExpired()) {
			log.info("들어온다1111111111");
			throw new AuthenticationCredentialsNotFoundException(username);
		}
		
		//UserDetailsService 인터페이스를 구현한 클래스의 loadUserByUsername() 메서드
		if(user==null) {
			log.info("들어온다2222222222");			
			throw new InternalAuthenticationServiceException(username);
		}
		
		
		log.debug("matchPassword :::::::: true!");
		
		//계정이 인증된다면 UsernamePasswordAuthenticationToken 객체에 화면에서 입력한 정보와 DB에서 가져온 권한을 담아서 리턴
		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		//AuthenticationProvider 인터페이스가 지정된 Authentication 객체를 지원하는 경우에 true를 리턴한다.
		return true;
	}
	
	private boolean matchPassword(String loginPwd, String password) {
		//비밀번호를 비교하는 메서드이다. 맞으면 true를 리턴한다.
		log.debug("matchPassword :::::::: check!");
		return loginPwd.equals(password);
	}

}
