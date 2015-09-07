package com.hand.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnetion {
	
	public static Connection getConnection(){
		Connection conn =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");//用来注册mysql的jdbc程序
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","root");//连接数据库

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;		
	}

}
