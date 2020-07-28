package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class BoardModifyAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		String pg = (String)session.getAttribute("memPg");
		String seq = request.getParameter("seq");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		BoardDTO boardDTO=null;
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDTO = boardDAO.selectList(Integer.parseInt(seq));
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		boardDAO.boardUpdate(boardDTO, Integer.parseInt(seq));
		
		request.setAttribute("display", "/board/boardModify.jsp");
		return "/main/index.jsp";
	}

}
