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
		log.info("MemberDao memberUpdate memberVo ::: " + memberVo);
		sql.update("member.memberUpdate",memberVo);
	}
	
	//회원번호 생성
	public String memberNumSeq() {
		return sql.selectOne("member.memberSeqNum");
	}

	//회원생성
	public void memberInsert(MemberVo memberVo) {
		sql.insert("member.memberInsert",memberVo);
	}
	
	//회원삭제
	public void memberDelete(String memberNum) {
		sql.delete("member.memberDelete",memberNum);
		
	}
	
	

}
