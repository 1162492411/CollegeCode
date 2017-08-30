package com.zyg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyg.dao.impl.CommentDaoImpl;
import com.zyg.entity.Comment;

/**
 * Servlet implementation class UpdateCommentAction
 */
@WebServlet("/amdin/commentManage/UpdateCommentAction")
public class UpdateCommentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Comment aComment = new Comment();
		aComment.setId(Integer.parseInt(request.getParameter("updateId")));
		aComment.setNid(Integer.parseInt(request.getParameter("updateNid")));
		aComment.setContent(request.getParameter("updateContent"));
		aComment.setAuthor(request.getParameter("updateAuthor"));
		if(new CommentDaoImpl().updateComment(aComment))
			response.sendRedirect("../statusManage/success.jsp");
		else
			response.sendRedirect("../statusManage/error.jsp");
	}

}
