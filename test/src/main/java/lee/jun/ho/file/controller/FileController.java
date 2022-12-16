package lee.jun.ho.file.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lee.jun.ho.file.service.FileService;
import lee.jun.ho.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FileController {

	@Autowired
	FileService fileService;
	
	//@RequestParam 적어줘야지 url 데이터 가져옴
	@GetMapping("/board/fileDownload.do")
	public void downloadFile(@RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
	      
		String uploadPath = "C:/Users/user/git/leehi/test/src/main/webapp/resources/upload/";
		
		log.info("map ::: " + map);
		
		//log.info("fileDownload map ::: " + map.get("FILE_NUM"));
		
		//String fileNum = (String) map.get("FILE_NUM");
		
		//String fileNum = request.getParameter("FILE_NUM");
		//log.info("fileNum ::: " + fileNum);
		
		//String selectFile = fileService.selectFile(fileNum);
		//log.info("selectFile ::: " + selectFile);
		
		Map<String, Object> file = fileService.selectFile(map);
		log.info("file ::: " + file);
		
		String fileStoredName = (String)file.get("FILE_STORED_NAME");
        String fileOrgName = (String)file.get("FILE_ORG_NAME");
        String fileSize = (String)file.get("FILE_SIZE");
        
        File downloadFile = new File(uploadPath + fileStoredName);
        log.info("downloadFile ::: " + downloadFile);
        
        //파일을 byte 배열로 변환한다.
        byte fileByte[] = FileUtils.readFileToByteArray(downloadFile);
        log.info("fileByte ::: " + fileByte);
        
        //application/octet-stream" 은 자바에서 사용하는 파일 다운로드 응답 형식으로, 어플리케이션 파일이 리턴된다고 설정한다.
        response.setContentType("application/octet-stream");
        //파일사이즈
        response.setContentLength(fileByte.length);
        //attachment;fileName="을 사용하면 다운로드시 파일 이름을 지정해줄 수 있다.
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileOrgName,"UTF-8") +"\";");
        //application/octet-stream"은 binary 데이터이기 때문에 binary로 인코딩해준다.
        response.setHeader("Content-Transfer-Encoding", "binary");
        //버퍼에 파일을 담아 스트림으로 출력한다.
        response.getOutputStream().write(fileByte);
        //버퍼에 저장된 내용을 클라이언트로 전송하고 버퍼를 비운다.
        response.getOutputStream().flush();
        //출력 스트림을 종료한다. 참고로 close() 함수 자체에서 flush() 함수를 호출하기 때문에 굳이 flush() 를 호출하지 않아도 된다.
        response.getOutputStream().close();
	        
	}
}
