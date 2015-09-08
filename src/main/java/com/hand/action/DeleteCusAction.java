package com.hand.action;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hand.service.CheckUserService;
import com.hand.util.ConnectionFactory;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCusAction extends ActionSupport{

	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String execute() throws Exception {
		
         System.out.println("id==>"+id);
		
		
		boolean bool = new CheckUserService().delete(Integer.parseInt(id));
		
		if(bool){
			System.out.println("INFO-->"+id+"删除成功");
			return "deletesuccess";
		}else{
			return "deletefail";
		}
	}
}
