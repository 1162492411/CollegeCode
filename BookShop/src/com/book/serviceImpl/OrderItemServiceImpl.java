package com.book.serviceImpl;

import com.book.dao.OrderItemMapper;
import com.book.pojo.OrderItem;
import com.book.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mo on 2016/12/13.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public Boolean add(OrderItem record) {
        return this.orderItemMapper.insertSelective(record) > 0;
    }
}
