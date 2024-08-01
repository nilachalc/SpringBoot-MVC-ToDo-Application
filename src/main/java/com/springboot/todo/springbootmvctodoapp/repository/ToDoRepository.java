package com.springboot.todo.springbootmvctodoapp.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.todo.springbootmvctodoapp.bean.ToDo;

@Repository
public class ToDoRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<ToDo> getAllToDosForLoggedInUser(int loggedInUserId) {
		return jdbcTemplate.queryForStream("SELECT ti.TODO_ID, ti.TODO_DESCRIPTION, ti.TARGET_DATE, ti.IS_COMPLETED FROM TODO_INFO ti WHERE ti.MAPPED_USER_ID =?"
								,  (rs, rowNum) -> {
									ToDo toDo = new ToDo();
									if (rs != null)
										toDo.setToDoId(rs.getInt("TODO_ID"));
										toDo.setDescription(rs.getString("TODO_DESCRIPTION"));
										toDo.setTargetDate(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(rs.getDate("TARGET_DATE").toLocalDate()));
										toDo.setIsCompleted((rs.getString("IS_COMPLETED").equals("Y") ? true : false));
									return toDo;
								}, loggedInUserId).sorted(Comparator.comparing(ToDo :: getIsCompleted).thenComparing(ToDo :: getDescription)).collect(Collectors.toList());
		
	}
	
	public void deleteToDosForLoggedInUser(int toDoId) {
		jdbcTemplate.update("DELETE FROM TODO_INFO ti WHERE ti.TODO_ID = ?", toDoId);
	}
	
	public void addToDosForLoggedInUser(ToDo newToDo) {
		jdbcTemplate.update("INSERT INTO TESTDB.TODO_INFO\r\n"
				+ "(TODO_ID, TODO_DESCRIPTION, TARGET_DATE, IS_COMPLETED, MAPPED_USER_ID)\r\n"
				+ "VALUES(todo_info_seq.NEXTVAL, ?, ?, ?, ?)", newToDo.getDescription(), Date.valueOf(LocalDate.parse(newToDo.getTargetDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))), (newToDo.getIsCompleted() ? "Y" : "N"), newToDo.getMappedUserId());
	}
	
	public ToDo getToDo(int toDoId) {
		return jdbcTemplate.queryForObject("SELECT ti.TODO_DESCRIPTION, ti.TARGET_DATE, ti.IS_COMPLETED, ti.MAPPED_USER_ID FROM TODO_INFO ti WHERE ti.TODO_ID =?"
								,  (rs, rowNum) -> {
									ToDo toDo = new ToDo();
									if (rs != null)
										toDo.setDescription(rs.getString("TODO_DESCRIPTION"));
										toDo.setTargetDate(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(rs.getDate("TARGET_DATE").toLocalDate()));
										toDo.setIsCompleted((rs.getString("IS_COMPLETED").equals("Y") ? true : false));
										toDo.setMappedUserId(rs.getInt("MAPPED_USER_ID"));
									return toDo;
								}, toDoId);
	}
	
	public void updateToDo(ToDo updatedToDo) {
		jdbcTemplate.update("UPDATE TESTDB.TODO_INFO\r\n"
				+ " SET TODO_DESCRIPTION = ?, TARGET_DATE = ?, IS_COMPLETED = ?, MAPPED_USER_ID = ? \r\n"
				+ "WHERE TODO_ID = ?", updatedToDo.getDescription(), Date.valueOf(LocalDate.parse(updatedToDo.getTargetDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))), (updatedToDo.getIsCompleted() ? "Y" : "N"), updatedToDo.getMappedUserId(), updatedToDo.getToDoId());
	}
}
