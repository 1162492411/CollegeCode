package com.book.service;

import com.book.pojo.Order;

import java.util.List;

/**
 * Created by Mo on 2016/12/13.
 */
public interface OrderService {

    Boolean add(Order record);//添加订单

    List selectByUid(Integer uid);//查询用户的所有订单

    Order selectByPrimaryKey(Integer oid);//查询指定的订单信息

    Order selectByCondition(Order record);//查询符合条件的订单
}
