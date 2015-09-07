package com.hand.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class LoginAction {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute(){
		String ret = "error";
		Connection conn = null;
//		System.out.println("password-->"+password);
//		System.out.println("username="+username);
		try {
			String URL = "jdbc:mysql://localhost/sakila";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "root", "root");
			String sql = "SELECT first_name,last_name FROM customer WHERE";
			sql+="  first_name= ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
//			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				username = rs.getString(1);
				
				ServletActionContext.getRequest().getSession().setAttribute("username", username);
				ret = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = "loginfail";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
		return ret;
	}



	


}
