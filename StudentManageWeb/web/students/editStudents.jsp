<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" media="screen"
	type="text/css" />
<body>
	编辑学生
	<form action="editStudents" name="edit" class="zebra">
		<div id="container">
			<table>
				<tr>
					<th>学号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>专业</th>
					<th>电话</th>
					<th>备注</th>
				</tr>
				<tr>
					<th><input type="text" id="Id" name="formerId"></th>
					<th><input type="text" id="Name" name="editName"></th>
					<th><input type="text" id="Sex" name="editSex"></th>
					<th><input type="text" id="Project" name="editProject"></th>
					<th><input type="text" id="Phone" name="editPhone"></th>
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