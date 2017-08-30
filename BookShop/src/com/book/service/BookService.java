package com.book.service;

import com.book.pojo.Book;

import java.util.List;

/**
 * Created by Mo on 2016/12/13.
 */
public interface BookService {

    Book selectById(Integer id);//根据id返回图书信息

    Boolean add(Book record);//添加图书

    List selectAll();//获取所有图书信息

    Boolean update(Book record);//修改图书信息
}
