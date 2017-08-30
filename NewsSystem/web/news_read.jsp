<%@page import="com.zyg.dao.impl.CommentDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,com.zyg.entity.News,com.zyg.entity.Topic,com.zyg.entity.Comment,com.zyg.dao.NewsDao,com.zyg.dao.TopicDao,com.zyg.dao.TopicDao,com.zyg.dao.impl.NewsDaoImpl,com.zyg.dao.impl.TopicDaoImpl,com.zyg.dao.impl.CommentDaoImpl"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css"></script>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<title>新闻中心</title>
</head>
<body>
<%--获取传递的参数 --%>
<%
	NewsDaoImpl aImpl = new NewsDaoImpl();
	int newsid = 0;
	if(request.getParameter("id") == null) newsid = 1;
	else newsid = Integer.parseInt(request.getParameter("id"));
	News aNews = aImpl.searchNews(newsid);
	Topic aTopic = new TopicDaoImpl().searchTopic(aNews.getTid());
	ArrayList<Comment> comments = new CommentDaoImpl().getCommmentsByNews(newsid);	
	int commentCount = new CommentDaoImpl().getCountByNews(newsid);
%>
	<%-- 顶部开始 --%>
	<div class="header">
		<%--顶栏开始 --%>
		<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">新闻中心</a>
		</div>
		<%--右侧用户状态 --%>
		<div style="float:right">
		<%--当用户未登陆时 --%>
		<c:if test='<%=String.valueOf(request.getSession().getAttribute("username"))== "null" %>'>
  				<a href="login.jsp"><button class="btn btn-default">登录</button></a>
  				<a href="register.jsp"><button class="btn btn-default">注册</button></a>
		</c:if>
		<%--当用户已登录时 --%>
		<c:if test='<%=String.valueOf(request.getSession().getAttribute("username"))!= "null" %>'>
			<button class="btn btn-default"><%=request.getSession().getAttribute("username") %></button>
			<a href="logout.jsp"><button class="btn btn-danger" >注销</button></a>
		</c:if>	
		<%--右侧用户状态栏结束 --%>
		</div>
		</nav>
		<%--顶栏结束 --%>
		<%--面包屑导航栏开始 --%>
		<ol class="breadcrumb">
  			<li><a href="topic_read.jsp">新闻中心</a></li>
  			<li><a href="topic_read.jsp?topicId=<%= aTopic.getId() %>"><%=aTopic.getTname() %></a></li>
  			<li class="active"><%= aNews.getTitle() %></li>
		</ol>
		<%--面包屑导航栏结束 --%>
	</div>
	<%--顶部结束 --%>
	
	<%--中间内容开始 --%>
		<div class="panel panel-default" style="width:60%;height:80%;position:absolute;left:20%;top:20%">
		<%--设置标题 --%>
		<div class="panel-heading" style="text-align:center">
			<a href="#"><h2><%= aNews.getTitle()%></h2></a><br />
			<p>&emsp;&emsp;作者：<%=aNews.getAuthor() %>&emsp;时间：<%= aNews.getCreateTime() %></p>
		</div>
		<%--设置数据 --%>
		<div class="panel panel-body">
		<p><%= aNews.getNcontent()%></p>
		</div>
	</div>
	<%--中间内容结束 --%>
	
	<%-- 显示评论开始 --%>
	<div class="panel panel-info" style="width:60%;height:50%;position:absolute;left:20%;top:110%">
	<%-- 设置标题 --%>
		<div class="panel-heading">用户评论&emsp;(<%=commentCount %>)</div>
	<%-- 填充数据 --%>
		<ul class="list-group">
		<c:forEach var="comment" items="<%=comments %>">
			<li class="list-group-item">
			&emsp;${comment.author }&emsp;&emsp;&emsp;&emsp;&emsp;${comment.createtime }<br />
			<textarea  class="form-control" style="width:60%,height:5%" disabled="disabled">${comment.content }</textarea>	
			</li>
		</c:forEach>
		</ul>
	</div>
	<%-- 显示评论结束 --%>
	<%--添加评论开始 --%>
	<%--当用户已登录时 --%>
	<c:if test='<%=String.valueOf(request.getSession().getAttribute("username"))!= "null" %>'>
		<form action="UserAddComment" style="width:60%;height:5%;position:absolute;left:20%;top:165%">
			<label class="sr-only" name="inputNid" value="<%=newsid %>">新闻编号</label>
			<textarea name="inputContent" class="form-control" placeholder="我也说两句"></textarea>
			<label class="sr-only" name="inputAuthor" value="request.getSession().getAttribute('username')">评论作者</label>
			<br />
			<button type="submit" class="btn btn-default">发表评论</button>
			<br /><br />
		</form>
	</c:if>	
	<%--添加评论结束 --%>
</body>
</html>