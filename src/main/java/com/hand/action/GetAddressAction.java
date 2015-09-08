package com.hand.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hand.entity.Customer;
import com.hand.util.ConnectionFactory;

public class GetAddressAction {

	public List<String> list;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getAddress() {
//		Dao dao = new DaoImpl();
//		list = dao.getAddress();
		Connection connection = null;
		ResultSet resultSet = null;
	
		try {
			list = new ArrayList<String>();
			connection = ConnectionFactory.getInstance().makeConnection();
			String sql = "SELECT address FROM address";
			PreparedStatement preparedStatement = connection.prepareCall(sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println("here start getAddress");
			while (resultSet.next()) {
				list.add(resultSet.getString("address"));
			}
		} catch (Exception e) {
		
			System.out.println("error for getAddress");
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				System.out.println("close all");
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}		
		System.out.println("getAddress end......");
		return "success";
	}
	
}
