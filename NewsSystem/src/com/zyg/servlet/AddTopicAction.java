package com.zyg.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zyg.dao.impl.TopicDaoImpl;
import com.zyg.entity.Topic;

/**
 * Servlet implementation class AddTopicAction
 */
@WebServlet("/admin/topicManage/AddTopicAction")
public class AddTopicAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Topic aTopic = new Topic();
		aTopic.setTname(request.getParameter("inputTname"));
		aTopic.setCreatetime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		if(new TopicDaoImpl().addTopic(aTopic))
			response.sendRedirect("../statusManage/success.jsp");
		else
			response.sendRedirect("../statusManage/error.jsp");
	}

}
