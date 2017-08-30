package com.book.dao;

import com.book.pojo.User;

public interface UserMapper {
    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);
}