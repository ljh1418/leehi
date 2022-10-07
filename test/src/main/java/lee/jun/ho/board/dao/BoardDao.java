package lee.jun.ho.board.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSessionTemplate sql;
	
	
	public void txInsert() {
		sql.insert("board.insert");
	}

}
