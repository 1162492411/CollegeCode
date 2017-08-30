<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,scoresDB.Scores" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示平均分页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="screen" type="text/css" /> 
</head>
<body>
<%ArrayList<Scores> scoresList = (ArrayList<Scores>)request.getAttribute("scoresList");%>
<div id="container">   

	<table class="zebra">
		<thead>
        	<tr>
				<th>课程编号</th>
				<th>平均成绩</th>
            </tr>
		</thead>	
		<!-- 下面将查询到的结果进行循环输出 -->
        <tbody>  	
        <%
        if(scoresList != null){
        for(int i =0; i< scoresList.size();i++){
        	Scores sco = scoresList.get(i);
        	out.print("<tr><td>" + sco.getCourseId() + "</td>");
        	out.print("<td>" + sco.getRemark() + "</td></tr>");   
       	 }
        }        
        %>
        </tbody>
		</table>
</div>
</body>
</html>