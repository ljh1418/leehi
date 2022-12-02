package lee.jun.ho.board.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVo {
	
	private String boardNum;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardViewcnt;
	private Date boardRegdate;
	
}
