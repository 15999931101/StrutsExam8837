package com.hand.action;

import javax.servlet.RequestDispatcher;

import com.hand.service.CheckUserService;

import freemarker.template.utility.Execute;

public class DeleteAction {
	
	private int customer_id;

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String execute(){
		

		System.out.println("id==>"+customer_id);
		
		
		
		boolean bool = new CheckUserService().delete(customer_id);
		
		if(bool){
			System.out.println("INFO-->"+customer_id+"删除成功");
			return "deletesuccess";
		}else{
			return "deletefail";
		}
	}


}
