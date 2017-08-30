package com.zyg.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.swing.internal.plaf.synth.resources.synth;
import com.zyg.dao.UserDao;
import com.zyg.entity.User;
import com.zyg.util.DaoFactory;

public class UserDaoImpl implements UserDao {
	private Connection con = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	private String sql = null;
	private int c = 0;

	// 获取所有用户
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<User>();
		try {
			con = DaoFactory.getConnection();// 创建连接对象
			String sql = "select * from t_user";// 定义sql语句
			st = con.prepareStatement(sql);// 创建命令对象
			rs = st.executeQuery();// 执行命令
			while (rs.next()) {// 循环
				// 读取一行中的每一列信息,将其封装进对象
				User aUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
				users.add(aUser);// 将对象放入集合中
			}
		} catch (Exception e) {
		} finally {// 释放资源
			DaoFactory.closeAll(con, st, rs);
		}
		return users;
	}
	//验证账户是否存在
	public boolean isUserExist(String name) {
		try {
			con = DaoFactory.getConnection();// 获取连接
			sql = "select * from t_user where username = ?";// 设置sql语句
			st = con.prepareStatement(sql);// 创建命令对象
			st.setString(1, name);
			rs = st.executeQuery();// 执行操作
			if (rs.next())
				return rs.getInt(1) > 0;
		} catch (SQLException e) {// 捕获异常
			e.printStackTrace();
		} finally {// 最后释放资源
			DaoFactory.closeAll(con, st, rs);
		}
		return false;
	}
	// 验证账户密码
	public boolean validateUser(String name, String password) {
		try {
			con = DaoFactory.getConnection();// 获取连接
			sql = "select * from t_user where username = ? and userpwd = ? ";// 设置sql语句
			st = con.prepareStatement(sql);// 创建命令对象
			st.setString(1, name);
			st.setString(2, password);
			rs = st.executeQuery();// 执行操作
			if (rs.next())
				return rs.getInt(1) > 0;
		} catch (SQLException e) {// 捕获异常
			e.printStackTrace();
		} finally {// 最后释放资源
			DaoFactory.closeAll(con, st, rs);
		}
		return false;
	}

	// 添加用户
	public boolean addUser(User aUser) {
		con = DaoFactory.getConnection();// 获取连接
		sql = "insert into t_user (username,userpwd,email,address,hobby)  values(?,?,?,?,?)";// 设置sql语句
		try {
			st = con.prepareStatement(sql);// 创建执行对象
			st.setString(1, aUser.getUsername());// 赋值
			st.setString(2, aUser.getUserpwd());
			st.setString(3, aUser.getEmail());
			st.setString(4, aUser.getAddress());
			st.setString(5, aUser.getHobby());
			return st.executeUpdate() > 0;// 返回执行结果
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.closeAll(con, st, rs);
		}
		return false;// 默认返回false表示失败
	}

	// 删除用户
	public boolean deleteUser(int id) {
		con = DaoFactory.getConnection();// 获取连接
		sql = "delete from t_user where id = ?";// 设置sql语句
		try {
			st = con.prepareStatement(sql);// 设置执行对象
			st.setInt(1, id);
			return st.executeUpdate() > 0;// 执行sql,对结果进行处理
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.closeAll(con, st, rs);
		}
		return false;
	}

	// 修改用户
	public boolean updateUser(User aUser) {
		con = DaoFactory.getConnection();// 获取连接
		sql = "update t_user set username=?,userpwd=?,email=?,address=?,hobby=? where id=?";// 设置sql语句
		try {
			st = con.prepareStatement(sql);// 创建执行对象
			st.setString(1, aUser.getUsername());
			st.setString(2, aUser.getUserpwd());
			st.setString(3, aUser.getEmail());
			st.setString(4, aUser.getAddress());
			st.setString(5, aUser.getHobby());
			st.setInt(6, aUser.getId());
			return st.executeUpdate() > 0;// 执行sql
			// 对结果处理
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.closeAll(con, st, rs);
		}
		return false;
	}

	// 查询用户
	public User searchUser(int id) {
		con = DaoFactory.getConnection();// 获取连接
		sql = "select * from t_user where id=?";// 设置sql语句
		User aUser = new User();
		try {
			st = con.prepareStatement(sql);// 创建执行对象
			st.setInt(1, id);
			rs = st.executeQuery();// 执行sql语句
			while (rs.next()) {
				aUser.setId(rs.getInt(1));
				aUser.setUsername(rs.getString(2));
				aUser.setUserpwd(rs.getString(3));
				aUser.setEmail(rs.getString(4));
				aUser.setAddress(rs.getString(5));
				aUser.setHobby(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.closeAll(con, st, rs);
		}
		return aUser;
	}

	// 从指定位置开始获取指定数量的记录
	public ArrayList<User> getUsersByPage(int pageIndex, int pageSize) {
		ArrayList<User> users = new ArrayList<User>();
		try {
			con = DaoFactory.getConnection();// 创建连接对象
			String sql = "select * from t_user limit ?,?";// 定义sql语句
			st = con.prepareStatement(sql);// 创建命令对象
			st.setInt(1, (pageIndex - 1) * pageSize);//设置参数值
			st.setInt(2, pageSize);
			rs = st.executeQuery();// 执行命令
			while (rs.next()) {// 循环读取一行中的每一列信息,将其封装进对象,放入结果集
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5),rs.getString(6)));
			}
		} catch (Exception e) {
		} finally {// 释放资源
			DaoFactory.closeAll(con, st, rs);
		}
		return users;
	}

	// 获取总记录数
	public int getCount() {
		try {
			con = DaoFactory.getConnection();// 创建连接对象
			String sql = "select count(*) from t_user"; // 定义sql语句
			st = con.prepareStatement(sql);// 创建命令对象
			rs = st.executeQuery();// 执行命令
			while (rs.next()) {
				c = rs.getInt(1);
			}
		} catch (Exception e) {
		} finally {// 释放资源
			DaoFactory.closeAll(con, st, rs);
		}
		return c;
	}
	


}
