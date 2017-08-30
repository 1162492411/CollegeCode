<%@ page language="java"
	import="java.util.ArrayList,com.zyg.entity.Topic,com.zyg.dao.TopicDao,com.zyg.dao.impl.TopicDaoImpl,com.zyg.util.PageController"
	contentType="text/html; charset=UTF-8" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css"></script>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script>
	function updateTopic(id){
		window.location.replace("updateTopic.jsp?id=" + id);
	}
	function deleteEnsure(pageid,id){
		var r = confirm("是否删除该条记录");
		if(r == true){
				var i=pageid.parentNode.parentNode.rowIndex;
				document.getElementById('myTable').deleteRow(i);
				window.location.replace("deleteTopic.jsp?id=" + id);
		}
		else{
		}	
	}
</script>
<title>显示栏目页面</title>
</head>
<body>
	<%--顶部 --%>
	<nav class="navbar navbar-default" role="navigation"> <%--顶部导航栏 --%>
	<div class="navbar-header">
		<a class="navbar-brand" href="#">
			<span class="glyphicon glyphicon-Topic"> 查看分类</span>
		</a>
	</div>
	<%--顶部搜索栏 --%>
	<div style="float:right">
		<form class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
			<button type="submit" class="btn btn-default" onclick="searchTopic()">搜索</button>
		</form>
	</div>
	</nav>
	<%--显示所有栏目 --%>
	<div class="table-responsive">
		<table class="table table-hover" id="myTable">
		<%-- 设置表头 --%>
			<thead>
				<tr>
					<th>栏目编号</th>
					<th>栏目名称</th>
					<th>创建时间</th>
					<th>&emsp;&emsp;操作</th>
				</tr>
			</thead>

			<%-- 设置数据 --%>
			<tbody>
				<%--设置分页 --%>
				<%
					PageController aController = new PageController(new TopicDaoImpl().getCount());
					if (request.getParameter("currentPageIndex") != null)
						aController.setCurrentPageIndex(Integer.parseInt(request.getParameter("currentPageIndex")));
					ArrayList<Topic> Topics = new TopicDaoImpl().getTopicsByPage(aController.getCurrentPageIndex(),
							aController.getPageSize());//调用对应方法获取记录
				%>
			<%--循环填充数据 --%>
			<c:forEach var="Topic" items="<%= Topics %>" >
			<tr>
				<td>${ Topic.id}</td>
				<td>${ Topic.tname}</td>
				<td>${ Topic.createtime}</td>
				<td><button type="button" class="btn btn-primary" onclick="updateTopic(${Topic.id})">编辑</button>
					<button type="button" class="btn btn-danger" onclick="deleteEnsure(this,${Topic.id })">删除</button></td>
			</tr>
			</c:forEach>	
					
			</tbody>
		</table>
				<%-- 分页按钮容器开始 --%>
		<div class="text-center">
			<ul class="pagination">
				<li><a href="showTopic.jsp?currentPageIndex=1">首页</a></li>
				<li><a
					href="showTopic.jsp?currentPageIndex=<%=aController.getPreviousPageIndex()%>">
						<<<  </a></li>
				<li class="active"><a
					href="showTopic.jsp?currentPageIndex=<%=aController.getCurrentPageIndex()%>">第<%=aController.getCurrentPageIndex()%>页
				</a></li>
				<li><a
					href="showTopic.jsp?currentPageIndex=<%=aController.getNextPageIndex()%>">
						>>> </a></li>
				<li><a
					href="showTopic.jsp?currentPageIndex=<%=aController.getTotalPages()%>">尾页</a></li>
			</ul>
		</div>
		<%--分页按钮容器结束 --%>
	</div>
</body>
</html>