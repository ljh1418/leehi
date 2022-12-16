package lee.jun.ho.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CriteriaMySQL {
	
	private int page;	//현재 페이지 번호
    private int perPageNum;	 //한 페이지당 보여줄 게시글의 갯수
    
    
    //특정 페이지의 게시글 시작 번호, 게시글 시작 행 번호
    public int getPageStart() {
    	log.info("getPageStart (this.page-1)*perPageNum; ::: " + (this.page-1)*perPageNum);
        return (this.page-1)*perPageNum;
    }
    
    public CriteriaMySQL() {
    	log.info("Criteria :::");
        this.page = 1;	//기본페이지 1
        this.perPageNum = 10;
    }
    
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }
    
    public int getPerPageNum() {
        return perPageNum;
    }
    
    public void setPerPageNum(int pageCount) {
        int cnt = this.perPageNum;
        if(pageCount != cnt) {
            this.perPageNum = cnt;
        } else {
            this.perPageNum = pageCount;
        }
    }
    
}
