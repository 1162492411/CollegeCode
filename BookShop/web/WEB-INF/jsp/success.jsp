<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>默认页面</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<%-- 导航条 --%>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Books</a>
        </div>


<%-- 导航链接 --%>
<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
        <li><a href="${pageContext.request.contextPath}/books">浏览图书</a></li>
        <li><a href="${pageContext.request.contextPath}/cart">查看购物车</a></li>
        <li><a href="${pageContext.request.contextPath}/order">查看订单</a></li>
    </ul>
</div>
    </div>
</nav>
<div class="jumbotron" style="padding-left: 10%">
    <h1>Welcome！</h1>
    <p>欢迎来到Books商城</p>
    <br /><br /><br />
</div>
</body>
</html>
