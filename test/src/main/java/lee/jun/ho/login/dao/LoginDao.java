package lee.jun.ho.login.dao;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lee.jun.ho.member.vo.MemberVo;

@Repository
public class LoginDao {

	@Inject
	SqlSession sql;
	
	public MemberVo login(MemberVo memberVo, HttpSession session) {
		// TODO Auto-generated method stub
		return sql.selectOne("login.login",memberVo);
	}

}
