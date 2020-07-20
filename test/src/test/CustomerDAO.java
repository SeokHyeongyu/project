package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CustomerDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public CustomerDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<CustomerVO> listCustomers(String pid) {
		List<CustomerVO> list = new ArrayList<CustomerVO>();
		try {
			con = dataFactory.getConnection();
			String query = "select * from CUSTOMERS ";
			query+= "where cust_id like '%"+pid+"%'";
			System.out.println("prepareStatement: "+ query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String cust_id = rs.getString("cust_id");
				String cust_name = rs.getString("cust_name");
				String cust_address = rs.getString("cust_address");
				
				CustomerVO vo = new CustomerVO();
				vo.setCust_id(cust_id);
				vo.setCust_name(cust_name);
				vo.setCust_address(cust_address);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}
