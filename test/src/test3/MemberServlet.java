package test3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member")
public class MemberServlet extends HttpServlet { 
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
                                                    throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
	  response.setContentType("text/html;charset=utf-8");
      PrintWriter out=response.getWriter();	
      MemberDAO dao=new MemberDAO();
      
      String pname = request.getParameter("pname");
      List<MemberVO> list=dao.listMembers(pname); 
    		
          out.print("<html><body>");
          out.print("<table  border=1><tr align='center' bgcolor='lightgreen'>");
          out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td></tr>");
 
         for (int i=0; i<list.size();i++){
    		MemberVO memberVO=(MemberVO) list.get(i);
    		String id=memberVO.getId();
    		String pwd = memberVO.getPwd();
    		String name=memberVO.getName();
    		String email=memberVO.getEmail();
    		Date joinDate = memberVO.getJoinDate();
    		out.print("<tr><td>"+id+"</td><td>"
					+pwd+"</td><td>"+name+"</td><td>"
					+email+"</td><td>"+joinDate+"</td><td>"
					+"<a href='/test/member?command=delMember&id=" +id+ "'>삭제 </a></td></tr>"
    				);		
          }
         
         String command = request.getParameter("command");
         if("delMember".equals(command)) {
       	  String pid =request.getParameter("id");
       	  dao.delMember(pid);
         }
         out.print("</table></body></html>");
         out.print("<a href='/test/SearchMember.html'>돌아가기</a>");
    	  
     }
}
