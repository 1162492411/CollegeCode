<%@ page language="java"
	import="java.util.ArrayList,com.zyg.entity.Comment,com.zyg.dao.CommentDao,com.zyg.dao.impl.CommentDaoImpl"
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
<title>修改评论页面</title>
</head>
<body>
	<%
		Comment aComment = new CommentDaoImpl().searchComment(Integer.parseInt(request.getParameter("id")));
	%>
	<div class="panel panel-dafault">
		<div class="panel-heading">
			<span class="glyphicon glyphicon-pencil"> 修改评论 </span>
		</div>
		<form action="UpdateCommentAction" method="post">
			<table class="table" style="width: 400px">
				<%-- 设置组件 --%>
				<tr style="display:none">
					<td>评论ID</td>
					<td><input type="text" name="updateId"
						class="form-control" placeholder="评论ID"
						value="<%=aComment.getId()%>" /></td>
				</tr>				
				<tr style="display:none">
					<td>新闻编号</td>
					<td><input type="text" name="updateNid"
						class="form-control" placeholder="新闻编号"
						value="<%=aComment.getNid()%>" /></td>
				</tr>
				<tr>
					<td>评论内容</td>
					<td><textarea name="updateContent"
						class="form-control" placeholder="评论内容" ><%=aComment.getContent()%></textarea></td>
				</tr>
				<tr>
					<td>评论作者</td>
					<td><input type="text" name="updateAuthor" class="form-control"
						placeholder="评论作者" value="<%=aComment.getAuthor()%>" /></td>
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