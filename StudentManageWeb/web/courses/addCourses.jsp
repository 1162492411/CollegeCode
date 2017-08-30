<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加课程页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="screen" type="text/css" />
</head>
<body>
<p align="center">添加课程</p>
<form action="addCourses">
<table>
<thead>
<tr>
	<th>课程编号<input type="text" name="addId"></th>
</tr>
<tr>
	<th>课程名称<input type="text" name="addName"></th>
</tr>
<tr>
	<th>任课教师<input type="text" name="addTeacher"></th>
</tr>
<tr>
	<th>课程学时<input type="text" name="addPeriod"></th>
</tr>
<tr>
	<th>课程学分<input type="text" name="addCredit"></th>
</tr>
<tr>
	<th>课程备注<input type="text" name="addRemark"></th>
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