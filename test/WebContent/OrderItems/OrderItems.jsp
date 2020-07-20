<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="orderItems1.*"
    %>
<!DOCTYPE html>
<html>
<head>
	<style>
	h1 {
	text-align: center;
	}
	</style>
<meta charset="UTF-8">
<title>조회</title>
</head>
<body>
	<h1>정보 조회</h1>
		<%request.setCharacterEncoding("utf-8");
		String p_num=request.getParameter("order_num");
		OrderItemsVO orderItemsVO =new OrderItemsVO ();
		orderItemsVO.setOrder_num(p_num);
		OrderItemsDAO dao = new OrderItemsDAO();
		List orderList = dao.orderList(orderItemsVO);
		%>
	
	<table border=1 width=800 align=center>
		<tr align=center bgcolor="FFFF66">
		<td>주문번호</td>
		<td>주문항목번호</td>
		<td>제품id</td>
		<td>황목 수량</td>
		<td>항목가격</td>
		<td>수정</td>
		<td>삭제</td>
		</tr>
	<%for (int i=0; i<orderList.size(); i++) {
		OrderItemsVO vo = (OrderItemsVO) orderList.get(i);
		String  orderNum=vo.getOrder_num();
		String orderItem=vo.getOrder_item();
		String prodId=vo.getProd_id();
		String quantity=vo.getQuantity();
		String itemPrice=vo.getItem_price();
		%>
		<tr align=center>
		<td><%= orderNum %></td>
		<td><%= orderItem %></td>
		<td><%= prodId %></td>
		<td><%= quantity %></td>
		<td><%= itemPrice %></td>
		<td>수정</td>
		<td><a href="/test/OrderItems/OrderItems.jsp?command=delMember&order_num=<%= orderNum %>">삭제 </a>></td>
		</tr>
	<%
	}
	%>
	</table>
</body>
</html>