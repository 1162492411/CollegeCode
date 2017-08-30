package com.book.controller;


import com.book.pojo.Cart;
import com.book.pojo.Order;
import com.book.pojo.OrderItem;
import com.book.service.OrderItemService;
import com.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Mo on 2016/12/13.
 */
@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    //前往结账页面/添加订单页面
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String toAddOrder(Double total, Model model){
        model.addAttribute("totalC",total);
        return "order/add";
    }

    //结账
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public @ResponseBody String addOrder(@RequestBody Order order, HttpSession session, Cart cart, OrderItem orderItem){
        if(orderService.add(order)){
            int oid = orderService.selectByCondition(order).getOid();
            cart = (Cart)session.getAttribute("cart");//持久化购物车中的订单条目
            for (int i = 0; i < cart.getList().size(); i++) {
                orderItem = (OrderItem)cart.getList().get(i);
                orderItem.setOid(oid);
                orderItemService.add(orderItem);
            }
            cart.removeAll();//持久化后清空购物车
            return "suc";
        }
        else return "err";
    }

    //前往查看用户所有订单页面
    @RequestMapping(method = RequestMethod.GET)
    public String toOrders(){
        return "order/orders";
    }

    //前往查看订单详情页面
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String toOrderDetail(@PathVariable int id, Model model) {
        model.addAttribute("id",id);
        return "order/detail";
    }

    //获取订单详情信息
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public @ResponseBody Order getOrderDetail(@PathVariable int id){
        return orderService.selectByPrimaryKey(id) ;
    }


    //查询用户所有订单
    @RequestMapping(value = "{id}/orders", method = RequestMethod.GET)
    public @ResponseBody List getOrders(@PathVariable int id){
        return this.orderService.selectByUid(id);
    }

}
