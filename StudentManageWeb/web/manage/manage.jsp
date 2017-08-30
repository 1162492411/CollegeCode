<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理页面</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="screen" type="text/css" /> 
</head>
<body>
	<div class="left-menu">
		<div class="logo">
			<i class="fa fa-align-justify"></i>
			<div></div>
		</div>
		<div class="accordion">
			<div class="section">
				<input type="radio" name="accordion-1" id="section-1"
					checked="checked" /> <label for="section-1"><span><a href="${pageContext.request.contextPath}/students/showStudents.jsp" target="mainFrame">学生管理</a></span></label>
				<div class="content">
					<ul>
						<li><i class="fa fa-inbox"></i><span><a href="${pageContext.request.contextPath}/students/addStudents.jsp" target="mainFrame">添加学生</a></span></li>
						<li><i class="fa fa-share"></i><span><a href="${pageContext.request.contextPath}/students/deleteStudents.jsp" target="mainFrame">删除学生</a></span></li>
						<li><i class="fa fa-archive"></i><span><a href="${pageContext.request.contextPath}/students/editStudents.jsp" target="mainFrame">修改学生</a></span></li>
						<li><i class="fa fa-archive2"></i><span><a href="${pageContext.request.contextPath}/students/searchStudents.jsp" target="mainFrame">查询学生</a></span></li>
					</ul>
				</div>
			</div>
			<div class="section">
				<input type="radio" name="accordion-1" id="section-2" value="toggle" />
				<label for="section-2"><span><a href="${pageContext.request.contextPath}/courses/showCourses.jsp" target="mainFrame">课程管理</a></span></label>
				<div class="content">
					<ul>
						<li><i class="fa fa-cog"></i><span><a href="${pageContext.request.contextPath}/courses/addCourses.jsp" target="mainFrame">添加课程</a></span></li>
						<li><i class="fa fa-group"></i><span><a href="${pageContext.request.contextPath}/courses/deleteCourses.jsp" target="mainFrame">删除课程</a></span></li>
						<li><i class="fa fa-sitemap"></i><span><a href="${pageContext.request.contextPath}/courses/editCourses.jsp" target="mainFrame">修改课程</a></span></li>
						<li><i class="fa fa-sitemap2" ></i><span><a href="${pageContext.request.contextPath}/courses/searchCourses.jsp" target="mainFrame">查询课程</a></span></li>
					</ul>
				</div>
			</div>
			<div class="section">
				<input type="radio" name="accordion-1" id="section-3" value="toggle" />
				<label for="section-3"><span><a href="${pageContext.request.contextPath}/scores/showScores.jsp" target="mainFrame">成绩管理</a></span></label>
				<div class="content">
					<ul>
<li><i class="fa fa-coffee"></i><span><a href="${pageContext.request.contextPath}/scores/addScores.jsp" target="mainFrame">录入成绩</a></span></li>
<li><i class="fa fa-sitemap"></i><span><a href="${pageContext.request.contextPath}/scores/searchScores.jsp" target="mainFrame">查询成绩</a></span></li>
<li><i class="fa fa-sitemap"></i><span><a href="${pageContext.request.contextPath}/scores/searchEverageScores.jsp" target="mainFrame">平均成绩</a></span></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div style="text-align: center; clear: both">
		<script src="${pageContext.request.contextPath}/js/manage.js" type="text/javascript"></script>
	</div>
</body>
</html>