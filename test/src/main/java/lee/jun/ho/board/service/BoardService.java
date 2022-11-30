package lee.jun.ho.board.service;

import java.util.List;

import lee.jun.ho.board.vo.BoardVo;

public interface BoardService{

	void txInsert() throws Exception;
	
	public void boardInsert(BoardVo boardVo) throws Exception;

	public List<BoardVo> boardList();
	
}
