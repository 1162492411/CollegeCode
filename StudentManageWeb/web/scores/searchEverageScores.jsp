<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询平均分页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="screen" type="text/css" />
</head>
<body>
<form action="searchEverageScores">
<p>课程:<input type="text"  name="searchCourseId"/></p>
<td clospan="2" align="center">
<input value="重置" type="reset"> 
<input value="查询" type="submit"> 
</td>
</form>
</body>
</html>