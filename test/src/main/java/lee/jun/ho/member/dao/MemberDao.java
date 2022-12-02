package lee.jun.ho.member.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lee.jun.ho.member.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MemberDao {

	@Inject
	SqlSession sql;
	
	public List<MemberVo> memberList() {
		return sql.selectList("member.memberList");
	}
	
	public void memberWriterInsert(MemberVo memberVo) {
		log.info("MemberDao memberWriterInsert memberVo ::: " + memberVo);
		sql.insert("member.memberWriterInsert",memberVo);
	}
	
	//회원 상세보기
	public MemberVo memberView(MemberVo memberVo) {
		return sql.selectOne("member.memberView",memberVo);
	}
	
	//회원 업데이트
	public void memberUpdate(MemberVo memberVo) {
		sql.update("member.memberUpdate",memberVo);
	}
	
	

}
