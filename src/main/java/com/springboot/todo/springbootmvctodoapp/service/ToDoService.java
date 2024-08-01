package com.springboot.todo.springbootmvctodoapp.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.todo.springbootmvctodoapp.bean.ToDo;
import com.springboot.todo.springbootmvctodoapp.repository.ToDoRepository;

@Service
public class ToDoService {
	
	@Autowired
	private ToDoRepository toDoRepository;
	
	public List<ToDo> fetchAllToDosForLoggedInUser(int loggedInUserId) {
		List<ToDo> allToDos = toDoRepository.getAllToDosForLoggedInUser(loggedInUserId);
		for (int i = 0; i < allToDos.size(); i++) {
			allToDos.get(i).setToDoSerialNumber(i+1);
		}
		return allToDos;
	}
	
	@Transactional
	public void deleteToDosForLoggedInUser(int toDoId) {
		toDoRepository.deleteToDosForLoggedInUser(toDoId);
	}
	
	@Transactional
	public List<ToDo> addToDosForLoggedInUser(ToDo newToDo) {
		toDoRepository.addToDosForLoggedInUser(newToDo);
		return fetchAllToDosForLoggedInUser(newToDo.getMappedUserId());
	}
	
	public ToDo fetchToDo(int toDoId) {
		return toDoRepository.getToDo(toDoId);
	}
	
	@Transactional
	public List<ToDo> updateToDo(ToDo newToDo) {
		toDoRepository.updateToDo(newToDo);
		return fetchAllToDosForLoggedInUser(newToDo.getMappedUserId());
	}
}