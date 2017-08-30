<%@page import="com.zyg.dao.impl.NewsDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除新闻页面</title>
</head>
<body>
<%
	if(new NewsDaoImpl().deleteNews(Integer.parseInt(request.getParameter("id")))){//若成功删除
		response.sendRedirect("showNews.jsp");
	}
	else{
		response.sendRedirect("../statusManage/error.jsp");
	}
%>
</body>
</html>