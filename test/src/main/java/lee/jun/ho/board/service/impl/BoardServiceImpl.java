package lee.jun.ho.board.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	public List<BoardVo> boardList() throws Exception{
		return boardDao.boardList();
	}

	@Override
	public void boardViewHit(String boardNum, HttpSession session) throws Exception {
		log.info("BoardServiceImpl >>> boardViewHit 들어옴 !!! ");
		long updateTime = 0;
		    // 세션에 저장된 조회시간 검색
			// 최초로 조회할 경우 세션에 저장된 값이 없기 때문에 if문은 실행X
		if(session.getAttribute("updateTime"+boardNum) != null){
		            // 세션에서 읽어오기
			updateTime = (Long) session.getAttribute("updateTime"+boardNum);
		}
		// 시스템의 현재시간을 current_time에 저장
		long currentTime = System.currentTimeMillis();
			log.info("currentTime >>> :" + currentTime);
			// 일정시간이 경과 후 조회수 증가 처리 24*60*60*1000(24시간)
			// 시스템현재시간 - 열람시간 > 일정시간(조회수 증가가 가능하도록 지정한 시간)
		if(currentTime - updateTime > 360*1000){ // 1000 = 1초
			boardDao.boardViewHit(boardNum);
			// 세션에 시간을 저장 : "update_time_"+bno는 다른변수와 중복되지 않게 명명한 것
			session.setAttribute("updateTime"+boardNum, currentTime);
		}
		
	}


	@Override
	public BoardVo boardView(BoardVo boardVo) {
		return boardDao.boardView(boardVo);
	}


	@Override
	public void updateBoard(BoardVo boardVo) {
		boardDao.updateBoard(boardVo);
		
	}


}
