package lee.jun.ho.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSession sql;
	
	public void txInsert() {
		sql.insert("board.insert");
	}

}
