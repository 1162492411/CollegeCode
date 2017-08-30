package com.zyg.entity;
/**
 * 赵坤园
 * @author Mo
 *
 */
public class User {
	private int id;
	private String username;
	private String userpwd;
	private String email;
	private String address;
	private String hobby;
	
	public User(int id, String username, String userpwd, String email, String address, String hobby) {
		super();
		this.id = id;
		this.username = username;
		this.userpwd = userpwd;
		this.email = email;
		this.address = address;
		this.hobby = hobby;
	}
	public User() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	
}
