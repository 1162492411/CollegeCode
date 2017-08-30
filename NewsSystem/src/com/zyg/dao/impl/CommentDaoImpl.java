package com.zyg.dao.impl;
/**
 * 评论的接口实现类
 * @author 张彦广
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.zyg.dao.CommentDao;
import com.zyg.entity.Comment;
import com.zyg.util.DaoFactory;

public class CommentDaoImpl implements CommentDao {
	private Connection con = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	private String sql = null;
	private int c = 0;

	//获取所有评论
	public ArrayList<Comment> getAllComments() {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			con = DaoFactory.getConnection();//创建连接对象
			String sql = "select * from t_comment order by createtime desc";//定义sql语句
			st = con.prepareStatement(sql);//创建命令对象
			rs = st.executeQuery();//执行命令
			while (rs.next()) {//循环
				//读取一行中的每一列信息,将其封装进对象
				Comment aComment = new Comment();
				aComment.setId(rs.getInt(1));
				aComment.setNid(rs.getInt(2));
				aComment.setContent(rs.getString(3));
				aComment.setCreatetime(String.valueOf(rs.getDate(4)));
				aComment.setAuthor(rs.getString(5));
				comments.add(aComment);//将对象放入集合中
			}
		} catch (Exception e) {
		}finally {//释放资源
			DaoFactory.closeAll(con,st,rs);
		}
		return comments;//返回对象集合
	}
	
	//获取指定新闻的所有评论
	public ArrayList<Comment> getCommmentsByNews(int nid) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			con = DaoFactory.getConnection();//创建连接对象
			String sql = "select * from t_comment where nid= ? order by createtime desc";//定义sql语句
			st = con.prepareStatement(sql);//创建命令对象
			st.setInt(1, nid);
			rs = st.executeQuery();//执行命令
			while (rs.next()) {//循环
				//读取一行中的每一列信息,将其封装进对象
				Comment aComment = new Comment();
				aComment.setId(rs.getInt(1));
				aComment.setNid(rs.getInt(2));
				aComment.setContent(rs.getString(3));
				aComment.setCreatetime(String.valueOf(rs.getDate(4)));
				aComment.setAuthor(rs.getString(5));
				comments.add(aComment);//将对象放入集合中
			}
		} catch (Exception e) {
		}finally {//释放资源
			DaoFactory.closeAll(con,st,rs);
		}
		return comments;//返回对象集合
	}
	
	// 添加评论
		public boolean addComment(Comment aComment) {
			con = DaoFactory.getConnection();// 获取连接
			sql = "insert into t_comment (nid,content,createtime,author)  values(?,?,?,?)";// 设置sql语句
			try {
				st = con.prepareStatement(sql);// 创建执行对象
				st.setInt(1, aComment.getNid());// 赋值
				st.setString(2, aComment.getContent());
				st.setString(3, aComment.getCreatetime());
				st.setString(4, aComment.getAuthor());
				return st.executeUpdate() > 0;// 返回执行结果
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DaoFactory.closeAll(con, st, rs);
			}
			return false;// 默认返回false表示失败
		}

		// 删除评论
		public boolean deleteComment(int id) {
			con = DaoFactory.getConnection();// 获取连接
			sql = "delete from t_comment where id = ?";// 设置sql语句
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

		// 修改评论
		public boolean updateComment(Comment aComment) {
			con = DaoFactory.getConnection();// 获取连接
			sql = "update t_comment set nid=?,content=?,author=? where id=?";// 设置sql语句
			try {
				st = con.prepareStatement(sql);// 创建执行对象
				st.setInt(1, aComment.getNid());
				st.setString(2, aComment.getContent());
				st.setString(3, aComment.getAuthor());
				st.setInt(4, aComment.getId());
				return st.executeUpdate() > 0;// 执行sql
				// 对结果处理
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DaoFactory.closeAll(con, st, rs);
			}
			return false;
		}

		// 查询评论
		public Comment searchComment(int id) {
			con = DaoFactory.getConnection();// 获取连接
			sql = "select * from t_comment where id=?";// 设置sql语句
			Comment aComment = new Comment();
			try {
				st = con.prepareStatement(sql);// 创建执行对象
				st.setInt(1, id);
				rs = st.executeQuery();// 执行sql语句
				while (rs.next()) {
					aComment.setId(rs.getInt(1));
					aComment.setNid(rs.getInt(2));
					aComment.setContent(rs.getString(3));
					aComment.setCreatetime(String.valueOf(rs.getDate(4)));
					aComment.setAuthor(rs.getString(5));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DaoFactory.closeAll(con, st, rs);
			}
			return aComment;
		}

		// 从指定位置开始获取指定数量的记录
		public ArrayList<Comment> getCommentsByPage(int pageIndex, int pageSize) {
			ArrayList<Comment> Comments = new ArrayList<Comment>();
			try {
				con = DaoFactory.getConnection();// 创建连接对象
				String sql = "select * from t_comment limit ?,?";// 定义sql语句
				// 创建命令对象
				st = con.prepareStatement(sql);
				st.setInt(1, (pageIndex - 1) * pageSize);
				st.setInt(2, pageSize);
				rs = st.executeQuery();// 执行命令
				while (rs.next()) {// 循环
					// 读取一行中的每一列信息,将其封装进对象
					Comment aComment = new Comment(rs.getInt(1), rs.getInt(2), rs.getString(3),String.valueOf(rs.getDate(4)), rs.getString(5));
					Comments.add(aComment);// 将对象放入集合中
				}
			} catch (Exception e) {
			} finally {// 释放资源
				DaoFactory.closeAll(con, st, rs);
			}
			return Comments;
		}

		// 获取总记录数
		public int getCount() {
			try {
				con = DaoFactory.getConnection();// 创建连接对象
				String sql = "select count(*) from t_comment"; // 定义sql语句
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

		//获取指定新闻的总评论数
		public int getCountByNews(int nid) {
			try {
				con = DaoFactory.getConnection();// 创建连接对象
				String sql = "select count(*) from t_comment where nid=?"; // 定义sql语句
				st = con.prepareStatement(sql);// 创建命令对象3
				st.setInt(1, nid);
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
