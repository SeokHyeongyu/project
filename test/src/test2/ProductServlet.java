package test2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet ("/products")
public class ProductServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req,resp);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out= response.getWriter();
		ProductDAO dao = new ProductDAO();
	
		String prod_id = request.getParameter("prod_id"); //조회			
		List<ProductVO> list =dao.listProducts(prod_id);
		System.out.println(list.toString());
		out.print("<html><body>");
		out.print("<table border = 1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>제품번호</td><td>제품명</td><td>제품가격</td><td>제품설명</td><td>공급업체 번호</td><td>삭제</td></tr>");
		
		for(int i=0; i<list.size();i++) {
			ProductVO productVO=(ProductVO) list.get(i);
			String P_id=productVO.getProd_id();
			String p_name =productVO.getProd_name();
			String P_price=productVO.getProd_price();
			String P_desc=productVO.getProd_desc();
			String v_id = productVO.getVend_id();
			out.print("<tr><td>"+P_id+"</td><td>"
					+p_name+"</td><td>"+P_price+"</td><td>"
					+P_desc+"</td><td>"+v_id+"</td><td>"
					+"<a href='/test/products?command=delMember&id=" +P_id+ "'>삭제 </a></td></tr>");
		}
		
		String command = request.getParameter("command"); 
		System.out.println(command);
		if("addProduct".equals(command)) {
			String P_id=request.getParameter("prod_id");
			String p_name =request.getParameter("prod_name");
			String P_price=request.getParameter("prod_price");
			String P_desc=request.getParameter("prod_desc");
			String v_id = request.getParameter("vend_id");
			ProductVO vo =new ProductVO();
			vo.setProd_id(P_id);
			vo.setProd_name(p_name);
			vo.setProd_price(P_price);
			vo.setProd_desc(P_desc);
			vo.setVend_id(v_id);
			dao.addProduct(vo);
		}else if("delMember".equals(command)) {
			String pid =request.getParameter("prod_id");
			dao.delProducts(pid);
		}
		out.print("</table></body></html>");
		out.print("<a href='/test/product.html'>돌아가기</a>");
	}
}
