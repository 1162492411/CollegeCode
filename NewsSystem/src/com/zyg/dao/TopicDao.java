package com.zyg.dao;

import java.util.ArrayList;
import com.zyg.entity.Topic;

public interface TopicDao {
	public ArrayList<Topic> getAllTopics();//获取所有新闻分类
	public boolean addTopic(Topic aTopic);//添加分类
	public boolean deleteTopic(int id);//删除分类
	public boolean updateTopic(Topic aTopic);//修改分类
	public Topic searchTopic(int id);//查询分类
	public ArrayList<Topic> getTopicsByPage(int pageIndex, int pageSize);//从指定位置开始获取指定数量的记录
	public int getCount();//获取总记录数
}
