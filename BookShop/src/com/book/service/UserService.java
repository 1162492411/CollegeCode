package com.book.service;

import com.book.pojo.User;

/**
 * Created by Mo on 2016/12/13.
 */
public interface UserService {

    Boolean validatePass(User user);//验证密码

    Boolean isAdmin(User user);//验证用户是否为管理员

    Boolean add(User record);//添加用户

}
