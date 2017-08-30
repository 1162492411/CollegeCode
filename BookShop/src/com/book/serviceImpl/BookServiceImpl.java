package com.book.serviceImpl;

import com.book.dao.BookMapper;
import com.book.pojo.Book;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mo on 2016/12/13.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book selectById(Integer id) {
        return this.bookMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean add(Book record) {
        return this.bookMapper.insertSelective(record) > 0;
    }

    @Override
    public List selectAll() {
        return this.bookMapper.selectAll();
    }

    @Override
    public Boolean update(Book record) {
        return this.bookMapper.updateByPrimaryKeySelective(record) > 0;
    }
}
