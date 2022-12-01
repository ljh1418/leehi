package lee.jun.ho.member.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lee.jun.ho.member.vo.MemberVo;

@Repository
public class MemberDao {

	@Inject
	SqlSession sql;
	
	public List<MemberVo> memberList() {
		return sql.selectList("member.memberList");
	}

}
