package com.hand.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hand.dao.UserDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserDaoImpl implements UserDao {
	
	public boolean save(Connection connection, String fname, String lname, String emial, int address) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO customer(first_name,last_name,address_id,email,store_id) VALUES(?,?,?,?,'')";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareCall(sql);
			preparedStatement.setString(1, fname);
			preparedStatement.setString(2, lname);
			preparedStatement.setString(3, emial);
			preparedStatement.setInt(4, address);
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("INFO-->插入出错！");
			return false;
		}
	}

	public boolean check(Connection connection, String name, String pwd) {
		// TODO Auto-generated method stub
		String sql = "select * from customer where first_name=? and last_name=?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareCall(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, pwd);
			boolean bool=preparedStatement.executeQuery().next();
			preparedStatement.close();
			return bool;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("INFO-->"+name+"查詢用戶信息失敗");
		}
		return false;
	}

	public JSONArray all(Connection connection) {
		// TODO Auto-generated method stub
		String sql = "SELECT first_name,last_name,address,email,customer_id,customer.last_update FROM customer,address WHERE customer.address_id=address.address_id LIMIT 0,100";
		JSONArray json = new JSONArray();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareCall(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("first_name", resultSet.getString("first_name"));
				jsonObject.put("last_name", resultSet.getString("last_name"));
				jsonObject.put("address", resultSet.getString("address"));
				jsonObject.put("email", resultSet.getString("email"));
				jsonObject.put("customer_id", resultSet.getString("customer_id"));
				jsonObject.put("last_update", resultSet.getString("last_update"));
				json.add(jsonObject);
			}
			preparedStatement.close();
			return json;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("INFO-->"+"查詢用戶信息失敗");
		}
		return null;
	}
	public List<String> address(Connection connection) {
		// TODO Auto-generated method stub
		String sql = "SELECT address FROM address";
		List<String> list = new ArrayList<String>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareCall(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				list.add(resultSet.getString("address"));
			}
			preparedStatement.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("INFO-->"+"查詢用戶信息失敗");
		}
		return null;
	}

	public boolean delete(Connection connection, int id) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement;
		String sql = null;
		try {
			sql = "DELETE FROM payment WHERE customer_id=?";
			preparedStatement = connection.prepareCall(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			sql = "DELETE FROM rental WHERE customer_id=?";
			preparedStatement = connection.prepareCall(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			sql = "DELETE FROM customer WHERE customer_id=?";
			preparedStatement = connection.prepareCall(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			preparedStatement.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("INFO-->删除出错！");
			return false;
		}
	}

}
