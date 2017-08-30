package com.book.serviceImpl;

import com.book.dao.OrderMapper;
import com.book.pojo.Order;
import com.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mo on 2016/12/13.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Boolean add(Order record) {
        return this.orderMapper.insertSelective(record) > 0;
    }

    @Override
    public List selectByUid(Integer uid) {
        return this.orderMapper.selectByUid(uid);
    }

    @Override
    public Order selectByPrimaryKey(Integer oid) {
        return this.orderMapper.selectByPrimaryKey(oid);
    }

    @Override
    public Order selectByCondition(Order record) {
        return this.orderMapper.selectByCondition(record);
    }
}
