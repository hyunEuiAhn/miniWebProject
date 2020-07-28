package board.action;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

public class BoardListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int pg = Integer.parseInt(request.getParameter("pg"));
		int endPg=pg*5;
		int startPg=endPg-4;
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		ArrayList<BoardDTO> list = boardDAO.boardList(startPg, endPg);

		request.setAttribute("list", list);
		int totalA = boardDAO.getTotal();

		BoardPaging boardPaging = new BoardPaging();
		boardPaging.setCurrentPage(pg);
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);

		boardPaging.makePagingHTML();

		request.setAttribute("boardPaging", boardPaging);
		HttpSession session = request.getSession();
		session.setAttribute("memPg", request.getParameter("pg")); 
		
		request.setAttribute("display", "/board/boardList.jsp");
		return "/main/index.jsp";
	}

}
