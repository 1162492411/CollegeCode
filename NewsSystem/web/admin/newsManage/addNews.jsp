<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.ArrayList,com.zyg.entity.News,com.zyg.dao.NewsDao,com.zyg.dao.impl.NewsDaoImpl,com.zyg.entity.Topic,com.zyg.dao.impl.TopicDaoImpl"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 加载BootStrap资源 -->
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-tdeme.min.css"></script>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<title>添加新闻页面</title>
</head>
<body style="margin:15px 25px 25px 25px;width:500px">
<%--顶部 --%>
	<nav class="navbar navbar-default" role="navigation">
	<%--导航栏 --%>
	   <div class="navbar-header">
      <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-plus">添加新闻</span></a>
   </div>
	</nav>
	<%--数据表格 --%>
	<div class="panel panel-dafault">
		<form action="AddNewsAction" method="post">
			<table class="table" style="width: 400px">
				<%-- 设置组件 --%>
				<tr>
				<tr>
					<td>栏目编号</td>
					<td><select class="form-control" name="inputTid">
					<%
						ArrayList<Topic> topics = new TopicDaoImpl().getAllTopics();
					%>
					<c:forEach var="atopic" items="<%= topics %>">
						<option  value="${atopic.id }" >${atopic.tname }</option>
					</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td>新闻标题</td>
					<td><input type="text" name="inputTitle"
						class="form-control" placeholder="新闻标题"
						 /></td>
				</tr>
				<tr>
					<td>新闻作者</td>
					<td><input type="text" name="inputAuthor"
						class="form-control" placeholder="新闻作者"
						/></td>
				</tr>
				<tr>
					<td>新闻内容</td>
					  <td><textarea rows="6" cols="50" name="inputNcontent" class="form-control" ></textarea></td>

				</tr>
				<tr>
					<td>新闻摘要</td>
					<td><textarea rows="3" cols="50" name="inputSummary" class="form-control" ></textarea></td>
				</tr>								
				<tr>
				<td colspan="2" align="center">
					<input type="submit" value="提交"  class="btn btn-primary"/>
					<input type="reset" value="重置"  class="btn btn-primary"/>
				</td>
				</tr>
			</table>
		</form>
	</div>
	

</body>
</html>