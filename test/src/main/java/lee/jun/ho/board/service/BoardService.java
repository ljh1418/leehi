package lee.jun.ho.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lee.jun.ho.board.vo.BoardVo;
import lee.jun.ho.utils.Criteria;
import lee.jun.ho.utils.CriteriaMySQL;

public interface BoardService{

	void txInsert() throws Exception;
	
	public void boardInsert(BoardVo boardVo, MultipartHttpServletRequest mhsr) throws Exception;

	public List<BoardVo> boardList(Criteria cri) throws Exception;

	public void boardViewHit(String boardNum, HttpSession session) throws Exception;

	public BoardVo boardView(BoardVo boardVo);

	public void updateBoard(BoardVo boardVo);

	public void boardDelete(String boardNum);

	public Map<String, Object> fileView(String boardNum);

	public int listCount() throws Exception;

	//에이작스 게시글
	public void boardInsert2(BoardVo boardVo, List<MultipartFile> multipartFile) throws Exception;
	
	//list 첨부파일 읽기
	public List<Map<String, Object>> fileData(String boardNum) throws Exception;
	
	//20221215
	public void boardInsert(BoardVo boardVo, MultipartFile[] file) throws Exception;

	//public void updateBoard(Map<String, Object> map, MultipartFile[] file);

	public void updateBoard(BoardVo boardVo, MultipartFile[] file) throws Exception;

	
}
