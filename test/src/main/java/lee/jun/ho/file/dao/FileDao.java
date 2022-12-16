package lee.jun.ho.file.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
public class FileDao {
	
	@Autowired
	SqlSessionTemplate sql;
	
	public Map<String, Object> selectFile(Map<String, Object> fileNum) {
		log.info("FileDao selectFile ::: " + fileNum);
		return sql.selectOne("file.selectFile", fileNum);
	}

	public String selectFile(String fileNum) {
		log.info("FileDao selectFile ::: " + fileNum);
		return sql.selectOne("file.selectFile", fileNum);
	}


}
