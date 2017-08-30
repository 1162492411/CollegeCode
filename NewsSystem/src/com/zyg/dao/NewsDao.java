package com.zyg.dao;

import java.util.ArrayList;
import com.zyg.entity.News;

public interface NewsDao {
	public ArrayList<News> getAllNews();//获取所有新闻
	public ArrayList<News> getTopNews(int tid, int number);//获取某个分类最靠前的几条记录
	public boolean addNews(News aNews);//添加新闻
	public boolean deleteNews(int id);//删除新闻
	public boolean updateNews(News aNews);//修改新闻
	public News searchNews(int id);//查询新闻
	public ArrayList<News> getNewssByPage(int pageIndex, int pageSize);//从指定位置开始获取指定数量的记录
	public int getCount();//获取总记录数
	public int getCountByTopic(int tid);//获取指定分类的新闻的总记录数
}
