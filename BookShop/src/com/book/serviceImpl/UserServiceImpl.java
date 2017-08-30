package com.book.serviceImpl;

import com.book.dao.UserMapper;
import com.book.pojo.User;
import com.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mo on 2016/12/13.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private User selectById(User user){
        return this.userMapper.selectByPrimaryKey(user.getUserId());
    }

    @Override
    public Boolean validatePass(User user) {
        return  user.getPassword().equals(selectById(user).getPassword());
    }

    @Override
    public Boolean isAdmin(User user) {
        return selectById(user).getAdmin();
    }

    @Override
    public Boolean add(User record) {
        return this.userMapper.insertSelective(record) > 0;
    }

}
