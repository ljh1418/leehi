package lee.jun.ho;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class MyInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		
		System.out.println("=== Interceptor START ===");
		
		return true;
	}
	
		public void postHandle(HttpServletRequest request,	HttpServletResponse response, Object handler, ModelAndView modelAndView)	throws Exception {
			System.out.println("===================       INTERCEPTOR			END       ==================="); 
		}
}
