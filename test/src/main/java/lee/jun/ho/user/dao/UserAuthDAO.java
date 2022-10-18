package lee.jun.ho.user.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lee.jun.ho.user.vo.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("userAuthDAO")
public class UserAuthDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public CustomUserDetails getUserById(String username) {
		log.info("getUserById 들어옴 >>> : " + username);
		
		log.info("===============================================");
		log.info("getUserById username : " + username);
		log.info("===============================================");
		return sqlSession.selectOne("user.selectUserById", username);
	}
	
	public void updateFailureCount(String username) {
		log.info("updateFailureCount 들어옴 >>> : " + username);
		sqlSession.update("user.updateFailureCount", username);
	}
	
	public int checkFailureCount(String username) {
		return sqlSession.selectOne("user.checkFailureCount", username);
	}
	
	public void updateDisabled(String username) {
		sqlSession.update("user.updateUnenabled", username);
	}

	public void updateFailureCountReset(String username) {
		sqlSession.update("user.updateFailureCountReset", username);
	}

}
