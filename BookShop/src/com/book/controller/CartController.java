package com.book.controller;

import com.book.pojo.Cart;
import com.book.pojo.OrderItem;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Mo on 2016/12/15.
 */
@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private BookService bookService;

    //前往查看购物车页面
    @RequestMapping(method = RequestMethod.GET)
    public String toCart(){
        return "order/cart";
    }

    //获取购物车信息
    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody
    List toCart(HttpSession session, Cart cart){
        cart = (Cart)session.getAttribute("cart");
        return fillCart(cart);
    }

    //填充购物车中的图书信息并返回购物车信息列表
    private List fillCart(Cart cart){
        int size = cart.getList().size();
        for (int i = 0; i < size; i++) {
            OrderItem orderItem = (OrderItem)cart.getList().get(i);
            orderItem.setBook(bookService.selectById(orderItem.getBid()));
        }
        return cart.getList();
    }

    //添加图书到购物车
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody String addCart(@RequestBody OrderItem record, HttpSession session, Cart cart){
        cart = (Cart) session.getAttribute("cart");
        if(cart.add(record)) return "suc";
        else return "err";
    }

    //将图书从购物车删除
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody String removeCart(@RequestBody Map map, HttpSession session, Cart cart){
        cart = (Cart) session.getAttribute("cart");
        if(cart.remove(Integer.parseInt(map.get("bid") + ""))) return "suc";
        else return "err";
    }

}
