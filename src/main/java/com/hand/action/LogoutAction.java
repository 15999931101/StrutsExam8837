package com.hand.action;

import org.apache.struts2.ServletActionContext;

public class LogoutAction {
	
	
	public String execute(){	
		
		ServletActionContext.getRequest().getSession().setAttribute("username", "null");
		return "logout";
	}

}
