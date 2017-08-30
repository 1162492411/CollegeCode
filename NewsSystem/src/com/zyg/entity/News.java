package com.zyg.entity;
/**
 *赵国亚
 */

public class News {
	

	private int id;
	private int tid;
	private String title;
	private String author;
	private String createTime;
	private String ncontent;
	private String modifyTime;
	private String summary;
	private String pic;

	
	
	public News() {
		super();
	}
	
	public News(int id, int tid, String title, String author, String createTime, String ncontent, String modifyTime,
			String summary,String pic) {
		super();
		this.id = id;
		this.tid = tid;
		this.title = title;
		this.author = author;
		this.createTime = createTime;
		this.ncontent = ncontent;
		this.modifyTime = modifyTime;
		this.summary = summary;
		this.pic = pic;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
}
