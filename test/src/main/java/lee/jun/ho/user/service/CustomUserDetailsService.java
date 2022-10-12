package lee.jun.ho.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lee.jun.ho.user.dao.UserAuthDAO;
import lee.jun.ho.user.vo.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;

	
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
	
	//UserDetailsService : DB에서 유저 정보를 가져오는 역활
	
	@Autowired
	private UserAuthDAO userAuthDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername >>> : " + username);
		
		CustomUserDetails user = userAuthDAO.getUserById(username);
		log.info("===============================================");
		log.info("user >>> : " + user);
		log.info("===============================================");
		if(user==null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}

}
