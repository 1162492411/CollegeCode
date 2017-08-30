<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加图书</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/books.js"></script>
</head>
<body>
<form class="form-horizontal" role="form" style="width:40%;height:80%;position:absolute;left:30%;top:10%">
    <nav class="navbar navbar-default" role="navigation">
        <%--顶部导航栏 --%>
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <span class="glyphicon glyphicon-plus">  添加图书</span>
            </a>
        </div>
    </nav>

    <%-- 数据 --%>
    <%-- 书名 --%>
    <div class="form-group">
        <label for="title" class="col-sm-3 control-label">图书标题</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="title" placeholder="图书标题" >
        </div>
    </div>

    <%-- 单价 --%>
    <div class="form-group">
        <label for="price" class="col-sm-3 control-label">单价</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="price" placeholder="图书单价" >
        </div>
    </div>

    <%-- 作者 --%>
    <div class="form-group">
        <label for="author" class="col-sm-3 control-label">作者</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="author" placeholder="图书作者" >
        </div>
    </div>

    <%-- 书号 --%>
    <div class="form-group">
        <label for="isbn" class="col-sm-3 control-label">书号</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="isbn" placeholder="图书书号" >
        </div>
    </div>
    <div class="form-group clearfix">
        <Button class="btn btn-primary pull-right pl20 pr20" id="addBookButton" onclick="addBook()">添加</Button>
    </div>
</form>
</body>
</html>
