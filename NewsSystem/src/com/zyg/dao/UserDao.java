package com.zyg.dao;

import java.util.ArrayList;

import com.zyg.entity.User;

public interface UserDao {
	public boolean isUserExist(String name);//验证账户是否存在
	public boolean validateUser(String name, String password);//验证账户密码是否存在
	public ArrayList<User> getAllUsers();//获取所有用户
	public boolean addUser(User aUser);//添加用户
	public boolean deleteUser(int id);//删除用户
	public boolean updateUser(User aUser);//修改用户
	public User searchUser(int id);//查询用户
	public ArrayList<User> getUsersByPage(int pageIndex, int pageSize);//从指定位置开始获取指定数量的记录
	public int getCount();//获取总记录数
}
