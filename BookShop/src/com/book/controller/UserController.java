package com.book.controller;

import com.book.pojo.Cart;
import com.book.pojo.User;
import com.book.service.OrderService;
import com.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Mo on 2016/12/13.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    //前往登录页面
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLogin() {
        return "user/login";
    }

    //登录验证
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String validateLogin(User user,Cart cart, HttpSession session){
        if(this.userService.validatePass(user)){
            session.setAttribute("uuid",user.getUserId());
            session.setAttribute("cart",cart);
            if(this.userService.isAdmin(user))  return "admin";
            else return "success";
        }
        else return "user/login";
    }

}
