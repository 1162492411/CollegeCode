<%@page import="com.zyg.util.ShowMoreNewsController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,com.zyg.entity.News,com.zyg.entity.Topic,com.zyg.dao.NewsDao,com.zyg.dao.TopicDao,com.zyg.dao.impl.NewsDaoImpl,com.zyg.dao.impl.TopicDaoImpl,com.zyg.util.PageController"
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
<script>
	function showMore(topicid,newscount) {
		window.location.replace("topic_read.jsp?topicId=" +topicid + "&newsCount=" + newscount);
	}
</script>
<title>新闻中心</title>
</head>
<body>
	<%-- 顶部开始 --%>
	<div class="header">
		<%--导航栏开始 --%>
		<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;新闻中心</a>
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
	<%--中间内容开始 --%>
	<div class="panel panel-default"
		style="width: 60%; height: 85%; position: absolute; left: 20%; top: 10%">
		<%--设置标题 --%>
		<div class="panel-heading">
			<ul class="nav nav-pills nav-justified">
				<%
					TopicDaoImpl aTopicDaoImpl = new TopicDaoImpl();
					ArrayList<Topic> topics = aTopicDaoImpl.getAllTopics();
				%>
				<c:forEach var="aTopic" items="<%=topics%>">
					<li><a href="topic_read.jsp?topicId=${aTopic.id }">${aTopic.tname }</a></li>
				</c:forEach>
			</ul>
		</div>
		<%--设置数据 --%>
		<div class="panel panel-body">
			<ul class="list-group">
				<%
					//根据新闻分类获取该分类的前10条记录
					NewsDaoImpl aNewsDaoImpl = new NewsDaoImpl();
					int topicId = 0;
					if (request.getParameter("topicId") == null)
						topicId = 1;
					else
						topicId = Integer.parseInt(request.getParameter("topicId"));
					//根据指定分类获取该分类下的总记录数
					ShowMoreNewsController aMoreNewsController = new ShowMoreNewsController(aNewsDaoImpl.getCountByTopic(topicId));
					if(request.getParameter("newsCount") != null)
						aMoreNewsController.setCurrentCount(Integer.parseInt(request.getParameter("newsCount")));
					ArrayList<News> newsListD = aNewsDaoImpl.getTopNews(topicId, aMoreNewsController.getCurrentCount());
				%>
				<c:forEach var="newsD" items="<%=newsListD%>">
					<li class="list-group-item"><a
						href="news_read.jsp?id=${newsD.id }"><h3>${newsD.title }</h3></a><br />
						&emsp;&emsp;${newsD.summary }<br /> <font
						style="color: gray">&emsp;&emsp;${newsD.createTime }&emsp;&emsp;</font>
						<span><a href="news_read.jsp?id=${newsD.id }">详细信息>></a></span>
					</li>
				</c:forEach>

				<span><button type="button" class="btn btn-default btn-lg btn-block"
					onclick="showMore(<%= topicId %>,<%= aMoreNewsController.step() %>)">加载更多</button>
				</span>
			</ul>
		</div>
	</div>
	
</body>
</html>