package test4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class VendorsDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public VendorsDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext= (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//조회
	public List<VendorsVO> listVendors(VendorsVO vendorsVO) {
		List<VendorsVO> list=new ArrayList<VendorsVO>();
		String vid=vendorsVO.getVend_id();
		System.out.println(vid);
		try {
			con = dataFactory.getConnection();
			String query = "select * from Vendors where vend_id like '%"+ vid +"%'";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String v_id = rs.getString("vend_id");
				String v_name = rs.getString("vend_name");
				String v_address = rs.getString("vend_address");
				String v_city = rs.getString("vend_city");
				String v_state = rs.getString("vend_state");
				String v_zip = rs.getString("vend_zip");
				String v_country = rs.getString("vend_country");
				System.out.println("************");
				VendorsVO vo = new VendorsVO();
				vo.setVend_id(v_id);
				vo.setVend_name(v_name);
				vo.setVend_address(v_address);
				vo.setVend_city(v_city);
				vo.setVend_state(v_state);
				vo.setVend_zip(v_zip);
				vo.setVend_country(v_country);
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
	//수정
	public void modifiedrVendors(String v_id) {
		try {
			con = dataFactory.getConnection();
			String query = "update Vendors set vend_id='"+v_id+"',vend_name= ";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//삭제
	public void delVendors(String v_id) {
		try {
			con = dataFactory.getConnection();
			String query = "delete from Vendors where vend_id='"+v_id+"'";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}