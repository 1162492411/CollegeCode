package com.qq.model;
/**
 * 账户对象
 * @author Mo
 */
public class User {

	private String userID;//账户
	private String userPassword;//密码
	private String userName;//昵称
	private String userSex;//性别
	private int userAge;//年龄
	private String userInfo;//简介
	private String userEmail;//邮箱
	
	//空的构造器
	public User(){
		
	}
	
	//根据用户名和密码初始化
	public User(String userID, String userPassword) {
		super();
		this.userID = userID;
		this.userPassword = userPassword;
	}
	
	
	//根据所有参数构造器
	public User(String userID, String userPassword, String userName, String userSex, int userAge, String userInfo,
			String userEmail) {
		super();
		this.userID = userID;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userSex = userSex;
		this.userAge = userAge;
		this.userInfo = userInfo;
		this.userEmail = userEmail;
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	
}
