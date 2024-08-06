package com.springboot.todo.springbootmvctodoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.todo.springbootmvctodoapp.bean.ToDo;
import com.springboot.todo.springbootmvctodoapp.service.ToDoService;

import jakarta.validation.Valid;

@Controller
public class ToDoController {
	
	@Autowired
	private ToDoService toDoService;
	
	@RequestMapping(value = "/welCome-ToDo", method = RequestMethod.GET)
	public String goToWelComePage(@RequestParam int loggedinUserId, ModelMap model) {
		List<ToDo> toDosForLoggedInUser = toDoService.fetchAllToDosForLoggedInUser(loggedinUserId);
		model.put("allToDos", toDosForLoggedInUser);
		return "WelComeToDo";
	}
	
	@RequestMapping(value = "/delete-ToDo")
	public String deleteToDosForLoggedInUser(@RequestParam int toDoId, @RequestParam int loggedinUserId, ModelMap model) {
		toDoService.deleteToDosForLoggedInUser(toDoId);
		model.put("allToDos", toDoService.fetchAllToDosForLoggedInUser(loggedinUserId));
		return "WelComeToDo";
	}
	
	@RequestMapping(value = "/add-ToDo", method = RequestMethod.GET)
	public String goToAddToDoPage(@RequestParam int loggedinUserId, ModelMap model) throws Exception {
		ToDo toDoForLoggedinUserId = new ToDo();
		toDoForLoggedinUserId.setMappedUserId(loggedinUserId);
		model.addAttribute("toDo", toDoForLoggedinUserId);
		return "UpsertToDo";
	}
	
	@RequestMapping(value = "/add-ToDo", method = RequestMethod.POST)
	public String addToDosForLoggedInUser(ModelMap model, @ModelAttribute("toDo") @Valid ToDo newToDo, BindingResult result) {
		if (result.hasErrors()) {
			return "UpsertToDo";
		}
		newToDo.setMappedUserId(newToDo.getMappedUserId());
		List<ToDo> toDosForLoggedInUser = toDoService.addToDosForLoggedInUser(newToDo);
		model.put("allToDos", toDosForLoggedInUser);
		return "WelComeToDo";
	}
	
	@RequestMapping(value = "/update-ToDo", method = RequestMethod.GET)
	public String goToUpdateToDoPage(@RequestParam int toDoId, ModelMap model) {
		model.addAttribute("toDo", toDoService.fetchToDo(toDoId));
		return "UpsertToDo";
	}
	
	@RequestMapping(value = "/update-ToDo", method = RequestMethod.POST)
	public String updateToDosForLoggedInUser(ModelMap model, @ModelAttribute("toDo") @Valid ToDo updatedToDo, BindingResult result) {
		if (result.hasErrors()) {
			return "UpsertToDo";
		}
		List<ToDo> toDosForLoggedInUser = toDoService.updateToDo(updatedToDo);
		model.put("allToDos", toDosForLoggedInUser);
		return "WelComeToDo";
	}
}
