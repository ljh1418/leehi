package lee.jun.ho.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Criteria{
	private int page;
	private int perPageNum;
	private int rowStart;
	private int rowEnd;
 
	public Criteria(){
		log.info("Criteria ::: ");
		
		//페이지 번호
		this.page = 1;
		
		//한페이지 당 개시글
		this.perPageNum = 10;
	 }
	
	public void setPage(int page){
		log.info("setPage ::: ");
		if (page <= 0)
		{
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum){
	  if (perPageNum <= 0 || perPageNum > 100)
	  {
	   this.perPageNum = 10;
	   return;
	  }
	  this.perPageNum = perPageNum;
	 }
	
	public int getPage(){
	  return page;
	 }
	
	public int getPageStart(){
	  return (this.page - 1) * perPageNum;
	 }
	
	public int getPerPageNum(){
	  return this.perPageNum;
	 }
	
	public int getRowStart() {
	 rowStart = ((page - 1) * perPageNum) + 1;
	 return rowStart;
	}
	
	public int getRowEnd() {
	  rowEnd = rowStart + perPageNum - 1;
	  return rowEnd;
	}
}