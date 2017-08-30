package com.zyg.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyg.dao.impl.CommentDaoImpl;
import com.zyg.entity.Comment;


@WebServlet("/UserAddComment")
public class UserAddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Comment aComment = new Comment();
		aComment.setNid(Integer.parseInt(request.getParameter("inputNid")));
		aComment.setContent(request.getParameter("inputContent"));
		aComment.setAuthor(request.getParameter("inputAuthor"));
		aComment.setCreatetime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		if(new CommentDaoImpl().addComment(aComment))
			response.sendRedirect("admin/statusManage/success.jsp");
		else
			response.sendRedirect("admin/statusManage/error.jsp");
	}

}
