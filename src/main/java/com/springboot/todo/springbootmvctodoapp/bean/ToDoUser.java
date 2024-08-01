package com.springboot.todo.springbootmvctodoapp.bean;

import org.springframework.stereotype.Component;

@Component
public class ToDoUser {
	private Integer userId;
	private String userName;
	private String userLogInId;
	private String password;
	private String dateOfBirth;
	
	public ToDoUser() {
		this.userId = 0;
		this.userName = "";
		this.userLogInId = "";
		this.password = "";
		this.dateOfBirth = "";
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLogInId() {
		return userLogInId;
	}
	public void setUserLogInId(String userLogInId) {
		this.userLogInId = userLogInId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public String toString() {
		return "ToDoUser [userId=" + userId + ", userName=" + userName + ", userLogInId=" + userLogInId + ", password="
				+ password + ", dateOfBirth=" + dateOfBirth + "]";
	}
}
