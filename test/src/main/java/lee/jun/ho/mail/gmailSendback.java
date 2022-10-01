package lee.jun.ho.mail;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

//public class 다른 패키지에서 접근이 가능함
//반드시 파일명과 일치해야함
public class gmailSendback {
    String user = "ktko@gmail.com";// 네이버일 경우 네이버 계정, gmail경우 gmail 계정
    String password = "password";// 패스워드

    
    //ex) 메인에서 객체 생성
    private Properties props;

    
    //클래스 안에 있어야함
    //
    public gmailSendback() {
    	
    }
    
}
