package test4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet ("/vendors")
public class VendorsServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	
	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		VendorsVO vendorsVO = new VendorsVO();
		VendorsDAO dao = new VendorsDAO();
		
		String vend_id = req.getParameter("vend_id");
		vendorsVO.setVend_id(vend_id);
		List<VendorsVO> list =dao.listVendors(vendorsVO);
		
		out.print("<html><body><table  border=1><tr align='center' bgcolor='lightgreen'>"
		 		+ "<td>공급업체번호</td><td>공급업체이름</td><td>공급업체주소</td><td>공급업체시</td><td>공급업체주</td><td>공급업체우편번호</td><td>공급업체국가</td>"
		 		+ "<td>수정</td><td>삭제</td></tr>");
		
		for(int i=0; i<list.size(); i++) {
			VendorsVO vo=(VendorsVO)list.get(i);
			String v_id = vo.getVend_id();
			String v_name = vo.getVend_name();
			String v_address = vo.getVend_address();
			String v_city = vo.getVend_city();
			String v_state = vo.getVend_state();
			String v_zip = vo.getVend_zip();
			String v_country = vo.getVend_country();
			out.print("<tr><td>"+v_id+"</td><td>"+v_name+"</td><td>"+v_address+"</td><td>"+v_city+"</td><td>"+v_state+""
			 		+ "</td><td>"+v_zip+"</td><td>"+v_country+"</td><td>"
			 		+ "<a href'/test/vendors??command=modifiedrVendors&vend_id="+v_id+"' >수정 </a></td><td>"
			 		+ "<a href='/test/vendors?command=delVendors&vend_id=" +v_id+"'>삭제 </a></td></tr>");
		}
		String command = req.getParameter("command");
		if("modifiedrVendors".equals(command)) {
			
//			String v_id=req.getParameter("vend_id");
//			String v_name = req.getParameter("vend_name");
//			String v_address = req.getParameter("vend_address");
//			String v_city = req.getParameter("vend_city");
//			String v_state = req.getParameter("vend_state");
//			String v_zip = req.getParameter("vend_zip");
//			String v_country =req.getParameter("vend_country");
//			dao.modifiedrVendors(v_id);
		}else if ("delVendors".equals(command)) {
			String v_id=req.getParameter("vend_id");
			dao.delVendors(v_id);
		}
		  out.print("</table></body></html><a href='/test/vendors.html'>돌아가기</a>");
	}
}
