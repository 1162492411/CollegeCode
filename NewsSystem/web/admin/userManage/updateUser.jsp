<%@ page language="java"
	import="java.util.ArrayList,com.zyg.entity.User,com.zyg.dao.UserDao,com.zyg.dao.impl.UserDaoImpl"
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
<title>修改用户页面</title>
</head>
<body>
	<%
		User aUser = new UserDaoImpl().searchUser(Integer.parseInt(request.getParameter("id")));
	%>
	<div class="panel panel-dafault">
		<div class="panel-heading">
			<span class="glyphicon glyphicon-pencil"> 修改用户 </span>
		</div>
		<form action="UpdateUserAction" method="post">
			<table class="table" style="width: 400px">
				<%-- 设置组件 --%>
				<tr style="display:none">
					<td>用户ID</td>
					<td><input type="text" name="updateId"
						class="form-control" placeholder="ID"
						value="<%=aUser.getId()%>" /></td>
				</tr>				
				<tr>
					<td>用户帐号</td>
					<td><input type="text" name="updateUsername"
						class="form-control" placeholder="账户"
						value="<%=aUser.getUsername()%>" /></td>
				</tr>
				<tr>
					<td>用户密码</td>
					<td><input type="text" name="updateUserpwd"
						class="form-control" placeholder="密码"
						value="<%=aUser.getUserpwd()%>" /></td>
				</tr>
				<tr>
					<td>电子邮件</td>
					<td><input type="email" name="updateEmail" class="form-control"
						placeholder="邮箱" value="<%=aUser.getEmail()%>" /></td>
				</tr>
				<tr>
					<td>地址</td>
					<td><input type="text" name="updateAddress"
						class="form-control" placeholder="地址"
						value="<%=aUser.getAddress()%>" /></td>
				</tr>
				<tr>
					<td>爱好</td>
					<td><input type="text" name="updateHobby" class="form-control"
						placeholder="爱好" value="<%=aUser.getHobby()%>" /></td>
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