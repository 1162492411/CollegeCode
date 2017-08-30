<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入bootStrap源文件 -->
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-tdeme.min.css"></script>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<title>添加用户页面</title>
</head>
<body style="margin:15px 25px 25px 25px;width:500px">
<%--顶部 --%>
	<nav class="navbar navbar-default" role="navigation">
	<%--导航栏 --%>
	   <div class="navbar-header">
      <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-plus">添加用户</span></a>
   </div>
	</nav>
	<%--数据表格 --%>
	<div class="form-group"> 
	<div class="panel panel-dafault">
		<form action="AddUserAction" method="post" name="addForm">
			<table class="table" style="width: 400px">
				<%-- 设置组件 --%>
				<tr>
					<td>用户账号</td>
					<td><input type="text" name="inputUsername"
						class="form-control" placeholder="用户账号" /></td>
				</tr>
				<tr>
					<td>用户密码</td>
					<td><input type="text" name="inputUserpwd"
						class="form-control" placeholder="密码" /></td>
				</tr>
				<tr>
					<td>电子邮件</td>
					<td><input type="email" name="inputEmail" class="form-control"
						placeholder="邮箱" /></td>
				</tr>
				<tr>
					<td>地址</td>
					<td><input type="text" name="inputAddress"
						class="form-control" placeholder="地址" /></td>
				</tr>
				<tr>
					<td>爱好</td>
					<td><input type="text" name="inputHobby" class="form-control"
						placeholder="爱好" /></td>
				</tr>
				<tr>
				<td colspan="2" align="center">
					<button  type="submit" class="btn btn-primary" >提交</button>
					<button  type="reset" class="btn btn-primary">重置</button>
				</td>
				</tr>
			</table>
		</form>
		</div><!-- panel panel-dafaul结束 -->
	</div><!-- form-group 结束 -->
	
</body>
</html>