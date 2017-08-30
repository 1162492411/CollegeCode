package com.book.dao;

import com.book.pojo.Order;

import java.util.List;

public interface OrderMapper {

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer oid);

    List selectByUid(Integer uid);//获取用户所有订单

    Order selectByCondition(Order record);//获取符合条件的订单
}