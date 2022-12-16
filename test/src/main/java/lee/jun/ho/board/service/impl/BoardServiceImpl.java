package lee.jun.ho.board.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lee.jun.ho.board.dao.BoardDao;
import lee.jun.ho.board.service.BoardService;
import lee.jun.ho.board.vo.BoardVo;
import lee.jun.ho.utils.Criteria;
import lee.jun.ho.utils.FileUtil;
import lee.jun.ho.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDao boardDao;
	
	
	@Resource
	FileUtils fileUtils;
	

	

	public void txInsert() throws Exception{
		boardDao.txInsert();
		boardDao.txInsert();
	}

	
	//파일업로드 없을때
//	@Override
//	public void boardInsert(BoardVo boardVo) throws Exception {
//		log.info("BoardServiceImpl boardInsert boardVo ::: " + boardVo);
//		
//		String boardNumSeq = boardDao.boardNumSeq();
//		log.info("boardNumSeq ::: " + boardNumSeq);
//		boardVo.setBoardNum(boardNumSeq);
//		
//		boardDao.boardInsert(boardVo);
//	}
	
	@Override
	public void boardInsert(BoardVo boardVo, MultipartHttpServletRequest mhsr) throws Exception {
		log.info("BoardServiceImpl boardInsert boardVo ::: " + boardVo);
		log.info("BoardServiceImpl boardInsert mhsr ::: " + mhsr);
		
		String boardNumSeq = boardDao.boardNumSeq();
		log.info("boardNumSeq ::: " + boardNumSeq);
		boardVo.setBoardNum(boardNumSeq);
		
		boardDao.boardInsert(boardVo);
		log.info("BoardServiceImpl boardInsert after ::: " + boardVo);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(boardVo, mhsr);
		
		log.info("BoardServiceImpl boardInsert list ::: " + list);
		
		int size = list.size();
		for(int i=0; i<size; i++){ 
			boardDao.fileInsert(list.get(i)); 
		}
	}
	
	
	@Override
	public List<BoardVo> boardList(Criteria cri) throws Exception{
		return boardDao.boardList(cri);
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

	@Override
	public void boardDelete(String boardNum) {
		boardDao.boardDelete(boardNum);
	}


	@Override
	public Map<String, Object> fileView(String boardNum) {
		return boardDao.fileView(boardNum);
	}


	@Override
	public int listCount() throws Exception {
		return boardDao.listCount();
	}


	@Override
	public void boardInsert2(BoardVo boardVo, List<MultipartFile> uploadFile) throws Exception {
		
		//보드넘버
		String boardNumSeq = boardDao.boardNumSeq();
		log.info("boardNumSeq ::: " + boardNumSeq);
		boardVo.setBoardNum(boardNumSeq);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo2(boardVo, uploadFile);
		log.info("boardInsert list >>> : " + list);
		
	      
		int size = list.size();
		log.info("size >>> : " + size);
	      
		boardDao.boardInsert(boardVo);
	      
		log.info("insertFile for 들어가기전 !!!!!!!!!!!!!");
		for(int i=0; i<size; i++){ 
			boardDao.boardInsert2(list.get(i)); 
		}
		
		
		
	}


	@Override
	public List<Map<String, Object>> fileData(String boardNum) throws Exception {
		log.info("fileData 들어옴 !!!!");
		return boardDao.fileData(boardNum);
	}

	
//	@Override
//	public void updateBoard(BoardVo boardVo, MultipartFile[] file) {
//		boardDao.updateBoard(boardVo);
//		String boardNum = boardVo.getBoardNum();
//		//기존의 파일정보
//		List<Map<String, Object>> listMap = boardDao.listMap(boardNum);
//		log.info("updateBoard listMap ::: " + listMap);
//		
//		List<Map<String, Object>> fileList = fileUtils.parseFileInfo(map, file);
//		
//			for(int i=0; i<listMap.size(); i++) {
//				String test = "FILE_TEST_"+listMap.get(i).get("FILE_NUM");
//				log.info("test ::: " + test);
//			}
//	}


	@Override
	public void boardInsert(BoardVo boardVo, MultipartFile[] file) throws Exception {
		String boardNumSeq = boardDao.boardNumSeq();
		log.info("boardNumSeq ::: " + boardNumSeq);
		boardVo.setBoardNum(boardNumSeq);
		
		boardDao.boardInsert(boardVo);
		
		 List<Map<String, Object>> fileList = FileUtil.parseFileInfo(boardVo, file);
		 log.info("fileList ::: " + fileList);
		    for(int i=0; i<fileList.size(); i++) {
		        boardDao.insertFile(fileList.get(i));
		    }
		
	}



//	@Override
//	public void updateBoard(Map<String, Object> map, MultipartFile[] file) {
//		 boardDao.updateBoard(map);
//		 
//		 String boardNum = (String) map.get("boardNum");
//		 log.info("updateBoard boardNum ::: " + boardNum);
//		 
//		 
//		 for(int i=0; i<file.length; i++) {
//			log.info("================== updateBoard start ==================");
//			log.info("파일 이름: "+file[i].getName());
//			log.info("파일 실제 이름: "+file[i].getOriginalFilename());
//			log.info("파일 크기: "+file[i].getSize());
//			log.info("content type: "+file[i].getContentType());
//			log.info("================== updateBoard   END ==================");
//		 }
//		 
//		 
//		 //새로운파일
//		 //기존의 파일정보를 가져옴
//		 //List<Map<String, Object>> fileDetail = boardDao.detailFile(map);
//		 
//		 //기존에있던 listMap
//		 List<Map<String, Object>> fileDetail = boardDao.fileData(boardNum);
//		 log.info("기존에 등록되어있던 파일 fileDetail ::: " + fileDetail);
//		 log.info("기존에 등록되어있던 파일 fileDetail size ::: " + fileDetail.size());
//		 
//		for(int i=0; i<fileDetail.size(); i++) {
//			String fileNum = "FILE_NUM=" + fileDetail.get(i).get("FILE_NUM");
//			log.info("fileNum ::: " + fileNum);
//			
//			//containsKey map 데이터에 들어있는 키값을 확
//			if(!map.containsKey(fileNum)) {
//				log.info("현재 데이터에는 " + fileNum + "의 번호가 없습니다.");
//				Map<String, Object> list = fileDetail.get(i);
//				log.info("list ::: " + list);
//				boardDao.updateDeleteFile(list);
//			}
//		}
//	}


	@Override
	public void updateBoard(BoardVo boardVo, MultipartFile[] file) throws Exception {
		log.info("updateBoard boardVo ::: " + boardVo);
		
		boardDao.updateBoard(boardVo);
		
		String boardNum = boardVo.getBoardNum();
		log.info("boardNum ::: " + boardNum);
		
		//새로운파일 들어옴
		List<Map<String, Object>> fileList = FileUtil.parseFileInfo(boardVo, file);
		 log.info("업데이트인데 새로운파일이 들어옴 fileList ::: " + fileList);
		    for(int i=0; i<fileList.size(); i++) {
		        boardDao.insertFile(fileList.get(i));
		    }
		
		
		
		List<Map<String, Object>> fileDetail = boardDao.fileData(boardNum);
		log.info("fileDetail ::: " + fileDetail);
		
		
		String[] fileArr = boardVo.getFileNum();
		log.info("fileArr ::: " + fileArr);
		
		String[] fileNum;
		Map<String, Object> mapData;
		
		for(int i=0; i<fileArr.length; i++) {
			log.info("fileArr ::: " + fileArr[i]);
			//배열데이터를 맵에 넣어보기 mapData.put(boardNum, fileArr);
		}
		
		
//		for(int i=0; i<fileDetail.size(); i++) {
//			String fileNum = "FILE_NUM=" + fileDetail.get(i).get("FILE_NUM");
//			log.info("fileNum ::: " + fileNum);
//		}		
		
		
	}
	
}
