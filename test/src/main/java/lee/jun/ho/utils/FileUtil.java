package lee.jun.ho.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lee.jun.ho.board.vo.BoardVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {
	
	
	
	public static List<Map<String, Object>> parseFileInfo(BoardVo boardVo, MultipartFile[] file) throws Exception{
		
		String uploadPath = "C:/Users/user/git/leehi/test/src/main/webapp/resources/upload/";
		
		String boardIDX = boardVo.getBoardNum();
		log.info("boardIDX ::: " + boardIDX);
	        
        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
 
        File target = new File(uploadPath);
        if(!target.exists()) target.mkdirs();
        
        for(int i=0; i<file.length; i++) {
	 
        String orgFileName = file[i].getOriginalFilename();
        String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
        String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + orgFileExtension;
        Long saveFileSize = file[i].getSize();
        
        log.info("================== file start ==================");
        log.info("파일 실제 이름: "+orgFileName);
        log.info("파일 저장 이름: "+saveFileName);
        log.info("파일 크기: "+saveFileSize);
        log.info("content type: "+file[i].getContentType());
        log.info("================== file   END ==================");
        
        target = new File(uploadPath, saveFileName);
        file[i].transferTo(target);
        
        Map<String, Object> fileInfo = new HashMap<String, Object>();
        fileInfo.put("fileBoardNum", boardIDX);
        fileInfo.put("fileOrgName", orgFileName);
        fileInfo.put("fileStoredName", saveFileName);
        fileInfo.put("fileSize", saveFileSize);
        fileList.add(fileInfo);
        
    }
        return fileList;
}

	
}
