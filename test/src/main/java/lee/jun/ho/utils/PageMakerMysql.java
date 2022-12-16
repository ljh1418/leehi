package lee.jun.ho.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class PageMakerMysql {

	private CriteriaMySQL cri;
    private int totalCount; // 총 게시글 수
    private int startPage; // 화면에 보여질 첫번째 페이지 번호
    private int endPage; // 화면에 보여질 마지막 페이지번호
    private boolean prev; // 이전 버튼 생성 여부
    private boolean next; // 다음 버튼 생성 여부
    private int displayPageNum = 5; // 화면 하단에 보여지는 페이지 버튼의 수
    
    public CriteriaMySQL getCri() {
        return cri;
    }
    public void setCri(CriteriaMySQL cri) {
    	log.info("setCri cri ::: " + cri);
        this.cri = cri;
    }
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        // 총 게시글 수를 셋팅할때 calcData() 메서드를 호출하여 페이징 관련 버튼 계산을 한다.
        calcData();
    }
    
    //페이징의 버튼들을 생성하는 계산식을 만들었다. 끝 페이지 번호, 시작 페이지 번호, 이전, 다음 버튼들을 구한다.
    private void calcData() {
        
    	// 끝 페이지 번호 = (현재 페이지 번호 / 화면에 보여질 페이지 번호의 갯수) * 화면에 보여질 페이지 번호의 갯수
        endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
        
        // 시작 페이지 번호 = (끝 페이지 번호 - 화면에 보여질 페이지 번호의 갯수) + 
        startPage = (endPage - displayPageNum) + 1;
        if(startPage <= 0) startPage = 1;
        
        // 마지막 페이지 번호 = 총 게시글 수 / 한 페이지당 보여줄 게시글의 갯수
        int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
        
        // A ? B : C ; 라고 생각했을 때 A가 참이면 B를 리턴, 거짓이면 C를 리턴한다. B C 순서 변경가능
        
        //이전버튼 생성여부
        prev = startPage == 1 ? false : true;
        
        //다음버튼 생성여부
        next = endPage * cri.getPerPageNum() < totalCount ? true : false;
        
    }
    
    public int getStartPage() {
        return startPage;
    }
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    public int getEndPage() {
        return endPage;
    }
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
    public boolean isPrev() {
        return prev;
    }
    public void setPrev(boolean prev) {
        this.prev = prev;
    }
    public boolean isNext() {
        return next;
    }
    public void setNext(boolean next) {
        this.next = next;
    }
    public int getDisplayPageNum() {
        return displayPageNum;
    }
    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }
	
}
