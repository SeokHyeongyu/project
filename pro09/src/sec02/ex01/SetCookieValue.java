package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/set")
public class SetCookieValue  extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out= resp.getWriter();
		Date d = new Date();
		Cookie c = new Cookie ("cookieTest",URLEncoder.encode("jsp프로그램입니다.", "utf-8"));
		c.setMaxAge(24*60*60);
		resp.addCookie(c);
		out.print("현재시간: "+d);
		out.print("문자열을 cookie에 저장합니다.");
	}

}
