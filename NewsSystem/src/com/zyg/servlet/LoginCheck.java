package com.zyg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zyg.dao.impl.UserDaoImpl;

@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("inputName");
		String password = request.getParameter("inputPassword");
		if(new UserDaoImpl().validateUser(name, password)){
			HttpSession session = request.getSession();
			session.setAttribute("username", name);
//			if("remember-me".equals(request.getParameter("isRemember"))){
//				Cookie aCookie = new Cookie("name",name);
//				aCookie.setMaxAge(60 * 30);
//				response.addCookie(aCookie);
//			}
			response.sendRedirect("index.jsp");
			return ;
		}
		else{
			System.out.println("loginCheck时未能成功验证");
			response.sendRedirect("login.jsp");
			return ;
		}
	}
}
