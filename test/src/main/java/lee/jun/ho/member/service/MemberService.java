package lee.jun.ho.member.service;

import java.util.List;

import lee.jun.ho.member.vo.MemberVo;

public interface MemberService {

	public List<MemberVo> memberList() throws Exception;

	public void memberWriterInsert(MemberVo memberVo);

	public void memberUpdate(MemberVo memberVo);

	//회원 상세보기
	public MemberVo memberView(MemberVo memberVo);


}
