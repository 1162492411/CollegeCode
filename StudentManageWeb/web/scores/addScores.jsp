<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加成绩页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="screen" type="text/css" />
</head>
<body>
<p align="center">添加成绩</p>
<form action="addScores">
<table>
<thead>
<tr>
	<th>学号<input type="text" name="addStudentId"></th>
</tr>
<tr>
	<th>课程号<input type="text" name="addCourseId"></th>
</tr>
<tr>
	<th>成绩<input type="text" name="addScore"></th>
</tr>
<tr>
	<th>学年<input type="text" name="addTerm"></th>
</tr>
<tr>
	<th>备注<input type="text" name="addRemark"></th>
</tr>
</thead>
<td colspan="2" align="center">
<input  type="reset" value="重置" />
<input  type="submit" value="提交" />
</td>
</table>
</form>
</body>
</html>