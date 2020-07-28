package imageboard.bean;

import lombok.Data;

@Data

public class ImageboardPaging {
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
			pagingHTML.append("[<span id='paging' onclick='imageboardPaging("+(startPage-1)+")'>이전</span>]");
		
		
		
		
		for(int i =startPage; i<=endPage;i++) {
			if(currentPage==i)
				pagingHTML.append("[<span id='currentPaging' onclick='imageboardPaging("+i+")'>"+i+"</span>]");
			else
				pagingHTML.append("[<span id='paging' onclick='imageboardPaging("+i+")'>"+i+"</span>]");
		}
		if(totalP > endPage) {
			pagingHTML.append("[<span id='paging' onclick='imageboardPaging(="+(endPage+1)+")'>다음</span>]");
		}
		
	
	}
}
