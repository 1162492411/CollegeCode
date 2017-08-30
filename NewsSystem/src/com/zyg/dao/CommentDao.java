package com.zyg.dao;

import java.util.ArrayList;
import com.zyg.entity.Comment;
/**
 * 评论的dao接口
 * @author 张彦广
 *
 */
public interface CommentDao {
	public ArrayList<Comment> getAllComments();//获取所有评论
	public ArrayList<Comment> getCommmentsByNews(int nid);//获取指定新闻的所有评论
	public boolean addComment(Comment aComment);//添加评论
	public boolean deleteComment(int id);//删除评论
	public boolean updateComment(Comment aComment);//修改评论
	public Comment searchComment(int id);//查询评论
	public ArrayList<Comment> getCommentsByPage(int pageIndex, int pageSize);//从指定位置开始获取指定数量的记录
	public int getCount();//获取总记录数
	public int getCountByNews(int nid);//获取指定新闻的总评论数
}
