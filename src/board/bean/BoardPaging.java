package board.bean;

import lombok.Data;

@Data

public class BoardPaging {
	private int currentPage;//현재 페이지
	private int pageBlock;//[이전][1][2][3][다음]
	private int pageSize;//1페이지 당 5개
	private int totalA;//총글 수
	private StringBuffer pagingHTML;
	
	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		int totalP =(totalA+pageSize-1)/pageSize;//총 페이지 수
		
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		
		int endPage = startPage+pageBlock-1;
		if(endPage>totalP) endPage = totalP;
		
		if(startPage > pageBlock)
			pagingHTML.append("[<a id='paging' href='boardList.do?pg="+(startPage-1)+"'>이전</a>]");
		for(int i =startPage; i<=endPage;i++) {
			if(currentPage==i)
				pagingHTML.append("[<a id='currentPaging' href='boardList.do?pg="+i+"'>"+i+"</a>]");
			else
				pagingHTML.append("[<a id='paging' href='boardList.do?pg="+i+"'>"+i+"</a>]");
		}
		if(totalP > endPage) {
			pagingHTML.append("[<a id='paging' href='boardList.do?pg="+(endPage+1)+"'>다음</a>]");
		}
	
	}
}
