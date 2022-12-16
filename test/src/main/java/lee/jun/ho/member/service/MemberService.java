package lee.jun.ho.member.service;

import java.util.List;

import lee.jun.ho.member.vo.MemberVo;

public interface MemberService {

	public List<MemberVo> memberList() throws Exception;

	public void memberUpdate(MemberVo memberVo);

	//회원 상세보기
	public MemberVo memberView(MemberVo memberVo);
	
	//회원생성 로직
	public void memberInsert(MemberVo memberVo);
	
	//회원삭제 로직
	public void memberDelete(String memberNum);



}
