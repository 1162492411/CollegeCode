package com.zyg.entity;

/**
 * 张彦广
 */


public class Comment {

	private int id;
	private int nid;
	private String content;
	private String createtime;
	private String author;

	// 无参构造方法
	public Comment() {

	}

	// 有参构造方法
	public Comment(int id, int nid, String content, String createtime, String author) {
		this.id = id;
		this.nid = nid;
		this.content = content;
		this.createtime = createtime;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
