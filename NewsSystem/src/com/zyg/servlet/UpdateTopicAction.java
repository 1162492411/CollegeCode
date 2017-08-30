package com.zyg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zyg.entity.Topic;
import com.zyg.dao.impl.TopicDaoImpl;

/**
 * Servlet implementation class UpdateTopicAction
 */
@WebServlet("/admin/topicManage/UpdateTopicAction")
public class UpdateTopicAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Topic aTopic = new Topic();
		aTopic.setId(Integer.parseInt(request.getParameter("updateTid")));	
		aTopic.setTname(request.getParameter("updateTname"));
		if(new TopicDaoImpl().updateTopic(aTopic))
			response.sendRedirect("../statusManage/success.jsp");
		else
			response.sendRedirect("../statusManage/error.jsp");
	}

}
