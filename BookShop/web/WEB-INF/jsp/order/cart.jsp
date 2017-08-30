<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/books.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            getCart();
        });
    </script>
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
                <li class="active"><a href="${pageContext.request.contextPath}/cart">查看购物车</a></li>
                <li><a href="${pageContext.request.contextPath}/order">查看订单</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="all" style="width:80%;height:80%;position:absolute;left:10%;top:10%" id="cartDiv">
    <%--顶部 --%>
    <nav class="navbar navbar-default" role="navigation">
        <%--顶部导航栏 --%>
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <span class="glyphicon glyphicon-shopping-cart">  购物车</span>
            </a>
        </div>

    </nav>
    <%-- 数据表格 --%>
    <div class="table-responsive">
        <table class="table table-hover" id="booksTable">
            <%-- 设置表头 --%>
            <thead>
            <tr>
                <th>名称</th>
                <th>作者</th>
                <th>书号</th>
                <th>总价</th>
                <th>操作</th>
            </tr>
            </thead>
            <%-- 订单数据表--%>
            <tbody id="bookTable">
            </tbody>
        </table>
        <%-- 按钮面板 --%>
        <div id="buttonPanel">
            <div class="form-group clearfix">
                <Button class="btn btn-info pull-right pl20 pr20" id="totalButton">总金额: 0</Button>
            </div>
            <div class="form-group clearfix">
                <a href="" id="addOrderLink">
                    <Button class="btn btn-primary pull-right pl20 pr20" id="addOrderButton" disabled>结账</Button>
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
