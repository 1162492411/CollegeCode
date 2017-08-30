<%@page import="com.zyg.dao.impl.TopicDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除用户页面</title>
</head>
<body>
<%
	if(new TopicDaoImpl().deleteTopic(Integer.parseInt(request.getParameter("id")))){//若成功删除
		response.sendRedirect("showTopic.jsp");
	}
	else{
		response.sendRedirect("../statusManage/error.jsp");
	}
%>
</body>
</html>