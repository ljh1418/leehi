package lee.jun.ho.user.handler;

import java.io.IOException;

import javax.annotation.Resource;
import javax.mail.Message;
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
import lee.jun.ho.user.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	
	//@Autowired
    //private SqlSessionTemplate sqlSession;
	//sqlSession은 SqlSessionTemplate 클래스에 의존성을 가지게 된다.
	
	@Resource(name="userSer")
	private UserService userSer;
	//userSer은 (name="userSer")의 이름을 가진 Bean 객체에 의존성을 가지게 된다.
	//@Resource(name="userSer")는 bean 강제지정
	
	
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
		
		log.info("onAuthenticationFailure loginidname : " + loginidname);
		log.info("onAuthenticationFailure loginpwdname : " + loginpwdname);
		log.info("onAuthenticationFailure errormsgname : " + errormsgname);
		log.info("onAuthenticationFailure defaultFailureUrl : " + defaultFailureUrl);
		
		String username = request.getParameter(loginidname);
		String password = request.getParameter(loginpwdname);
		
		//세션을 이용하지 않고 에러 메세지 보여주기
		//String erromsg = exception.getMessage();
		String errormsg = null;
		
		//이거 넣으면 500에러 발생... 원인 찾기
		//해결방법 > MessageUtils 을 사용하기 위해서 root-context.xml에서 bean 설정 /WEB-INF/message/security_messageproperties
		//security_messageproperties 에서 error.BadCredentials 라는 문구를 찾아서 에러메세지를 뿌림
		if(exception instanceof BadCredentialsException) {
			errormsg = MessageUtils.getMessage("error.BadCredentials");
			log.info("onAuthenticationFailure errormsg : " + errormsg);
			//비밀번호가 틀렸을 경우 실패카운터 메서드 실행!
			loginFailurCount(username);
		} else if(exception instanceof InternalAuthenticationServiceException) {
			errormsg = MessageUtils.getMessage("error.BadCredentials");
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
	
	protected void loginFailurCount(String username) {
		log.info("loginFailurCount : " + username);
		
		//실패카운터 증가
		userSer.countFailure(username);
		//실패 횟수를 가져옴
		int cnt = userSer.checkFailureCount(username);
		//실패 횟수 3회일때 계정 잠금
		if(cnt==3 || cnt > 3) {
			log.info("실패111");
			userSer.disabledUsername(username);
			log.info("실패222");
		}
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
		log.info("getErrormsgname 들어옴");
		return errormsgname;
	}

	public void setErrormsgname(String errormsgname) {
		log.info("setErrormsgname errormsgname : " + errormsgname);
		this.errormsgname = errormsgname;
	}

	public String getDefaultFailureUrl() {
		log.info("defaultFailureUrl 들어옴");
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		log.info("defaultFailureUrl : " + defaultFailureUrl);
		this.defaultFailureUrl = defaultFailureUrl;
	}
	
	

}
