package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;
import member.dao.MemberDAO1;

public class ModifyFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");

		MemberDAO1 memberDAO1 = MemberDAO1.getInstance();
		MemberDTO memberDTO = memberDAO1.getDTO(id);
		
		request.setAttribute("memberDTO", memberDTO);
		request.setAttribute("display", "/member/modifyForm.jsp");
		return "/main/index.jsp";
	}
}
