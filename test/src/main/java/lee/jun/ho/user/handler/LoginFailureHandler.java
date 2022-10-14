package lee.jun.ho.user.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lee.jun.ho.user.message.MessageUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	
	//HttpServletRequest 에서 로그인 아이디가 저장되어 있는 파라미터 이름. 아이디값이 들어오는 input 태그 name
	//HttpServletRequest 에서 로그인 비밀번호가 저장되어 있는 파라미터 이름. 비밀번호값이 들어오는 input 태그 name 
	//로그인 페이지에서 jstl을 이용하여 에러메시지를 가져올 때 사용할 변수 이름
	//실패시 보여줄 화면 url
	
	
	private String loginidname;
	private String loginpwdname;
	private String errormsgname;
	private String defaultFailureUrl;
	
	//AuthenticationFailureHandler 참조하면 기본으로 매소드 onAuthenticationFailure 만들어야함
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		log.info("로그인실패시 들어옵니다.");
		log.info("onAuthenticationFailure 들어옴!!!");
		log.info("onAuthenticationFailure request : " + request);
		log.info("onAuthenticationFailure response : " + response);
		log.info("onAuthenticationFailure AuthenticationException : " + exception);
		
		String username = request.getParameter(loginidname);
		String password = request.getParameter(loginpwdname);
		
		//세션을 이용하지 않고 에러 메세지 보여주기
		//String erromsg = exception.getMessage();
		String errormsg = null;
		
		if(exception instanceof BadCredentialsException) {
			errormsg = MessageUtils.getMessage("error.BadCredentials111");
		} else if(exception instanceof InternalAuthenticationServiceException) {
			errormsg = MessageUtils.getMessage("error.BadCredentials222");
		} else if(exception instanceof DisabledException) {
			errormsg = MessageUtils.getMessage("error.Disaled");
		} else if(exception instanceof CredentialsExpiredException) {
			errormsg = MessageUtils.getMessage("error.CredentialsExpired");
		}
		
		request.setAttribute(loginidname, username);
		request.setAttribute(loginpwdname, password);
		request.setAttribute(errormsgname, errormsg);
		
		
		
		//HttpServletRequest 의 getRequestDispatcher 메서드를 이용해서 보여줄 화면으로 forward 해준다. 
		//forward를 해줘야만 jstl을 이용해서 값들을 가져올 수 있다. 여기서 보여줄 화면은 로그인 실패 시 이동할 페이지(defaultFailureUrl) 이라고 생각하면 된다.
		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
	}

	//파라미터들의 getter/setter이다. getter/setter가 존재하지 않으면 <bean> 등록시 <property> 태그에 오류가 발생한다.
	public String getLoginidname() {
		return loginidname;
	}

	public void setLoginidname(String loginidname) {
		this.loginidname = loginidname;
	}

	public String getLoginpwdname() {
		return loginpwdname;
	}

	public void setLoginpwdname(String loginpwdname) {
		this.loginpwdname = loginpwdname;
	}

	public String getErrormsgname() {
		return errormsgname;
	}

	public void setErrormsgname(String errormsgname) {
		this.errormsgname = errormsgname;
	}

	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}
	
	

}
