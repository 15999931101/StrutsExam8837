package com.hand.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hand.dao.UserDao;
import com.hand.dao.impl.UserDaoImpl;
import com.hand.util.ConnectionFactory;

import net.sf.json.JSONArray;

public class CheckUserService {

	private UserDao userDao = new UserDaoImpl();
	
	public boolean save(String fname, String lname, String emial, String address) {
		Connection connection = null;
		boolean bool = false;
		try {
			connection = ConnectionFactory.getInstance().makeConnection();
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement("select * from address where address=?");
			preparedStatement.setString(1, address);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			int a_id = resultSet.getInt("address_id");
			
			resultSet.close();

			bool = userDao.save(connection, fname, lname, emial, a_id);

			return bool;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("INFO-->获取数据出错");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bool;
	}

	public boolean check(String name,String pwd ) {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getInstance().makeConnection();
			System.out.println(connection);
			connection.setAutoCommit(false);

			boolean bool = userDao.check(connection, name, pwd);

			return bool;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("INFO-->"+name+"查詢用戶信息失敗");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public JSONArray all() {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getInstance().makeConnection();
			connection.setAutoCommit(false);

			JSONArray json = userDao.all(connection);

			return json;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("INFO-->"+"查詢用戶信息失敗");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<String> address() {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getInstance().makeConnection();
			connection.setAutoCommit(false);

			List<String> list = userDao.address(connection);

			return list;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("INFO-->"+"查詢用戶信息失敗");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean delete(int id) {
		Connection connection = null;
		boolean bool = false;
		System.out.println("checkUserId-->"+id);
		try {
			connection = ConnectionFactory.getInstance().makeConnection();
			connection.setAutoCommit(false);

			bool = userDao.delete(connection, id);
			
			connection.commit();

			return bool;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("INFO-->删除customer数据出错");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bool;
	}
}
