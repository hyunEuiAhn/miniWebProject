package board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.dao.BoardDAO;

public class BoardWriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();

		String name = (String)session.getAttribute("memName");
		String id =  (String)session.getAttribute("memId");
		String email =  (String)session.getAttribute("memEmail");
		System.out.println(name);
		System.out.println(email);
		System.out.println(id);

		//Map<key, Value> map = new HashMap<>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id);
		map.put("name",name);
		map.put("email",email);
		map.put("subject",subject);
		map.put("content",content);

		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.write(map); 
		
		
		request.setAttribute("display", "/board/boardWrite.jsp");
		return "/main/index.jsp";
	}

}
