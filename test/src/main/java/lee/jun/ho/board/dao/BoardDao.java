package lee.jun.ho.board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lee.jun.ho.board.vo.BoardVo;
import lee.jun.ho.utils.Criteria;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BoardDao {
	
	@Autowired
	SqlSessionTemplate sql;
	
	public void txInsert() {
		sql.insert("board.insert");
	}
	
	public String boardNumSeq() throws Exception{
		log.info("boardNumSeq 들어옴 !!!");
		return sql.selectOne("board.boardNumSeq");
	}
	
	public String fileNumSeq() throws Exception{
		log.info("boardNumSeq 들어옴 !!!");
		return sql.selectOne("file.fileNumSeq");
	}
	
	public void boardInsert(BoardVo boardVo) {
		log.info("BoardDao boardInsert boardVo ::: " + boardVo);
		sql.insert("board.boardInsert", boardVo);
	}

	
	public List<BoardVo> boardList(Criteria cri) {
		return sql.selectList("board.boardList",cri);
	}

	public void boardViewHit(String boardNum) {
		sql.update("board.boardViewHit", boardNum);
	}


	public BoardVo boardView(BoardVo boardVo) {
		return sql.selectOne("board.boardView",boardVo);
	}


	public void updateBoard(BoardVo boardVo) {
		sql.update("board.updateBoard", boardVo);
	}


	public void boardDelete(String boardNum) {
		sql.delete("board.boardDelete", boardNum);
		
	}

	public void fileInsert(Map<String, Object> map) throws Exception{
		log.info("BoardDao fileInsert ::: " + map);
		sql.insert("file.fileInsert",map);
	}

	public Map<String, Object> fileView(String boardNum) {
		log.info("BoardDao fileView ::: " + boardNum);
		return sql.selectOne("file.fileView",boardNum);
	}

	public int listCount() {
		return sql.selectOne("board.listCount");
	}

	public void boardInsert2(Map<String, Object> map) {
		
		//List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		//listMap.add(map);
		
		
		sql.insert("file.fileInsert",map);
		
		
		
	}

	public List<Map<String, Object>> fileData(String boardNum) {
		log.info("boardNum ::: " + boardNum);
		return sql.selectList("file.fileData",boardNum);
	}

	public void insertFile(Map<String, Object> map) {
		sql.insert("file.insertFile",map);
		
	}

	public void updateBoard(Map<String, Object> map) {
		log.info("boardDao updateBoard ::: " + map);
		sql.update("board.updateBoard", map);
	}

	public List<Map<String, Object>> detailFile(Map<String, Object> map) {
		log.info("detailFile ::: " + map);
		return sql.selectList("file.detailFile",map);
	}

	public void updateDeleteFile(Map<String, Object> list) {
		log.info("boardDao updateDeleteFile ::: " + list);
		sql.update("file.updateDeleteFile",list);
	}
}
