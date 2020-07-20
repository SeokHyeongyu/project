package orderItems1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OrderItemsDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public OrderItemsDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext= (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<OrderItemsVO> orderList(OrderItemsVO ordervo){
		List list= new ArrayList();
		String p_num=ordervo.getOrder_num();
		System.out.println(p_num);//주문번호 잘 들어왔는지 확인용
		try {
			con=dataFactory.getConnection();
			String query="select * from OrderItems ";
			if(p_num!=null || p_num.length()!=0) { // 입력받은 이름이 널이 아니면 쿼리문 추가 전송
				query+="where order_num like '%"+p_num+"%'";
				pstmt=con.prepareStatement(query);
				System.out.println("prepareStatement 1:"+query); //회원 조회 쿼리문 확인 1
			}else {
				pstmt=con.prepareStatement(query); //입력받은 이름이 널이면 전체 조회 쿼리문 전송
			}
			System.out.println("prepareStatement 2:" +query);// 전체출력 쿼리문 확인 2
			ResultSet rs = pstmt.executeQuery(); // 데이터 베이스에서 받은 정보 ResultSet으로 옴겨 담음 (boolean)
			while (rs.next()) { //re가 참이면 whille문 실행, 데이터 베이스에메서 받은 정도 하나하나 옴긴 후 vo에 담음
				String  orderNum=rs.getString("order_num");
				String orderItem=rs.getString("order_item");
				String prodId=rs.getString("prod_id");
				String quantity=rs.getString("quantity");
				String itemPrice= rs.getString("item_price");
				
				OrderItemsVO vo = new OrderItemsVO();
				vo.setOrder_num(orderNum);
				vo.setOrder_item(orderItem);
				vo.setProd_id(prodId);
				vo.setQuantity(quantity);
				vo.setItem_price(itemPrice);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
