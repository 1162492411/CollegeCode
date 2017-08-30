<%@ page language="java"
	import="java.util.ArrayList,com.zyg.entity.Topic,com.zyg.dao.TopicDao,com.zyg.dao.impl.TopicDaoImpl"
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
<title>修改分类页面</title>
</head>
<body>
	<%
		Topic aTopic = new TopicDaoImpl().searchTopic(Integer.parseInt(request.getParameter("id")));
	%>
	<div class="panel panel-dafault">
		<div class="panel-heading">
			<span class="glyphicon glyphicon-pencil"> 修改分类 </span>
		</div>
		<form action="UpdateTopicAction" method="post">
			<table class="table" style="width: 400px">
				<%-- 设置组件 --%>
				<tr style="display:none">
					<td>分类ID</td>
					<td><input type="text" name="updateTid"
						class="form-control" placeholder="ID"
						value="<%=aTopic.getId()%>" /></td>
				</tr>				
				<tr>
					<td>分类名称</td>
					<td><input type="text" name="updateTname"
						class="form-control" placeholder="分类名称"
						value="<%=aTopic.getTname()%>" /></td>
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