
//数量选择控件
function gw(){
    //加的效果
    $(".add").click(function(){
        var n=$(this).prev().val();
        var num=parseInt(n)+1;
        $(this).prev().val(num);
    });
    //减的效果
    $(".reduce").click(function(){
        var n=$(this).next().val();
        var num=parseInt(n)-1;
        $(this).next().val(num);
    });
}
//------------------------------------------------------------------------------------------------------

//检查获取到的图书信息
function checkBooksData(data) {
    if(data[0] == null){
        $("#booksTable").html("现在还没有图书，请等待管理员上架!");
        $("#booksTbody").html("");
    }
    else
        handleBooksData(data);
}

//处理获取到的所有图书信息
function handleBooksData(data) {
    $("#booksTbody").html("");//首先清空页面数据
    var count = data.length;
    for(var i = 0;i < count;i++){//遍历结果集
        $("#booksTbody").append(
            '<tr id = ' + data[i].bid + '>'
            + "<td>" + data[i].bid + "</td>"
            +"<td><a href='/books/" + data[i].bid + "'>" + data[i].title +"</a></td>"
            + "<td>" + data[i].price + "</td>"
            + "<td>" + data[i].author + "</td>"
            + "<td>" + data[i].isbn + "</td>"
            + "<td><div class='gw_num'><em class='reduce'>-</em><input type='text' value='1' class='num' id='NUM" + data[i].bid  +  " ' /><em class='add'>+</em></div></td>"
            + "<td><Button class='btn btn-primary' onclick='addCart(" + data[i].bid +  ")'>加入购物车</Button></td>"
            + "</tr>");
    }
    alert($("#booksTbody").html());
}

//获取所有图书
function getAllBooks() {
    $.ajax({
        type : 'GET',
        url: '/books/all',
        success: function(data){
            checkBooksData(data);
        },
        error:function(){
            alert("加载所有图书信息失败!");
        }
    });
}

//------------------------------------------------------------------------------------------------------

//添加图书
function addBook() {
    var book = {
        "title" : $("#title").val(),
        "price" : parseFloat($("#price").val()).toFixed(2),
        "author" : $("#author").val(),
        "isbn" : $("#isbn").val()
    }
    $.ajax({
        type : 'POST',
        url: '/books',
        data : JSON.stringify(book),
        contentType : 'application/json',
        success: function(data){
            if(data == "suc"){
                 alert("添加图书成功！");
            }
            else
                alert("添加图书失败！");
        },
        error:function(){
            alert("提交图书信息失败!");
        }
    });
}

//修改图书
function updateBook() {
        var book = {
            "bid" : parseInt($("#bid").val()),
            "title" : $("#title").val(),
            "price" : parseFloat($("#price").val()).toFixed(2),
            "author" : $("#author").val(),
            "isbn" : $("#isbn").val()
        }
        $.ajax({
            type : 'PUT',
            url: '/books',
            data : JSON.stringify(book),
            contentType : 'application/json',
            success: function(data){
                if(data == "suc"){
                    alert("修改图书成功！");
                }
                else
                    alert("修改图书失败！");
            },
            error:function(){
                alert("提交修改图书请求失败!");
            }
        });
}

//------------------------------------------------------------------------------------------------------
//添加到购物车
function addCart(id) {
    var record = {
        "bid" : id,
        "quantity" : $('#NUM' + id ).val()
    }
    $.ajax({
        type : 'PUT',
        url: '/cart',
        data : JSON.stringify(record),
        contentType : 'application/json',
        success: function(data){
            if(data == "suc"){
                alert("已将该图书加入购物车");
            }
            else
                alert("未将该图书加入购物车");
        },
        error:function(){
            alert("请求加入购物车失败!");
        }
    });
}
//------------------------------------------------------------------------------------------------------
//检查查询到的购物车信息
function checkCartData(data) {
    if(data[0] == null){
        $("#booksTable").html("您的购物车空空如也，快去\<a href='/books'>选购图书</a>吧~");
        $("#buttonPanel").html("");
    }
    else
        handleCartData(data);
}

//处理查询到的购物车信息
function handleCartData(data) {
    $("#bookTable").html("");//首先清空页面数据
    var count = data.length;
    var totalCount = 0;
    for(var i = 0;i < count;i++){//遍历结果集
        $("#bookTable").append(
            '<tr id = ' + data[i].bid + '>'
            +"<td><a href='/books/" + data[i].bid + "'>" + data[i].book.title +"</a></td>"
            + "<td>" + data[i].book.author + "</td>"
            + "<td>" + data[i].book.isbn + "</td>"
            + "<td>" + data[i].book.price + "*" + data[i].quantity + "=" + parseFloat(data[i].book.price * data[i].quantity).toFixed(2) + "</td>"
            + "<td><Button class='btn btn-danger' onclick='deleteOrderItem("  + data[i].bid +  ")'>删除</Button></td>"
            + "</tr>");
        totalCount += data[i].book.price * data[i].quantity;
    }
    if(totalCount == 0) {
        $("#addOrderButton").attr('disabled','true');
    }
    else{
        $("#addOrderButton").removeAttr('disabled');
        $("#totalButton").text('总金额:' + parseFloat(totalCount).toFixed(2));
        $("#addOrderLink").attr('href','/order/add?total=' + parseFloat(totalCount).toFixed(2));
    }
}


//查询购物车
function getCart(){
    $.ajax({
        type : 'POST',
        url : '/cart',
        success: function(data){
            checkCartData(data);
        },
        error:function(){
            alert("请求查看购物车失败!");
        }
    });
}
//------------------------------------------------------------------------------------------------------
//页面中从购物车删除图书
function deleteFromCart(bid) {
    $("tr[id=" + bid + "]").remove();
}

//数据中从购物车删除图书
function deleteOrderItem(bid) {
    $.ajax({
        type : 'DELETE',
        url : '/cart',
        data : JSON.stringify({"bid" : parseInt(bid)}),
        contentType : 'application/json',
        success: function(data){
            if(data == "suc"){
                deleteFromCart(bid);
                alert("删除成功！");
            }
            else
              alert("删除失败!");

        },
        error:function(){
            alert("请求查看购物车失败!");
        }
    });

}
//------------------------------------------------------------------------------------------------------
//检查查询到的历史订单信息
function checkOrdersData(data) {
    if(data[0] == null){
        $("#orderTable").html("您的历史订单空空如也，快去\<a href='/books'>选购图书</a>吧~");
    }
    else
        handleOrdersData(data);
}

//处理查询到的所有历史订单信息
function handleOrdersData(data) {
    $("#ordersTable").html("");//首先清空页面数据
    var count = data.length;
    for(var i = 0;i < count;i++){//遍历结果集
        $("#ordersTable").append(
            '<tr id = ' + data[i].oid + '>'
            +"<td><a href='/order/" + data[i].oid + "'>" + data[i].oid +"</a></td>"
            + "<td>" + data[i].zipcode + "</td>"
            + "<td>" + data[i].address + "</td>"
            + "<td>" + data[i].total + "</td>"
            + "</tr>");
    }
}

//查询所有历史订单
function getOrders(uuid){
    $.ajax({
        type : 'GET',
        url: '/order/' + uuid +  '/orders',
        success: function(data){
            checkOrdersData(data);
        },
        error:function(){
            alert("请求历史订单失败!");
        }
    });
}
//------------------------------------------------------------------------------------------------------
//处理查询到的指定订单的信息
function handleOrderData(data) {
    $("#orderDetailTbody").html("");//首先清空页面数据
    var count = data.orders.length;
    for(var i = 0;i < count;i++){//遍历结果集
        $("#orderDetailTbody").append(
            '<tr id = ' + data.orders[i].itemid + '>'
            + "<td>" + data.orders[i].itemid + "</td>"
            +"<td><a href='/books/" + data.orders[i].bid + "'>" + data.orders[i].book.title +"</a></td>"
            + "<td>" + data.orders[i].book.price + "*" + data.orders[i].quantity + "=" + parseFloat(data.orders[i].book.price * data.orders[i].quantity).toFixed(2) + "</td>"
            + "<td>" + data.address + "</td>"
            + "</tr>");
    }
}

//查询指定订单信息
function getOrder(oid) {
    $.ajax({
        type : 'POST',
        url: '/order/' + oid ,
        success: function(data){
            handleOrderData(data);

        },
        error:function(){
            alert("获取订单详细信息失败!");
        }
    });
}

//------------------------------------------------------------------------------------------------------

//付款
function addOrder(){
        var record ={
            "userid" : parseInt($("#userid").val()),
            "zipcode" : $("#zipcode").val(),
            "address" : $("#address").val(),
            "total" : parseFloat($("#totalCount").val()).toFixed(2)
        };
        $.ajax({
            type : 'POST',
            url: '/order/add',
            data: JSON.stringify(record),
            contentType : 'application/json',
            success: function(data){
                if(data == "suc"){
                    alert("付款成功！");
                    location.replace("/order");
                }
                else
                    alert("付款失败！");
            },
            error:function(){
                alert("发送付款请求失败!");
            }
        });
}
