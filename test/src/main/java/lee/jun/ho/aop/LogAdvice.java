package lee.jun.ho.aop;


import org.springframework.stereotype.Component;

import lee.jun.ho.board.controller.BoardController;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

//@Aspect
@Slf4j
@Component
public class LogAdvice {

	//@Before("execution(* lee.jun.ho.board.service.BoardServiceImpl*.*(..)")
	public void logBefore() {
		log.info("============================");
	}
	
}
