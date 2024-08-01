package com.springboot.todo.springbootmvctodoapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.todo.springbootmvctodoapp.bean.ToDoUser;

@Repository
public class LoginRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public ToDoUser isValidUser(String name, String password) {
		List<ToDoUser> loggedInUser = jdbcTemplate.query("SELECT UI.USER_ID, UI.USER_NAME FROM USER_INFO UI WHERE UI.USER_LOGIN_ID = ? AND UI.USER_PASSWORD = ?"
								,  (rs, rowNum) -> {
									ToDoUser user = new ToDoUser();
									if (rs != null)
										user.setUserId(rs.getInt("USER_ID"));
										user.setUserName(rs.getString("USER_NAME"));
									return user;
								}, name, password);
		if (loggedInUser.isEmpty()) {
			return new ToDoUser();
		} else {
			return loggedInUser.get(0);
		}
		
	}
}
