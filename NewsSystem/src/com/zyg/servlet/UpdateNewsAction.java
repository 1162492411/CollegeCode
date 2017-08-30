package com.zyg.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zyg.entity.News;
import com.zyg.dao.impl.NewsDaoImpl;

/**
 * Servlet implementation class UpdateNewsAction
 */
@WebServlet("/admin/newsManage/UpdateNewsAction")
public class UpdateNewsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News aNews = new News();
		aNews.setId(Integer.parseInt(request.getParameter("updateId")));
		aNews.setTid(Integer.parseInt(request.getParameter("updateTid")));
		aNews.setTitle(request.getParameter("updateTitle"));
		aNews.setAuthor(request.getParameter("updateAuthor"));
		aNews.setNcontent(request.getParameter("updateNcontent"));
		aNews.setSummary(request.getParameter("updateSummary"));
		aNews.setModifyTime(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
		if(new NewsDaoImpl().updateNews(aNews))
			response.sendRedirect("../statusManage/success.jsp");
		else
			response.sendRedirect("../statusManage/error.jsp");
	}

}
