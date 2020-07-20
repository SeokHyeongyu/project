package test2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public ProductDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext= (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//추가 메소드
	public void addProduct(ProductVO vo) {
		try {
			con=dataFactory.getConnection();
			String id = vo.getProd_id();
			String name = vo.getProd_name();
			String price = vo.getProd_price();
			String desc = vo.getProd_desc();
			String v_id = vo.getVend_id();
			
			String query = "insert into products";
			query +=" (Prod_id,Prod_name,Prod_price,Prod_desc,Vend_id)";
			query +=" values(?,?,?,?,?)";
			System.out.println("PreparedStatement: "+query);
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, price);
			pstmt.setString(4, desc);
			pstmt.setString(4, v_id);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	// 조회
	public List<ProductVO> listProducts(String pid) {
		List <ProductVO> list = new ArrayList<ProductVO>();
		try {
			con = dataFactory.getConnection();
			String query ="select * from products ";
			query+="WHERE prod_id like '%"+pid+"%'";
			System.out.println("prepareStatement: "+ query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String prod_id=rs.getString("prod_id");
				String prod_name =rs.getString("prod_name");
				String prod_price=rs.getString("prod_price");
				String prod_desc=rs.getString("prod_desc");
				String vend_id=rs.getString("vend_id");
				System.out.println("test:");
				ProductVO vo = new ProductVO();
				vo.setProd_id(prod_id);
				vo.setProd_name(prod_name);
				vo.setProd_price(prod_price);
				vo.setProd_desc(prod_desc);
				vo.setVend_id(vend_id);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//삭제 메소드
	public void delProducts(String prod_id) { 
		// TODO Auto-generated method stub
		
	}
	
}
