package member.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.dao.MemberDAO;
import member.dao.MemberDAO1;

public class LoginAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		request.setCharacterEncoding("UTF-8");
		String id =request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//DB
		MemberDAO1 memberDAO1 = MemberDAO1.getInstance();
		Map<String,String> map = memberDAO1.login(id, pwd);
		
		//응답
		if(map==null) {
			request.setAttribute("display", "/member/loginFail.jsp");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("memName", map.get("name"));
			session.setAttribute("memId", id);
			session.setAttribute("memEmail", map.get("email1")+"@"+map.get("email2"));
		}
		String loginResult = "fail";
		request.setAttribute("loginResult", loginResult);
		request.setAttribute("display", "/template/body.jsp");
		return "/main/index.jsp";
	}

}
