<%@page import="org.apache.tomcat.util.descriptor.web.LoginConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,com.zyg.util.StringUtil,com.zyg.entity.News,com.zyg.entity.Topic,com.zyg.dao.NewsDao,com.zyg.dao.TopicDao,com.zyg.dao.impl.NewsDaoImpl,com.zyg.dao.impl.TopicDaoImpl,com.zyg.util.PageController"
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
<title>默认前台页面</title>
</head>
<body>
	<%-- 顶部开始 --%>
	<div class="header">
		<%--导航栏开始 --%>
		<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;新闻系统</a>
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
		<%--导航栏结束 --%>
	</div>
	<%--顶部结束 --%>

	<%--左侧内容开始 --%>
	<%-- 国内新闻开始--%>
	<div class="panel panel-primary"
		style="width: 20%; height: 25%; position: absolute; left: 10%; top: 10%">
		<%--设置标题 --%>
		<div class="panel-heading">国内新闻</div>
		<%--设置数据 --%>
		<div class="panel-body">
			<%
				NewsDaoImpl aNewsDaoImpl = new NewsDaoImpl();
				ArrayList<News> newsListA = aNewsDaoImpl.getTopNews(1, 5);
				for(int i=0;i < newsListA.size(); i++){
					out.println("<a href='news_read.jsp?id="
							 + newsListA.get(i).getId() + "'>"
							 + new StringUtil().SubStr(newsListA.get(i).getTitle())
							+"</a><br />");
				}
			%>
		</div>
	</div>
	<%-- 国内新闻结束--%>

	<%-- 国际新闻开始 --%>
	<div class="panel panel-primary"
		style="width: 20%; height: 25%; position: absolute; left: 10%; top: 40%">
		<%--设置标题 --%>
		<div class="panel-heading">国际新闻</div>
		<%--设置数据 --%>
		<div class="panel panel-body">
			<%
				ArrayList<News> newsListB = aNewsDaoImpl.getTopNews(2, 5);
			for(int i=0;i < newsListB.size(); i++){
				out.println("<a href='news_read.jsp?id="
						 + newsListB.get(i).getId() + "'>"
						 + new StringUtil().SubStr(newsListB.get(i).getTitle())
						+"</a><br />");
			}
			%>
		</div>
	</div>
	<%-- 国际新闻结束 --%>

	<%-- 娱乐新闻开始 --%>
	<div class="panel panel-primary"
		style="width: 20%; height: 25%; position: absolute; left: 10%; top: 70%">
		<%--设置标题 --%>
		<div class="panel-heading">娱乐新闻</div>
		<%--设置数据 --%>
		<div class="panel panel-body">
			<%
				ArrayList<News> newsListC = aNewsDaoImpl.getTopNews(3, 5);
			for(int i=0;i < newsListC.size(); i++){
				out.println("<a href='news_read.jsp?id="
						 + newsListC.get(i).getId() + "'>"
						 + new StringUtil().SubStr(newsListC.get(i).getTitle())
						+"</a><br />");
			}
			%>
		</div>
	</div>
	<%-- 娱乐新闻结束 --%>
	<%--左侧内容结束 --%>

	<%--中间内容开始 --%>
	<div class="panel panel-default"
		style="width: 50%; height: 85%; position: absolute; left: 35%; top: 10%">
		<%--设置标题 --%>
		<div class="panel-heading">
			<ul class="nav nav-pills nav-justified">
				<%
					TopicDaoImpl aTopicDaoImpl = new TopicDaoImpl();
					ArrayList<Topic> topics = aTopicDaoImpl.getAllTopics();
				%>
				<c:forEach var="aTopic" items="<%=topics%>">
					<li><a href="index.jsp?topicId=${aTopic.id }">${aTopic.getTname() }</a></li>
				</c:forEach>
			</ul>
		</div>
		<%--设置数据 --%>
		<div class="panel panel-body">
			<ul class="list-group">
				<%
					//根据新闻分类获取该分类的前10条记录
					int topicId = 0;
					if (request.getParameter("topicId") == null)
						topicId = 1;
					else
						topicId = Integer.parseInt(request.getParameter("topicId"));
					ArrayList<News> newsListD = aNewsDaoImpl.getTopNews(topicId, 10);
				%>
				<c:forEach var="newsD" items="<%=newsListD%>">
					<li class="list-group-item"><a
						href="news_read.jsp?id=${newsD.id }">${newsD.title }</a></li>
				</c:forEach>
				<br />	
				<span><a href="topic_read.jsp?topicId=<%=topicId %>">&emsp;&emsp;&emsp;>>更多新闻</a></span>
			</ul>
		</div>
	</div>
	<%--中间内容结束 --%>

	<%--友情链接开始 --%>
	<div class="panel panel-info"
		style="width: 50%; height: 15%; position: absolute; left: 25%; top: 100%">
		<div class="panel-heading">友情链接</div>
		<div class="panel-body">
			<a href="#">中国政府网</a> <a href="#">中国政府网</a> <a href="#">中国政府网</a>
			<a href="#">中国政府网</a> <a href="#">中国政府网</a> <a href="#">中国政府网</a>
			<a href="#">中国政府网</a> <a href="#">中国政府网</a> 
		</div>
	</div>
	<%--友情链接结束 --%>

	<%-- 底部footer开始 --%>
	<div id="footer" style="position: absolute; left: 35%; top: 115%">
		<p style="text-align: center">
			24小时客户服务热线：XXX-XXXXXXXX &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a>
			&#160;&#160;&#160;&#160; 新闻热线：XXX-XXXXXXXX <br />
		</p>
		<p style="text-align: center">
			Copyright &copy; 1999-2016 News China gov, All Right Reserver <br />
			第二十四组 版权所有
		</p>
	</div>
	<%-- 底部footer结束 --%>
</body>
</html>