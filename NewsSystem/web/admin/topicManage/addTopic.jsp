<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-tdeme.min.css"></script>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<title>添加分类页面</title>
</head>
<body style="margin:15px 25px 25px 25px;width:500px">
<%--顶部 --%>
	<nav class="navbar navbar-default" role="navigation">
	<%--导航栏 --%>
	   <div class="navbar-header">
      <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-plus">添加栏目</span></a>
   </div>
	</nav>
	<%--数据表格 --%>
	<div class="panel panel-dafault">
		<form action="AddTopicAction" method="post">
			<table class="table" style="width: 400px">
				<%-- 设置组件 --%>
				<tr>
					<td>栏目名称</td>
					<td><input type="text" name="inputTname"
						class="form-control" placeholder="栏目名称" /></td>
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