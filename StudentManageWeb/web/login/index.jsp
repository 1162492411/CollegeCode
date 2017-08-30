<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>默认登录页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="screen" type="text/css" />
</head>
<body>
<!-- 默认登录界面，用户输入值后将信息传递给loginCheck进行检测 -->
<form action="loginCheck"  name="index" method="post">
<div align="center">
<h1  >用户登录</h1>
<p >用户名:<input name="loginName" type="text"></p>
<p >密&nbsp;&nbsp;&nbsp;码:<input name="loginPassword" type="password"></p>
<p >
	<a href=register.jsp><input value="注册" type="button"></a>
	<input value="登录" type="submit"> 
</p>
</div>
</form>
</body>
</html>