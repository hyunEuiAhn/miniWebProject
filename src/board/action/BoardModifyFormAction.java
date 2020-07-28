package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

public class BoardModifyFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String pg = request.getParameter("pg");
		String seq = request.getParameter("seq");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		request.setAttribute("pg", pg);
		request.setAttribute("seq", seq);
		request.setAttribute("subject", subject);
		request.setAttribute("content", content);
		
		request.setAttribute("display", "/board/boardModifyForm.jsp");
		return "/main/index.jsp";
	}

}
