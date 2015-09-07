package com.hand.dao.impl;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
	
	private static String driver;
	private static String dburl;
	private static String user;
	private static String password;
	
	private static final ConnectionFactory factory= new ConnectionFactory();
	private   Connection conn;
	static {//可以使用static关键字创建静态代码块，只执行一次
		Properties prop = new Properties();
		try {
			
			InputStream in= ConnectionFactory.class.getClassLoader().
					getResourceAsStream("dbconfig.properties");
			prop.load(in);	
        System.out.println(prop);
			
		} catch (Exception e) {
			System.out.println("========配置文件读取错误=============");
			
		}
		
	
		driver = prop.getProperty("driver");
		dburl = prop.getProperty("dburl");
		user = prop.getProperty("user");
		password= prop.getProperty("password");
		System.out.println(driver+dburl+user+password);
	
	}
	
	private ConnectionFactory() {
		// TODO Auto-generated constructor stub
	}

	public static ConnectionFactory getInstance(){
		return factory ;
	}
	
	public Connection makeConnection(){
		try {
//			driver="com.mysql.jdbc.Driver";
//			dburl="jdbc:mysql://localhost:3306/sakila";
//			user ="root";
//			password="root";			
			Class.forName(driver);	
			conn = DriverManager.getConnection(dburl,user,password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
} 
