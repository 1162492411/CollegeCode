package com.zyg.util;
/**
 * 分页显示的控制类
 * @author 张彦广
 * 描述：该类用来对数据进行分页显示的页数有效性控制
 * 使用时请传入对应的对象的总记录数，推荐设置对象的getCount()方法
 * 默认每页显示五条记录，用户也可通过setPageSize(int pageSize)方法自定义每页显示记录的数量
 * 默认设置首页页码为1，尾页页码为对象的总记录数，上一页、当前页、下一页页码为1
 * 
 *
 */
public class PageController {
	private int count = 0;//总记录数
	private int pageSize = 5;//默认每页显示条数
	private int totalPages = 1;//默认总页数
	private int firstPageIndex = 1;//首页页码
	private int lastPageIndex = 1;//尾页页码
	private int currentPageIndex = 1;//当前页页码
	private int previousPageIndex = 1;//上一页页码
	private int nextPageIndex = 1;//下一页页码
	
	public PageController(int count){
		setCount(count);//初始化总记录数
		setTotalPages(this.count, this.pageSize);//初始化总页数
		setLastPageIndex(this.totalPages);//初始化尾页页码
		setCurrentPageIndex(1);//初始化当前页页码
	}
	//获取总记录数
	public int getCount() {
		return count;
	}
	
	//设置总记录数
	public void setCount(int count) {
		if(count < 0) throw new IllegalArgumentException();//不合法时抛出参数异常
		this.count = count;
	}
	
	//获取每页显示条数
	public int getPageSize() {
		return pageSize;
	}
	//设置每页显示条数
	public void setPageSize(int pageSize) {
		if(pageSize < 0 || pageSize > count) throw new IllegalArgumentException();//不合法时抛出参数异常
		this.pageSize = pageSize;
		setTotalPages(this.count, this.pageSize);
	}
	//获取总页数
	public int getTotalPages() {
		return totalPages;
	}
	//计算总页数
	private void setTotalPages(int count,int pageSize) {
		if(count < 0 || pageSize < 0 || pageSize > count) throw new IllegalArgumentException();//不合法时抛出参数异常
		this.totalPages =(count % pageSize == 0)? (count/pageSize):(count/pageSize + 1);
		setLastPageIndex(totalPages);
	}
	//获取首页页码
	public int getFirstPageIndex() {
		return firstPageIndex;
	}
	//获取尾页页码
	public int getLastPageIndex() {
		return lastPageIndex;
	}
	//设置尾页页码
	private void setLastPageIndex(int lastPageIndex) {
		this.lastPageIndex = lastPageIndex;
	}
	//获取当前页页码
	public int getCurrentPageIndex() {
		return currentPageIndex;
	}
	//设置当前页页码
	public void setCurrentPageIndex(int currentPageIndex) {
		if(currentPageIndex <= 0 || currentPageIndex > totalPages)throw new IllegalArgumentException();//不合法时抛出参数异常
		this.currentPageIndex = currentPageIndex;//设置当前页页码
		setPreviousPageIndex(currentPageIndex - 1);//设置前一页页码
		setNextPageIndex(currentPageIndex + 1);//设置下一页页码
	}
	//获取前一页页码
	public int getPreviousPageIndex() {
		return previousPageIndex;
	}
	//设置前一页页码
	private void setPreviousPageIndex(int previousPageIndex) {
		if(previousPageIndex <= 0 ) //当参数不合法时
			this.previousPageIndex = 1;//强制设置前一页页码为首页页码
		else//参数合法时
			this.previousPageIndex = previousPageIndex;//将参数的值赋给前一页页码
	}
	//获取下一页页码
	public int getNextPageIndex() {
		return nextPageIndex;
	}
	//设置下一页页码
	private void setNextPageIndex(int nextPageIndex) {
		if(nextPageIndex > totalPages)//当参数不合法时
			this.nextPageIndex = totalPages;//强制设置下一页页码为尾页页码
		else //参数合法时
			this.nextPageIndex = nextPageIndex;//将参数的值赋给下一页页码
	}
}
