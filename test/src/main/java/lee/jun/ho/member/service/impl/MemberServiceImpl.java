package lee.jun.ho.member.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import lee.jun.ho.member.dao.MemberDao;
import lee.jun.ho.member.service.MemberService;
import lee.jun.ho.member.vo.MemberVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	MemberDao memberDao;
	
	@Override
	public List<MemberVo> memberList() throws Exception {
		return memberDao.memberList();
	}
	
	//회원 상세보기
	@Override
	public MemberVo memberView(MemberVo memberVo) {
		return memberDao.memberView(memberVo);
	}
	
	//회원수정
	@Override
	public void memberUpdate(MemberVo memberVo) {
		memberDao.memberUpdate(memberVo);
	}
	
	//회원등록
	@Override
	public void memberInsert(MemberVo memberVo) {
		String memberNumSeq = memberDao.memberNumSeq();
		log.info("MemberServiceImpl memberInsert ::: " + memberNumSeq);
		memberVo.setMemberNum(memberNumSeq);
		memberDao.memberInsert(memberVo);
	}

	@Override
	public void memberDelete(String memberNum) {
		memberDao.memberDelete(memberNum);
	}



}
