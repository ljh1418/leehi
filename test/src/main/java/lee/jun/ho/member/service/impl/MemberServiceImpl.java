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

}
