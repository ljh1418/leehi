package lee.jun.ho.file.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lee.jun.ho.file.dao.FileDao;
import lee.jun.ho.file.service.FileService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileServiceImpl implements FileService{
	
	@Resource
	FileDao fileDao;
	
	@Override
	public Map<String, Object> selectFile(Map<String, Object> fileNum) {
		log.info("fileServiceImpl selectFile Map ::: " + fileNum);
		return fileDao.selectFile(fileNum);
	}

	@Override
	public String selectFile(String fileNum) throws Exception {
		log.info("fileServiceImpl selectFile String ::: " + fileNum);
		return fileDao.selectFile(fileNum);
	}

}
