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
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class PermissionFilter
 */
public class PermissionFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public PermissionFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String servletPath = httpServletRequest.getServletPath();

		HttpSession session = httpServletRequest.getSession();

		String flag = (String) session.getAttribute("flag");
		if (flag != null) {
			System.out.println("INFO-->" + flag);
		} else {
			System.out.println("INFO-->flag==null");
		}
		System.out.println("INFO-->" + servletPath);

		if (servletPath != null && servletPath.equals("/login.jsp") || servletPath.equals("/LoginServlet")) {
			System.out.println("INFO-->进入登录页面或提交登录信息");
			chain.doFilter(request, response);
		} else {
			if (flag != null && flag.equals("success")) {
				System.out.println("INFO-->已登录成功，进入其他页面");
				chain.doFilter(request, response);
			} else if (flag != null && flag.equals("error")) {
				System.out.println("INFO-->登录失败，重新返回登录页面");
				httpServletRequest.setAttribute("return_uri", servletPath);
				RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/login.jsp");
				requestDispatcher.forward(httpServletRequest, httpServletResponse);
			} else {
				System.out.println("INFO-->尚未登录，返回登录页面");
				httpServletRequest.setAttribute("return_uri", servletPath);
				RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/login.jsp");
				requestDispatcher.forward(httpServletRequest, httpServletResponse);
			}
		}

		// pass the request along the filter chain

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
