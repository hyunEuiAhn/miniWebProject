package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;
import member.dao.MemberDAO1;

public class CheckIdAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = request.getParameter("id");
		
		MemberDAO1 memberDAO1 = MemberDAO1.getInstance();
		MemberDTO memberDTO = memberDAO1.getDTO(id);
		
		request.setAttribute("id", id);
		if(memberDTO.getId()==null) {
			return "/member/checkIdOk.jsp?id="+id;
		}
		else return "/member/checkIdFail.jsp?id="+id;
	}

}
