package com.hand.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class PermissionFilter
 */
public class PermissionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PermissionFilter() {
    }
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String path = req.getServletPath();
		Object user = req.getSession().getAttribute("user");
		if(user != null){
			chain.doFilter(request, response);
		}else{
			if(!path.equals("/login.jsp")){//未登录，直接访问其它页面
				resp.sendRedirect("Login.action");
			}else{//用户直接访问登录页面
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				request.removeAttribute("msg");
				rd.forward(req, resp);
			}
			
		}
		
	}


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}
}
