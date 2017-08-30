package com.zyg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyg.dao.impl.UserDaoImpl;
import com.zyg.entity.User;

@WebServlet("/RegisterAction")
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User aUser = new User();
		aUser.setUsername(request.getParameter("loginUsername"));
		aUser.setUserpwd(request.getParameter("loginUserpwd"));
		aUser.setEmail(request.getParameter("loginEmail"));
		aUser.setAddress(request.getParameter("loginAddress"));
		aUser.setHobby(request.getParameter("loginHobby"));
		if(new UserDaoImpl().addUser(aUser))//如果注册成功
			response.sendRedirect("login.jsp");//跳转到登陆页面
		else //注册时失败跳转到注册页面
			response.sendRedirect("register.jsp");
		
	}

}
