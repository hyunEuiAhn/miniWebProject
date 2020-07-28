package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

public class BoardReplyFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String pg = request.getParameter("pg");
		String pseq = request.getParameter("pseq");
		
		request.setAttribute("pg", pg);
		request.setAttribute("pseq", pseq);
		request.setAttribute("display", "/board/boardReplyForm.jsp");
		return "/main/index.jsp";
	}

}
