package com.hand.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CheckLoginInterceptor implements Interceptor {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		System.out.println("checkLogin拦截器初始化");

	}

	public String intercept(ActionInvocation arg0) throws Exception {
		String url = "";
		System.out.println(ServletActionContext.getRequest().getSession().getAttribute("username"));
		if(null!=ServletActionContext.getRequest().getSession().getAttribute("username")){
			url = arg0.invoke();
		}else{
			url="loginfail";
		}
		return url;
	}

}
