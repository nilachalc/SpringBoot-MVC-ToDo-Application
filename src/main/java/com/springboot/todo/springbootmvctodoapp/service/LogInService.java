package com.springboot.todo.springbootmvctodoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.todo.springbootmvctodoapp.bean.ToDo;
import com.springboot.todo.springbootmvctodoapp.bean.ToDoUser;
import com.springboot.todo.springbootmvctodoapp.repository.LoginRepository;

@Service
public class LogInService {
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private ToDoService toDoService;
	
	public ToDoUser validateUser(String name, String password) {
		return loginRepository.isValidUser(name, password);	
	}
	
	public List<ToDo> fetchAllToDosForLoggedInUser(int loggedInUserId) {
		return toDoService.fetchAllToDosForLoggedInUser(loggedInUserId);	
	}
}
