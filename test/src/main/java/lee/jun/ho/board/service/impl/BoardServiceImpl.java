package lee.jun.ho.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lee.jun.ho.board.dao.BoardDao;
import lee.jun.ho.board.service.BoardService;
import lee.jun.ho.board.vo.BoardVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDao boardDao;
	

	public void txInsert() throws Exception{
		boardDao.txInsert();
		boardDao.txInsert();
	}


	@Override
	public void boardInsert(BoardVo boardVo) throws Exception {
		log.info("BoardServiceImpl boardInsert !!!");
		log.info("BoardServiceImpl boardInsert boardVo ::: " + boardVo);
		boardDao.boardInsert(boardVo);
	}


	@Override
	public List<BoardVo> boardList() {
		return boardDao.boardList();
	}


}
