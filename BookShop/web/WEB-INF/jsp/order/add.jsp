<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加订单</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/books.js"></script>

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
<div class="form-horizontal" role="form" style="width:40%;height:80%;position:absolute;left:30%;top:10%" >
    <%--顶部 --%>
    <nav class="navbar navbar-default" role="navigation">
        <%--顶部导航栏 --%>
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <span class="glyphicon glyphicon-usd">  添加订单</span>
            </a>
        </div>
    </nav>

    <%-- 数据 --%>
    <form method="post" >
        <%--<input type="hidden" name="_method" value="POST" />--%>
        <input type="hidden" id="userid" value="${uuid}" />
    <%-- 邮编 --%>
    <div class="form-group">
        <label for="zipcode" class="col-sm-3 control-label">邮政编码</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="邮政编码" >
        </div>
    </div>

    <%-- 地址 --%>
    <div class="form-group">
        <label for="address" class="col-sm-3 control-label">送货地址</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="address" name="address" placeholder="送货地址" >
        </div>
    </div>

    <%-- 总价 --%>
    <div class="form-group">
        <label for="totalCount" class="col-sm-3 control-label">订单金额</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="totalCount" name="totalCount" value="${totalC}" placeholder="订单金额" disabled>
        </div>
    </div>
        <div class="form-group clearfix">
            <Button class="btn btn-primary pull-right pl20 pr20" type="button" onclick="addOrder()">付款</Button>
        </div>
    </form>
</div>
</body>
</html>
