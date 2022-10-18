package lee.jun.ho.user.message;

import java.util.Locale;

import org.springframework.context.support.MessageSourceAccessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageUtils {
	
	private static MessageSourceAccessor msAcc = null;
	
	public void setMessageSourceAccessor(MessageSourceAccessor msAcc) {
		log.info("MessageUtils setMessageSourceAccessor : " + msAcc);
		MessageUtils.msAcc = msAcc;
	}
	
	public static String getMessage(String code) {
		log.info("MessageUtils getMessage code : " + code);
		log.info("Locale.getDefault() : " + Locale.getDefault());
		return msAcc.getMessage(code, Locale.getDefault());
	}
	
	public static String getMessage(String code, Object[] objs) {
		log.info("MessageUtils getMessage code 1 : " + code);
		log.info("MessageUtils getMessage objs 1 : " + objs);
		return msAcc.getMessage(code, objs, Locale.getDefault());
	}

}
