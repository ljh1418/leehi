package lee.jun.ho.member.vo;

import java.util.Date;
import lombok.Data;

@Data
public class MemberVo {
	
	private String memberNum;
	private String memberId;
	private String memberName;
	private String memberPw;
	private String memberEmail;
	private Date memberRegdate;

}
