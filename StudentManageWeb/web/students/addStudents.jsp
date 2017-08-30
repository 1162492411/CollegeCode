<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加学生页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="screen" type="text/css" />
</head>
<body>
<p align="center">添加学生</p>
<form action="addStudents">
<table>
<thead>
<tr>
	<th>学号<input type="text" name="addId"></th>
</tr>
<tr>
	<th>姓名<input type="text" name="addName"></th>
</tr>
<tr>
	<th>性别<input type="text" name="addSex"></th>
</tr>
<tr>
	<th>专业<input type="text" name="addProject"></th>
</tr>
<tr>
	<th>电话<input type="text" name="addPhone"></th>
</tr>
<tr>
	<th>备注<input type="text" name="addRemark"></th>
</tr>
</thead>
</table>
<p align="center">
<input  type="reset" value="重置" />
<input  type="submit" value="提交" />
</p>
</form>
</body>
</html>