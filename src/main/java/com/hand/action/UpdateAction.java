package com.hand.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hand.dao.CustomerDao;
import com.hand.dao.impl.CustomerDaoImp;
/*import com.hand.dao.impl.GetConnetion;*/
import com.hand.entity.Customer;
import com.hand.util.ConnectionFactory;


public class UpdateAction {

	private CustomerDao csDao = new CustomerDaoImp();
	private int customer_id;
	private String first_name;
	private String last_name;
	private String email;
	private String address;
	private String last_update;


	public CustomerDao getCsDao() {
		return csDao;
	}

	public void setCsDao(CustomerDao csDao) {
		this.csDao = csDao;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

    
	public String execute(){
		    
		int store_id=1;
		int active=1;
        
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String create_date=format.format(date); 
		String last_update = create_date;
		Connection conn = null;
	    conn=ConnectionFactory.getInstance().makeConnection();
		String sql = "select address_id from address where address=address";
		ResultSet rs = null;
		int address_id = 0;
		try {
			PreparedStatement ps1 = conn.prepareCall(sql);
			rs = ps1.executeQuery(sql);    
			while(rs.next()){
				
				address_id = (int) rs.getLong("address_id");
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(address);
		System.out.println("address_id"+address_id);
//		System.out.println(customer_id+"\t"+first_name+"\t"+last_name+"\t"+address+"\t"+last_update+"\t"+email+"\t"+address_id);
		Customer cst = new Customer();
		cst.setAddress(address);
		cst.setCustomer_id(customer_id);
		cst.setEmail(email);
		cst.setFirst_name(first_name);
		cst.setLast_name(last_name);
		cst.setLastUpdatetime(last_update);	

		cst.setActive(active);
		cst.setCreate_date(create_date);
		cst.setStore_id(store_id);
		cst.setAddress_id(address_id);

		try {   

			if(csDao.update(conn, cst)){
			    
				return "customerSuccess";				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "customerFail";
		
	
	}	

}

