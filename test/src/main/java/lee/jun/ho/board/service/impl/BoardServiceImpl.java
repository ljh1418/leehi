package lee.jun.ho.board.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lee.jun.ho.board.dao.BoardDao;
import lee.jun.ho.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDao boardDao;
	

	public void txInsert() throws Exception{
			boardDao.txInsert();
			boardDao.txInsert();
	}


}
