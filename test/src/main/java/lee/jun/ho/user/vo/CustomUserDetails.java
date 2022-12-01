package lee.jun.ho.user.vo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/*UserDetails 은 security.core.userdetails 인터페이스 에서 제공*/
@Slf4j
@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {
	
	
	//
	
	private String ID;
	private String PASSWORD;
	private String AUTHORITY;
	private boolean ENABLED;
	private String NAME;
	private String EMAIL;
	private int FAILURE_CNT;
	
	/*
		 함수명						리턴타입									설명
		 getAuthorities() 			Collection<? extends GrantedAuthority> 	계정이 갖고있는 권한 목록을 리턴한다.
		 getPassword() 				String 									계정의 비밀번호를 리턴한다.
		 getUsername() 				String 									계정의 이름을 리턴한다.
		 isAccountNonExpired() 		boolean 								계정이 만료되지 않았는지 리턴한다. (true: 만료안됨)
		 isAccountNonLocked() 		boolean 								계정이 잠겨있지 않았는지 리턴한다. (true: 잠기지 않음)
		 isCredentialNonExpired() 	boolean 								비밀번호가 만료되지 않았는지 리턴한다. (true: 만료안됨)
		 isEnabled() 				boolean 								계정이 활성화(사용가능)인지 리턴한다. (true: 활성화)
	*/
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		log.info("getAuthorities 들어옴!!!");
		
		//계정 권한을 리턴
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(AUTHORITY));
		System.out.println("auth >>> : " + auth.toString());
		return auth;
	}

	@Override
	public String getPassword() {
		log.info("PASSWORD : " + PASSWORD);
		return PASSWORD;
	}
	
	@Override
	public String getUsername() {
		log.info("ID : " + ID);
		return ID;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return ENABLED;
	}
	
	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		log.info("setNAME : " + name);
		NAME = name;
	}

}
