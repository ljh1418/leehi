package lee.jun.ho.member.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import lee.jun.ho.member.dao.MemberDao;
import lee.jun.ho.member.service.MemberService;
import lee.jun.ho.member.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	MemberDao memberDao;
	
	@Override
	public List<MemberVo> memberList() throws Exception {
		return memberDao.memberList();
	}

	@Override
	public void memberWriterInsert(MemberVo memberVo) {
		memberDao.memberWriterInsert(memberVo);
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


}
