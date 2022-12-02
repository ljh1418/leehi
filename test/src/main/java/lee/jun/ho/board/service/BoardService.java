package lee.jun.ho.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import lee.jun.ho.board.vo.BoardVo;

public interface BoardService{

	void txInsert() throws Exception;
	
	public void boardInsert(BoardVo boardVo) throws Exception;

	public List<BoardVo> boardList() throws Exception;

	public void boardViewHit(String boardNum, HttpSession session) throws Exception;

	public BoardVo boardView(BoardVo boardVo);

	public void updateBoard(BoardVo boardVo);

	
	
}
