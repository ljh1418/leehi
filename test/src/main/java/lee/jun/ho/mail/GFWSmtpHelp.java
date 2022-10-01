package lee.jun.ho.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Project : A010_gensUtil
 * @FileName : GFWSmtpHelp.java
 * @author ZEUS
 * @Date 2022. 9. 21.
 */
public class GFWSmtpHelp {
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	/** 메일 HOST **/
	private final String HOST = "smtp.gmail.com";
	/** 메일 PORT **/
	private final String PORT = "465";	
	/** 보내는 메일 ID **/
	private final String MAIL_ID = "ljh1418@gmail.com";  
	/** 보내는 메일 PW **/
	private final String MAIL_PW = "dlwnsgh1"; 
	
	private Properties props;
	private Session mailSession;
	
	public GFWSmtpHelp()
	{
		// SMTP 발송 Properties 설정
		this.props = new Properties();
		this.props.put("mail.transport.protocol", "smtp");
		this.props.put("mail.smtp.host", HOST);
		this.props.put("mail.smtp.port", PORT);
		this.props.put("mail.smtp.starttls.enable", "true");
		this.props.put("mail.smtp.ssl.trust", HOST);
		this.props.put("mail.smtp.auth", "true");			
		
		// SMTP Session 생성
		this.mailSession = Session.getDefaultInstance(this.props, new javax.mail.Authenticator(){
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(MAIL_ID, MAIL_PW);
			}
		});
	}
	
	
	/**
	 * @Date 2022. 9. 21.
	 * @param subejct - 제목
	 * @param body    - 내용
	 * @param addrList - 받는사람 주소 ( String 또는 List<String> )
	 * @return String
	 */
	public String sendMail(String subejct, String body, Object addrList) 
	{
		return this.sendMail(subejct, body, addrList, "text/html");
	}
	
	/**
	 * @Date 2022. 9. 21.
	 * @param subejct - 제목
	 * @param body    - 내용
	 * @param addrList - 받는사람 주소 ( String 또는 List<String> )
	 * @param contentType - 컨텐츠 타입
	 * @return String
	 */
	public String sendMail(String subejct, String body, Object addrList, String contentType) 
	{
		return this.sendMail(subejct, body, addrList, contentType, "UTF-8");
	}	
	
	/**
	 * @Date 2022. 9. 21.
	 * @param subejct - 제목
	 * @param body    - 내용
	 * @param addrList - 받는사람 주소 ( String 또는 List<String> )
	 * @param contentType - 컨텐츠 타입
	 * @param charSet - 캐릭터셋
	 * @return String
	 */
	public String sendMail(String subejct, String body, Object pAddrList, String contentType, String charSet) 
	{	
		try {
			
			List<String> addrList = new ArrayList<String>();
			
			if(pAddrList instanceof String)
			{
				addrList.add((String)pAddrList);
			}else if(pAddrList instanceof ArrayList) 
			{
				addrList = (List)pAddrList;
			}
			
			if(addrList.size() == 0) { return "수신자 정보가 없습니다"; }
			
			InternetAddress[] receiverList = new InternetAddress[addrList.size()];
			
			for (int i = 0; i < addrList.size(); i++)
			{	
				receiverList[i] = new InternetAddress(addrList.get(i));
			}
			
			// Mail 조립
			Message mimeMessage = new MimeMessage(mailSession);
			mimeMessage.setFrom(new InternetAddress(MAIL_ID));
			mimeMessage.setRecipients(Message.RecipientType.TO, receiverList);
	        
			// 메일 제목
			mimeMessage.setSubject(subejct);
	        // 메일 본문 (.setText를 사용하면 단순 텍스트 전달 가능)
			mimeMessage.setContent(body, contentType + "; charset=" + charSet +";");
			
			// Mail 발송
			Transport.send(mimeMessage);
			
		} catch(AddressException e) {
			this.log.error(e.getMessage());
			return "FAIL - " + e.getMessage();
			
		} catch(Exception e) {
			this.log.error(e.getMessage());
			return "FAIL - " + e.getMessage();
		}
		
		return "SUCCESS";
	}
}


	
	
	
	