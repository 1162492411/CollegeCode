<%@ page language="java"
	import="java.util.ArrayList,com.zyg.entity.News,com.zyg.dao.NewsDao,com.zyg.dao.impl.NewsDaoImpl"
	contentType="text/html; charset=UTF-8" errorPage="error.jsp"
	pageEncoding="UTF-8"%>
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
<title>修改新闻页面</title>
</head>
<body>
	<%
		News aNews = new NewsDaoImpl().searchNews(Integer.parseInt(request.getParameter("id")));
	%>
	<div class="panel panel-dafault">
		<div class="panel-heading">
			<span class="glyphicon glyphicon-pencil"> 修改新闻 </span>
		</div>
		<form action="UpdateNewsAction" method="post">
			<table class="table" style="width: 400px">
				<%-- 设置组件 --%>
				<tr style="display:none">
					<td>新闻编号</td>
					<td><input type="text" name="updateId"
						class="form-control" placeholder="ID"
						value="<%=aNews.getId()%>" /></td>
				</tr>				
				<tr>
					<td>栏目编号</td>
					<td><input type="text" name="updateTid"
						class="form-control" placeholder="栏目编号"
						value="<%=aNews.getTid() %>" /></td>
				</tr>
				<tr>
					<td>新闻标题</td>
					<td><input type="text" name="updateTitle"
						class="form-control" placeholder="新闻标题"
						value="<%=aNews.getTitle() %>" /></td>
				</tr>
				<tr>
					<td>新闻作者</td>
					<td><input type="text" name="updateAuthor"
						class="form-control" placeholder="新闻作者"
						value="<%=aNews.getAuthor() %>" /></td>
				</tr>
				<tr>
					<td>新闻内容</td>
					<td><textarea rows="8" cols="50" name="updateNcontent" class="form-control" ><%=aNews.getNcontent() %></textarea></td>
				</tr>
				<tr>
					<td>新闻摘要</td>
					<td><textarea rows="4" cols="50" name="updateSummary" class="form-control" ><%=aNews.getSummary() %></textarea></td>
				</tr>								
				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交"
						class="btn btn-primary" /> <input type="reset" value="重置"
						class="btn btn-primary" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>