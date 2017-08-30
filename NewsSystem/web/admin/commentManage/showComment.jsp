<%@ page language="java"
	import="java.util.ArrayList,com.zyg.entity.Comment,com.zyg.dao.CommentDao,com.zyg.dao.impl.CommentDaoImpl,com.zyg.util.PageController"
	contentType="text/html;charset=UTF-8" errorPage="../statusManage/error.jsp"
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
	function updateComment(id){
		window.location.replace("updateComment.jsp?id=" + id);
	}
	function deleteEnsure(pageid,id){
		var r = confirm("是否删除该条记录");
		if(r == true){
				var i=pageid.parentNode.parentNode.rowIndex;
				document.getElementById('myTable').deleteRow(i);
				window.location.replace("deleteComment.jsp?id=" + id);
		}
		else{
		}	
	}
</script>
<title>显示评论页面</title>
</head>
<body style="margin: 15px 25px 25px 25px">
	<%--顶部 --%>
	<nav class="navbar navbar-default" role="navigation"> <%--顶部导航栏 --%>
	<div class="navbar-header">
		<a class="navbar-brand" href="#">
			<span class="glyphicon glyphicon-Comment"> 查看评论</span>
		</a>
	</div>
	<%--顶部搜索栏 --%>
	<div style="float:right">
		<form class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
			<button type="submit" class="btn btn-default" onclick="searchComment()">搜索</button>
		</form>
	</div>
	</nav>
	<%--数据表格 --%>
	<div class="table-responsive">
		<table class="table table-hover" id="myTable">
			<%-- 设置表头 --%>
			<thead>
				<tr>
					<th>评论编号</th>
					<th>所属新闻</th>
					<th>评论内容</th>
					<th>评论时间</th>
					<th>评论作者</th>
					<th>&emsp;&emsp;操作</th>
				</tr>
			</thead>

			<%-- 设置数据 --%>
			<tbody>
				<%--设置分页--%>
				<%
					PageController aController = new PageController(new CommentDaoImpl().getCount());
					if (request.getParameter("currentPageIndex") != null)
						aController.setCurrentPageIndex(Integer.parseInt(request.getParameter("currentPageIndex")));
					ArrayList<Comment> commentss = new CommentDaoImpl().getCommentsByPage(aController.getCurrentPageIndex(),
							aController.getPageSize());//调用对应方法获取记录
				%> 
				<%--循环填充数据 --%>
				<c:forEach var="comment" items="<%= commentss %>" >
				<tr>
					<td>${ comment.id}</td>
					<td>${ comment.nid}</td>
					<td>${ comment.content}</td>
					<td>${ comment.createtime}</td>
					<td>${ comment.author }</td>
					<td><button type="button" class="btn btn-primary" onclick="updateComment(${comment.id})">编辑</button>
					<button type="button" class="btn btn-danger" onclick="deleteEnsure(this,${comment.id })">删除</button></td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
		<%--数据表格结束 --%>
		<%-- 分页按钮容器开始 --%>
		<div class="text-center">
			<a 
				href="showComment.jsp?currentPageIndex=1" class="btn btn-primary" id="firstPage">首页 
			</a>
			<a
				href="showComment.jsp?currentPageIndex=<%=aController.getPreviousPageIndex()%>"
				class="btn btn-primary" id="previousPage"> <<< 
			</a> 
			<a
				href="showComment.jsp?currentPageIndex=<%=aController.getCurrentPageIndex()%>"
				class="btn btn-primary active" id="currentPage"> 第<%=aController.getCurrentPageIndex()%>页
			</a> 
			<a
				href="showComment.jsp?currentPageIndex=<%=aController.getNextPageIndex()%>"
				class="btn btn-primary" id="nextPage"> >>> 
			</a> 
			<a
				href="showComment.jsp?currentPageIndex=<%=aController.getTotalPages()%>"
				class="btn btn-primary" id="lastPage"> 尾页 
			</a>
		</div>
		<%--分页按钮容器结束 --%>

	</div>
</body>
</html>