package com.zyg.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zyg.entity.User;

/**
 * Servlet Filter implementation class loginFilter
 */
@WebFilter("/admin/LoginFilter")
public class LoginFilter implements Filter {
    public LoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		//向下转型
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//取值
		HttpSession session = request.getSession();
		String name = String.valueOf(session.getAttribute("username"));
		System.out.println("登录过滤器中的session的值为:" + name);
		//判断
		if(name == "null"){
			System.out.println("即将跳转到登录页面");
			response.sendRedirect("../adminLogin.jsp");
			chain.doFilter(req, res);
		}
		else{
			System.out.println("通过了登录过滤器，即将放行该页面");
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
