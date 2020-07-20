package sec03.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/sess")
public class SessionTest extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out= resp.getWriter();
		HttpSession session = req.getSession();
		out.print("세션 아이디: "+session.getId()+"<br>");
		out.print("최초 세션 생성 시각: "+new Date(session.getCreationTime())+"<br>");
		out.print("최근 세션 생성 시각: "+new Date(session.getLastAccessedTime())+"<br>");
		
		out.print("기본 세션 유효시간: "+session.getMaxInactiveInterval()+"<br>");
		session.setMaxInactiveInterval(5);
		out.print("세션 유효 시간: "+session.getMaxInactiveInterval()+"<br>");
		if(session.isNew()) {
			out.print("새 세션이 만들어 졌습니다.");
		}
	}

}
