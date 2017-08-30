package com.zyg.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.zyg.dao.TopicDao;
import com.zyg.entity.Topic;
import com.zyg.entity.Topic;
import com.zyg.util.DaoFactory;

public class TopicDaoImpl implements TopicDao {
	private Connection con = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	private String sql = null;
	private int c = 0;
	//获取所有新闻
	public ArrayList<Topic> getAllTopics() {
		ArrayList<Topic> topics = new ArrayList<Topic>();
		try {
			//创建连接对象
			con = DaoFactory.getConnection();
			//定义sql语句
			String sql = "select * from t_topic";
			//创建命令对象
			st = con.prepareStatement(sql);
			//执行命令
			rs = st.executeQuery();
			//循环
			while (rs.next()) {
				//读取一行中的每一列信息,将其封装进对象
				Topic aTopic = new Topic(rs.getInt(1),rs.getString(2),String.valueOf(rs.getDate(3)));
				//将对象放入集合中
				topics.add(aTopic);
			}
		} catch (Exception e) {
		}finally {//释放资源
			DaoFactory.closeAll(con,st,rs);
		}
		return topics;//返回对象集合
	}
	
	// 添加新闻
	public boolean addTopic(Topic aTopic) {
		con = DaoFactory.getConnection();// 获取连接
		sql = "insert into t_topic (tname,createtime) values(?,?)";// 设置sql语句
		try {
			st = con.prepareStatement(sql);// 创建执行对象
			st.setString(1, aTopic.getTname());// 赋值
			st.setString(2, aTopic.getCreatetime());
			return st.executeUpdate() > 0;// 返回执行结果
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.closeAll(con, st, rs);
		}
		return false;// 默认返回false表示失败
	}

	// 删除新闻
	public boolean deleteTopic(int id) {
		con = DaoFactory.getConnection();// 获取连接
		sql = "delete from t_topic where id = ?";// 设置sql语句
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

	// 修改新闻
	public boolean updateTopic(Topic aTopic) {
		con = DaoFactory.getConnection();// 获取连接
		sql = "update t_topic set tname=? where id=?";// 设置sql语句
		try {
			st = con.prepareStatement(sql);// 创建执行对象
			st.setString(1, aTopic.getTname());// 赋值
			st.setInt(2, aTopic.getId());
			return st.executeUpdate() > 0;// 执行sql
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.closeAll(con, st, rs);
		}
		return false;
	}

	// 查询新闻
	public Topic searchTopic(int id) {
		con = DaoFactory.getConnection();// 获取连接
		sql = "select * from t_topic where id=?";// 设置sql语句
		Topic aTopic = new Topic();
		try {
			st = con.prepareStatement(sql);// 创建执行对象
			st.setInt(1, id);
			rs = st.executeQuery();// 执行sql语句
			while (rs.next()) {
				aTopic.setId(rs.getInt(1));
				aTopic.setTname(rs.getString(2));
				aTopic.setCreatetime(String.valueOf(rs.getDate(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.closeAll(con, st, rs);
		}
		return aTopic;
	}

	// 从指定位置开始获取指定数量的记录
	public ArrayList<Topic> getTopicsByPage(int pageIndex, int pageSize) {
		ArrayList<Topic> Topics = new ArrayList<Topic>();
		try {
			con = DaoFactory.getConnection();// 创建连接对象
			String sql = "select * from t_topic limit ?,?";// 定义sql语句
			// 创建命令对象
			st = con.prepareStatement(sql);
			st.setInt(1, (pageIndex - 1) * pageSize);
			st.setInt(2, pageSize);
			rs = st.executeQuery();// 执行命令
			while (rs.next()) {// 循环
				// 读取一行中的每一列信息,将其封装进对象
				Topic aTopic = new Topic(rs.getInt(1), rs.getString(2),String.valueOf(rs.getDate(3)));
				Topics.add(aTopic);// 将对象放入集合中
			}
		} catch (Exception e) {
		} finally {// 释放资源
			DaoFactory.closeAll(con, st, rs);
		}
		return Topics;
	}

	// 获取总记录数
	public int getCount() {
		try {
			con = DaoFactory.getConnection();// 创建连接对象
			String sql = "select count(*) from t_topic"; // 定义sql语句
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
