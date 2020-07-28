package imageboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import imageboard.dao.ImageBoardDAO;


public class ImageboardDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//data
		String[] seq = request.getParameterValues("checkb");
		System.out.println(seq[0]);
		//DB
		ImageBoardDAO imageboardDAO = ImageBoardDAO.getInstance();
		imageboardDAO.imageboardDelete(seq);
		
		//응답
		request.setAttribute("display","/imageboard/imageboardDelete.jsp");
		return "/main/index.jsp";
	}

}
