package lee.jun.ho.board.vo;



import java.util.Date;

import lombok.Data;

@Data
public class BoardVo {
	
	//게시판
	private String boardNum;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardViewcnt;
	private Date boardRegdate;
	
	private String[] fileNum;
	
}

