<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,studentsDB.Students"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>学生展示页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="screen" type="text/css" /> 
</head>
<body>
<%ArrayList<Students> studentsList = (ArrayList<Students>)request.getAttribute("studentsList");
//request.setCharacterEncoding("GBK");
%>
<div id="container">   

	<table class="zebra">
		<thead>
        	<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>专业</th>
				<th>电话</th>
				<th>备注</th>
            </tr>
		</thead>
		
		
		<!-- 下面将查询到的结果进行循环输出 -->
        <tbody>  	
        <%
        if(studentsList != null){
        for(int i =0; i< studentsList.size();i++){
        	Students stu = studentsList.get(i);
   //     	String a = new String (stu.getId().getBytes("ISO8859-1"),"GBK");
   //     	String b = new String (stu.getName().getBytes("UTF-8"),"GBK");
   //     	String c = new String (stu.getSex().getBytes("UTF-8"),"GB2312");
   //     	String d = new String (stu.getProject().getBytes("UTF-8"),"ISO8859-1");
   //     	String e = new String (stu.getPhone().getBytes("ISO8859-1"),"GBK");
   //     	String f = new String (stu.getRemark().getBytes("ISO8859-1"),"GBK");
        	out.print("<tr><td>" + stu.getId() + "</td>");
        	out.print("<td>" + stu.getName() + "</td>");
        	out.print("<td>" + stu.getSex() + "</td>");
        	out.print("<td>" + stu.getProject() + "</td>");
        	out.print("<td>" + stu.getPhone() + "</td>");
        	out.print("<td>" + stu.getRemark() + "</td></tr>");   
       	 }
        }
        
        %>
        </tbody>
		</table>
</div>

</body>
</html>