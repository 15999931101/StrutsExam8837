package com.hand.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hand.dao.CustomerDao;
import com.hand.dao.impl.CustomerDaoImp;
import com.hand.dao.impl.GetConnetion;
import com.hand.entity.Customer;

import freemarker.core.ReturnInstruction.Return;


public class AddAction {

	private String first_name;
	private String last_name;
	private String address;
	private String email;
	private CustomerDao csDao = new CustomerDaoImp();


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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public CustomerDao getCsDao() {
		return csDao;
	}


	public void setCsDao(CustomerDao csDao) {
		this.csDao = csDao;
	}


	public String execute(){

		int store_id=1;
		int active=1;

		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String create_date=format.format(date); 


		//		String first_name = req.getParameter("first_name");		
		//		String last_name = req.getParameter("last_name");
		//        String address = req.getParameter("address");
		//        String email =req.getParameter("email");
		String last_update =create_date;	

		System.out.println(first_name);
		Connection conn = null;

		conn = GetConnetion.getConnection();
		//        Connection conn=ConnectionFactory.getInstance().makeConnection();
		String sql = "select address_id from address where address=address";
		ResultSet rs = null;
		int address_id = 0;
		try {
			PreparedStatement ps1 = conn.prepareCall(sql);
			rs = ps1.executeQuery(sql); 
			while(rs.next()){
				address_id = (int) rs.getLong("address_id");
			}

			System.out.println(address_id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		Customer cst = new Customer();
		cst.setAddress(address);

		cst.setEmail(email);
		cst.setFirst_name(first_name);
		cst.setLast_name(last_name);
		cst.setLastUpdatetime(last_update);	   
		cst.setActive(active);
		cst.setCreate_date(create_date);
		cst.setStore_id(store_id);
		cst.setAddress_id(address_id);


		//		Connection conn =GetConnetion.getConnection();
		try {   

			csDao.save(conn, cst);
			return "addsuccess";

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if(rs!=null){			
					rs.close();
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return "addfail";       
	}
}



	
