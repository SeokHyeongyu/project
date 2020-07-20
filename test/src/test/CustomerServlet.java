package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out= resp.getWriter();
		
		String pid =req.getParameter("pid");
		
		CustomerDAO dao=new CustomerDAO();
		List<CustomerVO> list=dao.listCustomers(pid);
		
		  out.print("<html><body>");
	      out.print("<table  border=1><tr align='center' bgcolor='lightgreen'>");
	      out.print("<td>고객아이디</td><td>고객이름</td><td>고객주소</td><td>주문조회</td><td>삭제</td></tr>");
	     
	      for (int i=0; i<list.size();i++){
	    	  CustomerVO customerVO=(CustomerVO) list.get(i);
	  		String cust_id=customerVO.getCust_id();
	  		String cust_name = customerVO.getCust_name();
	  		String cust_address=customerVO.getCust_address();
	  		
	  		out.print("<tr><td>"+cust_id+"</td><td>"+
	  					cust_name+"</td><td>"+
	  					cust_address+"</td></tr>");		
	        }
	        out.print("</table></body></html>");
	}
	
}
