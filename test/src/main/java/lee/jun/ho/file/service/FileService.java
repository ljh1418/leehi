package lee.jun.ho.file.service;

import java.util.Map;

public interface FileService {

	public Map<String, Object> selectFile(Map<String, Object> map);

	public String selectFile(String fileNum) throws Exception;

}
