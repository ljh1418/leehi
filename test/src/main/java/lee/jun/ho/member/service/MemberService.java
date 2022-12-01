package lee.jun.ho.member.service;

import java.util.List;

import lee.jun.ho.member.vo.MemberVo;

public interface MemberService {

	public List<MemberVo> memberList() throws Exception;

}
