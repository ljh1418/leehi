package lee.jun.ho.user.handler;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lee.jun.ho.user.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	//RequestCache 객체 생성
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	//RedirectStrategy 객체를 생성한다
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	
	private String loginidname;
	private String defaultUrl;
	
	@Resource(name="userSer")
	private UserService userSer;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String username = request.getParameter(loginidname);
		
		userSer.resetFailureCnt(username);
		
		
		//resultRedirectStrategy 메서드 호출
		resultRedirectStrategy(request, response, authentication);
		
		//에러세션 지우는 메소드 실행
		clearAuthenticationAttributes(request);
		
		
		
	}
	
	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException{
		
		//RequestCache 객체를 통해 SavedRequest 객체를 가져온다.
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		//세션에 이동할 url의 정보가 담겨져 있을때 인증 권한이 필요한 페이지에 접근했을 경우
		if(savedRequest!=null) {
			log.info("권한이 필요한 페이지에 접근했을 경우");
			//savedRequest 객체를 통해 getRedirectUrl (로그인화면을 보기 전에 갔던 url)을 가져온다.
			String targetUrl = savedRequest.getRedirectUrl();
			redirectStratgy.sendRedirect(request, response, targetUrl);
		}else {
			//직접 로그인 화면으로 이동했을 경우를 뜻한다.
			log.info("직접 로그인 페이지로 이동했을경우");
			redirectStratgy.sendRedirect(request, response, defaultUrl);
		}
	}
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		//세션을 받아옴
		HttpSession session = request.getSession(false);
		//세션에 에러가 없으면 그냥 return
		if(session==null) return;
		//WebAttributes.AUTHENTICATION_EXCEPTION 이름 값으로 정의된 세션을 지운다
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	

	public String getLoginidname() {
		return loginidname;
	}

	public void setLoginidname(String loginidname) {
		this.loginidname = loginidname;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}
	
	
	
	
	
}
