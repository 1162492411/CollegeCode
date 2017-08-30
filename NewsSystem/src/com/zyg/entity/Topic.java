package com.zyg.entity;
/**
 * 新闻栏目实体类
 * @author 张逸加
 *
 */
public class Topic {
	private int id;
	private String tname;
	private String createtime;
	
	//不带形参的构造方法，也为默认的构造方法
	public Topic(){
		}
	
	//带形参的构造方法
	public Topic(int id, String tname, String createtime) {
		super();
		this.id = id;
		this.tname = tname;
		this.createtime = createtime;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
