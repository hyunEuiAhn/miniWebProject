package member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.bean.ZipcodeDTO;
import member.dao.MemberDAO;
import member.dao.MemberDAO1;

public class CheckPostAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String sido = request.getParameter("sido");
		String sigungu = request.getParameter("sigungu");
		String roadname = request.getParameter("roadname");

		System.out.println(sido+","+sigungu+","+roadname);
		//첫번 째 널일 때 값을 안 불러옴
		List<ZipcodeDTO> list = null;
		MemberDAO1 memberDAO1 = MemberDAO1.getInstance();

		if(sido!=null && roadname!=null){
			if(sido!="" && roadname!=null){
				list = memberDAO1.getZipcodeList(sido,sigungu,roadname);
			}
		}
		request.setAttribute("list",list);
		
		return "/member/checkPost.jsp";
	}

}
