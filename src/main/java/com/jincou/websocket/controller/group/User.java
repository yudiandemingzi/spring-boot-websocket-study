package com.jincou.websocket.controller.group;

/** 
 * 功能描述：用户模型
 */
public class User {

	private String username;
	private String pwd;
	
	public User() {}
	
	public User(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
