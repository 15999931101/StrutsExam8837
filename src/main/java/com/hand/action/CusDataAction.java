package com.hand.action;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hand.util.ConnectionFactory;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.opensymphony.xwork2.ActionSupport;

public class CusDataAction extends ActionSupport {
	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String execute() throws Exception {
		Connection conn = (Connection) ConnectionFactory.getInstance().makeConnection();
		String sql;
		String data = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		sql = "SELECT first_name,last_name,address,email,customer_id,c.last_update"
				+ " FROM customer c,address a"
				+ " WHERE c.address_id = a.address_id"
				+ " AND active = 1"
				+ " Limit " + (page-1)*9 + " , 9";
		try {
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			JsonObject object = new JsonObject();
			JsonArray ja = new JsonArray();
			while(rs.next()){
				JsonObject jo = new JsonObject();
				jo.addProperty("firstName", rs.getString(1).trim());
				jo.addProperty("lastName", rs.getString(2).trim());
				jo.addProperty("address", rs.getString(3).trim());
				jo.addProperty("email", rs.getString(4).trim());
				jo.addProperty("customerId", rs.getInt(5));
				jo.addProperty("lastUpdate", rs.getString(6));
				ja.add(jo);
			}

			object.add("customers",ja);
			System.out.println(object.toString());
			PrintWriter pw = response.getWriter();
			pw.write(object.toString());
			pw.flush();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
