package com.zyg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyg.dao.impl.UserDaoImpl;
import com.zyg.entity.User;

/**
 * Servlet implementation class AddUserAction
 */
@WebServlet("/admin/userManage/AddUserAction")
public class AddUserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		User aUser = new User();
		aUser.setUsername(request.getParameter("inputUsername"));
		aUser.setUserpwd(request.getParameter("inputUserpwd"));
		aUser.setEmail(request.getParameter("inputEmail"));
		aUser.setAddress(request.getParameter("inputAddress"));
		aUser.setHobby(request.getParameter("inputHobby"));
		if(new UserDaoImpl().addUser(aUser))
			response.sendRedirect("../statusManage/success.jsp");
		else
			response.sendRedirect("../statusManage/error.jsp");
	}

}
