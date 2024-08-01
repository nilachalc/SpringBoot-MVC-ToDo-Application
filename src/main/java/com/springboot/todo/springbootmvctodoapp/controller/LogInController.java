package com.springboot.todo.springbootmvctodoapp.controller;

import java.util.Locale;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;

import com.springboot.todo.springbootmvctodoapp.bean.ToDoUser;
import com.springboot.todo.springbootmvctodoapp.service.LogInService;

@SessionAttributes({"loggedinUserName", "loggedinUserId"})
@Controller
public class LogInController {
	
	@Autowired
	WebApplicationContext applicationContext;
	
	@Autowired
	private LogInService logInService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody String testPage() {
		String string = "Welcome To The World of Spring MVC!!! Choose proper URL to explore more!";
		System.out.println("Bean present in the Application Context --> ");
		String[] beans = applicationContext.getBeanDefinitionNames();
		for (int i = 0 ; i < applicationContext.getBeanDefinitionCount(); i++) {
			System.out.println(beans[i]);
		}
		System.out.println("Total number of beans --> " + applicationContext.getBeanDefinitionCount());
		
		return string;
	}
	
	@RequestMapping(value = "/todo-login", method = RequestMethod.GET)
	public String goToLogInPage(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
        
        if (session != null) {
            session.invalidate();
        }
		return "LogInToDo";
	}
	
	@RequestMapping(value = "/todo-login", method = RequestMethod.POST)
	public String welcomeUser(@RequestParam String name, @RequestParam(name = "password") String userPassword, ModelMap model, Locale locale) {
		ToDoUser user = logInService.validateUser(name, userPassword); 
		if (user.getUserId() == 0) {
			model.put("ErrorMessage", "Invalid Credentials!!");
			return "LogInToDo";
		} else {
			model.put("loggedinUserName", user.getUserName());
			model.put("loggedinUserId", user.getUserId());
			model.put("allToDos", logInService.fetchAllToDosForLoggedInUser(user.getUserId()));
			model.put("ErrorMessage", null);
		}
		System.out.println("Current Locale is " + locale);
		return "WelComeToDo";
	}
	
	@GetMapping("/logout-ToDo")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "LogInToDo";
    }
}
