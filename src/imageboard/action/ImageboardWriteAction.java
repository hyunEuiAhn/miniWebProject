package imageboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;
import com.oreilly.servlet.MultipartRequest;

import imageboard.bean.ImageBoardDTO;
import imageboard.dao.ImageBoardDAO;

public class ImageboardWriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//실제폴더
		String realFolder = request.getServletContext().getRealPath("/storage");
		System.out.println("실제폴더:"+realFolder);
		//업로드
		MultipartRequest multi = new MultipartRequest(request
													 ,realFolder
													 ,5*1024*1024
													 ,"UTF-8");
		//데이터
		String imageId=multi.getParameter("imageId");
		String imageName=multi.getParameter("imageName");
		int imagePrice=Integer.parseInt(multi.getParameter("imagePrice"));
		int imageQty=Integer.parseInt(multi.getParameter("imageQty"));
		String imageContent=multi.getParameter("imageContent");
		String image1=multi.getOriginalFileName("image1");
		
		
		ImageBoardDTO imageboardDTO = new ImageBoardDTO();
		imageboardDTO.setImageId(imageId);
		imageboardDTO.setImageName(imageName);
		imageboardDTO.setImagePrice(imagePrice);
		imageboardDTO.setImageQty(imageQty);
		imageboardDTO.setImageContent(imageContent);
		imageboardDTO.setImage1(image1);


		//DB
		ImageBoardDAO imageboardDAO = ImageBoardDAO.getInstance();
		imageboardDAO.imageboardWrite(imageboardDTO);
		
		//응답
		request.setAttribute("display", "/imageboard/imageboardWrite.jsp");
		return "/main/index.jsp";
	}

}
