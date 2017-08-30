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
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css"></script>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function checkUsername(name){
		window.location.replace("validateUser.jsp?username=" + name.value);
	}
</script>
<title>注册页面</title>
</head>
<body>
<%
	String usernamString ="";
	if(request.getParameter("username")!= null){
		usernamString = request.getParameter("username");
	}
%>
<!-- 该页面是注册页面，用户填表后判断是否信息在数据库中存在，
如不存在将信息传递给数据库，若存在，跳转到注册失败页面 -->
	<nav class="navbar navbar-default" role="navigation">
	<%--导航栏 --%>
	   <div class="navbar-header">
      <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-user">用户注册</span></a>
   </div>
   </nav>
	<%--数据表格 --%>
	<div class="form-group"> 
	<div class="panel panel-dafault">
		<form action="RegisterAction" method="post" name="register">
			<table class="table" style="width: 400px">
				<%-- 设置组件 --%>
				<tr>
					<td>用户账号</td>
					<td ><input type="text" name="loginUsername" id="loginUsername"
						class="form-control" placeholder="用户账号" value="<%=usernamString  %>" onblur="checkUsername(this)" /></td>
				</tr>
				<tr>
					<td>用户密码</td>
					<td><input type="text" name="loginUserpwd" id="loginUserpwd"
						class="form-control" placeholder="密码" /></td>
				</tr>
								<tr>
					<td>确认密码</td>
					<td><input type="text" name="loginUserpwd2" id="loginUserpwd2"
						class="form-control" placeholder="确认密码" /></td>
				</tr>
				<tr>
					<td>电子邮件</td>
					<td><input type="email" name="loginEmail" id="loginEmail"
					class="form-control" placeholder="邮箱" /></td>
				</tr>
				<tr>
					<td>地址</td>
					<td><input type="text" name="loginAddress" id="loginAddress"
						class="form-control" placeholder="地址" /></td>
				</tr>
				<tr>
					<td>爱好</td>
					<td><input type="text" name="loginHobby" id="loginHobby"
					 class="form-control" placeholder="爱好" /></td>
				</tr>
				<tr>
				<td colspan="2" align="center">
					<button  type="submit" class="btn btn-primary" ">提交</button>
					<button  type="reset" class="btn btn-primary">重置</button>
				</td>
				</tr>
			</table>
		</form>
		</div><!-- panel panel-dafaul结束 -->
	</div><!-- form-group 结束 -->
</body>
</html>