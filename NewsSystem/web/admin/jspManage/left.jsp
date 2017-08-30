<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台默认页面</title>
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css"></script>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
<ul class="nav nav-pills nav-stacked">
   <li class="active">
   	<a href="#">管理功能菜单</a>
   		&emsp;用户名:
	<%
		HttpSession aSession =request.getSession();
   		out.print(aSession.getAttribute("username"));
	%>
   </li>
	<li><a href="../commentManage/showComment.jsp" target="mainFrame">查看评论</a></li>
	<li><a href="../commentManage/addComment.jsp" target="mainFrame">添加评论</a></li>	
	<li><a href="../newsManage/showNews.jsp" target="mainFrame">查看新闻</a></li>
	<li><a href="../newsManage/addNews.jsp" target="mainFrame">添加新闻</a></li>
	<li><a href="../topicManage/showTopic.jsp" target="mainFrame">查看分类</a></li>
	<li><a href="../topicManage/addTopic.jsp" target="mainFrame">添加分类</a></li>
	<li><a href="../userManage/showUser.jsp" target="mainFrame">查看用户</a></li>
	<li><a href="../userManage/addUser.jsp" target="mainFrame">添加用户</a></li>
</ul>
</body>
</html>