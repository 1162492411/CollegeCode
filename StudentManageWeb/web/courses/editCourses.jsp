<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,coursesDB.Courses"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑课程页面</title>
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" media="screen"
	type="text/css" />
<body>
	编辑课程
	<form action="editCourses" name="edit" class="zebra">
		<div id="container">
			<table>
				<tr>
					<th>课程编号</th>
					<th>课程名称</th>
					<th>任课教师</th>
					<th>学时数</th>
					<th>学分</th>
					<th>备注</th>
				</tr>
				<tr>
				<%
				
				%>
					<th><input type="text" id="Id" name="formerId"></th>
					<th><input type="text" id="Name" name="editName"></th>
					<th><input type="text" id="Teacher" name="editTeacher"></th>
					<th><input type="text" id="Period" name="editPeriod"></th>
					<th><input type="text" id="Credit" name="editCredit"></th>
					<th><input type="text" id="Remark" name="editRemark"></th>
				</tr>
			</table>
		</div>
		<p align="center">
			<input type="reset" value="重置" /> 
			<input type="submit" value="提交" >
		</p>
	</form>

</body>
</html>