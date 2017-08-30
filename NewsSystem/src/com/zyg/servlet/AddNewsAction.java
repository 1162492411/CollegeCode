package com.zyg.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyg.dao.impl.NewsDaoImpl;
import com.zyg.entity.News;

/**
 * Servlet implementation class AddNewsAction
 */
@WebServlet("/admin/newsManage/AddNewsAction")
public class AddNewsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		News aNews = new News();
		aNews.setTid(Integer.parseInt(request.getParameter("inputTid")));
		aNews.setTitle(request.getParameter("inputTitle"));
		aNews.setAuthor(request.getParameter("inputAuthor"));
		aNews.setNcontent(request.getParameter("inputNcontent"));
		aNews.setSummary(request.getParameter("inputSummary"));
		aNews.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		aNews.setModifyTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		if(new NewsDaoImpl().addNews(aNews))
			response.sendRedirect("../statusManage/success.jsp");
		else
			response.sendRedirect("../statusManage/error.jsp");
	}

}
