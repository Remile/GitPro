package com.hama.vo;

import java.io.Serializable;

public class User implements Serializable {
	private String userName;
	private String passWord;
	private int id;
	
	public User(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
