<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书列表</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/books.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/books.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            gw();
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
                <li class="active"><a href="${pageContext.request.contextPath}/books">浏览图书</a></li>
                <li><a href="${pageContext.request.contextPath}/cart">查看购物车</a></li>
                <li><a href="${pageContext.request.contextPath}/order">查看订单</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="all" style="width:80%;height:80%;position:absolute;left:10%;top:10%">
    <%--顶部 --%>
    <nav class="navbar navbar-default" role="navigation">
        <%--顶部导航 --%>
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <span class="glyphicon glyphicon-th-list">  图书列表</span>
            </a>
        </div>

    </nav>
    <%-- 数据表格 --%>
    <div class="table-responsive">
        <table class="table table-hover" id="booksTable">
            <%-- 设置表头 --%>
            <thead>
            <tr>
                <th>ID</th>
                <th>名称</th>
                <th>单价</th>
                <th>作者</th>
                <th>书号</th>
                <th>数量</th>
                <th>操作</th>
            </tr>
            </thead>

            <%-- 订单数据表--%>
            <tbody id="booksTbody">
            <c:forEach var="book" items="${books}">
                <tr id="${book.bid}">
                    <td>${book.bid}</td>
                    <td><a href="/books/${book.bid}">${book.title}</a></td>
                    <td>${book.price}</td>
                    <td>${book.author}</td>
                    <td>${book.isbn}</td>
                    <td>
                        <div class="gw_num">
                            <em class="reduce">-</em>
                            <input type="text" value="1" class="num" id="NUM${book.bid}"/>
                            <em class="add">+</em>
                        </div>
                    </td>
                    <td>
                        <Button class="btn btn-primary" onclick="addCart(${book.bid})">加入购物车</Button>
                    </td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
