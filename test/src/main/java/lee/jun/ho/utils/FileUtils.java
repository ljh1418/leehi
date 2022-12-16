package lee.jun.ho.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lee.jun.ho.board.vo.BoardVo;
import lombok.extern.slf4j.Slf4j;


//@Component FileUtils 를 스캔하도록 도와주는 어노테이션
//어노테이션이 있어야 다른 클래스에서 사용 가능
@Component
@Slf4j
public class FileUtils {

	//로컬저장
	private static final String localFilePath = "C:/mp/file/"; // 파일이 저장될 위치
	
	//깃 프로젝트 위치
	private static final String filePath = "C:/Users/user/git/leehi/test/src/main/webapp/resources/upload/";
	
	public List<Map<String, Object>> parseInsertFileInfo(BoardVo boardVo, MultipartHttpServletRequest mpRequest) throws IllegalStateException, IOException{
		
		log.info("FileUtils parseInsertFileInfo ::: " + boardVo);
		
		Iterator<String> iterator = mpRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String fileOrgName = null;
		String fileOrgExtension = null;
		String fileStoredName = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> listMap = null;
		
		//
		String boardNum = boardVo.getBoardNum();
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()){
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				fileOrgName = multipartFile.getOriginalFilename();
				fileOrgExtension = fileOrgName.substring(fileOrgName.lastIndexOf("."));
				fileStoredName = getRandomString() + fileOrgExtension;
				
				file = new File(filePath + fileStoredName);
				multipartFile.transferTo(file);
				listMap = new HashMap<String, Object>();
				listMap.put("fileBoardNum", boardNum);
				listMap.put("fileOrgName", fileOrgName);
				listMap.put("fileStoredName", fileStoredName);
				listMap.put("fileSize", multipartFile.getSize());
				list.add(listMap);
			}
		}
		return list;
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public List<Map<String, Object>> parseInsertFileInfo2(BoardVo boardVo, List<MultipartFile> uploadFile) throws Exception{
	      
	      /*
	         Iterator은 데이터들의 집합체? 에서 컬렉션으로부터 정보를 얻어올 수 있는 인터페이스입니다.
	         List나 배열은 순차적으로 데이터의 접근이 가능하지만, Map등의 클래스들은 순차적으로 접근할 수가 없습니다.
	         Iterator을 이용하여 Map에 있는 데이터들을 while문을 이용하여 순차적으로 접근합니다.
	      */
	      
	      log.info("parseInsertFileInfo2 >boardVo >>> :" + boardVo);
	      log.info("parseInsertFileInfo2 uploadFile >>>> :" + uploadFile);
	      
	      String originalFileName = null;
	      String originalFileExtension = null;
	      String storedFileName = null;
	      
	      List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	      
	      Map<String, Object> listMap = null;
	      
	      //String boardNum = boardVo.getBoardNum();
	      //String fileNum = boardVo.getFileNum();
	      
	      File file = new File(filePath);
	      if(file.exists() == false) {
	         file.mkdirs();
	      }
	      
	      //String fileSeq = boardDao.fileSeq();
	      
	      
	      //String fileSeq2 = boardDao.fileSeq();
	      //int fileSeqint = Integer.valueOf(fileSeq2);


	      
	      for(MultipartFile multipartFile1 : uploadFile) {
	         if (!multipartFile1.getOriginalFilename().isEmpty()) {
	         log.info("while!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	         //
	            
	         //multipartFile = mpRequest.getFile("uploadFile");
	         //log.info("multipartFile >>> :" + multipartFile);
	         //if(multipartFile.isEmpty() == false) {
	            
	            originalFileName = multipartFile1.getOriginalFilename();
	            originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
	            storedFileName = getRandomString() + originalFileExtension;
	            
	            file = new File(filePath + storedFileName);
	            multipartFile1.transferTo(file);
	            
	            //listMap에 담음
	            listMap = new HashMap<String, Object>();
	            listMap.put("fileBoardNum", boardVo.getBoardNum());
	            //listMap.put("FILE_NUM", fileSeqint);
	            listMap.put("fileOrgName", originalFileName);
	            listMap.put("fileStoredName", storedFileName);
	            listMap.put("fileSize", multipartFile1.getSize());
	            
	            //list에 담음
	            list.add(listMap);
	            
	            //fileSeqint++;
	         }else {
	            System.out.println("파일없음...");
	         }
	            
	         //}
	      }
	      
	      System.out.println("list >>> : " +list);
	      
	      return list;
	   }
	
}
