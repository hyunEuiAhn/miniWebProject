package imageboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import imageboard.bean.ImageBoardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.dao.ImageBoardDAO;

public class ImageboardListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		//DB
		ImageBoardDAO imageboardDAO = ImageBoardDAO.getInstance();
		
		int endNum = pg*3;
		int startNum = endNum-2;
		List<ImageBoardDTO> list =
				imageboardDAO.imageboardList(startNum,endNum);
		
		//페이징 처리
		int totalA = imageboardDAO.getTotalA();
		
		ImageboardPaging imageboardPaging = new ImageboardPaging();
		imageboardPaging.setCurrentPage(pg);
		imageboardPaging.setPageBlock(3);
		imageboardPaging.setPageSize(3);
		imageboardPaging.setTotalA(totalA);
		
		imageboardPaging.makePagingHTML();

		//응답
		request.setAttribute("list", list);
		request.setAttribute("imageboardPaging", imageboardPaging);
		request.setAttribute("pg",pg);
		request.setAttribute("display", "/imageboard/imageboardList.jsp");
		return "/main/index.jsp";
	}

}
