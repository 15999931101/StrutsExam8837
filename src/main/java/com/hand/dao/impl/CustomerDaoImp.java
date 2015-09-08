package com.hand.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.hand.dao.CustomerDao;
import com.hand.entity.Customer;




public class CustomerDaoImp implements CustomerDao {
    

	public ResultSet get(Connection conn) throws SQLException {
		
		PreparedStatement ps = conn.prepareStatement("SELECT f.film_id,f.title,f.description,l.name  FROM  film f,LANGUAGE l  WHERE f.language_id=l.language_id ");

	 
		return ps.executeQuery();
		
	}
	
	public void save(Connection conn, Customer cst) throws SQLException {	
		
		String insertSql = "insert into customer(store_id,first_name,last_name,"
				+ "email,address_id,active,create_date,last_update) values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insertSql);	
		ps.setLong(1, cst.getStore_id());
		ps.setString(2, cst.getFirst_name());
		ps.setString(3, cst.getLast_name());
		ps.setString(4,cst.getEmail());
		ps.setLong(5,cst.getAddress_id());
		ps.setLong(6,cst.getActive());
		ps.setString(7,cst.getCreate_date());
		ps.setString(8,cst.getLastUpdatetime());
	
		ps.execute();
		System.out.println("添加成功");
		if( ps != null) 
	        ps.close(); 		
	    if(conn!= null) 
	        conn.close(); 
		
	}

	public boolean update(Connection conn, Customer cst) throws SQLException {

//		System.out.println(cst.getStore_id()+"\t"+cst.getFirst_name()+"\t"+cst.getLast_name()+"\t"+cst.getEmail()+"\t"+cst.getAddress_id());
//		System.out.println(cst.getActive()+"\t"+cst.getCreate_date()+"\t"+cst.getLastUpdatetime()+"\t"+cst.getCustomer_id());
	
	    String sql="UPDATE customer SET store_id=?,first_name=?,last_name=?,email=?,address_id=?,active=?,create_date=?,last_update=? WHERE customer_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);	
		
		ps.setLong(1, cst.getStore_id());
		ps.setString(2,cst.getFirst_name());
		ps.setString(3, cst.getLast_name());
		ps.setString(4,cst.getEmail());
		ps.setLong(5,cst.getAddress_id());
		ps.setLong(6,cst.getActive());
		ps.setString(7,cst.getCreate_date());
		ps.setString(8,cst.getLastUpdatetime());	
	    ps.setLong(9, cst.getCustomer_id());
		if(ps.execute()){
		System.out.println("更新成功");
		return true;
		}else{
			
			return false;
		} 
		
	}

	public void delete(Connection conn, Customer cs) throws SQLException {
		
		System.out.println("删除成功");
		String sql = "DELETE FROM payment WHERE customer_id=?";
		PreparedStatement preparedStatement = conn.prepareCall(sql);
		preparedStatement.setInt(1, cs.getCustomer_id());
		preparedStatement.execute();
		sql = "DELETE FROM rental WHERE customer_id=?";
		preparedStatement = conn.prepareCall(sql);
		preparedStatement.setInt(1, cs.getCustomer_id());
		preparedStatement.execute();
		sql = "DELETE FROM customer WHERE customer_id=?";
		preparedStatement = conn.prepareCall(sql);
		preparedStatement.setInt(1, cs.getCustomer_id());
		preparedStatement.execute();
		preparedStatement.close();

		
		
	}

	



}
