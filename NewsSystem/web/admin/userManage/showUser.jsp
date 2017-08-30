<%@ page language="java"
	import="java.util.ArrayList,com.zyg.entity.User,com.zyg.dao.UserDao,com.zyg.dao.impl.UserDaoImpl,com.zyg.util.PageController"
	contentType="text/html; charset=UTF-8" errorPage="../statusManage/error.jsp"
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
	function updateUser(id){
		window.location.replace("updateUser.jsp?id=" + id);
	}
	function deleteEnsure(pageid,id){
		var r = confirm("是否删除该条记录");
		if(r == true){
				var i=pageid.parentNode.parentNode.rowIndex;
				document.getElementById('myTable').deleteRow(i);
				window.location.replace("deleteUser.jsp?id=" + id);
		}
		else{
		}	
	}
</script>
<title>显示用户页面</title>
</head>
<body style="margin: 15px 25px 25px 25px">
	<%--顶部 --%>
	<nav class="navbar navbar-default" role="navigation"> <%--顶部导航栏 --%>
	<div class="navbar-header">
		<a class="navbar-brand" href="#">
			<span class="glyphicon glyphicon-user"> 查看用户</span>
		</a>
	</div>
	<%--顶部搜索栏 --%>
	<div style="float:right">
		<form class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
			<button type="submit" class="btn btn-default" onclick="searchUser()">搜索</button>
		</form>
	</div>
	</nav>
	<%--数据表格 --%>
	<div class="table-responsive">
		<table class="table table-hover" id="myTable">
			<%-- 设置表头 --%>
			<thead>
				<tr>
					<th>&emsp;用户编号</th>
					<th>&emsp;用户帐号</th>
					<th>&emsp;用户密码</th>
					<th>&emsp;电子邮件</th>
					<th>&emsp;地址</th>
					<th>&emsp;爱好</th>
					<th>&emsp;&emsp;操作</th>
				</tr>
			</thead>

			<%-- 设置数据 --%>
			<tbody>
				<%--设置分页 --%>
				<%
					PageController aController = new PageController(new UserDaoImpl().getCount());
					if (request.getParameter("currentPageIndex") != null)
						aController.setCurrentPageIndex(Integer.parseInt(request.getParameter("currentPageIndex")));
					ArrayList<User> Users = new UserDaoImpl().getUsersByPage(aController.getCurrentPageIndex(),
							aController.getPageSize());//调用对应方法获取记录
				%>
				<%--循环填充数据 --%>
				<c:forEach var="User" items="<%=Users %>">
					<tr id="${ User.id }">
						<td>${ User.id }</td>
						<td>${ User.username }</td>
						<td>${ User.userpwd }</td>
						<td>${ User.email }</td>
						<td>${ User.address }</td>
						<td>${ User.hobby }</td>
						<td><button type="button" class="btn btn-primary" onclick="updateUser(${ User.id })">编辑</button>
							<button type="button" class="btn btn-danger"
								onclick="deleteEnsure(this,${ User.id })">删除</button></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<%--数据表格结束 --%>
		<%-- 分页按钮容器开始 --%>
		<div class="text-center">
			<ul class="pagination">
				<li><a href="showUser.jsp?currentPageIndex=1">首页</a></li>
				<li><a
					href="showUser.jsp?currentPageIndex=<%=aController.getPreviousPageIndex()%>">
						<<<  </a></li>
				<li class="active"><a
					href="showUser.jsp?currentPageIndex=<%=aController.getCurrentPageIndex()%>">第<%=aController.getCurrentPageIndex()%>页
				</a></li>
				<li><a
					href="showUser.jsp?currentPageIndex=<%=aController.getNextPageIndex()%>">
						>>> </a></li>
				<li><a
					href="showUser.jsp?currentPageIndex=<%=aController.getTotalPages()%>">尾页</a></li>
			</ul>
		</div>
		<%--分页按钮容器结束 --%>

	</div>
</body>
</html>