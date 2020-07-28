package board.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

public class BoardViewAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		BoardDAO boardDAO = new BoardDAO();
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		String id = (String)session.getAttribute("memId");
		String pg = (String)session.getAttribute("memPg");


		BoardDTO boardDTO = boardDAO.selectList(seq);
		String writeId = boardDTO.getId();
		
		
		//조회수 새로고침 방지 30분 동안 쿠키 살아있기
		boolean today = false;
		Cookie[] ar =request.getCookies();
		
		if(ar!=null) {
			for(int i=0; i<ar.length; i++) {
				if(ar[i].getName().equals(id+seq)) {
					today=true;
				}
			}//for
			if(!today) {
				boardDAO.hitUpdate(boardDTO, seq);
				
				Cookie cookie = new Cookie(id+seq,seq+"");//생성
				cookie.setMaxAge(15);	//초단위 -3초 후에 없어진다.
				response.addCookie(cookie);	//클라이언트에게 보내기
			}
		}
		
		
		request.setAttribute("seq", seq);
		request.setAttribute("pg", pg);
		request.setAttribute("id", id);
		request.setAttribute("writeId", writeId);
		request.setAttribute("boardDTO", boardDTO);
		
		
		request.setAttribute("display","/board/boardView.jsp");
		return "/main/index.jsp";
	}

}
