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
 * Servlet implementation class UpdateUserAction
 */
@WebServlet("/admin/userManage/UpdateUserAction")
public class UpdateUserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		User aUser = new User();
		aUser.setId(Integer.parseInt(request.getParameter("updateId")));
		aUser.setUsername(request.getParameter("updateUsername"));
		aUser.setUserpwd(request.getParameter("updateUserpwd"));
		aUser.setEmail(request.getParameter("updateEmail"));
		aUser.setAddress(request.getParameter("updateAddress"));
		aUser.setHobby(request.getParameter("updateHobby"));
		if(new UserDaoImpl().updateUser(aUser))
			response.sendRedirect("../statusManage/success.jsp");
		else
			response.sendRedirect("../statusManage/error.jsp");
	}

}
