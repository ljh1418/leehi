package lee.jun.ho.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class PageMaker{

	 private int totalCount;
	 private int startPage;
	 private int endPage;
	 private boolean prev;
	 private boolean next;

	 //페이지 갯수 기정 ex)1 2 3 4 5 , ex)1 2 3 4 5 6 7 8 9 10
	 private int displayPageNum = 5;

	 private Criteria cri;
	 
	 public void setCri(Criteria cri) {
	  this.cri = cri;
	 }

	 public void setTotalCount(int totalCount) {
	  this.totalCount = totalCount;
	  calcData();
	 }

	 public int getTotalCount() {
	  return totalCount;
	 }

	 public int getStartPage() {
	  return startPage;
	 }

	 public int getEndPage() {
	  return endPage;
	 }

	 public boolean isPrev() {
	  return prev;
	 }

	 public boolean isNext() {
	  return next;
	 }

	 public int getDisplayPageNum() {
	  return displayPageNum;
	 }

	 public Criteria getCri() {
	  return cri;
	 }
	 
	 //페이징 계산
	 private void calcData() {
	  endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
	  startPage = (endPage - displayPageNum) + 1;
	  
	  int tempEndPage = (int) (Math.ceil(totalCount / (double)cri.getPerPageNum()));
	  if (endPage > tempEndPage)
	  {
	   endPage = tempEndPage;
	  }
	  prev = startPage == 1 ? false : true;
	  next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	 }
	 
	}