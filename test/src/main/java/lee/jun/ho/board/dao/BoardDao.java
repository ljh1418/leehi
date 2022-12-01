package lee.jun.ho.board.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lee.jun.ho.board.vo.BoardVo;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BoardDao {
	
	@Autowired
	SqlSessionTemplate sql;
	
	
	public void txInsert() {
		sql.insert("board.insert");
	}


	public void boardInsert(BoardVo boardVo) {
		log.info("BoardDao boardInsert !!!");
		log.info("BoardDao boardInsert boardVo ::: " + boardVo);
		sql.insert("board.boardInsert", boardVo);
	}

	
	public List<BoardVo> boardList() {
		return sql.selectList("board.boardList");
	}

	public void boardViewHit(String boardNum) {
		log.info("boardViewHit >>> : " + boardNum);
		sql.update("board.boardViewHit", boardNum);
	}

}
