package com.zyg.util;
/**
 * 显示更多新闻的控制类
 * @author Mo
 *
 */
public class ShowMoreNewsController {
	private  int currentCount = 5;//默认当前记录数
	private int defaultStep = 5;//默认每次增长的记录数
	private int totalCount = 0;//默认总记录数
	
	//构造器--设置总记录数
	public ShowMoreNewsController(int totalCount){
		this.totalCount = totalCount;
	}
	
	//构造器--设置总记录数与每次增长的记录数
	public ShowMoreNewsController(int totalCount, int defaultStep) {
		this.totalCount = totalCount;
		this.defaultStep = defaultStep;
	}
	
	//获取每次增长后的记录数
	public int step(){
		if(currentCount < 0) throw new IllegalArgumentException();//参数有效性检查
		if(currentCount + defaultStep > totalCount) return totalCount;//边界检查
		return currentCount + defaultStep;//返回
	}
	
	//获取当前记录数
	public int getCurrentCount() {
		return currentCount;
	}
	
	//设置当前记录数
	public void setCurrentCount(int currentCount) {
		if(currentCount < 0) throw new IllegalArgumentException();
		this.currentCount = currentCount;
	}

	//获取默认增长记录数
	public int getDefaultStep() {
		return defaultStep;
	}

	//设置增长记录数
	public void setDefaultStep(int defaultStep) {
		if(defaultStep < 1) throw new IllegalArgumentException();
		this.defaultStep = defaultStep;
	}

	//获取总记录数
	public int getTotalCount() {
		return totalCount;
	}

	//设置总记录数
	private void setTotalCount(int totalCount) {
		if(totalCount < 0 ) throw new IllegalArgumentException();
		this.totalCount = totalCount;
	}


	
}
