package com.zyg.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.zyg.dao.NewsDao;
import com.zyg.entity.News;
import com.zyg.util.DaoFactory;

public class NewsDaoImpl implements NewsDao {
	private Connection con = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	private String sql = null;
	private int c = 0;

	// 获取所有新闻
	public ArrayList<News> getAllNews() {
		ArrayList<News> Newss = new ArrayList<News>();
		try {
			con = DaoFactory.getConnection();// 创建连接对象
			String sql = "select * from t_news";// 定义sql语句
			st = con.prepareStatement(sql);// 创建命令对象
			rs = st.executeQuery();// 执行命令
			while (rs.next()) {// 循环
				// 读取一行中的每一列信息,将其封装进对象
				News aNews = new News(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), String.valueOf(rs.getDate(5)),
						rs.getString(6), String.valueOf(rs.getDate(7)), rs.getString(8), rs.getString(9));
				Newss.add(aNews);// 将对象放入集合中
			}
		} catch (Exception e) {
		} finally {// 释放资源
			DaoFactory.closeAll(con, st, rs);
		}
		return Newss;// 返回对象集合
	}

	// 获取某个分类最靠前的几条记录
	public ArrayList<News> getTopNews(int tid, int number) {
		ArrayList<News> Newss = new ArrayList<News>();
		try {
			con = DaoFactory.getConnection();// 创建连接对象
			String sql = "select * from t_news where tid=? limit ?";// 定义sql语句
			st = con.prepareStatement(sql);// 创建命令对象
			st.setInt(1, tid);
			st.setInt(2, number);
			rs = st.executeQuery();// 执行命令
			while (rs.next()) {// 循环
				// 读取一行中的每一列信息,将其封装进对象
				News aNews = new News(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), String.valueOf(rs.getDate(5)),
						rs.getString(6), String.valueOf(rs.getDate(7)), rs.getString(8), rs.getString(9));
				Newss.add(aNews);// 将对象放入集合中
			}
		} catch (Exception e) {
		} finally {// 释放资源
			DaoFactory.closeAll(con, st, rs);
		}
		return Newss;// 返回对象集合
	}

	// 添加新闻
	public boolean addNews(News aNews) {
		con = DaoFactory.getConnection();// 获取连接
		sql = "insert into t_news (tid,title,author,createtime,ncontent,modifytime,summary)  values(?,?,?,?,?,?,?)";// 设置sql语句
		try {
			st = con.prepareStatement(sql);// 创建执行对象
			st.setInt(1, aNews.getTid());// 赋值
			st.setString(2, aNews.getTitle());
			st.setString(3, aNews.getAuthor());
			st.setString(4, aNews.getCreateTime());
			st.setString(5, aNews.getNcontent());
			st.setString(6, aNews.getModifyTime());
			st.setString(7, aNews.getSummary());
			return st.executeUpdate() > 0;// 返回执行结果
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.closeAll(con, st, rs);
		}
		return false;// 默认返回false表示失败
	}

	// 删除新闻
	public boolean deleteNews(int id) {
		con = DaoFactory.getConnection();// 获取连接
		sql = "delete from t_news where id = ?";// 设置sql语句
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
	public boolean updateNews(News aNews) {
		con = DaoFactory.getConnection();// 获取连接
		sql = "update t_news set tid=?,title=?,author=?,ncontent=?,modifytime=?,summary=? where id=?";// 设置sql语句
		try {
			st = con.prepareStatement(sql);// 创建执行对象
			st.setInt(1, aNews.getTid());// 赋值
			st.setString(2, aNews.getTitle());
			st.setString(3, aNews.getAuthor());
			st.setString(4, aNews.getNcontent());
			st.setString(5, aNews.getModifyTime());
			st.setString(6, aNews.getSummary());
			st.setInt(7, aNews.getId());
			return st.executeUpdate() > 0;// 执行sql
			// 对结果处理
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.closeAll(con, st, rs);
		}
		return false;
	}

	// 查询新闻
	public News searchNews(int id) {
		con = DaoFactory.getConnection();// 获取连接
		sql = "select * from t_news where id=?";// 设置sql语句
		News aNews = new News();
		try {
			st = con.prepareStatement(sql);// 创建执行对象
			st.setInt(1, id);
			rs = st.executeQuery();// 执行sql语句
			while (rs.next()) {
				aNews.setId(rs.getInt(1));
				aNews.setTid(rs.getInt(2));
				aNews.setTitle(rs.getString(3));
				aNews.setAuthor(rs.getString(4));
				aNews.setCreateTime(String.valueOf(rs.getDate(5)));
				aNews.setNcontent(rs.getString(6));
				aNews.setModifyTime(String.valueOf(rs.getDate(7)));
				aNews.setSummary(rs.getString(8));
				aNews.setPic(rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DaoFactory.closeAll(con, st, rs);
		}
		return aNews;
	}

	// 从指定位置开始获取指定数量的记录
	public ArrayList<News> getNewssByPage(int pageIndex, int pageSize) {
		ArrayList<News> Newss = new ArrayList<News>();
		try {
			con = DaoFactory.getConnection();// 创建连接对象
			String sql = "select * from t_news limit ?,?";// 定义sql语句
			// 创建命令对象
			st = con.prepareStatement(sql);
			st.setInt(1, (pageIndex - 1) * pageSize);
			st.setInt(2, pageSize);
			rs = st.executeQuery();// 执行命令
			while (rs.next()) {// 循环
				// 读取一行中的每一列信息,将其封装进对象
				News aNews = new News(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), String.valueOf(rs.getDate(5)),
						rs.getString(6), String.valueOf(rs.getDate(7)), rs.getString(8), rs.getString(9));
				Newss.add(aNews);// 将对象放入集合中
			}
		} catch (Exception e) {
		} finally {// 释放资源
			DaoFactory.closeAll(con, st, rs);
		}
		return Newss;
	}

	// 获取总记录数
	public int getCount() {
		try {
			con = DaoFactory.getConnection();// 创建连接对象
			String sql = "select count(*) from t_news"; // 定义sql语句
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

	//获取指定分类的新闻的总记录数
	public int getCountByTopic(int tid) {
		try {
			con = DaoFactory.getConnection();// 创建连接对象
			String sql = "select count(*) from t_news where tid= ? "; // 定义sql语句
			st = con.prepareStatement(sql);// 创建命令对象
			st.setInt(1, tid);
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
