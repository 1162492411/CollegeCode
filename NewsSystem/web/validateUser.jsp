<%@page import="javax.imageio.spi.RegisterableService"%>
<%@page import="com.zyg.dao.impl.UserDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证用户是否存在</title>
</head>
<body>
<%
	if(new UserDaoImpl().isUserExist(request.getParameter("username"))){
		out.println("该用户已存在！！请重新填写用户名！！\n5秒后自动跳转回<a href='register.jsp'>注册</a>页面");
		response.setHeader("Refresh","5;register.jsp");
	}
	else {
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}
%>
</body>
</html>