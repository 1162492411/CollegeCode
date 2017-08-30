<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" import="java.util.*,coursesDB.Courses" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程展示页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="screen" type="text/css" /> 
</head>
<body>
<%ArrayList<Courses> coursesList = (ArrayList<Courses>)request.getAttribute("coursesList");

%>
<div id="container">   

	<table class="zebra">
		<thead>
        	<tr>
				<th>课程编号</th>
				<th>课程名称</th>
				<th>任课教师</th>
				<th>学时数</th>
				<th>学分</th>
				<th>备注</th>
            </tr>
		</thead>
		
		
		<!-- 下面将查询到的结果进行循环输出 -->
        <tbody>  	
        <%
        if(coursesList != null){
        for(int i =0; i< coursesList.size();i++){
        	Courses cou = coursesList.get(i);
        	out.print("<tr><td>" + cou.getId() + "</td>");
        	out.print("<td>" + cou.getName() + "</td>");
        	out.print("<td>" + cou.getTeacher() + "</td>");
        	out.print("<td>" + cou.getPeriod() + "</td>");
        	out.print("<td>" + cou.getCredit() + "</td>");
        	out.print("<td>" + cou.getRemark() + "</td></tr>");   
       	 }
        }
        
        %>
        </tbody>
		</table>
</div>
</body>
</html>
