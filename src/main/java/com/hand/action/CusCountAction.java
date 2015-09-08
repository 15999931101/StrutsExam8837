package com.hand.action;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.JsonObject;
import com.hand.util.ConnectionFactory;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.opensymphony.xwork2.ActionSupport;

public class CusCountAction extends ActionSupport{

	@Override
	public String execute() throws Exception {
		Connection conn = (Connection) ConnectionFactory.getInstance().makeConnection();
		String sql = "SELECT count(customer_id) FROM customer WHERE active=1";
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				int page = rs.getInt(1);
				JsonObject object = new JsonObject();
				object.addProperty("count", page);
				request.getSession().setAttribute("count", page);
				PrintWriter pw = response.getWriter();
				pw.write(object.toString());
				pw.flush();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
