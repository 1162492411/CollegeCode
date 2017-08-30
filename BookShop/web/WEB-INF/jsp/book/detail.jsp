<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书详细信息</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<form class="form-horizontal" role="form" style="width:40%;height:80%;position:absolute;left:30%;top:10%" >
<%--顶部 --%>
<nav class="navbar navbar-default" role="navigation">
    <%--顶部导航栏 --%>
    <div class="navbar-header">
        <a class="navbar-brand" href="#">
            <span class="glyphicon glyphicon-info-sign">  图书详细信息</span>
        </a>
    </div>
</nav>

<%-- 数据 --%>
    <%-- id --%>
    <div class="form-group">
        <label for="bid" class="col-sm-3 control-label">图书ID</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="bid" value="${book.bid}" disabled>
        </div>
    </div>

    <%-- 书名 --%>
    <div class="form-group">
        <label for="title" class="col-sm-3 control-label">图书标题</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="title" value="${book.title}" disabled>
        </div>
    </div>

    <%-- 单价 --%>
    <div class="form-group">
        <label for="price" class="col-sm-3 control-label">单价</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="price" value="${book.price}" disabled>
        </div>
    </div>

    <%-- 作者 --%>
    <div class="form-group">
        <label for="author" class="col-sm-3 control-label">作者</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="author" value="${book.author}" disabled>
        </div>
    </div>

    <%-- 书号 --%>
    <div class="form-group">
        <label for="isbn" class="col-sm-3 control-label">书号</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="isbn" value="${book.isbn}" disabled>
        </div>
    </div>

</form>
</body>
</html>
